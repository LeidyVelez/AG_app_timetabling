/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesions;

import entities.Dia;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Usuario
 */
@Local
public interface DiaFacadeLocal {

    void create(Dia dia);

    void edit(Dia dia);

    void remove(Dia dia);

    Dia find(Object id);

    List<Dia> findAll();

    List<Dia> findRange(int[] range);

    int count();
    
}
