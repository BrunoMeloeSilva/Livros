package github.com.brunomeloesilva.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import github.com.brunomeloesilva.domain.DetalhesErro;
import github.com.brunomeloesilva.services.exceptions.AutorExistenteException;
import github.com.brunomeloesilva.services.exceptions.AutorNaoEncontradoException;
import github.com.brunomeloesilva.services.exceptions.LivroNaoEncontradoException;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(LivroNaoEncontradoException.class)
    public ResponseEntity<DetalhesErro> hanbleLivroNaoEncontradoExcention (LivroNaoEncontradoException e, HttpServletRequest r){

        DetalhesErro detalhesErro = new DetalhesErro(404l, "O Livro não existe no banco de dados.", "http://erros.livrosAPI.com/livro/404", System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(detalhesErro);
    }
    
    @ExceptionHandler(AutorExistenteException.class)
    public ResponseEntity<DetalhesErro> hanbleAutorExistenteException(AutorExistenteException e, HttpServletRequest r){

        DetalhesErro detalhesErro = new DetalhesErro(409l, "O Autor já existe no banco de dados.", "http://erros.livrosAPI.com/autor/409", System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(detalhesErro);
    }

    @ExceptionHandler(AutorNaoEncontradoException.class)
    public ResponseEntity<DetalhesErro> hanbleAutorNaoEncontradoException(AutorNaoEncontradoException e, HttpServletRequest r){

        DetalhesErro detalhesErro = new DetalhesErro(404l, "O Autor não existe no banco de dados.", "http://erros.livrosAPI.com/autor/404", System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(detalhesErro);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<DetalhesErro> hanbleDataIntegrityViolationException(DataIntegrityViolationException e, HttpServletRequest r){

        DetalhesErro detalhesErro = new DetalhesErro(400l, "Requisição com JSON mal feito", "http://erros.livrosAPI.com/400", System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(detalhesErro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DetalhesErro> hanbleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest r){

        DetalhesErro detalhesErro = new DetalhesErro(400l
        , "O JSON na requisição não contém os campos obrigatórios: " 
        , "http://erros.livrosAPI.com/400", System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(detalhesErro);
    }
}
