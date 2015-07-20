/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Usuario
 */
@Embeddable
public class MateriaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idMateria")
    private int idMateria;
    @Basic(optional = false)
    @NotNull
    @Column(name = "grado_idGrado")
    private int gradoidGrado;

    public MateriaPK() {
    }

    public MateriaPK(int idMateria, int gradoidGrado) {
        this.idMateria = idMateria;
        this.gradoidGrado = gradoidGrado;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public int getGradoidGrado() {
        return gradoidGrado;
    }

    public void setGradoidGrado(int gradoidGrado) {
        this.gradoidGrado = gradoidGrado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idMateria;
        hash += (int) gradoidGrado;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MateriaPK)) {
            return false;
        }
        MateriaPK other = (MateriaPK) object;
        if (this.idMateria != other.idMateria) {
            return false;
        }
        if (this.gradoidGrado != other.gradoidGrado) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MateriaPK[ idMateria=" + idMateria + ", gradoidGrado=" + gradoidGrado + " ]";
    }
    
}
