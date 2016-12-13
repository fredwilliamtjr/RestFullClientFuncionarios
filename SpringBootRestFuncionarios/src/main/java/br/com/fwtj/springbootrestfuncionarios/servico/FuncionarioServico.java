/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fwtj.springbootrestfuncionarios.servico;

import br.com.fwtj.springbootrestfuncionarios.dominio.Falta;
import br.com.fwtj.springbootrestfuncionarios.dominio.Funcionario;
import br.com.fwtj.springbootrestfuncionarios.repositorio.FaltasRepositorio;
import br.com.fwtj.springbootrestfuncionarios.servico.excecao.FuncionarioCamposObrigatoriosExcecao;
import br.com.fwtj.springbootrestfuncionarios.servico.excecao.FuncionarioNaoEncontradoExcecao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import br.com.fwtj.springbootrestfuncionarios.repositorio.FuncionariosRepositorio;
import br.com.fwtj.springbootrestfuncionarios.servico.excecao.FaltaInvalidaExcecao;

/**
 *
 * @author Junior
 */
@Service
public class FuncionarioServico {

    @Autowired
    FuncionariosRepositorio funcionarioRepositorio;

    @Autowired
    FaltasRepositorio faltasRepositorio;

    public List<Funcionario> todos() {
        return funcionarioRepositorio.findAll();
    }

    public Funcionario salvar(Funcionario funcionario) {
        funcionario.setId(null);
        if (funcionario.getNome() == null) {
            throw new FuncionarioCamposObrigatoriosExcecao("Atributo nome é obrigatório!");
        }
        funcionario = funcionarioRepositorio.save(funcionario);
        funcionario.setRespostaAPI("Funcionário salvo com sucesso!");
        return funcionario;
    }

    public Funcionario atualizar(Funcionario funcionario) {

        buscar(funcionario.getId());

        if (funcionario.getNome() == null) {
            throw new FuncionarioCamposObrigatoriosExcecao("Atributo nome é obrigatório!");
        }
        funcionario = funcionarioRepositorio.save(funcionario);
        funcionario.setRespostaAPI("Funcionário Id: " + funcionario.getId() + ", salvo com sucesso!");
        return funcionario;
    }

    public Funcionario buscar(Long id) {
        Funcionario funcionario = funcionarioRepositorio.findOne(id);
        if (funcionario == null) {
            throw new FuncionarioNaoEncontradoExcecao("Funcionário não encontrado com o ID : " + id);
        }
        return funcionario;
    }

    public String deletar(Long id) {
        Funcionario funcionario = buscar(id);
        funcionarioRepositorio.delete(funcionario);
        return "Funcionário Id : " + id + ", deletado com sucesso!";
    }

    public List<Falta> faltas(Long idFuncionario) {
        Funcionario funcionario = buscar(idFuncionario);
        return funcionario.getFaltas();
    }

    public Falta salvarFalta(Long idFuncionario, Falta falta) {
        Funcionario funcionario = buscar(idFuncionario);
        falta.setFuncionario(funcionario);
        falta = faltasRepositorio.save(falta);
        falta.setRespostaAPI("Falta salva com sucesso!");
        return falta;
    }

    public Falta buscarFalta(Long id) {
        Falta falta = faltasRepositorio.findOne(id);
        if (falta == null) {
            throw new FuncionarioNaoEncontradoExcecao("Falta não encontrado com o ID : " + id);
        }
        return falta;
    }

    public String deletarFalta(Long idFuncionario, String idFalta) {
        Funcionario funcionario = buscar(idFuncionario);

        if (idFalta.equals("todas")) {
            List<Falta> faltas = funcionario.getFaltas();
            for (Falta falta : faltas) {
                faltasRepositorio.delete(falta);
            }
            return "Todas as faltas do funcionario ID : " + idFuncionario + ", deletadas com sucesso!";
        }

        Long idFaltaLong = null;
        try {
            idFaltaLong = Long.valueOf(idFalta);
        } catch (NumberFormatException e) {
            throw new FaltaInvalidaExcecao("O ID da Falta na URI não é valido");
        }

        Falta falta = buscarFalta(idFaltaLong);
        faltasRepositorio.delete(falta);
        return "Falta Id : " + idFalta + ", deletada com sucesso!";
    }

    public Falta atualizarFalta(Long idFuncionario, Long idFalta, Falta falta) {
        Funcionario funcionario = buscar(idFuncionario);
        Falta buscarFalta = buscarFalta(idFalta);

        if (!buscarFalta.getFuncionario().equals(funcionario)) {
            throw new FaltaInvalidaExcecao("O ID da Falta na URI não corresponde para o ID do Funcionario na URI");
        }

        falta.setFuncionario(funcionario);
        falta.setId(idFalta);

        falta = faltasRepositorio.save(falta);
        falta.setRespostaAPI("Falta salva com sucesso!");
        return falta;
    }

}
