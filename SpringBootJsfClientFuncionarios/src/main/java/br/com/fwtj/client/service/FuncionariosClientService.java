package br.com.fwtj.client.service;

import br.com.fwtj.client.model.Falta;
import br.com.fwtj.client.model.Funcionario;
import br.com.fwtj.client.util.ConverterBase64;
import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Junior
 */
@Service
public class FuncionariosClientService implements Serializable {

    private static final long serialVersionUID = 1L;

    private static RestTemplate restTemplate = new RestTemplate();

    public List<Funcionario> lista(String tipoAutenticacao, String usuario, String separador, String senha, String url) {
        RequestEntity<Void> requestEntity = RequestEntity
                .get(URI.create(url + "/funcionarios"))
                .header("Authorization", tipoAutenticacao + ConverterBase64.encodarBase64(usuario + separador + senha))
                .build();
        ResponseEntity<Funcionario[]> responseEntity = null;
        try {
            responseEntity = restTemplate.exchange(requestEntity, Funcionario[].class);
        } catch (RestClientException | IllegalArgumentException restClientException) {
            List<Funcionario> lista2 = new ArrayList<>();
            return lista2;
        }
        return Arrays.asList(responseEntity.getBody());
    }

    public Funcionario salvar(String tipoAutenticacao, String usuario, String separador, String senha, String url, Funcionario funcionario) {
        RequestEntity<Funcionario> requestEntity = RequestEntity
                .post(URI.create(url + "/funcionarios"))
                .header("Authorization", tipoAutenticacao + ConverterBase64.encodarBase64(usuario + separador + senha))
                .body(funcionario);
        ResponseEntity<Funcionario> responseEntity = restTemplate.exchange(requestEntity, Funcionario.class);
        funcionario = responseEntity.getBody();
        funcionario.setUri(responseEntity.getHeaders().getLocation().toString());
        return funcionario;
    }
    
    public Falta salvarFalta(String tipoAutenticacao, String usuario, String separador, String senha, String url, Funcionario funcionario, Falta falta) {
        RequestEntity<Falta> requestEntity = RequestEntity
                .post(URI.create(url + "/funcionarios/" + funcionario.getId().toString() + "/faltas"))
                .header("Authorization", tipoAutenticacao + ConverterBase64.encodarBase64(usuario + separador + senha)).body(falta);
        ResponseEntity<Falta> responseEntity = restTemplate.exchange(requestEntity, Falta.class);
        falta = responseEntity.getBody();
        falta.setUri(responseEntity.getHeaders().getLocation().toString());
        return falta;

    }

    public String atualizar(String tipoAutenticacao, String usuario, String separador, String senha, String url, String id, Funcionario funcionario) {
        RequestEntity<Funcionario> requestEntity = RequestEntity
                .put(URI.create(url + "/funcionarios/" + id))
                .header("Authorization", tipoAutenticacao + ConverterBase64.encodarBase64(usuario + separador + senha)).body(funcionario);
        ResponseEntity<Void> responseEntity = restTemplate.exchange(requestEntity, Void.class);
        String resposta = responseEntity.getHeaders().get("Resposta").get(0);
        return resposta;

    }

    public Funcionario buscar(String tipoAutenticacao, String usuario, String separador, String senha, String url, String id) {
        RequestEntity<Void> requestEntity = RequestEntity
                .get(URI.create(url + "/funcionarios/" + id))
                .header("Authorization", tipoAutenticacao + ConverterBase64.encodarBase64(usuario + separador + senha)).build();
        ResponseEntity<Funcionario> responseEntity = restTemplate.exchange(requestEntity, Funcionario.class);
        Funcionario body = responseEntity.getBody();
        return body;
    }

    public String deletar(String tipoAutenticacao, String usuario, String separador, String senha, String url, String id) {
        RequestEntity<Void> requestEntity = RequestEntity
                .delete(URI.create(url + "/funcionarios/" + id))
                .header("Authorization", tipoAutenticacao + ConverterBase64.encodarBase64(usuario + separador + senha)).build();
        ResponseEntity<Void> exchange = restTemplate.exchange(requestEntity, Void.class);
        String resposta = exchange.getHeaders().get("Resposta").get(0);
        return resposta;
    }

    public String deletarFalta(String tipoAutenticacao, String usuario, String separador, String senha, String url, String idFuncionario, String idFalta) {
        RequestEntity<Void> requestEntity = RequestEntity
                .delete(URI.create(url + "/funcionarios/" + idFuncionario + "/faltas/" + idFalta))
                .header("Authorization", tipoAutenticacao + ConverterBase64.encodarBase64(usuario + separador + senha)).build();
        ResponseEntity<Void> exchange = restTemplate.exchange(requestEntity, Void.class);
        String resposta = exchange.getHeaders().get("Resposta").get(0);
        return resposta;
    }

}
