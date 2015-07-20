/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesions;

import entities.Hora;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Usuario
 */
@Local
public interface HoraFacadeLocal {

    void create(Hora hora);

    void edit(Hora hora);

    void remove(Hora hora);

    Hora find(Object id);

    List<Hora> findAll();

    List<Hora> findRange(int[] range);

    int count();
    
}
