package nuaa.ggx.pos.collector.crawler.yecrawler.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import nuaa.ggx.pos.collector.crawler.yecrawler.interfaces.IWeiboCrawler;
import nuaa.ggx.pos.collector.crawler.yecrawler.model.User;
import nuaa.ggx.pos.collector.crawler.yecrawler.model.Weibo;
import nuaa.ggx.pos.collector.dao.impl.BaseDao;
import nuaa.ggx.pos.collector.dao.interfaces.IWeiboUserDao;
import nuaa.ggx.pos.collector.model.TConsensus;
import nuaa.ggx.pos.collector.model.TConsensusDetail;
import nuaa.ggx.pos.collector.model.TWeiboUser;
import nuaa.ggx.pos.collector.service.interfaces.IConsensusDetailService;
import nuaa.ggx.pos.collector.service.interfaces.IConsensusService;
import nuaa.ggx.pos.collector.service.interfaces.IWeiboUserService;
import nuaa.ggx.pos.collector.util.WeiboTools;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlCheckBoxInput;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class WeiboCrawler implements IWeiboCrawler {

	@Autowired
	private WebClient client;

	@Autowired
	private IConsensusService consensusService;

	@Autowired
	private IConsensusDetailService consensusDetailService;
	
	@Autowired
	private IWeiboUserService weiboUserService;

	private Integer pageStart;
	public void setPageStart(Integer pageStart){
		this.pageStart = pageStart;
	}
	
	private Integer pageEnd;	
	public void setPageEnd(Integer pageEnd){
		this.pageEnd = pageEnd;
	}
	
	private String username;
	public void setUsername(String username){
		this.username = username;
	}	
	
	private String password;
	public void setPassword(String password){
		this.password = password;
	}	
	
	private boolean login()
			throws FailingHttpStatusCodeException, MalformedURLException,
			IOException {

		boolean state = false;
		HtmlPage page = client.getPage("http://login.weibo.cn/login/");
		// System.out.println(page.asText());

		HtmlTextInput ln = (HtmlTextInput) page.getElementByName("mobile");
		page.setFocusedElement((HtmlElement) page.getElementByName("mobile"));
		page.tabToNextElement();
		HtmlPasswordInput pwd = (HtmlPasswordInput) page.tabToNextElement();

		if (null == username || "".equals(username)) {
			ln.setText("nuaais@sina.cn");
		} 
		else {
			ln.setText(username);
		}
		
		if (null == password || "".equals(password)) {
			pwd.setText("admin123456");
		}
		else {
			pwd.setText(password);
		}

		HtmlSubmitInput btn = (HtmlSubmitInput) page.getElementByName("submit");
		HtmlPage page2 = btn.click();
		if (!page2.getUrl().toString().contains("login.weibo.cn")) {
			state = true;
		}
		return state;
	}

	private TConsensusDetail collectInfo(Element aRow)
			throws FailingHttpStatusCodeException, MalformedURLException,
			IOException, ParseException {

		if (!aRow.hasAttr("id")) {
			return null;
		}

		TWeiboUser weiboUser = new TWeiboUser();
		TConsensus consensus = new TConsensus();
		TConsensusDetail consensusDetail = new TConsensusDetail();
		
		//解析微博divId
		String divId = aRow.attr("id");
		if (null != divId) {
			consensusDetail.setDivId(divId);
		} else {
			return null;
		}
		
		//解析发帖用户信息
		Elements nks = aRow.getElementsByClass("nk");
		if (nks != null && !nks.isEmpty()) {
			Element aUser = nks.get(0);
			if (analysisUser(aUser, weiboUser)) {
				consensusDetail.setUserId(weiboUser.getUid());
				weiboUserService.merge(weiboUser);
			} else {
				return null;
			}
		} else {
			return null;
		}
		
		//微博评论页面信息
		String url = "weibo.cn/comment/" + divId;
		consensus.setUrl(url);

		//解析微博内容
		Elements ctts = aRow.getElementsByClass("ctt");
		if (ctts != null) {
			Element ctt = ctts.get(0);
			String content = ctt.text().replaceAll("'", "\'");
			if (content.startsWith("#")){
				int end = StringUtils.indexOf(content, "#", 1);
				if (end > 1) {
					consensusDetail.setSharpTopic(StringUtils.substring(content, 1, end));
				}
			}
			int startBlank = StringUtils.indexOf(content, "【");
			int endBlank = StringUtils.indexOf(content, "】");
			if (endBlank > startBlank && endBlank > 0) {
				String title = StringUtils.substring(content, startBlank + 1, endBlank);
				consensusDetail.setBlankTitle(title);
				consensus.setTitle(title);
			}
			else {
				consensus.setTitle(StringUtils.substring(content, 0, 30));
			}
			consensus.setSummary(content);
		}
		
		//解析发布时间
		String time = aRow.getElementsByClass("ct").first().text();
		if (time != null && time != "") {
			consensus.setPublishTime(new Timestamp(WeiboTools.getFormulaDateTime(time)));
		}

		//解析转发评论
		Elements cmts = aRow.getElementsByClass("cmt");
		if (cmts != null && cmts.size() != 0) {
			Elements aTags = cmts.get(0).getElementsByTag("a");
			if (aTags != null && aTags.size() != 0) {
				Element aUser = aTags.get(0);
				TWeiboUser cmtUser = new TWeiboUser();
				if (aUser != null) {
					if (analysisUser(aUser, cmtUser)) {
						weiboUserService.merge(cmtUser);
					}
					consensusDetail.setCmtUid(cmtUser.getUid());
					Elements ccs = aRow.getElementsByClass("cc");
					if (ccs != null && ccs.size() != 0) {
						Element cc = ccs.get(0);
						String oriCmtUrl = cc.attr("href");
						consensusDetail.setOriUrl(oriCmtUrl);
					}
				}
			}
			String divText = StringUtils.substring(cmts.last().parent().text(), 5);
			String cmtReason = StringUtils.substring(divText,
								0, StringUtils.lastIndexOf(divText, "赞"));
			if (cmtReason != null && cmtReason != "") {
				consensus.setTitle(cmtReason);
			}
		}
		consensus.setCollectTime(new Timestamp(new Date().getTime()));
		consensus.setCollectWebsiteId(1);
		consensusDetail.setTConsensus(consensus);
		return consensusDetail;
	}

	/*public void collectInfo(HtmlPage page, String userKey)
			throws FailingHttpStatusCodeException, MalformedURLException,
			IOException {

		// 判断是否登录
		if (page.getUrl().toString().contains("login.weibo.cn")) {
			login("nuaais@sina.cn", "admin123456");
		}

		Document pagedoc = Jsoup.parse(page.asXml());

		Elements rows = pagedoc.getElementsByClass("c");

		if (rows != null && rows.size() > 0) {
			for (int i = 0; i < rows.size(); i++) {
				Element aRow = rows.get(i);
				if (!aRow.hasAttr("id")) {
					continue;
				}

				Weibo weibo = new Weibo();
				User user = new User();

				weibo.setDivId(aRow.attr("id"));

				weibo.setUserId(userKey);

				Elements ctts = aRow.getElementsByClass("ctt");
				if (ctts != null) {
					Element ctt = ctts.get(0);
					weibo.setContent(ctt.text().replaceAll("'", "\'"));
				}
				String time = aRow.getElementsByClass("ct").first().text();
				if (time != null && time != "") {
					String pubTime = AnalyseWeibo.getFormulaDate(time);
					weibo.setPubTime(pubTime);
				}

				Elements cmts = aRow.getElementsByClass("cmt");
				if (cmts != null && cmts.size() != 0) {
					Elements aTags = cmts.get(0).getElementsByTag("a");
					Element aUser;
					if (aTags != null && aTags.size() != 0) {
						aUser = aTags.get(0);
						User cmtUser = new User();
						if (aUser != null) {
							AnalyseWeibo.analysisUser(aUser, cmtUser);
							UserDao.addUser(cmtUser);
							weibo.setCmtUid(cmtUser.getUid());
							Elements ccs = aRow.getElementsByClass("cc");
							if (ccs != null && ccs.size() != 0) {
								Element cc = ccs.get(0);
								String oriCmtUrl = cc.attr("href");
								weibo.setOriCmtUrl(oriCmtUrl);
							}
						}
					}
					// String cmtReason = cmts.last().text();
					// if (cmtReason != null && cmtReason != "") {
					// weibo.setCmtReason(cmtReason);
					// }
				}
				WeiboDao.addWeibo(weibo);
			}
		}
	}*/
	
	@Override
	public void crawl(String keyword)
			throws FailingHttpStatusCodeException, MalformedURLException,
			IOException, ParseException {
		login();
		String enKey = URLEncoder.encode(keyword, "UTF-8");
		String searchString = "http://weibo.cn/search/mblog?keyword="
				+ enKey + "&page=";
		for (int i = pageStart; i <= pageEnd; i++) {
			HtmlPage page = client.getPage(searchString + i);
			System.out.println("page:" + i);
			if (page.getUrl().toString().contains("login.weibo.cn")) {
				login();
			}

			Document pagedoc = Jsoup.parse(page.asXml());
			Elements postBodys = pagedoc.getElementsByClass("post_body");
			Element contentElement = postBodys.get(0);
			String content = contentElement.text();
			
			Elements rows = pagedoc.getElementsByClass("c");

			if (rows != null && rows.size() > 0) {
				for (Element aRow : rows) {
					TConsensusDetail consensus = collectInfo(aRow);
					if (null != consensus) {
						try {
							consensusDetailService.save(consensus);
						} catch (RuntimeException e) {
							Throwable cause = e.getCause();
						    if(cause instanceof org.hibernate.exception.ConstraintViolationException) {
						        String errMsg = ((org.hibernate.exception.ConstraintViolationException)cause).getSQLException().getMessage();
						        if(StringUtils.isNotBlank(errMsg) && errMsg.indexOf("IDX_T_A")!=-1) {
						            throw new RuntimeException("XXXXXXXXXX重复！");
						        }
						    }
						}
						
					}
				}
			}

		}
	}

	/*public void crawlUser(String keyword, int pageStart, int pageEnd)
			throws FailingHttpStatusCodeException, MalformedURLException,
			IOException {
		// login();
		String enKey = URLEncoder.encode(keyword, "UTF-8");
		String searchString = "http://weibo.cn/" + enKey + "?page=";
		for (int i = pageStart; i <= pageEnd; i++) {
			HtmlPage page = client.getPage(searchString + i);
			System.out.println("page:" + i);
			collectInfo(page, keyword);
		}
	}*/

	private boolean analysisUser(Element aUser,TWeiboUser weiboUser) {
		String userName = aUser.text();
		if (userName != null && userName != "") {
			weiboUser.setNickname(userName);			
		}
		else {
			return false;
		}
		
		String userUrl = aUser.attr("href");
		if (userName != null && userName != "") {
				weiboUser.setUid(userUrl.substring(16));
		}
		else {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		Logger log = Logger.getLogger(BaseDao.class);
		System.out.println(StringEscapeUtils.escapeHtml4(""));
	}
	// public static void main(String[] args) {
	// try {
	// crawlSearch("王思聪", 15, 100);
	// } catch (FailingHttpStatusCodeException e) {
	// e.printStackTrace();
	// } catch (MalformedURLException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
}
