package nuaa.ggx.pos.collector.service.action;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import nuaa.ggx.pos.collector.crawler.yecrawler.interfaces.IWeiboCrawler;
import nuaa.ggx.pos.collector.crawler.yecrawler.model.User;
import nuaa.ggx.pos.collector.model.Student;
import nuaa.ggx.pos.collector.model.TKeyword;
import nuaa.ggx.pos.collector.model.TSubject;
import nuaa.ggx.pos.collector.service.interfaces.ISubjectService;
import nuaa.ggx.pos.collector.util.Constants;

import org.apache.log4j.Logger;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

public class WeiboJob {
	
	private static Logger log = Logger.getLogger(WeiboJob.class);
	
	@Resource
	private ISubjectService subjectService;
	
	@Resource
	private IWeiboCrawler weiboCrawler;
	
	public void execute(){
		Set<TKeyword> keywords = listKeywords();
		if (keywords != null) {
			for (TKeyword tKeyword : keywords) {
				String keyword = tKeyword.getKeyword();
				Constants.WEIBO_CRAWLER_POOL.execute(new CrawlWork(keyword));
			}
		}
	}
	
	public Set<TKeyword> listKeywords() {
		List<TSubject> subjects = subjectService.listAll();
		Set<TKeyword> keywords = new HashSet<TKeyword>();
		for (TSubject tSubject : subjects) {
			keywords.addAll(tSubject.getTKeywords());
		}
		return keywords;
	}
	
	class CrawlWork implements Runnable{
		
		private String keyword;
		
		public CrawlWork(String keyword) {
			this.keyword = keyword;
		}
		
		@Override
		public void run() {
			try {
				weiboCrawler.crawl(keyword);
			} catch (FailingHttpStatusCodeException e) {
				e.printStackTrace();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}		
	}
}
