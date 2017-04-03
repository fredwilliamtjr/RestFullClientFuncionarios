/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fwtj.clientmvc.controle;

import br.com.fwtj.clientmvc.modelo.Pessoa;
import br.com.fwtj.clientmvc.servico.PessoaServico;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Junior
 */
// INFORMA QUE ESSA CLASSE E UM CONTROLADOR DE ENDERECOS
@Controller
public class InicioControle {
    
    //INJETA DEPENDENCIA
    @Autowired
    PessoaServico pessoaServico;

    
    // BUSCA A LISTA DE PESSOAS NO BANCO
    public List<Pessoa> listaPessoa() {
        return pessoaServico.findAll();
    }

    // MAPEA ESSE ENDERECO PARA ESSA FUNCAO
    @RequestMapping("/inicio")
    public ModelAndView index() {
        //CRIA UM MODELO DE VIEW, ONDE PODEMOS COLOCAR OBJETOS
        ModelAndView mv = new ModelAndView("inicio");
        //ADICIONA OBJETO LISTA DE PESSOAS NA VIEW
        mv.addObject("pessoas", listaPessoa());
        return mv;
    }

}
