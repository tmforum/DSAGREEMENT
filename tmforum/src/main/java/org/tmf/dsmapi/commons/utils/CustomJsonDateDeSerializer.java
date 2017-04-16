/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmf.dsmapi.commons.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//public class CustomJsonDateDeserializer extends StdDeserializer<Date> {
public class CustomJsonDateDeSerializer extends JsonDeserializer<Date> {
    @Override
    public Date deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException {

		System.out.println("In CustomJsonDateDeserializer");

		String[] formatStrings = {"yyyy-MM-dd'T'HH:mm:ssXXX", "yyyy-MM-dd'T'HH:mm", "yyyy-MM-dd"};
        String dateString = jsonparser.getText();

		for (String formatString : formatStrings) {
			try {
				return new SimpleDateFormat(formatString).parse(dateString);
			} catch (ParseException e) {
			}
		}
		return null;
	}
}
