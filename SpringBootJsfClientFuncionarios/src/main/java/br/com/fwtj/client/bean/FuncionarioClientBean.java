/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fwtj.client.bean;

import br.com.fwtj.client.model.Falta;
import br.com.fwtj.client.model.Funcionario;
import br.com.fwtj.client.service.FuncionariosClientService;
import br.com.fwtj.client.util.jsf.FacesUtil;
import br.com.fwtj.client.util.jsf.FacesViewScope;
import br.com.fwtj.client.util.jsf.SessionUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.component.commandbutton.CommandButton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Junior
 */
@Controller
@Scope(FacesViewScope.NAME)
public class FuncionarioClientBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String TIPOAUTENTICACAO = "Basic ";
    private static final String USUARIO = "fwtjsistemas";
    private static final String SEPARADOR = ":";
    private static final String SENHA = "fwtjsistemas";

    private CommandButton botaoInserirFalta = new CommandButton();

    @Autowired
    FuncionariosClientService funcionariosClientService;

    @Autowired
    Funcionario funcionarioEdicao;

    @Autowired
    Falta faltaEdicao;

    List<Funcionario> lista = new ArrayList<>();
    List<Falta> listaFaltas = new ArrayList<>();

    public String URL = "http://localhost:8080";

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public Funcionario getFuncionarioEdicao() {
        return funcionarioEdicao;
    }

    public void setFuncionarioEdicao(Funcionario funcionarioEdicao) {
        this.funcionarioEdicao = funcionarioEdicao;
    }

    public Falta getFaltaEdicao() {
        return faltaEdicao;
    }

    public void setFaltaEdicao(Falta faltaEdicao) {
        this.faltaEdicao = faltaEdicao;
    }

    public List<Funcionario> getLista() {
        return lista;
    }

    public void setLista(List<Funcionario> lista) {
        this.lista = lista;
    }

    public List<Falta> getListaFaltas() {
        return listaFaltas;
    }

    public void setListaFaltas(List<Falta> listaFaltas) {
        this.listaFaltas = listaFaltas;
    }

    public CommandButton getBotaoInserirFalta() {
        return botaoInserirFalta;
    }

    public void setBotaoInserirFalta(CommandButton botaoInserirFalta) {
        this.botaoInserirFalta = botaoInserirFalta;
    }

    public FuncionarioClientBean() {
        Object param = SessionUtil.getParam("url");
        if (param == null) {
            SessionUtil.setParam("url", URL);
        } else {
            URL = param.toString();
        }

    }

    public void inicioLista() {
        listar();
    }

    public void inicioCadastro() {
        Funcionario funcionarioASerEditado = (Funcionario) SessionUtil.getParam("funcionarioasereditado");
        if (funcionarioASerEditado == null) {
            funcionarioEdicao = new Funcionario();
            botaoInserirFalta.setDisabled(true);
        } else {
            funcionarioEdicao = funcionarioASerEditado;
            List<Falta> faltas = funcionarioASerEditado.getFaltas();
            if (faltas != null) {
                boolean addAll = listaFaltas.addAll(faltas);
            }
        }
    }

    public void inicioConfiguracao() {

    }

    public void atualizarUrl() {
        SessionUtil.setParam("url", URL);
        listar();
    }

    public void listar() {
        lista = funcionariosClientService.lista(TIPOAUTENTICACAO, USUARIO, SEPARADOR, SENHA, SessionUtil.getParam("url").toString());
    }

    public void novo() {
        funcionarioEdicao = new Funcionario();
        SessionUtil.setParam("funcionarioasereditado", null);
        botaoInserirFalta.setDisabled(true);
        listaFaltas = new ArrayList<>();
    }
    
    public void novaFalta() {
        faltaEdicao = new Falta();
    }

    public void salvar() {
        if (funcionarioEdicao.getId() == null) {
            funcionarioEdicao = funcionariosClientService.salvar(TIPOAUTENTICACAO, USUARIO, SEPARADOR, SENHA, URL, funcionarioEdicao);
            System.out.println("Salvo em " + funcionarioEdicao.getUri());
            FacesUtil.addErrorMessage("Salvo em " + funcionarioEdicao.getUri());
            funcionarioEdicao = new Funcionario();
            SessionUtil.setParam("funcionarioasereditado", null);
            botaoInserirFalta.setDisabled(false);
            listar();
        } else {
            String mensagem = funcionariosClientService.atualizar(TIPOAUTENTICACAO, USUARIO, SEPARADOR, SENHA, URL, funcionarioEdicao.getId().toString(), funcionarioEdicao);
            System.out.println(mensagem);
            FacesUtil.addErrorMessage(mensagem);
            funcionarioEdicao = new Funcionario();
            SessionUtil.setParam("funcionarioasereditado", null);
            botaoInserirFalta.setDisabled(false);
            listar();
        }
    }

    public void inserirFalta() {
        Falta salvarFalta = funcionariosClientService.salvarFalta(TIPOAUTENTICACAO, USUARIO, SEPARADOR, SENHA, URL, funcionarioEdicao, faltaEdicao);
        System.out.println("Salvo em " + salvarFalta.getUri());
        FacesUtil.addErrorMessage("Salvo em " + salvarFalta.getUri());
        faltaEdicao = new Falta();
        funcionarioEdicao = funcionariosClientService.buscar(TIPOAUTENTICACAO, USUARIO, SEPARADOR, SENHA, URL, funcionarioEdicao.getId().toString());
        SessionUtil.setParam("funcionarioasereditado", funcionarioEdicao);
        listaFaltas = funcionarioEdicao.getFaltas();
    }

    public void deletar(String id) {
        String mensagem = funcionariosClientService.deletar(TIPOAUTENTICACAO, USUARIO, SEPARADOR, SENHA, URL, id);
        listar();
        System.out.println(mensagem);
        FacesUtil.addErrorMessage(mensagem);
    }

    public void deletarFalta(Falta falta) {
        String mensagem = funcionariosClientService.deletarFalta(TIPOAUTENTICACAO, USUARIO, SEPARADOR, SENHA, URL, funcionarioEdicao.getId().toString(), falta.getId().toString());
        listaFaltas.remove(falta);
        funcionarioEdicao.setFaltas(listaFaltas);
        System.out.println(mensagem);
        FacesUtil.addErrorMessage(mensagem);
    }

    public String editar(String id) {
        Funcionario buscar = funcionariosClientService.buscar(TIPOAUTENTICACAO, USUARIO, SEPARADOR, SENHA, URL, id);
        SessionUtil.setParam("funcionarioasereditado", buscar);
        return "cadastro?faces-redirect=true";
    }

}
