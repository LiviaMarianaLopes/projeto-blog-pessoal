package br.com.montreal.blogpessoal.service;

import br.com.montreal.blogpessoal.model.Postagem;
import br.com.montreal.blogpessoal.repository.PostagemRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PostagemService {
    @Autowired
    private PostagemRepository postagemRepository;

    public List<Postagem> buscarPostagems() {
        return postagemRepository.findAll();
    }

    public Postagem buscarPostagem(Long id) {
        Optional<Postagem> postagem = postagemRepository.findById(id);
        return postagem.orElse(null);
    }

    public Postagem cadastrarPostagem(@Valid Postagem postagem) {

        return postagemRepository.save(postagem);

    }

    @Transactional
    public Postagem atualizarPostagem(Long id, @Valid Postagem postagem) {
        Optional<Postagem> postagemOptional = postagemRepository.findById(id);

        if (postagemOptional.isPresent()) {
            postagem.setId(id);
            return postagemRepository.save(postagem);
        }
        else{
            throw new RuntimeException("Postagem não encontrado");
        }
    }

    @Transactional
    public void deletarPostagem(Long id) {
        Postagem postagem = postagemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Postagem não encontrado"));
        postagemRepository.delete(postagem);
    }

    public List<Postagem> filtrarPorAutorETema(Long autorId, Long temaId) {
        return postagemRepository.filtrarPorAutorETema(autorId, temaId);
    }

}
