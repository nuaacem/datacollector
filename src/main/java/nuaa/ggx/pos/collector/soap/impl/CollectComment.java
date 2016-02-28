package nuaa.ggx.pos.collector.soap.impl;

import javax.annotation.Resource;

import nuaa.ggx.pos.collector.crawler.yecrawler.interfaces.IWeiboCommentCrawler;
import nuaa.ggx.pos.collector.soap.ICollectComment;
import nuaa.ggx.pos.collector.util.Constants;

public class CollectComment implements ICollectComment {

	@Resource
	IWeiboCommentCrawler weiboCommentCrawler;

	@Override
	public Integer collectComment(String url) {
		Integer num = weiboCommentCrawler.crawl(url);
		
		if (null == num) {
			return Constants.COMMENT_CRAWL_FAIL;
		} else if (0 == num) {
			return Constants.COMMENT_CRAWL_ZERO;
		}
		
		//test code
		num = 3;
		return num;
	}

}
