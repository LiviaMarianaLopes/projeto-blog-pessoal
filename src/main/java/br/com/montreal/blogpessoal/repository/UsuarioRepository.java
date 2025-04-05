package br.com.montreal.blogpessoal.repository;

import br.com.montreal.blogpessoal.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
