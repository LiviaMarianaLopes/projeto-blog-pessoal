package br.com.montreal.blogpessoal.repository;

import br.com.montreal.blogpessoal.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostagemRepository extends JpaRepository<Postagem, Long> {
}
