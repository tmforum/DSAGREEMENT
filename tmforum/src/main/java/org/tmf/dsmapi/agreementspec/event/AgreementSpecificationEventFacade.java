package org.tmf.dsmapi.agreementspec.event;

import org.tmf.dsmapi.commons.facade.AbstractFacade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

/**
 * Created by atinsingh on 4/19/17.
 */
@Stateless
public class AgreementSpecificationEventFacade extends AbstractFacade<AgreementSpecificationEvent> {

    /**
     * Initialize Facade
     */
    public AgreementSpecificationEventFacade() {
       super(AgreementSpecificationEvent.class);
    }

    @PersistenceContext(unitName = "DSAgreementPU")
    protected EntityManager entityManager;

    /**
     * Return entity manager
     * @return
     */
    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
