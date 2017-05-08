package org.tmf.dsmapi.event;

import org.tmf.dsmapi.commons.facade.AbstractFacade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by atinsingh on 5/7/17.
 */
@Stateless
public class EventFacade<T> extends AbstractFacade<T> {


    @PersistenceContext(unitName = "DSAgreementPU")
    protected EntityManager entityManager;



    public EventFacade(){
        super((Class<T>) Event.class);
    }
    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
