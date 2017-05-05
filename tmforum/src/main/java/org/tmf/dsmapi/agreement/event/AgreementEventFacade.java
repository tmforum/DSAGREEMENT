package org.tmf.dsmapi.agreement.event;

import org.tmf.dsmapi.commons.facade.AbstractFacade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

@Stateless
public class AgreementEventFacade extends AbstractFacade<AgreementEvent> {

    public AgreementEventFacade() {
       super(AgreementEvent.class);
    }

    @PersistenceContext(unitName = "DSAgreementPU")
    protected EntityManager entityManager;

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
