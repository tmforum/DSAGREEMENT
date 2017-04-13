package org.tmf.dsmapi.agreement.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.tmf.dsmapi.commons.utils.CustomJsonDateDeSerializer;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;

// This is an embedded class, to be embedded within other entities

@Embeddable
public class TimePeriod {

    @Temporal(TemporalType.TIMESTAMP)
    @XmlElement(type = String.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    @JsonDeserialize(using = CustomJsonDateDeSerializer.class)
    private Date starDateTime;

    @XmlElement(type = String.class)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    @JsonDeserialize(using = CustomJsonDateDeSerializer.class)
    private Date endDateTime;

	/*
    public TimePeriod(Date starDateTime, Date endDateTime) {
        this.starDateTime = starDateTime;
        this.endDateTime = endDateTime;
    }
	*/

    public Date getStarDateTime() {
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        return starDateTime;

    }

    public void setStarDateTime(Date starDateTime) throws ParseException {
        this.starDateTime =starDateTime ;
    }

    public Date getEndDateTime() {
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        //return dateFormat.format(endDateTime);
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) throws ParseException {
        this.endDateTime =endDateTime;
    }
}
