/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fwtj.springbootrestfuncionarios.recursos;

import br.com.fwtj.springbootrestfuncionarios.dominio.Falta;
import br.com.fwtj.springbootrestfuncionarios.dominio.Funcionario;
import br.com.fwtj.springbootrestfuncionarios.servico.FuncionarioServico;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author Junior
 */
@RestController
@RequestMapping("/funcionarios")
public class FuncionariosRecurso {

    @Autowired
    FuncionarioServico funcionarioServico;
    
    @RequestMapping(method = RequestMethod.GET, 
            produces = 
                    {
                        MediaType.APPLICATION_JSON_VALUE,
                        MediaType.APPLICATION_XML_VALUE,
                        "application/x-yaml"
                    })
    public ResponseEntity<List<Funcionario>> listar() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Usuario Logado : " + auth.getName());
        return ResponseEntity.ok().body(funcionarioServico.todos());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Funcionario> salvar(@Valid @RequestBody Funcionario funcionario) {
        funcionario = funcionarioServico.salvar(funcionario);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(funcionario.getId().toString()).toUri();
        return ResponseEntity.created(uri).header("Resposta", funcionario.getRespostaAPI()).body(funcionario);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> buscar(@PathVariable Long id) {
        Funcionario funcionario = funcionarioServico.buscar(id);
        
        CacheControl cacheControl = CacheControl.maxAge(60, TimeUnit.SECONDS);
        
        return ResponseEntity.ok().cacheControl(cacheControl).body(funcionario);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        String resposta = funcionarioServico.deletar(id);
        return ResponseEntity.accepted().header("Resposta", resposta).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> atualizar(@PathVariable Long id,@Valid @RequestBody Funcionario funcionario) {
        funcionario.setId(id);
        funcionario = funcionarioServico.atualizar(funcionario);
        return ResponseEntity.accepted().header("Resposta", funcionario.getRespostaAPI()).build();
    }
    
    @RequestMapping(value = "/{idFuncionario}/faltas", method = RequestMethod.GET)
    public ResponseEntity<List<Falta>> listarFalta(@PathVariable Long idFuncionario){
        List<Falta> faltas = funcionarioServico.faltas(idFuncionario);
        return ResponseEntity.ok().body(faltas);
    }
    
    @RequestMapping(value = "/{idFuncionario}/faltas", method = RequestMethod.POST)
    public ResponseEntity<Void> salvarFalta(@PathVariable Long idFuncionario,@Valid @RequestBody Falta falta){
        falta = funcionarioServico.salvarFalta(idFuncionario, falta);
        
        Map<String, String> mapaUri = new HashMap<String, String>();
        mapaUri.put("idFalta", falta.getId().toString());
        
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idFalta}").buildAndExpand(mapaUri).toUri();
        
        return ResponseEntity.created(uri).header("Resposta", falta.getRespostaAPI()).build();
    }
    
    @RequestMapping(value = "/{idFuncionario}/faltas/{idFalta}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletarFalta(@PathVariable Long idFuncionario,@PathVariable String idFalta){
        String resposta = funcionarioServico.deletarFalta(idFuncionario, idFalta);
        return ResponseEntity.accepted().header("Resposta", resposta).build();
    }
    
    @RequestMapping(value = "/{idFuncionario}/faltas/{idFalta}", method = RequestMethod.PUT)
    public ResponseEntity<Void> atualizarFalta(@PathVariable Long idFuncionario,@PathVariable Long idFalta,@Valid @RequestBody Falta falta){
        falta = funcionarioServico.atualizarFalta(idFuncionario, idFalta, falta);
        return ResponseEntity.accepted().header("Resposta", falta.getRespostaAPI()).build();
    }

}
