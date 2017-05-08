
package org.tmf.dsmapi.event;

public enum AgreementEventEnum {

    AgreementCreationNotification("AgreementCreationNotification"),
    AgreementValueChangeNotification("AgreementValueChangeNotification"),
    AgreementStateChangeNotification("AgreementStateChangeNotification"),
    AgreementRemoveNotification("AgreementRemoveNotification"),
    AgreementSpecCreationNotification("AgreementSpecCreationNotification"),
    AgreementSpecValueChangeNotification("AgreementSpecValueChangeNotification"),
    AgreementSpecStateChangeNotification("AgreementSpecStateChangeNotification"),
    AgreementSpecRemoveNotification("AgreementSpecRemoveNotification");

    private String text;

    AgreementEventEnum(String text) {
        this.text = text;
    }

    /**
     *
     * @return
     */
    public String getText() {
        return this.text;
    }

    /**
     *
     * @param text
     * @return
     */
    public static AgreementEventEnum fromString(String text) {
        if (text != null) {
            for (AgreementEventEnum b : AgreementEventEnum.values()) {
                if (text.equalsIgnoreCase(b.text)) {
                    return b;
                }
            }
        }
        return null;
    }

}