package org.tmf.dsmapi.event;

import org.tmf.dsmapi.commons.jaxrs.RESTClient;
import org.tmf.dsmapi.commons.utils.TMFFilter;
import org.tmf.dsmapi.commons.utils.URIParser;
import org.tmf.dsmapi.event.EventFacade;
import org.tmf.dsmapi.hub.Hub;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;
import java.util.List;
import java.util.Set;

/**
 * Created by atinsingh on 5/7/17.
 */
@Stateless
@Local
public class RestEventPublisher<T> {




    @EJB
    private EventFacade eventFacade  ;

    @EJB
    private RESTClient client;


    public  void publish(Hub hub, String id, T event) {

        // Get the query param from the hub query field
        MultivaluedMap<String, String> query = URIParser.getParameters(hub.getQuery());

        // Set the ID field in the map.
        query.putSingle("id", id);

        // Get the filter fiels
        Set<String> fields = URIParser.getFieldsSelection(query);

        // Search matched event
        List<T> eventList
                = eventFacade.findByCriteria(query,event.getClass());

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
