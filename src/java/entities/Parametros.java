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
@Table(name = "parametros")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parametros.findAll", query = "SELECT p FROM Parametros p"),
    @NamedQuery(name = "Parametros.findById", query = "SELECT p FROM Parametros p WHERE p.id = :id"),
    @NamedQuery(name = "Parametros.findByNumeroEras", query = "SELECT p FROM Parametros p WHERE p.numeroEras = :numeroEras"),
    @NamedQuery(name = "Parametros.findByNumeroPoblacion", query = "SELECT p FROM Parametros p WHERE p.numeroPoblacion = :numeroPoblacion"),
    @NamedQuery(name = "Parametros.findByVaueFitnessfixed", query = "SELECT p FROM Parametros p WHERE p.vaueFitnessfixed = :vaueFitnessfixed"),
    @NamedQuery(name = "Parametros.findByVaueFitnesshard", query = "SELECT p FROM Parametros p WHERE p.vaueFitnesshard = :vaueFitnesshard"),
    @NamedQuery(name = "Parametros.findByVaueFitnesssoft", query = "SELECT p FROM Parametros p WHERE p.vaueFitnesssoft = :vaueFitnesssoft")})
public class Parametros implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numeroEras")
    private int numeroEras;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numeroPoblacion")
    private int numeroPoblacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vaue_Fitness_fixed")
    private int vaueFitnessfixed;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vaue_Fitness_hard")
    private int vaueFitnesshard;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vaue_Fitness_soft")
    private int vaueFitnesssoft;

    public Parametros() {
    }

    public Parametros(Integer id) {
        this.id = id;
    }

    public Parametros(Integer id, int numeroEras, int numeroPoblacion, int vaueFitnessfixed, int vaueFitnesshard, int vaueFitnesssoft) {
        this.id = id;
        this.numeroEras = numeroEras;
        this.numeroPoblacion = numeroPoblacion;
        this.vaueFitnessfixed = vaueFitnessfixed;
        this.vaueFitnesshard = vaueFitnesshard;
        this.vaueFitnesssoft = vaueFitnesssoft;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNumeroEras() {
        return numeroEras;
    }

    public void setNumeroEras(int numeroEras) {
        this.numeroEras = numeroEras;
    }

    public int getNumeroPoblacion() {
        return numeroPoblacion;
    }

    public void setNumeroPoblacion(int numeroPoblacion) {
        this.numeroPoblacion = numeroPoblacion;
    }

    public int getVaueFitnessfixed() {
        return vaueFitnessfixed;
    }

    public void setVaueFitnessfixed(int vaueFitnessfixed) {
        this.vaueFitnessfixed = vaueFitnessfixed;
    }

    public int getVaueFitnesshard() {
        return vaueFitnesshard;
    }

    public void setVaueFitnesshard(int vaueFitnesshard) {
        this.vaueFitnesshard = vaueFitnesshard;
    }

    public int getVaueFitnesssoft() {
        return vaueFitnesssoft;
    }

    public void setVaueFitnesssoft(int vaueFitnesssoft) {
        this.vaueFitnesssoft = vaueFitnesssoft;
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
        if (!(object instanceof Parametros)) {
            return false;
        }
        Parametros other = (Parametros) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Parametros[ id=" + id + " ]";
    }
    
}
