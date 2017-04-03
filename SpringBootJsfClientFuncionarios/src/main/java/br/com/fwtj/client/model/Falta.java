package br.com.fwtj.client.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import org.springframework.stereotype.Component;


/**
 *
 * @author Junior
 */
@Component
public class Falta implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date data;
    private String motivo;
    private Funcionario funcionario;
    private String Uri;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String getUri() {
        return Uri;
    }

    public void setUri(String Uri) {
        this.Uri = Uri;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.id);
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
        final Falta other = (Falta) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "falta{" + "id=" + id + ", data=" + data + ", motivo=" + motivo + '}';
    }

}
