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
	//������
	private String senderAccount;
	//�˻�����
	private String senderPassword;
	
	
	public static void sendMial() throws MessagingException, UnsupportedEncodingException {
        //2���������ʼ������������� Session�Ự ����
        Session session = Session.getInstance(getProperties());
        //���õ�����Ϣ�ڿ���̨��ӡ����
        session.setDebug(true);
        
        //3�������ʼ���ʵ������
        Message msg = getMimeMessage(session, "3409473342@qq.com", "1343614311@qq.com");
        
        //4������session�����ȡ�ʼ��������Transport
        Transport transport = session.getTransport();
        
        //���÷����˵��˻���������
        transport.connect("3409473342@qq.com", "mmffrinwfqnrcice");
        //�����ʼ��������͵������ռ��˵�ַ��message.getAllRecipients() ��ȡ�������ڴ����ʼ�����ʱ��ӵ������ռ���, ������, ������
        transport.sendMessage(msg,msg.getAllRecipients());
         
        //���ֻ�뷢�͸�ָ�����ˣ���������д��
        //transport.sendMessage(msg, new Address[]{new InternetAddress("xxx@qq.com")});
         
        //5���ر��ʼ�����
        transport.close();
	}
	
	
	
	
	
	/**
	 * �����ʼ�
	 * @param session �ͷ����������ĻỰ
	 * @param sendMail ����������
	 * @param reciveMail �ռ�������
	 * @return
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	
	private static Message getMimeMessage(Session session, 
			String sendMail, String reciveMail) throws MessagingException, UnsupportedEncodingException {
		// ���⡢���ġ������ˡ��ռ��� ������ʱ��{������ͼƬ}
		Message msg = new MimeMessage(session);
		// ����
		msg.setSubject("���Ǳ��Ⱑ��");
		// ������
		Address ads = new InternetAddress(sendMail,"��������","utf-8");
		msg.setFrom(ads);
		// �ռ���
		msg.addRecipient(MimeMessage.RecipientType.TO,new InternetAddress(reciveMail, "�ռ���A", "utf-8"));
		// ����ʱ��
		msg.setSentDate(new Date());
		// �������ģ��������ɲ�����
		msg.setContent("����...", "text/html;charset=utf-8");		
		//�����ʼ�
		msg.saveChanges();
		return msg;
	}







	/**
	 * �����ʼ���������������
	 * @return
	 */
	public static Properties getProperties() {
		//1�������ʼ��������Ĳ�������
        Properties props = new Properties();
        
        //���ô���Э��
        props.setProperty("mail.transport.protocol", "smtp");
        
        //���÷����˵�SMTP��������ַ
        props.setProperty("mail.smtp.host", "smtp.qq.com");
        
        //����SMTP�������˿ں�
        props.setProperty("mail.smtp.port", "465");
        
        //�����û�����֤��ʽ
        props.setProperty("mail.smtp.auth", "true");
        
        //QQ��SSL��ȫ��֤
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", "465"); 
        return props;
    }
}
