# Email sending with/without attachment 

## Description
This project is a Spring Boot application that provides endpoints for sending emails with and without attachments using the Spring Boot Mail dependency. Users can interact with the application via Postman by accessing specific URLs to send emails.

**Setup and api endpoint usage**

1. **Clone the repository:**
   git clone <repository_url>
   
2. **Spring Mail Properties:**
  ```
  spring.mail.host=smtp.example.com  
  spring.mail.port=587  
  spring.mail.username=your_email@example.com  
  spring.mail.password=your_email_password  
  spring.mail.properties.mail.smtp.auth=true  
  spring.mail.properties.mail.smtp.starttls.enable=true  
`````````````````````````
4. **Endpoints**  
  Send Email with Attachment:
```````````````````````````
    URL: localhost:8080/sendEmailWithAttachment  
      Method: POST  
        Request Body:
          {
              "to": "recipient@example.com",
              "subject": "Subject of the Email",
              "body": "Body of the Email",
              "attachment": "anyAttachment"
          }
```````````````````````````
 Send Email without Attachment:
```````````````````````````
    URL: localhost:8080/sendEmailWithoutAttachment   
      Method: POST  
        Request Body:
          {
              "to": "recipient@example.com",
              "subject": "Subject of the Email",
              "body": "Body of the Email"
          }
```````````````````````````

_via postman, head to body> form-data and in keyvalue put the assigned data onto key value fields. Say, if to setup to, in key put "to" and in value set the associated value as a text, but in case of
attachment set type as file, and upload file_
        
**Technologies Used**  
- Java  
- Spring Boot  
- Spring Mail  
