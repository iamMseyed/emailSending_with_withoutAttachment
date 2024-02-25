package com.seyed.emailSending.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileOutputStream;


@RestController
public class EmailController {
    private final JavaMailSender javaMailSender;
    public EmailController(JavaMailSender javaMailSender) {
            this.javaMailSender = javaMailSender;
        }//constructor based injection

    @PostMapping("/sendEmailWithAttachment")
      public  ResponseEntity<String> sendEmailWA(@RequestParam("to") String to,
                                                 @RequestParam("subject") String subject,
                                                 @RequestParam("body") String body,
                                                 @RequestPart("attachment") MultipartFile attachment
                                                 )
    {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(body);

            String uploadDirectory = "src/main/java/com/seyed/emailSending/attachments/";
            File directory = new File(uploadDirectory);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Save the file to the upload directory
            String filePath = uploadDirectory + attachment.getOriginalFilename();
            File newFile = new File(filePath);
            try (FileOutputStream fos = new FileOutputStream(newFile)) {
                fos.write(attachment.getBytes());
            }

            mimeMessageHelper.addAttachment("attachment", newFile);
            javaMailSender.send(mimeMessage);

            return ResponseEntity.ok("If the email is provided valid, receiver will receive the mail shortly");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send email: " + e.getMessage());
        }
    }

    @PostMapping("/sendEmailWithoutAttachment")
    public ResponseEntity<String> sendEmailWOA(@RequestParam("to") String to,
                                               @RequestParam("subject") String subject,
                                               @RequestParam("body") String body
    ){
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try{
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(body);

            javaMailSender.send(mimeMessage);

            return ResponseEntity.ok("If the email is provided valid, receiver will receive the mail shortly");
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send an email: "+e.getMessage());
        }

    }
}