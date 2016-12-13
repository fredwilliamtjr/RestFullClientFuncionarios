/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fwtj.springbootrestfuncionarios.recursos;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Junior
 */
@RestController
@RequestMapping("/")
public class InicialRecurso {

    @RequestMapping(method = RequestMethod.GET)
    public String listar() {
        return ""
                + "Fwtj Sistemas"
                + "<br>"
                + "<br>"
                + "<br>"
                + "WebService Rest Full Funcionarios"
                + "<br>"
                + "<br>"
                + "Recursos em /funcionarios";
    }


}
