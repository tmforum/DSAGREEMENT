/**
 * Note: Adding default org.glassfish.jersey.jackson.JacksonFeature (jersey-media-json-jackson in pom.xml)
 * causes a dependency issue and a runtime error. This is documented here:
 * http://stackoverflow.com/questions/20709827/force-glassfish-4-to-use-jackson-2-3
 * Hence we're working with a custom JacksonFeature implementation (org.tmf.dsmapi.commons.jaxrs.JacksonFeature).
 **/

package org.tmf.dsmapi.commons.jaxrs;

import com.fasterxml.jackson.jaxrs.base.JsonParseExceptionMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.core.Feature;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;

import org.glassfish.jersey.CommonProperties;

public class JacksonFeature implements Feature {
    public boolean configure(final FeatureContext context) {
        String postfix = '.' + context.getConfiguration().getRuntimeType().name().toLowerCase();

        context.property(CommonProperties.MOXY_JSON_FEATURE_DISABLE + postfix, true);

        context.register(JsonParseExceptionMapper.class);
        context.register(JsonMappingExceptionMapper.class);
        context.register(JacksonJsonProvider.class, MessageBodyReader.class, MessageBodyWriter.class);

        return true;
    }
}
