package br.com.fwtj.client.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Component;


/**
 *
 * @author Junior
 */
@Component
public class Funcionario implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataNascimento;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataContratacao;
    private String telefone;
    private String setor;
    private List<Falta> faltas;
    private String Uri;

    public Funcionario() {
    }

    public Funcionario(String nome, Date dataNascimento, Date DataContratacao, String setor) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.dataContratacao = DataContratacao;
        this.setor = setor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Date getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(Date dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public List<Falta> getFaltas() {
        return faltas;
    }

    public void setFaltas(List<Falta> faltas) {
        this.faltas = faltas;
    }

    public String getUri() {
        return Uri;
    }

    public void setUri(String Uri) {
        this.Uri = Uri;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Funcionario other = (Funcionario) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Funcionario{" + "id=" + id + ", nome=" + nome + ", setor=" + setor + '}';
    }

}
