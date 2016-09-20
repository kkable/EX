package comtrue.homework.service;

import java.io.File;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.inject.Inject;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

public class MailService {

	 @Autowired
	 protected JavaMailSender  mailSender;

	 @Inject
	 MemberService service;
	
	
	public void timemail() {
		MimeMessage msg = mailSender.createMimeMessage();
		try {
			msg.setSubject("회원 목록입니다");
			msg.setText("엑셀파일로 첨부합니다");
			msg.setRecipient(RecipientType.TO , new InternetAddress("pyb1010114@naver.com"));
			msg.setFileName("회원목록.xls");
			msg.setDataHandler(new DataHandler(new FileDataSource(new File(service.makeExcelFile()))));
			mailSender.send(msg); 
		} catch (Exception e) {
			System.out.println("메일보내기 실패");
		}
		System.out.println("시간 메일보냅니다");
	}
}
