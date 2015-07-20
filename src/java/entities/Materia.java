/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "materia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Materia.findAll", query = "SELECT m FROM Materia m"),
    @NamedQuery(name = "Materia.findByIdMateria", query = "SELECT m FROM Materia m WHERE m.materiaPK.idMateria = :idMateria"),
    @NamedQuery(name = "Materia.findByNombreMateria", query = "SELECT m FROM Materia m WHERE m.nombreMateria = :nombreMateria"),
    @NamedQuery(name = "Materia.findByGradoidGrado", query = "SELECT m FROM Materia m WHERE m.materiaPK.gradoidGrado = :gradoidGrado")})
public class Materia implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MateriaPK materiaPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombreMateria")
    private String nombreMateria;

    public Materia() {
    }

    public Materia(MateriaPK materiaPK) {
        this.materiaPK = materiaPK;
    }

    public Materia(MateriaPK materiaPK, String nombreMateria) {
        this.materiaPK = materiaPK;
        this.nombreMateria = nombreMateria;
    }

    public Materia(int idMateria, int gradoidGrado) {
        this.materiaPK = new MateriaPK(idMateria, gradoidGrado);
    }

    public MateriaPK getMateriaPK() {
        return materiaPK;
    }

    public void setMateriaPK(MateriaPK materiaPK) {
        this.materiaPK = materiaPK;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (materiaPK != null ? materiaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Materia)) {
            return false;
        }
        Materia other = (Materia) object;
        if ((this.materiaPK == null && other.materiaPK != null) || (this.materiaPK != null && !this.materiaPK.equals(other.materiaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Materia[ materiaPK=" + materiaPK + " ]";
    }
    
}
