package com.iaschowrai.alert_service.service;

import com.iaschowrai.alert_service.entity.Alert;
import com.iaschowrai.alert_service.repository.AlertRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final AlertRepository alertRepository;


    public EmailService(JavaMailSender javaMailSender, AlertRepository alertRepository) {
        this.javaMailSender = javaMailSender;
        this.alertRepository = alertRepository;
    }

    public void sendEmail(String to, String subject, String body, Long userId){
        log.info("Sending email to : {}, subject: {}",to,subject );

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setFrom("noreply@iaschowrai.com");
        mailMessage.setSubject(subject);
        mailMessage.setText(body);

        try{
            javaMailSender.send(mailMessage);
            final Alert alertSent = Alert.builder()
                    .sent(true)
                    .createdAt(LocalDateTime.now())
                    .userId(userId)
                    .build();

            alertRepository.saveAndFlush(alertSent);


        } catch (MailException ex){
            log.error("Failed to send email to : {}", to, ex);
            final Alert alertSent = Alert.builder()
                    .sent(false)
                    .createdAt(LocalDateTime.now())
                    .userId(userId)
                    .build();

            alertRepository.saveAndFlush(alertSent);
            return;
        }
        log.info("Email sent to {}", to);
    }
}
