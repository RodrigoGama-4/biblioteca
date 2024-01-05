package com.biblioteca.biblioteca.services.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.biblioteca.biblioteca.dtos.UsuarioDTO;

import org.springframework.beans.factory.annotation.Value;


@Service
public class MailService {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromMail;

    public String senderMail(UsuarioDTO data){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromMail);
        simpleMailMessage.setSubject("CADASTRADO NA BIBLIOTECA");
        simpleMailMessage.setText("Olá," + data.nome() + " agora você pode alugar livros a vontade na nossa biblioteca");
        simpleMailMessage.setTo(data.email());
        

        mailSender.send(simpleMailMessage);

        return "Email enviado com sucesso";
    }

}
