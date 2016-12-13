/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fwtj.springbootrestfuncionarios.servico.excecao;

/**
 *
 * @author Junior
 */
public class FaltaInvalidaExcecao extends RuntimeException {

    //private static final long serialVersionUID = 1L;

    public FaltaInvalidaExcecao(String message) {
        super(message);
    }

    public FaltaInvalidaExcecao(String message, Throwable cause) {
        super(message, cause);
    }

}
