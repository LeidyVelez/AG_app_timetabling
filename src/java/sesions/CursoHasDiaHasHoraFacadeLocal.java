/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesions;

import entities.CursoHasDiaHasHora;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Usuario
 */
@Local
public interface CursoHasDiaHasHoraFacadeLocal {

    void create(CursoHasDiaHasHora cursoHasDiaHasHora);

    void edit(CursoHasDiaHasHora cursoHasDiaHasHora);

    void remove(CursoHasDiaHasHora cursoHasDiaHasHora);

    CursoHasDiaHasHora find(Object id);

    List<CursoHasDiaHasHora> findAll();

    List<CursoHasDiaHasHora> findRange(int[] range);

    int count();
    
}
