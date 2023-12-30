package com.biblioteca.biblioteca.domain;

import jakarta.persistence.*;
import java.util.Date;

import com.biblioteca.biblioteca.dtos.EmprestimoDTO;

@Entity
@Table(name = "emprestimo")
public class Emprestimo {

    @Id
    @GeneratedValue()
    @Column(name = "emprestimo_id")
    private Long emprestimoId;

    @ManyToOne
    @JoinColumn(name = "livro_isbn", referencedColumnName = "isbn")
    private Livros livro;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "user_id")
    private Usuario usuario;


    @Temporal(TemporalType.DATE)
    @Column(name = "data_emprestimo")
    private Date dataEmprestimo;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_devolucao")
    private Date dataDevolucao;

    public Emprestimo(EmprestimoDTO emprestimoDTO){
        
    }

    public Emprestimo() {
    }
}

