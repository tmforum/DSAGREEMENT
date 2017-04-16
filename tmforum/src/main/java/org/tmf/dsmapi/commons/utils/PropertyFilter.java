package org.tmf.dsmapi.commons.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by atinsingh on 4/16/17.
 */
/*
public class PropertyFilter {
    public static String applyFilter(Object object, String[] filterFields) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.addMixIn(Object.class, AntPathFilterMixin.class);

        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("antPathFilter", new AntPathPropertyFilter(filterFields));
        ObjectWriter writer = objectMapper.writer(filterProvider);

        return writer.writeValueAsString(object);
    }
}*/

/**
 * Implementation that allows to set nested properties. The filter will use the
 * parents from the context to identify if a property has to be filtered.
 *
 * Example: user -&gt; manager (user)
 *
 * "id", "firstName", "lastName", "manager.id", "manager.fullName"
 *
 * { "id" : "2", "firstName" : "Atin", "lastName" : "Singh", manager : { "id" :
 * "1", "fullName" : "Architect"}}
 *
 */

public class PropertyFilter extends SimpleBeanPropertyFilter {

    private static final Logger log = Logger.getLogger(PropertyFilter.class.toString());

    /**
     * Set of property names to include.
     */
    protected final Set<String> _propertiesToInclude ;

    public PropertyFilter(Set<String> _propertiesToInclude) {
        super();
        this._propertiesToInclude = _propertiesToInclude;

    }

    /**
     * Gets the path to test.
     *
     * @param writer
     *            the writer
     * @param jsonGenerator
     *            the jsongenerator
     * @return the path to test
     */
    private String getPathforMatch(final PropertyWriter writer, final JsonGenerator jsonGenerator){

        StringBuilder nestedPath = new StringBuilder();
        //log.log(Level.INFO, " This I got for writer.getName() "+writer.getName());
        nestedPath.append(writer.getName());
        //log.log(Level.INFO, "This I got for writer.fullName() "+ writer);
        JsonStreamContext context = jsonGenerator.getOutputContext();
        if(context!=null){
            context = context.getParent();
           // log.log(Level.INFO, "This is parent I got from context "+ context.getCurrentValue());
        }
        while (context!=null){
            //log.log(Level.INFO, "Inside the while loop " + context.getCurrentName());
            if(context.getCurrentName()!=null){
                if (nestedPath.length() > 0) {
                    nestedPath.insert(0, ".");
                }
                nestedPath.insert(0, context.getCurrentName());
                //log.log(Level.INFO, "Inside the while if loop " + context.getCurrentName());


            }
           // nestedPath.insert(0, context.getCurrentName());
            context = context.getParent();
        }
       // System.out.println("I am in Property Filter with NextedPath");
        //System.out.println(nestedPath.toString());
        //log.log(Level.INFO,"I am in Property Filter with NextedPath" );
        return nestedPath.toString();
    }


    /*
     * (non-Javadoc)
     *
     * @see
     * com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter#include(
     * com.fasterxml.jackson.databind.ser. BeanPropertyWriter)
     */
    @Override
    protected boolean include(final BeanPropertyWriter writer) {
        throw new UnsupportedOperationException("Cannot call include without JsonGenerator");
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter#include(
     * com.fasterxml.jackson.databind.ser. PropertyWriter)
     */
    @Override
    protected boolean include(final PropertyWriter writer) {
        throw new UnsupportedOperationException("Cannot call include without JsonGenerator");
    }


    /**
     * Include.
     *
     * @param writer
     *            the writer
     * @param jgen
     *            the jgen
     * @return true, if successful
     */
    protected boolean include(final PropertyWriter writer, final JsonGenerator jgen) {
        String pathToTest = getPathforMatch(writer, jgen);
        // Check cache first


        // Only Excludes.
        if (_propertiesToInclude.isEmpty()) {
            return true;
        }

        // Else do full check
        boolean include = false;
        // Check Includes first
        for (String pattern : _propertiesToInclude) {
            //log.log(Level.INFO,"This the property I am going to include "+pattern+" nested path is "+pathToTest);
            if (matchPath(pathToTest, pattern)) {
               // log.log(Level.INFO,"This the property I have included "+pattern);
                include = true;
                break;
            }
        }

        return include;
    }


    /*
    * (non-Javadoc)
    *
    * @see com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter#
    * serializeAsField(java.lang.Object,
    * com.fasterxml.jackson.core.JsonGenerator,
    * com.fasterxml.jackson.databind.SerializerProvider,
    * com.fasterxml.jackson.databind.ser.PropertyWriter)
    */
    @Override
    public void serializeAsField(final Object pojo, final JsonGenerator jgen, final SerializerProvider provider,
                                 final PropertyWriter writer) throws Exception {
        log.info("I am in now serializeAsField");
        if (include(writer, jgen)) {
            writer.serializeAsField(pojo, jgen, provider);
        } else if (!jgen.canOmitFields()) { // since 2.3
            writer.serializeAsOmittedField(pojo, jgen, provider);
        }
    }


    /**
     * Only uses AntPathMatcher if the pattern contains wildcards, else use
     * simple equals
     *
     * @param pathToTest
     * @param pattern
     * @return
     */
    private boolean matchPath(String pathToTest, String pattern) {
        //log.log(Level.INFO, "This is the pattern "+pattern+ "This is the path "+ pathToTest);
        return pattern.equals(pathToTest);

    }
}
