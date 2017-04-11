package org.tmf.dsmapi.agreement.event;

import org.tmf.dsmapi.agreement.model.Agreement;

/**
 * Created by atinsingh on 4/5/17.
 */
public class AgreementEventBody {
    private Agreement entity;

    public AgreementEventBody(Agreement entity) {
        this.entity = entity;
    }

    public Agreement getEntity() {
        return entity;
    }

    public void setEntity(Agreement entity) {
        this.entity = entity;
    }
}
