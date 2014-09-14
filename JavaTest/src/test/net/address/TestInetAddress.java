package test.net.address;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;


/**
 * 测试获取本地IP地址<br>
 * 获取本地网络地址<br>
 * 获取网络接口列表<br>
 * 回环测试地址<br>
 * @author Administrator
 *
 */
public class TestInetAddress {

	public static void main(String[] args) {
		test();
	}
	
	private static void test(){
		try {
			InetAddress address = InetAddress.getLocalHost();
			String addrStr = address.toString();
			System.out.println("1:");
			System.out.println(addrStr);
			System.out.println(address);
			
			InetAddress[] address2 = InetAddress.getAllByName("localhost");
			System.out.println("2:");
			for (int i = 0; i < address2.length; i++) {
				System.out.println(address2[i]);
			}
			
			InetAddress address3 = InetAddress.getByName("localhost");
			String addr3Str = address3.toString();
			System.out.println("3:"+addr3Str);
			System.out.println("4:"+InetAddress.getLocalHost().getHostAddress());
			
			InetAddress[] address4 = InetAddress.getAllByName(InetAddress.getLocalHost().getHostName());
			System.out.println("5:");
			System.out.println(InetAddress.getLocalHost().getHostName());
			for (int i = 0; i < address4.length; i++) {
				System.out.println(address4[i]);
			}
			System.out.println("6:");
			List<InetAddress> adds = new ArrayList<InetAddress>();
			Enumeration<NetworkInterface> enu = NetworkInterface.getNetworkInterfaces();
			while(enu.hasMoreElements()){
				NetworkInterface netif = enu.nextElement();
				Enumeration<InetAddress> enuAddr = netif.getInetAddresses();
				while(enuAddr.hasMoreElements()){
					InetAddress add = enuAddr.nextElement();
					System.out.println(add.getHostAddress());
					if (add.isAnyLocalAddress()
							|| add.isLoopbackAddress()) {
					}else{
						if (add instanceof Inet4Address) {
							adds.add(add);
						}
					}
				}
			}
			System.out.println("7:");
			System.out.println(adds);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

}
