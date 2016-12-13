/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fwtj.client.bean;

import br.com.fwtj.client.model.Funcionario;
import br.com.fwtj.client.service.FuncionariosClientService;
import br.com.fwtj.client.util.jsf.FacesUtil;
import br.com.fwtj.client.util.jsf.FacesViewScope;
import br.com.fwtj.client.util.jsf.SessionUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Junior
 */
@Controller
//@Scope("view") // substitui @ViewScoped
//@Scope(value = "request")
@Scope(FacesViewScope.NAME)
public class FuncionarioClientBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String TIPOAUTENTICACAO = "Basic ";
    private static final String USUARIO = "fwtjsistemas";
    private static final String SEPARADOR = ":";
    private static final String SENHA = "fwtjsistemas";
    

    @Autowired
    FuncionariosClientService funcionariosClientService;

    @Autowired
    Funcionario funcionarioEdicao;

    List<Funcionario> lista = new ArrayList<>();
    
    public String URL = "http://localhost:8081";

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

    public List<Funcionario> getLista() {
        return lista;
    }

    public void setLista(List<Funcionario> lista) {
        this.lista = lista;
    }

    public FuncionarioClientBean() {
        Object param = SessionUtil.getParam("url");
        if (param  == null) {
            SessionUtil.setParam("url", URL);
        }else{
            URL = param.toString();
        }
        
    }
    
    public void atualizarUrl(){
        SessionUtil.setParam("url", URL);
        listar();
        
    }
    
    public void listar() {
        lista = funcionariosClientService.lista(TIPOAUTENTICACAO, USUARIO, SEPARADOR, SENHA, SessionUtil.getParam("url").toString());
    }

    public void salvar() {
        if (funcionarioEdicao.getId() == null) {
            funcionarioEdicao = funcionariosClientService.salvar(TIPOAUTENTICACAO, USUARIO, SEPARADOR, SENHA, URL, funcionarioEdicao);
            System.out.println("Salvo em " + funcionarioEdicao.getUri());
            FacesUtil.addErrorMessage("Salvo em " + funcionarioEdicao.getUri());
            funcionarioEdicao = new Funcionario();
            listar();
        } else {
            String mensagem = funcionariosClientService.atualizar(TIPOAUTENTICACAO, USUARIO, SEPARADOR, SENHA, URL, funcionarioEdicao.getId().toString(), funcionarioEdicao);
            System.out.println(mensagem);
            FacesUtil.addErrorMessage(mensagem);
            funcionarioEdicao = new Funcionario();
            listar();
        }

    }

    public void deletar(String id) {
        String mensagem = funcionariosClientService.deletar(TIPOAUTENTICACAO, USUARIO, SEPARADOR, SENHA, URL, id);
        listar();
        System.out.println(mensagem);
        FacesUtil.addErrorMessage(mensagem);
    }

    public void editar(String id) {
        Funcionario buscar = funcionariosClientService.buscar(TIPOAUTENTICACAO, USUARIO, SEPARADOR, SENHA, URL, id);
        funcionarioEdicao = buscar;
    }

    public void inico() {
        funcionarioEdicao = new Funcionario();
        listar();
    }

}
