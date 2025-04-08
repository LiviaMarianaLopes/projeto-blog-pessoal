package br.com.montreal.blogpessoal.controller;

import br.com.montreal.blogpessoal.model.Usuario;
import br.com.montreal.blogpessoal.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity createUsuario(@Valid @RequestBody Usuario consultaRequest) {
        try{
            Usuario usuario = usuarioService.cadastrarUsuario(consultaRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @Valid @RequestBody Usuario consultaRequest) {
        try{
            Usuario usuario = usuarioService.atualizarUsuario(id, consultaRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            usuarioService.deletarUsuario(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
