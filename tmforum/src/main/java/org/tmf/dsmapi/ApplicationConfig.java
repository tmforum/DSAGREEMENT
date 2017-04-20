package org.tmf.dsmapi;


import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.tmf.dsmapi.commons.jaxrs.JacksonFeature;


@ApplicationPath("api")
public class ApplicationConfig extends ResourceConfig {
    public ApplicationConfig(){
        /**
         * Package to load resources for Agreement and Agreement Specification
         */
        packages("org.tmf.dsmapi.agreement","org.tmf.dsmapi.agreementspec");

        /**
         * Jackson Feature to inclide the Jackson functionality from com.fasterxml.*
         */
        register(JacksonFeature.class);
        /**
         * Mapping of Exceptions as JSON response equivalent to Exception in the code
         */
        register(org.tmf.dsmapi.commons.jaxrs.BadUsageExceptionMapper.class);
        register(org.tmf.dsmapi.commons.jaxrs.JacksonConfigurator.class);
        register(org.tmf.dsmapi.commons.jaxrs.JsonMappingExceptionMapper.class);
        register(org.tmf.dsmapi.commons.jaxrs.UnknowResourceExceptionMapper.class);
        register(org.tmf.dsmapi.commons.jaxrs.UnknowResourceExceptionMapper.class);
        register(org.tmf.dsmapi.hub.HubResource.class);
        /*
        register(org.tmf.dsmapi.appointment.AppointmentResource.class);
        register(org.tmf.dsmapi.appointment.AppointmentAdminResource.class);
        register(org.tmf.dsmapi.schedule.ScheduleResource.class);
        register(org.tmf.dsmapi.schedule.ScheduleAdminResource.class);
        register(org.tmf.dsmapi.freeSlot.FreeSlotResource.class);
        register(org.tmf.dsmapi.freeSlot.FreeSlotAdminResource.class);
		*/
    }

}
