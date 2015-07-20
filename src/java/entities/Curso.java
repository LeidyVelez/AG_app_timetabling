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
@Table(name = "curso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Curso.findAll", query = "SELECT c FROM Curso c"),
    @NamedQuery(name = "Curso.findById", query = "SELECT c FROM Curso c WHERE c.id = :id"),
    @NamedQuery(name = "Curso.findByMateriaidMateria", query = "SELECT c FROM Curso c WHERE c.materiaidMateria = :materiaidMateria"),
    @NamedQuery(name = "Curso.findByCodGrado", query = "SELECT c FROM Curso c WHERE c.codGrado = :codGrado"),
    @NamedQuery(name = "Curso.findByPeso", query = "SELECT c FROM Curso c WHERE c.peso = :peso"),
    @NamedQuery(name = "Curso.findByIntensidad", query = "SELECT c FROM Curso c WHERE c.intensidad = :intensidad"),
    @NamedQuery(name = "Curso.findByAnoEscolarAno", query = "SELECT c FROM Curso c WHERE c.anoEscolarAno = :anoEscolarAno")})
public class Curso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Materia_idMateria")
    private int materiaidMateria;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_grado")
    private int codGrado;
    @Column(name = "peso")
    private Integer peso;
    @Column(name = "intensidad")
    private Integer intensidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ano_escolar_ano")
    private int anoEscolarAno;

    public Curso() {
    }

    public Curso(Integer id) {
        this.id = id;
    }

    public Curso(Integer id, int materiaidMateria, int codGrado, int anoEscolarAno) {
        this.id = id;
        this.materiaidMateria = materiaidMateria;
        this.codGrado = codGrado;
        this.anoEscolarAno = anoEscolarAno;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMateriaidMateria() {
        return materiaidMateria;
    }

    public void setMateriaidMateria(int materiaidMateria) {
        this.materiaidMateria = materiaidMateria;
    }

    public int getCodGrado() {
        return codGrado;
    }

    public void setCodGrado(int codGrado) {
        this.codGrado = codGrado;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public Integer getIntensidad() {
        return intensidad;
    }

    public void setIntensidad(Integer intensidad) {
        this.intensidad = intensidad;
    }

    public int getAnoEscolarAno() {
        return anoEscolarAno;
    }

    public void setAnoEscolarAno(int anoEscolarAno) {
        this.anoEscolarAno = anoEscolarAno;
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
        if (!(object instanceof Curso)) {
            return false;
        }
        Curso other = (Curso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Curso[ id=" + id + " ]";
    }
    
}
