package org.tmf.dsmapi.commons.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.apache.commons.lang3.StringUtils;
import ch.mfrey.jackson.antpathfilter.AntPathFilterMixin;
import ch.mfrey.jackson.antpathfilter.AntPathPropertyFilter;

import java.util.Set;
import java.util.HashSet;
import java.util.Collection;

/**
 * This class provides a function that can be invoked at the Resource Layer, to filter JSON properties
 * based on the user's specification (provided as part of the URL).
 **/

public class TMFFilter {
    public static final String FIELD_SPILITER = ".";
    public static final String FIELD_ASSIGNMENT = "=";
    private static String FILTER_NAME = "antPathFilter";

    /**
     * This function uses Jackson AntPathFiler implementation for this task.
     * https://github.com/Antibrumm/jackson-antpathfilter
     **/
    public static String applyFilter(Object o, Set<String> userFilterFields) throws Exception {

        //Set<String> filterFields = new HashSet<String>(userFilterFields);
        Set<String> filterFields = new HashSet<String>(getFilterFields(userFilterFields));

        ObjectMapper mapper = new ObjectMapper();
        FilterProvider fProvider = new SimpleFilterProvider().
                addFilter(FILTER_NAME, new AntPathPropertyFilter(filterFields.toArray(new String[0])));

        mapper.addMixIn(Object.class, AntPathFilterMixin.class);

        return mapper.writer(fProvider).writeValueAsString(o);
    }

    /**
     * This utility function scans the input property names and returns parent property names for nested properties
     * For a nested property to be returned (included) by the filter function, the parent property must be
     * included as well
     * e.g. An input property "user.firstname" will result in 2 property names: "user" and "user.firstname"
     **/
    private static Set<String> getFilterFields(Set<String> fieldSet) {
        Set<String> modifiedFieldset = new HashSet<String>();

        for (String field : fieldSet) {
            if (StringUtils.countMatches(field, FIELD_SPILITER) > 0) {
                while (StringUtils.countMatches(field, FIELD_SPILITER) > 0) {
                    String[] subfield = StringUtils.split(field, FIELD_SPILITER, 2);
                    if (subfield[0].contains(FIELD_ASSIGNMENT)) {
                        String[] fieldName = StringUtils.split(subfield[0], FIELD_ASSIGNMENT);
                        modifiedFieldset.add(fieldName[0]);
                    } else {
                        modifiedFieldset.add(subfield[0]);
                    }
                    modifiedFieldset.add(field);
                    field = subfield[1];
                }
                if (field.contains(FIELD_ASSIGNMENT)) {
                    String[] fieldName = StringUtils.split(field, FIELD_ASSIGNMENT);
                    modifiedFieldset.add(fieldName[0]);
                } else {
                    modifiedFieldset.add(field);
                }
            } else {
                if (field.contains(FIELD_ASSIGNMENT)) {
                    String[] fieldName = StringUtils.split(field, FIELD_ASSIGNMENT);
                    modifiedFieldset.add(fieldName[0]);
                } else {
                    modifiedFieldset.add(field);
                }
            }
        }
        return modifiedFieldset;
    }
}
