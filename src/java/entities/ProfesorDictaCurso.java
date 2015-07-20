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
@Table(name = "profesor_dicta_curso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProfesorDictaCurso.findAll", query = "SELECT p FROM ProfesorDictaCurso p"),
    @NamedQuery(name = "ProfesorDictaCurso.findById", query = "SELECT p FROM ProfesorDictaCurso p WHERE p.id = :id"),
    @NamedQuery(name = "ProfesorDictaCurso.findByProfesoridProfesor", query = "SELECT p FROM ProfesorDictaCurso p WHERE p.profesoridProfesor = :profesoridProfesor"),
    @NamedQuery(name = "ProfesorDictaCurso.findByCursoId", query = "SELECT p FROM ProfesorDictaCurso p WHERE p.cursoId = :cursoId")})
public class ProfesorDictaCurso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "profesor_idProfesor")
    private int profesoridProfesor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "curso_id")
    private int cursoId;

    public ProfesorDictaCurso() {
    }

    public ProfesorDictaCurso(Integer id) {
        this.id = id;
    }

    public ProfesorDictaCurso(Integer id, int profesoridProfesor, int cursoId) {
        this.id = id;
        this.profesoridProfesor = profesoridProfesor;
        this.cursoId = cursoId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getProfesoridProfesor() {
        return profesoridProfesor;
    }

    public void setProfesoridProfesor(int profesoridProfesor) {
        this.profesoridProfesor = profesoridProfesor;
    }

    public int getCursoId() {
        return cursoId;
    }

    public void setCursoId(int cursoId) {
        this.cursoId = cursoId;
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
        if (!(object instanceof ProfesorDictaCurso)) {
            return false;
        }
        ProfesorDictaCurso other = (ProfesorDictaCurso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ProfesorDictaCurso[ id=" + id + " ]";
    }
    
}
