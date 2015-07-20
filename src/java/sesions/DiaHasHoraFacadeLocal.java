/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesions;

import entities.DiaHasHora;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Usuario
 */
@Local
public interface DiaHasHoraFacadeLocal {

    void create(DiaHasHora diaHasHora);

    void edit(DiaHasHora diaHasHora);

    void remove(DiaHasHora diaHasHora);

    DiaHasHora find(Object id);

    List<DiaHasHora> findAll();

    List<DiaHasHora> findRange(int[] range);

    int count();
    
}
