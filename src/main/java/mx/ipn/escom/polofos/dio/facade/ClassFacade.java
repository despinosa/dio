/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ipn.escom.polofos.dio.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import mx.ipn.escom.polofos.dio.model.Class;

/**
 *
 * @author crypt
 */
@Stateless
public class ClassFacade extends AbstractFacade<Class> {

    @PersistenceContext(unitName = "DioPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClassFacade() {
        super(Class.class);
    }
    
}
