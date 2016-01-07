package nuaa.ggx.pos.collector.crawler.yecrawler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.Set;

import javax.annotation.Resource;

import nuaa.ggx.pos.collector.crawler.yecrawler.interfaces.IWeiboCrawler;
import nuaa.ggx.pos.collector.model.TConsensus;
import nuaa.ggx.pos.collector.model.TConsensusDetail;
import nuaa.ggx.pos.collector.model.TKeyword;
import nuaa.ggx.pos.collector.service.action.WeiboJob;
import nuaa.ggx.pos.collector.service.interfaces.IConsensusDetailService;
import nuaa.ggx.pos.collector.service.interfaces.IConsensusService;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springcontext-config.xml")
public class WeiboCrawlerTest {
	
	@Resource
	private IWeiboCrawler weiboCrawler;
	
	@Resource
	private IConsensusDetailService consensusDetailService;
	
	@Resource
	private IConsensusService consensusService;
	
	@Resource
	private WeiboJob weiboJob;
	
	@Test
	public void listTest() {
		Set<TKeyword> keywords = weiboJob.listKeywords();
		System.out.println(keywords);
	}
	
	@Test
	public void weiboCrawlerTest() {
		Assert.assertNotNull(weiboCrawler);
		try {
			weiboCrawler.crawl("红包");
		} catch (FailingHttpStatusCodeException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void weiboJobTest() {
		Assert.assertNotNull(weiboJob);
		weiboJob.execute();
		while(true){
			
		}
	}
	
	@Test
	public void oneToOneTest() {
		TConsensus consensus = new TConsensus();
		TConsensusDetail consensusDetail = new TConsensusDetail();
		consensus.setTitle("testTitle");
		consensusDetail.setBlankTitle("testBlankTitle");
//		consensus.setTConsensusDetail(consensusDetail);
//		TConsensus consensus2 = consensusService.getById(1);
		consensusDetail.setTConsensus(consensus);
//		consensusService.save(consensus);
		consensusDetailService.save(consensusDetail);
	}
	
	public static void main(String[] args) {
		System.out.println("☺");
	}
}
