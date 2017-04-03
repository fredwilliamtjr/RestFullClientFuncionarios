/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fwtj.clientmvc.servico;

import br.com.fwtj.clientmvc.modelo.Pessoa;
import br.com.fwtj.clientmvc.repositorio.Pessoas;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Junior
 */
// CLASSE DE SERVICO QUE FAZ A PONTE ENTRE O REPOSITORIO E O CONTROLE
// PODERIAMOS CHAMAR O REPOSITORIO DE DENTRO DO CONTROLE, MAIS NAO E COSIDERADO BOA PRATICA.
@Service
public class PessoaServico {
    
    @Autowired
    private Pessoas pessoas;
    
    public List<Pessoa> findAll(){
        return pessoas.findAll();
    }

    public Pessoa save(Pessoa pessoa) {
        return pessoas.save(pessoa);
    }

    public Pessoa findOne(Long id) {
        return pessoas.findOne(id);
    }

    public void delete(Long id) {
        pessoas.delete(id);
    }
    
}
