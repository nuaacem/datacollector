package nuaa.ggx.pos.collector.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Constants {

	public static final Integer COMMENT_CRAWL_FAIL = -1;
	public static final Integer COMMENT_CRAWL_ZERO = 0;

	public static ExecutorService WEIBO_CRAWLER_POOL = Executors.newFixedThreadPool(10);

}
