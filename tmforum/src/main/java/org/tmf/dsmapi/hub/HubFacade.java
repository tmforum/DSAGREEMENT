package org.tmf.dsmapi.hub;

import org.tmf.dsmapi.commons.facade.AbstractFacade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

/**
 * Created by atinsingh on 4/17/17.
 */

@Stateless
public class HubFacade extends AbstractFacade<Hub> {

    @PersistenceContext(unitName = "DSAgreementPU")
    private EntityManager entityManager;

    public HubFacade() {
        super(Hub.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
