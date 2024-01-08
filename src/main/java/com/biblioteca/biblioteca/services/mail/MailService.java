package com.biblioteca.biblioteca.services.mail;

import java.time.LocalDate;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.biblioteca.biblioteca.domain.Emprestimo;
import com.biblioteca.biblioteca.repository.EmprestimoRepository;
import com.biblioteca.biblioteca.services.EmprestimoService;

import org.springframework.beans.factory.annotation.Value;

/*USAR LOG PARA VER SE O EMAIL FOI ENVIADO CORRETAMENTE */



@Service
public class MailService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MailService.class);

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Value("${spring.mail.username}")
    private String fromMail;



    @Scheduled(cron = "0 0 12 * * ?")
    public void senderMailBilling(){
        List<Emprestimo> emprestimos = this.buscarUsuariosComDevolucaoAtrasada();

        for (Emprestimo emprestimo: emprestimos){
            this.senderMail(emprestimo.getUsuario().getNome(), emprestimo.getUsuario().getEmail(),
             "DIA DA DEVOLUÇÃO", "Prezado, o dia da devolução do livro chegou, por favor nos entregue o livro: " + emprestimo.getLivro().getTitulo() + "que estar pendente.");
        }
    }

    public void senderMail(String nome, String email, String tituloEmail, String corpoEmail){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromMail);
        simpleMailMessage.setSubject(tituloEmail);
        simpleMailMessage.setText("Olá," + nome + corpoEmail);
        simpleMailMessage.setTo(email);
        

        mailSender.send(simpleMailMessage);
        LOGGER.info("Email enviado com sucesso ao endereço: " + email);
    }

    /*Retorna a lista de emprestimos que estao para devolução */
    public List<Emprestimo> buscarUsuariosComDevolucaoAtrasada() {
        LocalDate dataAtual = LocalDate.now();
        return emprestimoRepository.findByDataDevolucaoBeforeAndDataDevolucaoIsNotNull(dataAtual);
    }

}
