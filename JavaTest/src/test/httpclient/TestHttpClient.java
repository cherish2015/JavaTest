package test.httpclient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Ignore;
import org.junit.Test;

@SuppressWarnings("unused")
public class TestHttpClient {

	public static void main(String[] args) {

	}
	
	@Test
	public void test(){
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpGet = new HttpGet("http://www.alimama.com/");
			CloseableHttpResponse response = httpclient.execute(httpGet);
			try {
				System.out.println(response.getStatusLine());
				HttpEntity entity = response.getEntity();
				EntityUtils.consume(entity);
			} finally {
				response.close();
			}
			
			/*System.out.println("---------------------------------------------");
			HttpPost httpPost = new HttpPost("https://login.taobao.com/member/login.jhtml");
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("TPL_username","google"));
			nvps.add(new BasicNameValuePair("TPL_password", "781751086"));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			httpPost.addHeader("Referer", "https://login.taobao.com/member/login.jhtml?style=minisimple&from=alimama&redirectURL=http%3A%2F%2Flogin.taobao.com%2Fmember%2Ftaobaoke%2Flogin.htm%3Fis_login%3d1&full_redirect=true&disableQuickLogin=true");
			CloseableHttpResponse response2 = httpclient.execute(httpPost);
			
			try {
				System.out.println(response2.getStatusLine());
				HttpEntity entity2 = response2.getEntity();
				EntityUtils.consume(entity2);
			} finally {
				response2.close();
			}*/
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	@Ignore
	@Test
	public void testSimple(){
		String result = "";
		try {
			result = Request.Get("http://www.baidu.com").execute().returnContent().toString();
			System.out.println(result);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
