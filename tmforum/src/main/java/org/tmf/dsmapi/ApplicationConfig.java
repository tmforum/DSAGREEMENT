package org.tmf.dsmapi;

import javax.ws.rs.ApplicationPath;

import com.fasterxml.jackson.jaxrs.annotation.JacksonFeatures;
//import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.message.filtering.EntityFiltering;
import org.glassfish.jersey.message.filtering.EntityFilteringFeature;
import org.glassfish.jersey.message.filtering.SelectableEntityFilteringFeature;
import org.glassfish.jersey.server.ResourceConfig;
//import org.glassfish.jersey.moxy.json.MoxyJsonConfig;
import org.tmf.dsmapi.agreement.AgreementResource;
import org.tmf.dsmapi.agreement.model.AgreementSpecification;
import org.tmf.dsmapi.agreementspec.AgreementSpecificationResource;

@ApplicationPath("api")
public class ApplicationConfig extends ResourceConfig {
    public ApplicationConfig(){
        packages("org.tmf.dsmapi.agreement","org.tmf.dsmapi.agreementspec");

        property(SelectableEntityFilteringFeature.QUERY_PARAM_NAME, "fields");
        register(SelectableEntityFilteringFeature.class);
        packages ("com.fasterxml.jackson.jaxrs.json");


        // Configure MOXy Json provider. Comment this line to use Jackson. Uncomment to use MOXy.
        //register(new MoxyJsonConfig().setFormattedOutput(true).resolver());
        register(JacksonFeatures.class);
        /*
        packages ("org.tmf.dsmapi.agreement.Agreement");
        packages("org.tmf.dsmapi.agreementSpec.AgreementSpecification");




        register(org.tmf.dsmapi.commons.jaxrs.BadUsageExceptionMapper.class);
        register(org.tmf.dsmapi.commons.jaxrs.JacksonConfigurator.class);
        register(org.tmf.dsmapi.commons.jaxrs.JsonMappingExceptionMapper.class);
        register(org.tmf.dsmapi.commons.jaxrs.UnknowResourceExceptionMapper.class);
        register(org.tmf.dsmapi.commons.jaxrs.UnknowResourceExceptionMapper.class);

        register(org.tmf.dsmapi.hub.HubResource.class);
        register(org.tmf.dsmapi.appointment.AppointmentResource.class);
        register(org.tmf.dsmapi.appointment.AppointmentAdminResource.class);
        register(org.tmf.dsmapi.schedule.ScheduleResource.class);
        register(org.tmf.dsmapi.schedule.ScheduleAdminResource.class);
        register(org.tmf.dsmapi.freeSlot.FreeSlotResource.class);
        register(org.tmf.dsmapi.freeSlot.FreeSlotAdminResource.class);
		*/
    }

}
