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
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    @JsonDeserialize(using = CustomJsonDateDeSerializer.class)
    private Date startDateTime;

    @Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    @JsonDeserialize(using = CustomJsonDateDeSerializer.class)
    private Date endDateTime;

    public Date getStartDateTime() {
        return startDateTime;

    }
    public void setStartDateTime(Date startDateTime)  {
        this.startDateTime = startDateTime ;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) throws ParseException {
        this.endDateTime = endDateTime;
    }
}
