package org.tmf.dsmapi.agreement.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.tmf.dsmapi.commons.utils.CustomJsonDateDeSerializer;
import org.tmf.dsmapi.commons.utils.CustomJsonDateSerializer;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;

// This is an embedded class, to be embedded within other entities

@Embeddable
public class TimePeriod {

    @Temporal(TemporalType.TIMESTAMP)
   // @XmlElement(type = String.class)
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    @JsonDeserialize(using = CustomJsonDateDeSerializer.class)
    private Date startDateTime;

   // @XmlElement(type = String.class)
    @Temporal(TemporalType.TIMESTAMP)
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    @JsonDeserialize(using = CustomJsonDateDeSerializer.class)
    private Date endDateTime;

	/*
    public TimePeriod(Date starDateTime, Date endDateTime) {
        this.starDateTime = starDateTime;
        this.endDateTime = endDateTime;
    }
	*/
    //@JsonSerialize(using = CustomJsonDateSerializer.class)
    public Date getStarDateTime() {
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        return startDateTime;

    }
    //@JsonDeserialize(using = CustomJsonDateDeSerializer.class)
    public void setStartDateTime(Date startDateTime)  {
        this.startDateTime =startDateTime ;
    }

    public Date getEndDateTime() {
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        //return dateFormat.format(endDateTime);
        return endDateTime;
    }

    //@JsonDeserialize(using = CustomJsonDateDeSerializer.class)
    public void setEndDateTime(Date endDateTime) throws ParseException {
        this.endDateTime =endDateTime;
    }
}
