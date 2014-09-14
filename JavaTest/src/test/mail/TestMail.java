package test.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class TestMail {

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			test();
		}
	}
	
	
	/**
	 * 进入QQ邮箱——>设置——>账户——>POP3/IMAP/SMTP选择——>开启POP3/SMTP服务
	 */
	private static void test(){
		String host = "smtp.qq.com";
		String from = "191914131@qq.com";
		String to = "781751086@qq.com";
		
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		
		Session session = Session.getDefaultInstance(props, null);
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("test mail");
			message.setText("http://www.baidu.com", "utf8");
			message.saveChanges();
			
			Transport transport = session.getTransport("smtp");
			System.out.println("正在连接...");
			transport.connect(host, "191914131", "781751086");
			System.out.println("正在发送...");
			transport.sendMessage(message, message.getAllRecipients());
			System.out.println("邮件发送成功");
			
			
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
