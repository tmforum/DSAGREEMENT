package org.tmf.dsmapi.hub;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.tmf.dsmapi.commons.utils.CustomJsonDateDeSerializer;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by atinsingh on 4/17/17.
 */
@Entity
public class Hub {

    @Transient
    private static final long serialVersioUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private Integer leaseSeconds;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonDeserialize(using = CustomJsonDateDeSerializer.class)
    private Date dateTime;

    private String callback;
    private String query;

    public Hub() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getLeaseSeconds() {
        return leaseSeconds;
    }

    public void setLeaseSeconds(Integer leaseSeconds) {
        this.leaseSeconds = leaseSeconds;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hub)) return false;

        Hub hub = (Hub) o;

        if (getId() != null ? !getId().equals(hub.getId()) : hub.getId() != null) return false;
        if (getLeaseSeconds() != null ? !getLeaseSeconds().equals(hub.getLeaseSeconds()) : hub.getLeaseSeconds() != null)
            return false;
        if (getDateTime() != null ? !getDateTime().equals(hub.getDateTime()) : hub.getDateTime() != null) return false;
        if (getCallback() != null ? !getCallback().equals(hub.getCallback()) : hub.getCallback() != null) return false;
        return getQuery() != null ? getQuery().equals(hub.getQuery()) : hub.getQuery() == null;

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getLeaseSeconds() != null ? getLeaseSeconds().hashCode() : 0);
        result = 31 * result + (getDateTime() != null ? getDateTime().hashCode() : 0);
        result = 31 * result + (getCallback() != null ? getCallback().hashCode() : 0);
        result = 31 * result + (getQuery() != null ? getQuery().hashCode() : 0);
        return result;
    }
}
