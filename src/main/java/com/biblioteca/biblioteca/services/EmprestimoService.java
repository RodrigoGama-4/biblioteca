package com.biblioteca.biblioteca.services;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.biblioteca.domain.Emprestimo;
import com.biblioteca.biblioteca.domain.Livros;
import com.biblioteca.biblioteca.exceptions.EmprestimoNaoEncontradoException;
import com.biblioteca.biblioteca.exceptions.LivroNaoEncontradoException;
import com.biblioteca.biblioteca.exceptions.LivrosOcupadosException;
import com.biblioteca.biblioteca.exceptions.UsuarioPossuiLivroExcepction;
import com.biblioteca.biblioteca.repository.EmprestimoRepository;
import com.biblioteca.biblioteca.services.mail.MailService;


@Service
public class EmprestimoService {
    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private LivrosService livrosService;

    @Autowired
    private MailService mailService;

    public Page<Emprestimo> getAll(Pageable pageable){
        return this.emprestimoRepository.findAll(pageable);
    }

    public Optional<Emprestimo> findbyId(Long id){
        return this.emprestimoRepository.findById(id);
    }

    @Transactional
    public void saveLoad(Emprestimo emprestimo) throws RuntimeException{

        if (this.userHasBook(emprestimo.getLivro().getIsbn(), emprestimo.getUsuario().getUserId())){
            throw new UsuarioPossuiLivroExcepction("Usuário já possui esse Livro");
        }

        Optional<Livros> livroOptional = this.livrosService.findbyId(emprestimo.getLivro().getIsbn());
        
        if (livroOptional.isPresent()){
            Livros livro = livroOptional.get();

            if ( livro.getQuantidadeDisponivel() > 0){
                livro.setQuantidadeDisponivel(livro.getQuantidadeDisponivel() - 1);
                this.emprestimoRepository.save(emprestimo);

                // Enviar o e-mail de forma assíncrona
                CompletableFuture.runAsync(() -> this.mailService.senderMail(emprestimo.getUsuario().getNome(), 
                                                                            emprestimo.getUsuario().getEmail(),
                                                                "Aluguel de livros", 
                                                                            "Parabéns, você alugou o livro: "+ emprestimo.getLivro().getTitulo()));
            }
            else{
                throw new LivrosOcupadosException("Todos os livros estão ocupados, não é possível fazer o empréstimo.");
            }
        }
        else{
            throw new LivroNaoEncontradoException(emprestimo.getLivro().getIsbn());
        }
    }

    @Transactional
    public void deleteLoad(Emprestimo empres) {
        Optional<Emprestimo> empreOptional = this.findbyId(empres.getEmprestimoId());
        Optional<Livros> livroOptional = this.livrosService.findbyId(empres.getLivro().getIsbn());


       if (empreOptional.isPresent() && livroOptional.isPresent()){
            Emprestimo emprestimo = empreOptional.get();
            Livros livro = livroOptional.get();

            livro.setQuantidadeDisponivel(livro.getQuantidadeDisponivel()+1);
            this.emprestimoRepository.delete(emprestimo);
       }
       else{
            throw new EmprestimoNaoEncontradoException("Empréstimo não encontrado");
       }
    }

    /*Garantir que cada usuário nao tenha duas vezes o mesmo livro */
    private boolean userHasBook(String isbn, Long idUsuario){
        Emprestimo emprestimo = emprestimoRepository.findByIsbnAndUsuarioId(isbn, idUsuario);
        boolean exists = emprestimo != null;
        return exists;

    }
}
