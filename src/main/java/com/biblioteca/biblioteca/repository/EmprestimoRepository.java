package com.biblioteca.biblioteca.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.biblioteca.biblioteca.domain.Emprestimo;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long>{
    @Query("SELECT e FROM Emprestimo e WHERE e.livro.isbn = :isbn AND e.usuario.userId = :usuarioId")
    Emprestimo findByIsbnAndUsuarioId(@Param("isbn") String isbn, @Param("usuarioId") Long usuarioId);

    List<Emprestimo> findByDataDevolucaoBeforeAndDataDevolucaoIsNotNull(LocalDate dataDevolucao);

}
