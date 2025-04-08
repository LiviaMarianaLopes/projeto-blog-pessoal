package br.com.montreal.blogpessoal.controller;

import br.com.montreal.blogpessoal.DTO.AuthDTO;
import br.com.montreal.blogpessoal.DTO.UsuarioLogin;
import br.com.montreal.blogpessoal.model.Usuario;
import br.com.montreal.blogpessoal.repository.UsuarioRepository;
import br.com.montreal.blogpessoal.security.TokenService;
import br.com.montreal.blogpessoal.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthDTO authDTO) {
        try {
            var usuarioSenha = new UsernamePasswordAuthenticationToken(authDTO.usuario(), authDTO.senha());
            var auth = this.authenticationManager.authenticate(usuarioSenha);
            var token = tokenService.generateToken((Usuario) auth.getPrincipal());
            var usuarioEntity = (Usuario) auth.getPrincipal();

            UsuarioLogin usuarioLogin = new UsuarioLogin();
            usuarioLogin.setId(usuarioEntity.getId());
            usuarioLogin.setNome(usuarioEntity.getNome());
            usuarioLogin.setUsuario(usuarioEntity.getUsuario());
            usuarioLogin.setFoto(usuarioEntity.getFoto());
            usuarioLogin.setToken(token);
            return ResponseEntity.ok(usuarioLogin);
        } catch (BadCredentialsException e) {
            return ResponseEntity
                    .badRequest()
                    .body("Credenciais inválidas");
        }
    }

    @PostMapping
    public ResponseEntity createUsuario(@Valid @RequestBody Usuario usuarioRequest) {
        try{
            String encryptedPassword = new BCryptPasswordEncoder().encode(usuarioRequest.getSenha());
            usuarioRequest.setSenha(encryptedPassword);
            Usuario usuario = usuarioService.cadastrarUsuario(usuarioRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @Valid @RequestBody Usuario usuarioRequest) {
        try{
            Usuario usuario = usuarioService.atualizarUsuario(id, usuarioRequest);
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
