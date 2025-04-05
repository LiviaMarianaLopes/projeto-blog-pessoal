package br.com.montreal.blogpessoal.repository;

import br.com.montreal.blogpessoal.model.Tema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemaRepository extends JpaRepository<Tema, Long> {
}
