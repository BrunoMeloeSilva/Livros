package github.com.brunomeloesilva.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import github.com.brunomeloesilva.domain.Livro;
import github.com.brunomeloesilva.repository.LivrosRepository;

@RestController
@RequestMapping("/livros")
public class LivrosResources {

    @Autowired
    private LivrosRepository livrosRepository;

    @GetMapping
    public List<Livro> listar() {
        return livrosRepository.findAll();
    }

    @PostMapping
    public void salvar(@RequestBody Livro livro) {
        livrosRepository.save(livro);
    }

    @GetMapping("/{livroId}")
    public ResponseEntity<?> buscar(@PathVariable Long livroId) {
        Optional<Livro> livro = livrosRepository.findById(livroId);
        if(livro.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(livro);
    }

    @DeleteMapping("/{livroId}")
    public void deletar(@PathVariable("livroId") Livro livro){
        livrosRepository.delete(livro);
    }

    @PutMapping("/{livroId}")
    public void atualizar(@PathVariable("livroId") Long livroId, @RequestBody Livro livro){
        livro.setId(livroId);
        livrosRepository.save(livro);
    }
}