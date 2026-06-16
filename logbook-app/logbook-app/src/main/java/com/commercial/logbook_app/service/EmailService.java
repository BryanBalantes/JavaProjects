package com.commercial.logbook_app.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

  private final JavaMailSender javaMailSender;

  public EmailService(JavaMailSender javaMailSender) {
    this.javaMailSender = javaMailSender;
  }

  public void sendSimpleEmail(String recepient, String subject, String body) {
    //        SimpleMailMessage message = new SimpleMailMessage();
    //        message.setTo(recepient);
    //        message.setSubject(subject);
    //        message.setText(body);
    //
    //        javaMailSender.send(message);
  }
}
