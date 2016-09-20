package comtrue.homework.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.inject.Inject;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import comtrue.homework.service.MemberService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class MailTest {

	
	 @Autowired
	 protected JavaMailSender  mailSender;
	 @Inject
	 MemberService service;
	 
	@Test
	public void Mail() throws Exception{
			MimeMessage msg = mailSender.createMimeMessage();
	        msg.setSubject("제목");
	        msg.setText("내용");
	        msg.setRecipient(RecipientType.TO , new InternetAddress("pyb1010114@naver.com"));
	        msg.setFileName("회원관리.xls");
	        msg.setDataHandler(new DataHandler(new FileDataSource(new File(service.makeExcelFile()))));
	        mailSender.send(msg); 
		
	}
	
	
	
	
	
}
