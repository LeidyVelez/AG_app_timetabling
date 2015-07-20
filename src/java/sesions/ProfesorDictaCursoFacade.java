/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesions;

import entities.ProfesorDictaCurso;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Usuario
 */
@Stateless
public class ProfesorDictaCursoFacade extends AbstractFacade<ProfesorDictaCurso> implements ProfesorDictaCursoFacadeLocal {
    @PersistenceContext(unitName = "WebApplication3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProfesorDictaCursoFacade() {
        super(ProfesorDictaCurso.class);
    }
    
}
