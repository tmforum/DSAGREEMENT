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

@ApplicationPath("api")
public class ApplicationConfig extends ResourceConfig {
    public ApplicationConfig(){
        packages("org.tmf.dsmapi.agreement;org.tmf.dsmapi.agreementspec")
        .register(JacksonFeature.class);

        //property(SelectableEntityFilteringFeature.QUERY_PARAM_NAME, "fields");
        //register(SelectableEntityFilteringFeature.class);
    }

}
