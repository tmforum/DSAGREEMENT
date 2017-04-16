package org.tmf.dsmapi;

import javax.ws.rs.ApplicationPath;

//import com.fasterxml.jackson.jaxrs.annotation.JacksonFeatures;
import com.fasterxml.jackson.databind.ser.FilterProvider;
//import com.fasterxml.jackson.jaxrs.annotation.JacksonFeatures;
//import org.glassfish.jersey.jackson.JacksonFeature;
//import org.glassfish.jersey.message.filtering.EntityFiltering;
//import org.glassfish.jersey.message.filtering.EntityFilteringFeature;
//import org.glassfish.jersey.message.filtering.SelectableEntityFilteringFeature;
import org.glassfish.jersey.message.filtering.EntityFilteringFeature;
import org.glassfish.jersey.message.filtering.SelectableEntityFilteringFeature;
import org.glassfish.jersey.server.ResourceConfig;
//import org.glassfish.jersey.moxy.json.MoxyJsonConfig;

import org.tmf.dsmapi.agreement.AgreementResource;
import org.tmf.dsmapi.agreement.model.AgreementSpecification;
import org.tmf.dsmapi.agreementspec.AgreementSpecificationResource;
import org.tmf.dsmapi.commons.jaxrs.JacksonFeature;
//import org.tmf.dsmapi.commons.jaxrs.CustomObjectMapperProvider;

@ApplicationPath("api")
public class ApplicationConfig extends ResourceConfig {
    public ApplicationConfig(){
        packages("org.tmf.dsmapi.agreement","org.tmf.dsmapi.agreementspec");

        //register(EntityFilteringFeature.class);
        //register(SelectableEntityFilteringFeature.class);
        //property(SelectableEntityFilteringFeature.QUERY_PARAM_NAME, "fields");
        //packages ("com.fasterxml.jackson.jaxrs.json");


        // Configure MOXy Json provider. Comment this line to use Jackson. Uncomment to use MOXy.
        //register(new MoxyJsonConfig().setFormattedOutput(true).resolver());
        register(JacksonFeature.class);
        //register(CustomObjectMapperProvider.class);
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
