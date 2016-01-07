package nuaa.ggx.pos.collector.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Constants {

	public static ExecutorService WEIBO_CRAWLER_POOL = Executors
			.newFixedThreadPool(10);

}
