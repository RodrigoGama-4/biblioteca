package com.biblioteca.biblioteca.domain;

import jakarta.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "emprestimo")
public class Emprestimo {

    @Id
    @Column(name = "emprestimo_id")
    private UUID emprestimoId;

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

}

