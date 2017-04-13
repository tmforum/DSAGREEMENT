package org.tmf.dsmapi.agreement.model;



/**
 * Created by atinsingh on 4/5/17.
 */
public enum  AgreementStatusEnum {

    INITIALIZED("Initialized"),
    IN_PROCESS("InProcess"),
    PENDING_UPDATE("PendingUpdate"),
    VALIDATED("Validated"),
    REJECTED("Rejected"),
    CLOSED("closed");

    private final String value;

    AgreementStatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static AgreementStatusEnum fromValue(String v) {
        for (AgreementStatusEnum c: AgreementStatusEnum.values()) {
            if (c.value.equalsIgnoreCase(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }


    /**
     * String value
     * @return
     * allowed object is
     * {@link String}
     */
    @Override
    public String toString() {
        return "AgreementStatusEnum{" +
                "value='" + value + '\'' +
                '}';
    }
}
