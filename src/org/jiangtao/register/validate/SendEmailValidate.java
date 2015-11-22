package org.jiangtao.register.validate;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmailValidate {
	public static void sendEmail(String userEmail, String content)
			throws Exception {
		Properties properties = new Properties();
		properties.setProperty("mail.dug", "true");
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.host", "smtp.163.com");
		properties.setProperty("mail.transport.protocol", "smtp");
		Session session = Session.getInstance(properties);
		Message msg = new MimeMessage(session);
		msg.setSubject("注册验证码");
		// 设置邮件内容
		// 改变
		msg.setText("尊敬的用户你好"+"，很高兴你能够来到我们这里，你的验证码是       " + content + "，希望你能够从这里成长。");
		// 设置发件人
		msg.setFrom(new InternetAddress("jiangtao103cp@163.com"));
		Transport transport = session.getTransport();
		// 连接邮件服务器
		transport.connect("jiangtao103cp", "j3281033013991j");
		// 发送邮件 ，改变
		transport.sendMessage(msg, new Address[] { new InternetAddress(
				userEmail) });
		// 关闭连接
		transport.close();
	}
}
