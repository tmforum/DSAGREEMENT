package org.tmf.dsmapi.agreement.model;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Embeddable;

// This is an embedded class, to be embedded within other entities

@Embeddable
public class TimePeriod {

    private Date starDateTime;
    private Date endDateTime;

    public TimePeriod(Date starDateTime, Date endDateTime) {
        this.starDateTime = starDateTime;
        this.endDateTime = endDateTime;
    }

    public String getStarDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        return dateFormat.format(starDateTime);

    }

    public void setStarDateTime(Date starDateTime) {
        this.starDateTime = starDateTime;
    }

    public String getEndDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        return dateFormat.format(endDateTime);
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }
}
