package br.com.montreal.blogpessoal.controller;

import br.com.montreal.blogpessoal.model.Postagem;
import br.com.montreal.blogpessoal.service.PostagemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/postagens")
public class PostagemController {
    @Autowired
    private PostagemService postagemService;

    @GetMapping
    public ResponseEntity<List<Postagem>> readPostagens() {
        List<Postagem> listaPostagens = postagemService.buscarPostagems();
        if (listaPostagens.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listaPostagens, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createPostagem(@Valid @RequestBody Postagem postagemRequest) {
        try{
            Postagem postagem = postagemService.cadastrarPostagem(postagemRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(postagem);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @Valid @RequestBody Postagem postagemRequest) {
        try{
            Postagem postagem = postagemService.atualizarPostagem(id, postagemRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(postagem);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            postagemService.deletarPostagem(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/filtro")
    public ResponseEntity<List<Postagem>> filtrarPostagens(
            @RequestParam(required = false) Long autor,
            @RequestParam(required = false) Long tema) {

        List<Postagem> postagensFiltradas = postagemService.filtrarPorAutorETema(autor, tema);
        return ResponseEntity.ok(postagensFiltradas);
    }

}

