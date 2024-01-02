package com.biblioteca.biblioteca.domain;

import jakarta.persistence.*;
import java.util.Date;

import com.biblioteca.biblioteca.dtos.EmprestimoDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @JsonIgnore
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

    public Emprestimo() {
    }

    public Long getEmprestimoId() {
        return emprestimoId;
    }

    public void setEmprestimoId(Long emprestimoId) {
        this.emprestimoId = emprestimoId;
    }

    public Livros getLivro() {
        return livro;
    }

    public void setLivro(Livros livro) {
        this.livro = livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    
}

