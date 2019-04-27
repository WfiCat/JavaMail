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
 * ���ӵ��ʼ�����
 * 
 * @author Zero
 *
 */
public class ComplexMail {
	public static void main(String[] args) throws UnsupportedEncodingException, MessagingException {
		sendMial();
	}

	// ������
	private String senderAccount;
	// �˻�����
	private String senderPassword;

	public static void sendMial() throws MessagingException, UnsupportedEncodingException {
		// 2���������ʼ������������� Session�Ự ����
		Session session = Session.getInstance(getProperties());
		// ���õ�����Ϣ�ڿ���̨��ӡ����
		session.setDebug(true);

		// 3�������ʼ���ʵ������
		Message msg = getMimeMessage(session, "3409473342@qq.com", "1343614311@qq.com");

		// 4������session�����ȡ�ʼ��������Transport
		Transport transport = session.getTransport();

		// ���÷����˵��˻���������
		transport.connect("3409473342@qq.com", "mmffrinwfqnrcice");
		// �����ʼ��������͵������ռ��˵�ַ��message.getAllRecipients() ��ȡ�������ڴ����ʼ�����ʱ��ӵ������ռ���, ������, ������
		transport.sendMessage(msg, msg.getAllRecipients());

		// ���ֻ�뷢�͸�ָ�����ˣ���������д��
		// transport.sendMessage(msg, new Address[]{new InternetAddress("xxx@qq.com")});

		// 5���ر��ʼ�����
		transport.close();
	}

	/**
	 * �����ʼ�
	 * 
	 * @param session    �ͷ����������ĻỰ
	 * @param sendMail   ����������
	 * @param reciveMail �ռ�������
	 * @return
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */

	private static Message getMimeMessage(Session session, String sendMail, String reciveMail)
			throws MessagingException, UnsupportedEncodingException {
		// ���⡢���ġ������ˡ��ռ��� ������ʱ��{������ͼƬ}
		Message msg = new MimeMessage(session);
		// ����
		msg.setSubject("�����ʼ�(ͼƬ������)");
		// ������
		Address ads = new InternetAddress(sendMail, "��������", "utf-8");
		msg.setFrom(ads);
		// �ռ���
		msg.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(reciveMail, "�ռ���A", "utf-8"));
		// ����ʱ��
		msg.setSentDate(new Date());
		// �������ģ��������ɲ�����
		/* msg.setContent("����...", "text/html;charset=utf-8"); */
		// **********************************************************

//		// 5. ����ͼƬ���ڵ㡱
//		MimeBodyPart imagePart = new MimeBodyPart();
//		DataHandler imageDataHandler = new DataHandler(new FileDataSource("src//abc.png")); // ��ȡ�����ļ�
//		imagePart.setDataHandler(imageDataHandler); // ��ͼƬ������ӵ����ڵ㡱
//		imagePart.setContentID("image_fairy_tail"); // Ϊ���ڵ㡱����һ��Ψһ��ţ����ı����ڵ㡱�����ø�ID��
//		// 6. �����ı����ڵ㡱
//		MimeBodyPart textPart = new MimeBodyPart();
//		// �������ͼƬ�ķ�ʽ�ǽ�����ͼƬ�������ʼ�������, ʵ����Ҳ������ http ���ӵ���ʽ�������ͼƬ
//		textPart.setContent("����һ��ͼƬ<br/><img src='cid:image_fairy_tail'/>", "text/html;charset=UTF-8");
//
//		// 7. ���ı�+ͼƬ������ �ı� �� ͼƬ ���ڵ㡱�Ĺ�ϵ���� �ı� �� ͼƬ ���ڵ㡱�ϳ�һ����ϡ��ڵ㡱��
//		MimeMultipart mm_text_image = new MimeMultipart();
//		mm_text_image.addBodyPart(textPart);
//		mm_text_image.addBodyPart(imagePart);
//		mm_text_image.setSubType("related"); // ������ϵ
//
//		// 8. �� �ı�+ͼƬ �Ļ�ϡ��ڵ㡱��װ��һ����ͨ���ڵ㡱
//		MimeBodyPart text_image = new MimeBodyPart();
//		text_image.setContent(mm_text_image);
//		// 9. �����������ڵ㡱
//		MimeBodyPart attachment = new MimeBodyPart();
//		DataHandler attDataHandler = new DataHandler(new FileDataSource("src//�Ͽ�ǰ����ͷ.txt")); // ��ȡ�����ļ�
//		attachment.setDataHandler(attDataHandler); // ������������ӵ����ڵ㡱
//		attachment.setFileName(MimeUtility.encodeText(attDataHandler.getName())); // ���ø������ļ�������Ҫ���룩
//		// 10. ���ã��ı�+ͼƬ���� ���� �Ĺ�ϵ���ϳ�һ����Ļ�ϡ��ڵ㡱 / Multipart ��
//		MimeMultipart mm = new MimeMultipart();
//		mm.addBodyPart(text_image);
//		mm.addBodyPart(attachment); // ����ж�����������Դ������������
//		mm.setSubType("mixed"); // ��Ϲ�ϵ
//
//		// 11. ���������ʼ��Ĺ�ϵ�������յĻ�ϡ��ڵ㡱��Ϊ�ʼ���������ӵ��ʼ�����
//		msg.setContent(mm);

		// ����ͼƬ�ڵ�
		MimeBodyPart imgPart = new MimeBodyPart();
		DataHandler imgDataHandler = new DataHandler(new FileDataSource("src/main/java/imgs/timg.jpg"));// ��ȡ�����ļ�
		imgPart.setDataHandler(imgDataHandler); // ��ͼƬ������ӵ����ڵ�
		imgPart.setContentID("cat_img"); // Ϊ���ڵ㡱����һ��Ψһ��ţ����ı����ڵ㡱�����ø�ID��
		// �����ı��ڵ� Ŀ�ģ�����Ϊ�˼���ͼƬ�ڵ�
		MimeBodyPart textPart = new MimeBodyPart();
		textPart.setContent("����һ��ͼƬ<br/><img src='cid:cat_img'/>", "text/html;charset=UTF-8");

		// MIME(Multipurpose Internet Mail Extensions)
		// ��������ڵ㣨�ı�+ͼƬ������ �ı� �� ͼƬ ���ڵ㡱�Ĺ�ϵ���� �ı� �� ͼƬ ���ڵ㡱�ϳ�һ����ϡ��ڵ㡱��
		MimeMultipart mm_text_img = new MimeMultipart();
		mm_text_img.addBodyPart(imgPart);
		mm_text_img.addBodyPart(textPart);
		mm_text_img.setSubType("related");

		// ������ֻ�ܳ�����ͨ�ڵ㣨MimeBodyPart���������ܳ��ָ��Ͻڵ㣨MimeMutipart��
		// �ı�+ͼƬ �Ļ�ϡ��ڵ㡱��װ��һ����ͨ���ڵ㡱
		MimeBodyPart text_image = new MimeBodyPart();
		text_image.setContent(mm_text_img);

		// �����������ڵ㡱
		MimeBodyPart attachment = new MimeBodyPart();
		DataHandler addDataHandler = new DataHandler(new FileDataSource("src/main/java/�Ͽ�ǰ����ͷ.txt"));
		attachment.setDataHandler(addDataHandler);
		// ����������һ�䣬����ղ�������
		attachment.setFileName(MimeUtility.encodeText(addDataHandler.getName())); // ���ø������ļ�������Ҫ���룩
		
		// ���ã��ı�+ͼƬ���� ���� �Ĺ�ϵ���ϳ�һ����Ļ�ϡ��ڵ㡱 / Multipart ��
		MimeMultipart mm = new MimeMultipart();
		mm.addBodyPart(text_image);
		mm.addBodyPart(attachment); // ����ж�����������Դ������������
		mm.setSubType("mixed"); // ��Ϲ�ϵ

		// ----------------------�������ļ�����
		// ���������ʼ��Ĺ�ϵ�������յĻ�ϡ��ڵ㡱��Ϊ�ʼ���������ӵ��ʼ�����
		msg.setContent(mm);

		// **************************************************************
		// �����ʼ�
		msg.saveChanges();
		return msg;
	}

	/**
	 * �����ʼ���������������
	 * 
	 * @return
	 */
	public static Properties getProperties() {
		// 1�������ʼ��������Ĳ�������
		Properties props = new Properties();

		// ���ô���Э��
		props.setProperty("mail.transport.protocol", "smtp");

		// ���÷����˵�SMTP��������ַ
		props.setProperty("mail.smtp.host", "smtp.qq.com");

		// ����SMTP�������˿ں�
		props.setProperty("mail.smtp.port", "465");

		// �����û�����֤��ʽ
		props.setProperty("mail.smtp.auth", "true");

		// QQ��SSL��ȫ��֤
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		return props;
	}
}
