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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "dia_has_hora")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DiaHasHora.findAll", query = "SELECT d FROM DiaHasHora d"),
    @NamedQuery(name = "DiaHasHora.findById", query = "SELECT d FROM DiaHasHora d WHERE d.id = :id"),
    @NamedQuery(name = "DiaHasHora.findByDiaId", query = "SELECT d FROM DiaHasHora d WHERE d.diaId = :diaId"),
    @NamedQuery(name = "DiaHasHora.findByHoraId", query = "SELECT d FROM DiaHasHora d WHERE d.horaId = :horaId")})
public class DiaHasHora implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dia_id")
    private int diaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hora_id")
    private int horaId;

    public DiaHasHora() {
    }

    public DiaHasHora(Integer id) {
        this.id = id;
    }

    public DiaHasHora(Integer id, int diaId, int horaId) {
        this.id = id;
        this.diaId = diaId;
        this.horaId = horaId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getDiaId() {
        return diaId;
    }

    public void setDiaId(int diaId) {
        this.diaId = diaId;
    }

    public int getHoraId() {
        return horaId;
    }

    public void setHoraId(int horaId) {
        this.horaId = horaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DiaHasHora)) {
            return false;
        }
        DiaHasHora other = (DiaHasHora) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.DiaHasHora[ id=" + id + " ]";
    }
    
}
