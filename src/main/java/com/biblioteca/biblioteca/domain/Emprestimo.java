package com.biblioteca.biblioteca.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

import com.biblioteca.biblioteca.dtos.EmprestimoDTO;

@Getter
@Setter
@Entity
@NoArgsConstructor
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
        this.livro = emprestimoDTO.isbn();
        this.usuario = emprestimoDTO.idUsuario();
        this.dataEmprestimo = emprestimoDTO.data_emprestimo();
        this.dataDevolucao = emprestimoDTO.data_devo();

    }
    
}

