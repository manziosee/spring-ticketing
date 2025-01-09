package com.ticketing.Controller;

import com.ticketing.Model.EmailRequest;
import com.ticketing.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest emailRequest) {
        try {
            emailService.sendEmail(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getText());
            return ResponseEntity.ok("Email sent successfully to " + emailRequest.getTo());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to send email: " + e.getMessage());
        }
    }

    @PostMapping("/send-bulk")
    public ResponseEntity<String> sendBulkEmail(@RequestBody EmailRequest emailRequest) {
        try {
            emailService.sendBulkEmail(emailRequest);
            return ResponseEntity.ok("Bulk email sent successfully to multiple recipients");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to send bulk email: " + e.getMessage());
        }
    }
}