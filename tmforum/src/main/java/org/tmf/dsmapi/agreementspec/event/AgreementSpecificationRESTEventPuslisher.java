package org.tmf.dsmapi.agreementspec.event;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.tmf.dsmapi.commons.jaxrs.RESTClient;
import org.tmf.dsmapi.commons.utils.Jackson;
import org.tmf.dsmapi.commons.utils.URIParser;
import org.tmf.dsmapi.hub.Hub;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;
import java.util.List;
import java.util.Set;

/**
 * Created by atinsingh on 4/20/17.
 */
@Stateless
@Asynchronous
public class AgreementSpecificationRESTEventPuslisher {

    @EJB
    AgreementSpecificationEventFacade eventFacade;

    @EJB
    RESTClient client;

    public void publish(Hub hub,AgreementSpecificationEvent event) {
        //get the query param from the hub query field
        MultivaluedMap<String, String> query = URIParser.getParameters(hub.getQuery());

        //set the ID field in the map.
        query.putSingle("id", event.getId());

        //get the filter fiels

        Set<String> fields = URIParser.getFieldsSelection(query);

        //seach matched event

        List<AgreementSpecificationEvent> eventList
                = eventFacade.findByCriteria(query, AgreementSpecificationEvent.class);

        if(!eventList.isEmpty() && eventList!=null){
            if(!fields.isEmpty() && !fields.contains(URIParser.ALL_FIELDS)) {
                fields.add("id");
                fields.add("date");
                fields.add("eventType");
                fields.add("reason");

                ObjectNode rootNode = Jackson.createNode(event,fields);
                client.publishEvent(hub.getCallback(),rootNode);
            }else{
                client.publishEvent(hub.getCallback(),event);
            }
        }


    }

}
