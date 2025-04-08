package br.com.montreal.blogpessoal.service;

import br.com.montreal.blogpessoal.model.Usuario;
import br.com.montreal.blogpessoal.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> buscarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarUsuario(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.orElse(null);
    }

    public Usuario cadastrarUsuario(@Valid Usuario usuarioRequest) {
        if(usuarioRepository.findByUsuario(usuarioRequest.getUsuario()) != null) {
            throw new RuntimeException("Este email já foi cadastrado");
        }
        return usuarioRepository.save(usuarioRequest);
    }

    @Transactional
    public Usuario atualizarUsuario(Long id, @Valid Usuario usuarioRequest) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()) {
            usuarioRequest.setId(id);
            return usuarioRepository.save(usuarioRequest);
        }
        else{
            throw new RuntimeException("Usuario não encontrado");
        }
    }

    @Transactional
    public void deletarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
        usuarioRepository.delete(usuario);
    }

}
