package com.oguzkurtcebe.petclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class PetClinicMailService {
	@Autowired
	private JavaMailSender mailSender;
	
	public void mailSenderFunction(ResponseEntity res) {
         SimpleMailMessage msg=new SimpleMailMessage();
         msg.setFrom("o@k");
         msg.setTo("k@m");
         msg.setSubject("Durum");
         msg.setText("Hata"+res);
        mailSender.send(msg);
	}

	

}
