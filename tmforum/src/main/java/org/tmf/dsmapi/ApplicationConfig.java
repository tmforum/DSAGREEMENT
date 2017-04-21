package org.tmf.dsmapi;

/** 
 Note: Adding default org.glassfish.jersey.jackson.JacksonFeature (jersey-media-json-jackson in pom.xml)
 causes a dependency issue and a runtime error. This is documented here: 
 http://stackoverflow.com/questions/20709827/force-glassfish-4-to-use-jackson-2-3
 Hence we're working with a custom JacksonFeature implementation (org.tmf.dsmapi.commons.jaxrs.JacksonFeature).
**/

import org.tmf.dsmapi.commons.jaxrs.JacksonFeature;
import javax.ws.rs.ApplicationPath;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.message.filtering.EntityFiltering;
import org.glassfish.jersey.message.filtering.EntityFilteringFeature;
import org.glassfish.jersey.message.filtering.SelectableEntityFilteringFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.tmf.dsmapi.agreement.AgreementResource;
import org.tmf.dsmapi.agreement.model.AgreementSpecification;
import org.tmf.dsmapi.agreementspec.AgreementSpecificationResource;

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
    }
}
