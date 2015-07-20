/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "ano_escolar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AnoEscolar.findAll", query = "SELECT a FROM AnoEscolar a"),
    @NamedQuery(name = "AnoEscolar.findByAno", query = "SELECT a FROM AnoEscolar a WHERE a.ano = :ano")})
public class AnoEscolar implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ano")
    private Integer ano;

    public AnoEscolar() {
    }

    public AnoEscolar(Integer ano) {
        this.ano = ano;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ano != null ? ano.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AnoEscolar)) {
            return false;
        }
        AnoEscolar other = (AnoEscolar) object;
        if ((this.ano == null && other.ano != null) || (this.ano != null && !this.ano.equals(other.ano))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.AnoEscolar[ ano=" + ano + " ]";
    }
    
}
