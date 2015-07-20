/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesions;

import entities.ProfesorDictaCurso;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Usuario
 */
@Local
public interface ProfesorDictaCursoFacadeLocal {

    void create(ProfesorDictaCurso profesorDictaCurso);

    void edit(ProfesorDictaCurso profesorDictaCurso);

    void remove(ProfesorDictaCurso profesorDictaCurso);

    ProfesorDictaCurso find(Object id);

    List<ProfesorDictaCurso> findAll();

    List<ProfesorDictaCurso> findRange(int[] range);

    int count();
    
}
