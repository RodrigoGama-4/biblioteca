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

    public void senderMail(String nome, String email, String tituloEmail, String corpoEmail){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromMail);
        simpleMailMessage.setSubject(tituloEmail);
        simpleMailMessage.setText("Olá," + nome + corpoEmail);
        simpleMailMessage.setTo(email);
        

        mailSender.send(simpleMailMessage);
        LOGGER.info("Email enviado com sucesso ao endereço: " + email);
    }

}
