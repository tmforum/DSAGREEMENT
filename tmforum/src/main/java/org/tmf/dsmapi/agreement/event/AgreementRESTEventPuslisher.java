package org.tmf.dsmapi.agreement.event;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.tmf.dsmapi.commons.jaxrs.RESTClient;
import org.tmf.dsmapi.commons.utils.Jackson;
import org.tmf.dsmapi.commons.utils.URIParser;
import org.tmf.dsmapi.commons.utils.TMFFilter;
import org.tmf.dsmapi.hub.Hub;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;
import java.util.List;
import java.util.Set;

@Stateless
@Asynchronous
public class AgreementRESTEventPuslisher {
    @EJB
    AgreementEventFacade eventFacade;

    @EJB
    RESTClient client;

    public void publish(Hub hub, AgreementEvent event) {

        // Get the query param from the hub query field
        MultivaluedMap<String, String> query = URIParser.getParameters(hub.getQuery());

        // Set the ID field in the map.
        query.putSingle("id", event.getId());

        // Get the filter fiels
        Set<String> fields = URIParser.getFieldsSelection(query);

        // Search matched event
        List<AgreementEvent> eventList
                = eventFacade.findByCriteria(query, AgreementEvent.class);

        if(!eventList.isEmpty() && eventList!=null){
            if(!fields.isEmpty() && !fields.contains(URIParser.ALL_FIELDS)) {
                fields.add("id");
                fields.add("eventTime");
                fields.add("eventType");
                //fields.add("reason");

				try {
					client.publishEvent(hub.getCallback(), TMFFilter.applyFilter(event, fields));
				} catch(Exception e) {
					e.printStackTrace();
				}
                //ObjectNode rootNode = Jackson.createNode(event,fields);
                //client.publishEvent(hub.getCallback(),rootNode);
            } else {
                client.publishEvent(hub.getCallback(),event);
            }
        }


    }

}
