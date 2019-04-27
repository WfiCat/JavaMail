package cn.hbeu.mail;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/**
 * 复杂的邮件测试
 * 
 * @author Zero
 *
 */
public class ComplexMail {
	public static void main(String[] args) throws UnsupportedEncodingException, MessagingException {
		sendMial();
	}

	// 发件人
	private String senderAccount;
	// 账户密码
	private String senderPassword;

	public static void sendMial() throws MessagingException, UnsupportedEncodingException {
		// 2、创建与邮件服务器交互的 Session会话 对象
		Session session = Session.getInstance(getProperties());
		// 设置调试信息在控制台打印出来
		session.setDebug(true);

		// 3、创建邮件的实例对象
		Message msg = getMimeMessage(session, "3409473342@qq.com", "1343614311@qq.com");

		// 4、根据session对象获取邮件传输对象Transport
		Transport transport = session.getTransport();

		// 设置发件人的账户名和密码
		transport.connect("3409473342@qq.com", "mmffrinwfqnrcice");
		// 发送邮件，并发送到所有收件人地址，message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
		transport.sendMessage(msg, msg.getAllRecipients());

		// 如果只想发送给指定的人，可以如下写法
		// transport.sendMessage(msg, new Address[]{new InternetAddress("xxx@qq.com")});

		// 5、关闭邮件连接
		transport.close();
	}

	/**
	 * 创建邮件
	 * 
	 * @param session    和服务器交互的会话
	 * @param sendMail   发件人邮箱
	 * @param reciveMail 收件人邮箱
	 * @return
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */

	private static Message getMimeMessage(Session session, String sendMail, String reciveMail)
			throws MessagingException, UnsupportedEncodingException {
		// 标题、正文、发送人、收件人 、发送时间{附件、图片}
		Message msg = new MimeMessage(session);
		// 标题
		msg.setSubject("复杂邮件(图片、附件)");
		// 发送人
		Address ads = new InternetAddress(sendMail, "发件人张", "utf-8");
		msg.setFrom(ads);
		// 收件人
		msg.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(reciveMail, "收件人A", "utf-8"));
		// 发送时间
		msg.setSentDate(new Date());
		// 发送正文（可以做成参数）
		/* msg.setContent("正文...", "text/html;charset=utf-8"); */
		// **********************************************************

//		// 5. 创建图片“节点”
//		MimeBodyPart imagePart = new MimeBodyPart();
//		DataHandler imageDataHandler = new DataHandler(new FileDataSource("src//abc.png")); // 读取本地文件
//		imagePart.setDataHandler(imageDataHandler); // 将图片数据添加到“节点”
//		imagePart.setContentID("image_fairy_tail"); // 为“节点”设置一个唯一编号（在文本“节点”将引用该ID）
//		// 6. 创建文本“节点”
//		MimeBodyPart textPart = new MimeBodyPart();
//		// 这里添加图片的方式是将整个图片包含到邮件内容中, 实际上也可以以 http 链接的形式添加网络图片
//		textPart.setContent("这是一张图片<br/><img src='cid:image_fairy_tail'/>", "text/html;charset=UTF-8");
//
//		// 7. （文本+图片）设置 文本 和 图片 “节点”的关系（将 文本 和 图片 “节点”合成一个混合“节点”）
//		MimeMultipart mm_text_image = new MimeMultipart();
//		mm_text_image.addBodyPart(textPart);
//		mm_text_image.addBodyPart(imagePart);
//		mm_text_image.setSubType("related"); // 关联关系
//
//		// 8. 将 文本+图片 的混合“节点”封装成一个普通“节点”
//		MimeBodyPart text_image = new MimeBodyPart();
//		text_image.setContent(mm_text_image);
//		// 9. 创建附件“节点”
//		MimeBodyPart attachment = new MimeBodyPart();
//		DataHandler attDataHandler = new DataHandler(new FileDataSource("src//上课前检查插头.txt")); // 读取本地文件
//		attachment.setDataHandler(attDataHandler); // 将附件数据添加到“节点”
//		attachment.setFileName(MimeUtility.encodeText(attDataHandler.getName())); // 设置附件的文件名（需要编码）
//		// 10. 设置（文本+图片）和 附件 的关系（合成一个大的混合“节点” / Multipart ）
//		MimeMultipart mm = new MimeMultipart();
//		mm.addBodyPart(text_image);
//		mm.addBodyPart(attachment); // 如果有多个附件，可以创建多个多次添加
//		mm.setSubType("mixed"); // 混合关系
//
//		// 11. 设置整个邮件的关系（将最终的混合“节点”作为邮件的内容添加到邮件对象）
//		msg.setContent(mm);

		// 创建图片节点
		MimeBodyPart imgPart = new MimeBodyPart();
		DataHandler imgDataHandler = new DataHandler(new FileDataSource("src/main/java/imgs/timg.jpg"));// 读取本地文件
		imgPart.setDataHandler(imgDataHandler); // 将图片数据添加到“节点
		imgPart.setContentID("cat_img"); // 为“节点”设置一个唯一编号（在文本“节点”将引用该ID）
		// 创建文本节点 目的：就是为了加载图片节点
		MimeBodyPart textPart = new MimeBodyPart();
		textPart.setContent("这是一张图片<br/><img src='cid:cat_img'/>", "text/html;charset=UTF-8");

		// MIME(Multipurpose Internet Mail Extensions)
		// 组合两个节点（文本+图片）设置 文本 和 图片 “节点”的关系（将 文本 和 图片 “节点”合成一个混合“节点”）
		MimeMultipart mm_text_img = new MimeMultipart();
		mm_text_img.addBodyPart(imgPart);
		mm_text_img.addBodyPart(textPart);
		mm_text_img.setSubType("related");

		// 正文中只能出现普通节点（MimeBodyPart），而不能出现复合节点（MimeMutipart）
		// 文本+图片 的混合“节点”封装成一个普通“节点”
		MimeBodyPart text_image = new MimeBodyPart();
		text_image.setContent(mm_text_img);

		// 创建附件“节点”
		MimeBodyPart attachment = new MimeBodyPart();
		DataHandler addDataHandler = new DataHandler(new FileDataSource("src/main/java/上课前检查插头.txt"));
		attachment.setDataHandler(addDataHandler);
		// 若无下面这一句，则接收不到附件
		attachment.setFileName(MimeUtility.encodeText(addDataHandler.getName())); // 设置附件的文件名（需要编码）
		
		// 设置（文本+图片）和 附件 的关系（合成一个大的混合“节点” / Multipart ）
		MimeMultipart mm = new MimeMultipart();
		mm.addBodyPart(text_image);
		mm.addBodyPart(attachment); // 如果有多个附件，可以创建多个多次添加
		mm.setSubType("mixed"); // 混合关系

		// ----------------------以上是文件构造
		// 设置整个邮件的关系（将最终的混合“节点”作为邮件的内容添加到邮件对象）
		msg.setContent(mm);

		// **************************************************************
		// 保存邮件
		msg.saveChanges();
		return msg;
	}

	/**
	 * 连接邮件服务器参数配置
	 * 
	 * @return
	 */
	public static Properties getProperties() {
		// 1、连接邮件服务器的参数配置
		Properties props = new Properties();

		// 设置传输协议
		props.setProperty("mail.transport.protocol", "smtp");

		// 设置发件人的SMTP服务器地址
		props.setProperty("mail.smtp.host", "smtp.qq.com");

		// 设置SMTP服务器端口号
		props.setProperty("mail.smtp.port", "465");

		// 设置用户的认证方式
		props.setProperty("mail.smtp.auth", "true");

		// QQ：SSL安全认证
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		return props;
	}
}
