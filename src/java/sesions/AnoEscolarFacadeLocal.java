/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesions;

import entities.AnoEscolar;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Usuario
 */
@Local
public interface AnoEscolarFacadeLocal {

    void create(AnoEscolar anoEscolar);

    void edit(AnoEscolar anoEscolar);

    void remove(AnoEscolar anoEscolar);

    AnoEscolar find(Object id);

    List<AnoEscolar> findAll();

    List<AnoEscolar> findRange(int[] range);

    int count();
    
}
