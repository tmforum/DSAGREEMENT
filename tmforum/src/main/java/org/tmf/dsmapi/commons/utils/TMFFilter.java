package org.tmf.dsmapi.commons.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import java.util.Set;

/**
 * Created by atinsingh on 4/16/17.
 */
public class TMFFilter {

    public static String applyFilter(Object object, Set<String> filterFields) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.addMixIn(Object.class, PropertyFilterMixin.class);

        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("tmfFilter", new PropertyFilter(filterFields));
        ObjectWriter writer = objectMapper.writer(filterProvider);

        return writer.writeValueAsString(object);
    }
}
