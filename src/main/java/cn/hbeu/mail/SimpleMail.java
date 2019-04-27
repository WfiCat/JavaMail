package cn.hbeu.mail;



import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SimpleMail {
	public static void main(String[] args) throws UnsupportedEncodingException, MessagingException {
		sendMial();
	}
	//发件人
	private String senderAccount;
	//账户密码
	private String senderPassword;
	
	
	public static void sendMial() throws MessagingException, UnsupportedEncodingException {
        //2、创建与邮件服务器交互的 Session会话 对象
        Session session = Session.getInstance(getProperties());
        //设置调试信息在控制台打印出来
        session.setDebug(true);
        
        //3、创建邮件的实例对象
        Message msg = getMimeMessage(session, "3409473342@qq.com", "1343614311@qq.com");
        
        //4、根据session对象获取邮件传输对象Transport
        Transport transport = session.getTransport();
        
        //设置发件人的账户名和密码
        transport.connect("3409473342@qq.com", "mmffrinwfqnrcice");
        //发送邮件，并发送到所有收件人地址，message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
        transport.sendMessage(msg,msg.getAllRecipients());
         
        //如果只想发送给指定的人，可以如下写法
        //transport.sendMessage(msg, new Address[]{new InternetAddress("xxx@qq.com")});
         
        //5、关闭邮件连接
        transport.close();
	}
	
	
	
	
	
	/**
	 * 创建邮件
	 * @param session 和服务器交互的会话
	 * @param sendMail 发件人邮箱
	 * @param reciveMail 收件人邮箱
	 * @return
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	
	private static Message getMimeMessage(Session session, 
			String sendMail, String reciveMail) throws MessagingException, UnsupportedEncodingException {
		// 标题、正文、发送人、收件人 、发送时间{附件、图片}
		Message msg = new MimeMessage(session);
		// 标题
		msg.setSubject("这是标题啊！");
		// 发送人
		Address ads = new InternetAddress(sendMail,"发件人张","utf-8");
		msg.setFrom(ads);
		// 收件人
		msg.addRecipient(MimeMessage.RecipientType.TO,new InternetAddress(reciveMail, "收件人A", "utf-8"));
		// 发送时间
		msg.setSentDate(new Date());
		// 发送正文（可以做成参数）
		msg.setContent("正文...", "text/html;charset=utf-8");		
		//保存邮件
		msg.saveChanges();
		return msg;
	}







	/**
	 * 连接邮件服务器参数配置
	 * @return
	 */
	public static Properties getProperties() {
		//1、连接邮件服务器的参数配置
        Properties props = new Properties();
        
        //设置传输协议
        props.setProperty("mail.transport.protocol", "smtp");
        
        //设置发件人的SMTP服务器地址
        props.setProperty("mail.smtp.host", "smtp.qq.com");
        
        //设置SMTP服务器端口号
        props.setProperty("mail.smtp.port", "465");
        
        //设置用户的认证方式
        props.setProperty("mail.smtp.auth", "true");
        
        //QQ：SSL安全认证
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", "465"); 
        return props;
    }
}
