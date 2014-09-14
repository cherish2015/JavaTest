package test.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.Ignore;
import org.junit.Test;


/**
 * jar包内有些配置文件想放在jar包外面，比如文件config.properties：<br>
 * 如果这个文件是以路径方式载入的，比如new file("./config/config.properties")，<br>
 * 那么将config.properties放在jar包相同目录下的config目录下即可，<br>
 * 也就是说“./”路径等价于jar包所在目录；<br>
 * 如果这个文件是以ClassPath下的文件这种方式载入的，比如在Spring中载入classpath:config.properties，<br>
 * 则在MF文件的配置文件的ClassPath中添加“./”，然后将这个配置文件与jar包放在同一个目录即可，<br>
 * 当然也可以在MF文件的配置文件的ClassPath中添加“./config/”，<br>
 * 然后把配置文件都放在jar包相同目录下的config目录下。
 * @author Administrators
 *
 */
public class TestProperties {

	public static void main(String[] args) {
		test();
	}
	
	private static void test(){
		Properties props = new Properties();
		InputStream in = Thread.currentThread().
				getContextClassLoader().getResourceAsStream("./config/test.properties");
		try {
			props.load(in);
			String test = props.getProperty("test");
			System.out.println(test);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Ignore
	@Test
	public void testPropertiesStore(){
		OutputStream out;
		InputStream inStream;
		try {
			out = new FileOutputStream("test.properties");
			inStream = new FileInputStream("test.properties");
			Properties props = new Properties();
			props.put("test", "ok");
			props.store(out, "taobao_cookies");
			
			props.clear();
			props.load(inStream);
			String test = (String) props.get("test");
			System.out.println(test);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	public void testGenerateCookies(){
		String cookieStr = "t=71293c06aa367ccdf57429f2fd4d6832; "
				+ "cna=eOqRDDvD9SECAXOt+md0aOfG; "
				+ "isg=1E6B473B3056D55F0E87A19A73C2B30A; "
				+ "cookie2=f25c85e4e74cdac17dc6acf9b1b28feb; "
				+ "_tb_token_=XZpAe7ylCen; "
				+ "v=0; "
				+ "cookie32=228f8933ed06ee95a26af8e239139cf3; "
				+ "cookie31=OTc4NjYyNzAsaG9vb29vZ2xlLDc4MTc1MTA4NkBxcS5jb20sVEI%3D; "
				+ "alimamapwag=TW96aWxsYS80LjAgKGNvbXBhdGlibGU7IE1TSUUgNy4wOy"
				+ "BXaW5kb3dzIE5UIDYuMTsgV09XNjQ7IFRyaWRlbnQvNS4wOyBTTENDMjsgLk5FVC"
				+ "BDTFIgMi4wLjUwNzI3OyAuTkVUIENMUiAzLjUuMzA3Mjk7IC5ORVQgQ0xSIDM"
				+ "uMC4zMDcyOTsgLk5FVDQuMEM7IC5ORVQ0LjBFKQ%3D%3D; "
				+ "login=W5iHLLyFOGW7aA%3D%3D; "
				+ "alimamapw=DV9WWgsJXgpTPlINAARUAgVRVVIMAl1UWgAGVFEPVwMAUgVXAwQIUQEE";
		
		String[] kvPairs = cookieStr.split(";");
		Map<String, String> mapCookies = new HashMap<String, String>();
		for (int i = 0; i < kvPairs.length; i++) {
			kvPairs[i] = kvPairs[i].trim();
			String[] kvItem = kvPairs[i].split("=",2);
			mapCookies.put(kvItem[0], kvItem[1]);
		}
		System.out.println(mapCookies);
		
		OutputStream out;
		try {
			out = new FileOutputStream("test.properties");
			Properties props = new Properties();
			props.putAll(mapCookies);
			props.store(out, "comments");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
