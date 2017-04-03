/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fwtj.clientmvc.controle;

import br.com.fwtj.clientmvc.modelo.Pessoa;
import br.com.fwtj.clientmvc.repositorio.Pessoas;
import br.com.fwtj.clientmvc.servico.PessoaServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Junior
 */
// INFORMA QUE ESSA CLASSE E UM CONTROLADOR DE ENDERECOS
@Controller
// MAPEA ESSE ENDERECO PARA ESSA FUNCAO PARA TODA CLASSE
@RequestMapping("/pessoa")
public class PessoaControle {
    
    //INJETA DEPENDENCIA
    @Autowired
    PessoaServico pessoaServico;

    //QUANDO NAO TEMOS NADA DENTRO DO () USA O MAPEAMENTO DA CLASSE
    @RequestMapping
    public ModelAndView nova() {
        ModelAndView mv = new ModelAndView("pessoa");
        mv.addObject("pessoa", new Pessoa());
        return mv;
    }
    
    // USANDO O MAPEAMENTO DA CLASSE COM METODO POST
    @RequestMapping(method = RequestMethod.POST)
    // VALIDA AS ANOTACOES NO MODELO, REPASSA O ERRO PARA A VIEW E ATIBUTOS
    public String salvar(@Validated Pessoa pessoa, Errors errors, RedirectAttributes attributes) {
        // CASO TENHA ERRO NO MODELO, RERONA O MESMO OBJETO PARA VIEW
        if (errors.hasErrors()) {
            return "pessoa";
        }
            //SALVA PESSOA NO BANCO
            pessoaServico.save(pessoa);
            // ENVIA O ATIBUTO DA MENSAGEM, QUE USA O MensagemGeral.html QUE FOI INSERIDO NA PAGINA
            attributes.addFlashAttribute("mensagem", "Pessoa salva com sucesso!");
            // REDIRECIONA PARA O ENDERECO, CAINDO NO METODO nova()
            return "redirect:/pessoa";
    }

    // MAPERA O ENDERECO DA CLASSE, MAIS O CAMPO ID FICANDO /PESSOA/1 POR EXEMPLO.
    // COMO NAO TEMOS O RequestMethod, POR PADRAO SERA GET
    // COM ISSO CAI NESSE METODO QUE BUSCA A PESSOA NO BANCO E DEVOLVE PARA VIEW
    @RequestMapping("{id}")
    public ModelAndView edicao(@PathVariable("id") Long id) {
        Pessoa findOne = pessoaServico.findOne(id);
        ModelAndView mv = new ModelAndView("pessoa");
        mv.addObject(findOne);
        return mv;
    }

    // MAPERA O ENDERECO DA CLASSE, MAIS O CAMPO ID FICANDO /PESSOA/1 POR EXEMPLO.
    // COMO TEMOS O RequestMethod, ELE FILTRA E CHAMA O METODO excluir
    // QUE BUSCA O REGISTRO PARA TER O NOME PARA A MENSAGEM E
    // EXCLUIR O MESMO
    // E REDIRECIONA COPARA O INICIO EXIBINDO A LISTA
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public String excluir(@PathVariable Long id, RedirectAttributes attributes) {
        Pessoa findOne = pessoaServico.findOne(id);
        pessoaServico.delete(id);
        attributes.addFlashAttribute("mensagem", "Pessoa, " + findOne.getNome() + ", excluída com sucesso!");
        return "redirect:/inicio";
    }

}
