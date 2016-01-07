package nuaa.ggx.pos.collector.crawler.yecrawler.interfaces;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

public interface IWeiboCrawler {
	public void crawl(String keyword) throws FailingHttpStatusCodeException, MalformedURLException,
	IOException, ParseException;
}
