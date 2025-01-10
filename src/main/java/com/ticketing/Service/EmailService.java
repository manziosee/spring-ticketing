package com.ticketing.Service;

import com.ticketing.Model.EmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.retry.annotation.Retryable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.util.List;
import java.util.Arrays;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender mailSender;

    @Retryable
    public void sendEmail(String to, String subject, String text) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true);

        mailSender.send(message);

        logger.info("Email sent successfully to {}", to);
    }

    @Async
    @Retryable
    public void sendBulkEmail(EmailRequest emailRequest) {
        List<String> toList = Arrays.asList(emailRequest.getTo().split(","));
        int batchSize = 100;
        for (int i = 0; i < toList.size(); i += batchSize) {
            List<String> batch = toList.subList(i, Math.min(toList.size(), i + batchSize));
            for (String to : batch) {
                try {
                    sendEmail(to, emailRequest.getSubject(), emailRequest.getText());
                } catch (MessagingException e) {
                    logger.error("Failed to send email to {}: {}", to, e.getMessage());
                }
            }
        }
    }
}