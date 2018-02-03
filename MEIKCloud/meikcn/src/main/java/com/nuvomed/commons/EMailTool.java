package com.nuvomed.commons;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.nuvomed.dto.TemaiMessage;
public class EMailTool {
	
	 public static void send(TemaiMessage temaiMessage, boolean html){
		try {
			JavaMailSender javaMailSenderImpl=getJavaMailSenderImpl();
			MimeMessage message = javaMailSenderImpl.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "utf-8");
            messageHelper.setFrom(new InternetAddress(SystemConfig.Admin_Setting_Map.get(SystemConstants.EMAIL_NAME)));
            if(temaiMessage.getTo() !=null && !temaiMessage.getTo().isEmpty()){
            	messageHelper.setTo(temaiMessage.getTo());
            }
            if(temaiMessage.getSubject() !=null){
            	messageHelper.setSubject(temaiMessage.getSubject());
            }
            if(temaiMessage.getText() !=null){
            	messageHelper.setText(temaiMessage.getText(),html);
            }
			javaMailSenderImpl.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	 
	public static JavaMailSenderImpl getJavaMailSenderImpl(){
	    JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
	    javaMailSenderImpl.setHost(SystemConfig.Admin_Setting_Map.get(SystemConstants.EMAIL_HOST));
	    javaMailSenderImpl.setUsername(SystemConfig.Admin_Setting_Map.get(SystemConstants.EMAIL_NAME));
	    javaMailSenderImpl.setPassword(SystemConfig.Admin_Setting_Map.get(SystemConstants.EMAIl_PASSWORD));
	    Properties properties = new Properties();
	    properties.setProperty("mail.smtp.auth", "true");
	    properties.setProperty("mail.smtp.timeout", "30000");
	    javaMailSenderImpl.setJavaMailProperties(properties);
	    return javaMailSenderImpl;
     }

}
