package org.tmf.dsmapi.agreementspec;

import org.tmf.dsmapi.agreement.model.AgreementSpecification;
import org.tmf.dsmapi.commons.exceptions.BadUsageException;
import org.tmf.dsmapi.commons.exceptions.UnknownResourceException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
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
@Path("/agreementManagement/agreementSpecification")
public class AgreementSpecificationResource {

    @EJB
    AgreementSpecificationFacade agreementSpecificationFacade;

    public AgreementSpecificationResource() {
    }

    /**
     *
     * @param specification
     * @param uriInfo
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
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response find( @Context UriInfo uriInfo) throws BadUsageException{
        //get all queryparams

        List<AgreementSpecification> agreementSpecifications = agreementSpecificationFacade.findAll();

        Response response;
        if(agreementSpecifications!=null){
           response = Response.ok(agreementSpecifications).build();
        }else{
            response = Response.status(Response.Status.NOT_FOUND).build();
        }
        return response;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findbyID( @PathParam("id") String id, @Context UriInfo uriInfo) throws BadUsageException, UnknownResourceException{
        //get all queryparams

         AgreementSpecification specification = agreementSpecificationFacade.find(id);

        Response response;
        if(specification!=null){
            response = Response.ok(specification).build();
        }else{
            response = Response.status(Response.Status.NOT_FOUND).build();
        }
        return response;
    }
}