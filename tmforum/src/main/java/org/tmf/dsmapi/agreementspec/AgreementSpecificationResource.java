package org.tmf.dsmapi.agreementspec;

import org.tmf.dsmapi.agreement.model.AgreementSpecification;
import org.tmf.dsmapi.commons.exceptions.BadUsageException;
import org.tmf.dsmapi.commons.exceptions.UnknownResourceException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by atinsingh on 4/6/17.
 */

@Stateless
@Path("/agreementSpecification")
public class AgreementSpecificationResource {

    @EJB
    AgreementSpecificationFacade agreementSpecificationFacade;

    public AgreementSpecificationResource() {
    }

    /**
     *
     * @param specification
     * @param info
     * @return
     * @throws BadUsageException
     * @throws UnknownResourceException
     */

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(AgreementSpecification specification, @Context UriInfo uriInfo) throws BadUsageException, UnknownResourceException {
        //Create entity
        Response response = null;
        try {
            agreementSpecificationFacade.checkCreation(specification);
            agreementSpecificationFacade.create(specification);
            specification.setHref(uriInfo.getAbsolutePath()+"/"+specification.getId());
            agreementSpecificationFacade.edit(specification);

            // create response;
             response = Response.status(Response.Status.CREATED).entity(specification).build();

        }catch (BadUsageException ex){
             response = Response.status(Response.Status.BAD_REQUEST).build();
        }catch (Exception ex){
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return response;
    }


    // GET /agreementSpecification?fields=&{filtering}

    public Response find(@Context UriInfo uriInfo) throws BadUsageException{
        //get all queryparams

        MultivaluedMap<String,String> queryParams = uriInfo.getQueryParameters();

        Map<String, List<String>> entry = new HashMap();
        for(Map.Entry<String,List<String>> listEntry : queryParams.entrySet()){
            entry.put(listEntry.getKey(),listEntry.getValue());
        }

        return null;

    }
}
