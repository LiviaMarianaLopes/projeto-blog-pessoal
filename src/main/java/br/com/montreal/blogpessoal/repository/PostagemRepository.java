package br.com.montreal.blogpessoal.repository;

import br.com.montreal.blogpessoal.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PostagemRepository extends JpaRepository<Postagem, Long> {
    @Query("SELECT p FROM Postagem p WHERE (:autorId IS NULL OR p.usuario.id = :autorId) AND (:temaId IS NULL OR p.tema.id = :temaId)")
    List<Postagem> filtrarPorAutorETema(@Param("autorId") Long autorId, @Param("temaId") Long temaId);
}
