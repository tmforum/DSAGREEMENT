package org.tmf.dsmapi.agreement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import org.tmf.dsmapi.commons.jaxrs.PATCH;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import com.fasterxml.jackson.databind.node.ObjectNode;
//import com.github.fge.jsonpatch.JsonPatch;
//import com.github.fge.jsonpatch.JsonPatchException;
//import com.github.fge.jsonpatch.diff.JsonDiff;
import org.tmf.dsmapi.commons.exceptions.BadUsageException;
import org.tmf.dsmapi.commons.exceptions.UnknownResourceException;
import org.tmf.dsmapi.commons.utils.Jackson;
import org.tmf.dsmapi.commons.utils.URIParser;
import org.tmf.dsmapi.agreement.model.Agreement;
import org.tmf.dsmapi.agreement.AgreementFacade;
//import org.tmf.dsmapi.appointment.event.AppointmentEventPublisherLocal;
//import org.tmf.dsmapi.schedule.ScheduleFacade;
@Stateless
@Path("/agreementManagement/agreement")
public class AgreementResource {

    @EJB
    AgreementFacade agreementFacade;
/*
    @EJB
    AppointmentEventPublisherLocal publisher;
*/

    public AgreementResource() {
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response create(Agreement entity, @Context UriInfo info) 
		throws BadUsageException, UnknownResourceException {

		System.out.println("~~~ Calling Facade ~~~");
		agreementFacade.create(entity);
		System.out.println("~~~ Facade Called ~~~");
		//System.out.println(entity.toSting());

		Response response = Response.status(Response.Status.CREATED).entity(entity).build();
                //Response response = Response.status(Response.Status.CREATED).build();
		System.out.println("~~~ Before sending Response ~~~");
		System.out.println("~~~ Before sending Response ~~~");
		return response;
    }

	@GET
	@Path("/{id}")
    @Produces({"application/json"})
	public Response get(@PathParam("id") String id, @Context UriInfo info) throws UnknownResourceException {
		// The URL parameters are returned as a standard immutable JAX-RS <K,V> pair
		// The function receives query parameters as below - hence we need to parse it first
		// /agreement?fields=id,name&status=approved&engagedParty.name="So Magic Ltd"

		Response response = Response.status(Response.Status.NOT_FOUND).build();
		//Response response;
		MultivaluedMap<String, String> queryParameters = info.getQueryParameters();
		
		// A mutable map is created just for use of the function "getFieldsSelection".
		// Seems unwarranted, but we'll treat it as "boilerplate" code.
		Map<String, List<String>> mutableMap = new HashMap();
		for (Map.Entry<String, List<String>> e : queryParameters.entrySet()) {
			mutableMap.put(e.getKey(), e.getValue());
		}

		// Get the list of fields to be returned in the GET request
		Set<String> fieldSet = URIParser.getFieldsSelection(mutableMap);

		// Query the requested Agreement ID via JPA
		Agreement agreement = agreementFacade.find(id);

		if(agreement != null) {
			if (fieldSet.isEmpty() || fieldSet.contains(URIParser.ALL_FIELDS)) {
				response = Response.ok(agreement).build();
			} 
			else {
				/*
				response = Response.status(Response.Status.NOT_FOUND).build();
				fieldSet.add(URIParser.ID_FIELD);
				ObjectNode node = Jackson.createNode(agreement, fieldSet);
				response = Response.ok(node).build();
				*/
			}
		}
		return response;
	}
}
