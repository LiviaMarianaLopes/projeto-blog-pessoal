package br.com.montreal.blogpessoal.controller;

import br.com.montreal.blogpessoal.model.Tema;
import br.com.montreal.blogpessoal.service.TemaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/temas")
public class TemaController {
    @Autowired
    private TemaService temaService;

    @GetMapping
    public ResponseEntity<List<Tema>> readTemas() {
        List<Tema> listaTemas = temaService.buscarTemas();
        if (listaTemas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listaTemas, HttpStatus.OK);
        }

    @PostMapping
    public ResponseEntity createTema(@Valid @RequestBody Tema temaRequest) {
        try{
            Tema tema = temaService.cadastrarTema(temaRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(tema);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @Valid @RequestBody Tema temaRequest) {
        try{
            Tema tema = temaService.atualizarTema(id, temaRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(tema);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            temaService.deletarTema(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}

