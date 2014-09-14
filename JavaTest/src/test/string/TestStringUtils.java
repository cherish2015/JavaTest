package test.string;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.junit.Test;

import test.ITest;

public class TestStringUtils implements ITest {

	@Override
	@Test
	public void test() {
		
		String test = "http://item.taobao.com/item.htm?spm=0.0.0.0.pie85w&id=41069852894";
		byte[] temp = StringUtils.getBytesIso8859_1(test);
		String test2 = StringUtils.newStringIso8859_1(temp);
		System.out.println(test2);
		
		try {
			String test3 = URLEncoder.encode(test, "utf-8");
			System.out.println(test3);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
