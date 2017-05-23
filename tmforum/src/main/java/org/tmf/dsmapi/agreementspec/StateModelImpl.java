package org.tmf.dsmapi.agreementspec;

import org.tmf.dsmapi.agreement.model.AgreementStatusEnum;
import org.tmf.dsmapi.commons.workflow.StateModelBase;

/**
 * Created by atinsingh on 4/5/17.
 */
public class StateModelImpl extends StateModelBase<AgreementStatusEnum> {

    /**
     * Constructor
     *
     * @param type
     */
    public StateModelImpl(Class<AgreementStatusEnum> type) {
        super(type);
    }

    /**
     * Overide the draw method to define the transisions of state
     */
    protected void draw() {

        /**
         * From State initialize , we can go to inProcess only
         */
        fromFirst(AgreementStatusEnum.INITIALIZED).to(AgreementStatusEnum.INPROCESS);

        /**
         * From inprocess, it can go to Pending Update, Validated, Rejected
         */
        from(AgreementStatusEnum.INPROCESS).to(AgreementStatusEnum.PENDING_UPDATE, AgreementStatusEnum.REJECTED, AgreementStatusEnum.VALIDATED);

        /**
         * From Validated and Pending Update, It can go to in Process Again
         */
        from(AgreementStatusEnum.PENDING_UPDATE).to(AgreementStatusEnum.INPROCESS);
        from(AgreementStatusEnum.VALIDATED).to(AgreementStatusEnum.INPROCESS);

        /**
         * From Rejected it will be closed.
         */
        from(AgreementStatusEnum.REJECTED).to(AgreementStatusEnum.CLOSED);
    }

}
