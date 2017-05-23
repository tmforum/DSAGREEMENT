package org.tmf.dsmapi.agreement;

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
import javax.ws.rs.DELETE;

import org.tmf.dsmapi.event.AgreementEventEnum;
import org.tmf.dsmapi.event.EventPublisher;
import org.tmf.dsmapi.commons.jaxrs.PATCH;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.tmf.dsmapi.commons.exceptions.BadUsageException;
import org.tmf.dsmapi.commons.exceptions.UnknownResourceException;
import org.tmf.dsmapi.commons.utils.URIParser;
import org.tmf.dsmapi.agreement.model.Agreement;
import org.tmf.dsmapi.commons.utils.TMFFilter;

@Stateless
@Path("/agreementManagement/agreement")
public class AgreementResource {

    @EJB
    AgreementFacade agreementFacade;

    @EJB
    EventPublisher<Agreement> eventPublisher;
    //EventPublisherInterface<Agreement> eventPublisher;

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

        // Generate notification for the resource creation
        eventPublisher.generateEventNotification(entity, new Date(), AgreementEventEnum.AgreementCreationNotification);

        // Generate response and return
        Response response = Response.status(Response.Status.CREATED).entity(entity).build();

        return response;
    }

    /**
     * This function will match the end point
     * https://host:port/DSAgreement/agreementManagement/agreement/{id}?fields=field1,field2
     * {id} is mandatory, if {id} is not passed, URL will return not found 404
     *
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

        // The mutableMap creates a Map<String, List<String>>, instead of a MultivaluedMap
        Map<String, List<String>> mutableMap = new HashMap();

        for (Map.Entry<String, List<String>> e : queryParameters.entrySet()) {
            mutableMap.put(e.getKey(), e.getValue());
        }

        // Get the list of fields to be returned in the GET request
        Set<String> fieldSet = URIParser.getFieldsSelection(mutableMap);

        // Query the requested Agreement ID via JPA
        Agreement agreement = agreementFacade.find(id);

        if (agreement != null) {
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
     *
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

            for (Agreement ag : agreementSet) {
                filteredAgreementSet += TMFFilter.applyFilter(ag, fieldSet);
            }

            response = Response.ok(filteredAgreementSet).build();
        }
        return response;
    }

    /**
     * This function will match the url http://host:port/DSAgreement/agreementManagement/agreement/{id}
     * function will find the entity associated with it and delete that entity
     *
     * @param id
     * @return
     **/
    @DELETE
    @Path("{id}")
    public Response deleteByID(@PathParam("id") String id) throws UnknownResourceException {

        Response response;

        // Query the requested Agreement ID via JPA
        agreementFacade.remove(id);
        response = Response.status(Response.Status.NO_CONTENT).build();

        return response;
    }

    /**
     * Function will match the URL
     * PATCH http://host:port/DSAgreement/agreementManagement/agreement/{id}
     * function will take Ageement Object JSON to patch the existing object.
     * <p>
     * Note: This is akin to an update using POST, rather than an actual PATCH.
     * For a verbatim PATCH, please use the jsonpatch version of the function.
     *
     * @param id
     * @param patchObject
     * @throws BadUsageException
     * @throws UnknownResourceException
     **/
    @PATCH
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response patch(@PathParam("id") String id, Agreement partialEntity)
            throws BadUsageException, UnknownResourceException {

        // If the partialEntity property values is different from the database entity, then
        // patch the entity and generate the appropriate notifications
        Agreement entity = agreementFacade.patchAttributes(id, partialEntity);

        Response response;

        if (entity != null) {
            response = Response.ok(entity).build();
        } else {
            response = Response.status(Response.Status.NOT_FOUND).entity("No object Available for Patching").build();
        }

        return response;
    }

    /**
     * Function will match the URL
     * PATCH http://host:port/DSAgreement/agreementManagement/agreement/{id}
     * <p>
     * Note: This is an implementation of RFC 6902 for JSON patch operations, using the JSON Patch library
     * https://github.com/java-json-tools/json-patch
     *
     * @param id
     * @param patchObject
     * @throws BadUsageException
     * @throws UnknownResourceException
     **/
    @PATCH
    @Path("{id}")
    @Consumes("application/json-patch+json")
    public Response patch(@PathParam("id") String id, JsonPatch patch)
            throws BadUsageException, UnknownResourceException, JsonPatchException {

        // Retrieve the entity to be patched.
        Agreement entity = agreementFacade.find(id);

        // Convert the entity into a JSON object, for use by the JSONPatch library
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.convertValue(entity, JsonNode.class);

        // Invoke the JSONPatch library to perform the diff and genrate the patch entity
        final JsonNode patchedNode = patch.apply(node);
        Agreement partialEntity = mapper.convertValue(patchedNode, Agreement.class);

        // If the partialEntity property values is different from the database entity, then
        // patch the entity and generate the appropriate notifications
        entity = agreementFacade.patchAttributes(id, partialEntity);

        return Response.status(Response.Status.ACCEPTED).entity(entity).build();
    }

    /**
     * Return Set of unique elements to avoid List with same elements in case of join
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
