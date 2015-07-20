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
@Table(name = "curso_has_dia_has_hora")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CursoHasDiaHasHora.findAll", query = "SELECT c FROM CursoHasDiaHasHora c"),
    @NamedQuery(name = "CursoHasDiaHasHora.findById", query = "SELECT c FROM CursoHasDiaHasHora c WHERE c.id = :id"),
    @NamedQuery(name = "CursoHasDiaHasHora.findByCursoID", query = "SELECT c FROM CursoHasDiaHasHora c WHERE c.cursoID = :cursoID"),
    @NamedQuery(name = "CursoHasDiaHasHora.findByDiaHasHoraId", query = "SELECT c FROM CursoHasDiaHasHora c WHERE c.diaHasHoraId = :diaHasHoraId")})
public class CursoHasDiaHasHora implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "curso_ID")
    private int cursoID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dia_has_hora_id")
    private int diaHasHoraId;

    public CursoHasDiaHasHora() {
    }

    public CursoHasDiaHasHora(Integer id) {
        this.id = id;
    }

    public CursoHasDiaHasHora(Integer id, int cursoID, int diaHasHoraId) {
        this.id = id;
        this.cursoID = cursoID;
        this.diaHasHoraId = diaHasHoraId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCursoID() {
        return cursoID;
    }

    public void setCursoID(int cursoID) {
        this.cursoID = cursoID;
    }

    public int getDiaHasHoraId() {
        return diaHasHoraId;
    }

    public void setDiaHasHoraId(int diaHasHoraId) {
        this.diaHasHoraId = diaHasHoraId;
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
        if (!(object instanceof CursoHasDiaHasHora)) {
            return false;
        }
        CursoHasDiaHasHora other = (CursoHasDiaHasHora) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CursoHasDiaHasHora[ id=" + id + " ]";
    }
    
}
