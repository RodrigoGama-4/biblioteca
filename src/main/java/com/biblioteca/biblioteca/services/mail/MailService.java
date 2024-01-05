package com.biblioteca.biblioteca.services.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Value;

/*USAR LOG PARA VER SE O EMAIL FOI ENVIADO CORRETAMENTE */



@Service
public class MailService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MailService.class);

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromMail;

    public void senderMail(String nome, String email){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromMail);
        simpleMailMessage.setSubject("CADASTRADO NA BIBLIOTECA");
        simpleMailMessage.setText("Olá," + nome + " agora você pode alugar livros a vontade na nossa biblioteca");
        simpleMailMessage.setTo(email);
        

        mailSender.send(simpleMailMessage);
        LOGGER.info("Email enviado com sucesso ao endereço: " + email);
    }

}
