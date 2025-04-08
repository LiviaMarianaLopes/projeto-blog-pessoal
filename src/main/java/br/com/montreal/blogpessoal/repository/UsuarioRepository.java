package br.com.montreal.blogpessoal.repository;

import br.com.montreal.blogpessoal.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
   // Optional<Usuario> findByUsuario(String email);
    UserDetails findByUsuario(String email);

}
