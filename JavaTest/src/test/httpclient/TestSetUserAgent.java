package test.httpclient;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.junit.Test;

import test.ITest;

public class TestSetUserAgent implements ITest{
	
	private static Logger log = Logger.getLogger(TestSetUserAgent.class);

	@Override
	@Test
	public void test() {
		CloseableHttpClient hc = HttpClients.createDefault();
		HttpGet hg = new HttpGet("http://www.baidu.com/");
		hg.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/34.0.1847.137 Safari/537.36 LBBROWSER");
		
		try {
			CloseableHttpResponse resp = hc.execute(hg);
			try {
				HttpEntity entity = resp.getEntity();
				if (entity != null) {
					String content = EntityUtils.toString(entity);
					log.info(content);
					EntityUtils.consume(entity);
				}
			} finally{
				resp.close();
			}
		} catch (ClientProtocolException e) {
			log.error(e.getMessage(), e);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		} finally{
			try {
				hc.close();
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
		}
	}

}
