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
import java.util.HashSet;
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
import javax.ws.rs.core.MediaType;
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
import org.tmf.dsmapi.commons.utils.TMFFilter;

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
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response create(Agreement entity, @Context UriInfo info) 
		throws BadUsageException, UnknownResourceException {

		// Check inputs for mandatory fields and set defaults if certain inputs have not been provided
		agreementFacade.checkCreation(entity);

		// Create a new "agreement" entity in the database 
		// In-case ID is not sent in the input, the persistence layer will generate a new ID
		// Hence post creation, set the appropriate ID in the entity link field
		agreementFacade.create(entity);
		entity.setHref(info.getAbsolutePath() + "/" + entity.getId());
		agreementFacade.edit(entity);

		Response response = Response.status(Response.Status.CREATED).entity(entity).build();

		return response;
    }

    /** 
     * This function will match the end point
     * https://host:port/DSAgreement/agreementManagement/agreement/{id}?fields=field1,field2
     * {id} is mandatory, if {id} is not passed, URL will return not found 404
     * @param id
     * @param uriInfo
     * @return
     * @throws BadUsageException
     * @throws UnknownResourceException
     * @throws Exception
    **/

	@GET
	@Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
	public Response get(@PathParam("id") String id, @Context UriInfo info) throws UnknownResourceException, Exception {
		/**
		 The URL parameters are returned as a standard immutable JAX-RS <K,V> pair
		 The function receives query parameters as below - hence we need to parse it first
		 Note: We find a match by ID only - any other query criteria is discarded, other than
		 the fields filtering
		 URL: e.g. /agreement/{id}?fields=id,name&status=approved&engagedParty.name="So Magic Ltd"
		**/

		Response response;
		MultivaluedMap<String, String> queryParameters = info.getQueryParameters();

		// TBD - handle the queryParameters!!
		
		// The mutableMap creates a Map<String, List<String>>, instead of a MultivaluedMap
		Map<String, List<String>> mutableMap = new HashMap();

		for (Map.Entry<String, List<String>> e : queryParameters.entrySet()) {
			mutableMap.put(e.getKey(), e.getValue());
		}

		// Get the list of fields to be returned in the GET request
		Set<String> fieldSet = URIParser.getFieldsSelection(mutableMap);

		// Query the requested Agreement ID via JPA
		Agreement agreement = agreementFacade.find(id);

		if(agreement != null) {
			// Since all fields have been requested, no filtering is needed
			if (fieldSet.isEmpty() || fieldSet.contains(URIParser.ALL_FIELDS)) {
				response = Response.ok(agreement).build();
			} 
			// Else apply filter
			else {
				response = Response.ok(TMFFilter.applyFilter(agreement, fieldSet)).build();
			}
		} else {
			response = Response.status(Response.Status.NOT_FOUND).build();
		}
		return response;
	}

    /** 
     * This function will return a collection of agreements and match the end point
     * https://host:port/DSAgreement/agreementManagement/agreement?fields=field1,field2
     * @param uriInfo
     * @return
     * @throws BadUsageException
     * @throws UnknownResourceException
     * @throws Exception
    **/

	@GET
    @Produces({MediaType.APPLICATION_JSON})
	public Response get(@Context UriInfo info) throws UnknownResourceException, Exception {
		/**
		 The URL parameters are returned as a standard immutable JAX-RS <K,V> pair
		 The function receives query parameters as below - hence we need to parse it first
		 Note: The match is found on the basis of the query criteria provided in the URL
		 URL: e.g. /agreement?fields=id,name&status=approved&engagedParty.name="So Magic Ltd"
		**/

		Response response;
		MultivaluedMap<String, String> queryParameters = info.getQueryParameters();
		
		// The mutableMap creates a Map<String, List<String>>, instead of a MultivaluedMap
		Map<String, List<String>> mutableMap = new HashMap();

		for (Map.Entry<String, List<String>> e : queryParameters.entrySet()) {
			mutableMap.put(e.getKey(), e.getValue());
		}

		// Extract the list of fields to be returned in the GET request
		// This function also removes the "fields" key. Hence the mutableMap can be passed to JPA
		Set<String> fieldSet = URIParser.getFieldsSelection(mutableMap);

		// Query the requested Agreement ID via JPA
		Set<Agreement> agreementSet = findByCriteria(mutableMap);

		if (fieldSet.isEmpty() || fieldSet.contains(URIParser.ALL_FIELDS)) {
			// Since all fields have been requested, no filtering is needed
			response = Response.ok(agreementSet).build();
		} else {
			// Else apply filter
			String filteredAgreementSet = "";

			for(Agreement ag : agreementSet) {
				filteredAgreementSet += TMFFilter.applyFilter(ag, fieldSet);
			}

			response = Response.ok(filteredAgreementSet).build();
		}
		return response;
	}

	/**
	 Return Set of unique elements to avoid List with same elements in case of join
	**/
    private Set<Agreement> findByCriteria(Map<String, List<String>> criteria) throws BadUsageException {

        List<Agreement> resultList = null;

        if (criteria != null && !criteria.isEmpty()) {
            resultList = agreementFacade.findByCriteria(criteria, Agreement.class);
        } else {
            resultList = agreementFacade.findAll();
        }
        if (resultList == null) {
            return new LinkedHashSet<Agreement>();
        } else {
            return new LinkedHashSet<Agreement>(resultList);
        }
    }
}
