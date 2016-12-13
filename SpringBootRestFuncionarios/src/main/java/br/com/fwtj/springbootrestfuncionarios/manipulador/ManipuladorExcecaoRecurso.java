/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fwtj.springbootrestfuncionarios.manipulador;

import br.com.fwtj.springbootrestfuncionarios.dominio.DetalhesErro;
import br.com.fwtj.springbootrestfuncionarios.servico.excecao.FaltaInvalidaExcecao;
import br.com.fwtj.springbootrestfuncionarios.servico.excecao.FaltaNaoEncontradaExcecao;
import br.com.fwtj.springbootrestfuncionarios.servico.excecao.FuncionarioCamposObrigatoriosExcecao;
import br.com.fwtj.springbootrestfuncionarios.servico.excecao.FuncionarioNaoEncontradoExcecao;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author Junior
 */
@ControllerAdvice
public class ManipuladorExcecaoRecurso {

    @ExceptionHandler(FuncionarioNaoEncontradoExcecao.class)
    public ResponseEntity<DetalhesErro> manipuladorFuncionarioNaoEncontradoExcecao(FuncionarioNaoEncontradoExcecao e, HttpServletRequest request) {
        DetalhesErro detalhesErro = new DetalhesErro("404", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .header("Motivo", e.getMessage())
                .body(detalhesErro);
    }

    @ExceptionHandler(FuncionarioCamposObrigatoriosExcecao.class)
    public ResponseEntity<DetalhesErro> manipuladorFuncionarioNaoEncontradoExcecao(FuncionarioCamposObrigatoriosExcecao e, HttpServletRequest request) {
        DetalhesErro detalhesErro = new DetalhesErro("409", e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .header("Motivo", e.getMessage())
                .body(detalhesErro);
    }
    
    @ExceptionHandler(FaltaNaoEncontradaExcecao.class)
    public ResponseEntity<DetalhesErro> manipuladorFaltaNaoEncontradaExcecao(FaltaNaoEncontradaExcecao e, HttpServletRequest request) {
        DetalhesErro detalhesErro = new DetalhesErro("404", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .header("Motivo", e.getMessage())
                .body(detalhesErro);
    }
    
    @ExceptionHandler(FaltaInvalidaExcecao.class)
    public ResponseEntity<DetalhesErro> manipuladorFaltaInvalidaExcecao(FaltaInvalidaExcecao e, HttpServletRequest request) {
        DetalhesErro detalhesErro = new DetalhesErro("409", e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .header("Motivo", e.getMessage())
                .body(detalhesErro);
    }

}
