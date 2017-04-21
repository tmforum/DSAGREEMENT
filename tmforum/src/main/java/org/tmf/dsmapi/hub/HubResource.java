package org.tmf.dsmapi.hub;

import org.tmf.dsmapi.commons.exceptions.BadUsageException;
import org.tmf.dsmapi.commons.exceptions.ExceptionType;
import org.tmf.dsmapi.commons.exceptions.UnknownResourceException;
import org.tmf.dsmapi.commons.jaxrs.Report;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by atinsingh on 4/17/17.
 */

@Stateless
@Path("/agreementManagement/hub")
public class HubResource {

    @EJB
    HubFacade hubFacade;
    private static Logger logger = Logger.getLogger(HubResource.class.getName());

    public HubResource() {
    }

    /**
     * Add a new hub entity
     * @param entity
     * @return
     * @throws BadUsageException
     */

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Hub entity) throws BadUsageException {
        try {
            Hub hub = hubFacade.find(entity.getId());
            if(hub!=null){
                throw new BadUsageException(ExceptionType.BAD_USAGE_FORMAT, "Hub with id "+entity.getId()+ "Already exists in system");
            }
        }catch (UnknownResourceException ex){
            logger.log(Level.INFO, "New resource will be created");
        }

        hubFacade.create(entity);
        return Response.status(Response.Status.CREATED).entity(entity).build();

    }

    /**
     * Function will remove the subscribed resource,
     * @param id
     * @return
     * @throws UnknownResourceException
     */
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") String id) throws UnknownResourceException{
        hubFacade.remove(id);
        return Response.accepted().build();
    }


    @DELETE
    public Response delete(){
        return null;
    }

    /**
     * Find all the subscribed hub,
     * mapped to GET http://host:port/DSAgreement/agreementManagement/hub
     * @return
     */

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response find() throws UnknownResourceException{
        //find list of all hubs in the database
        List<Hub> hubList = hubFacade.findAll();
        if(hubList==null){
            throw new UnknownResourceException(
                    ExceptionType.UNKNOWN_RESOURCE, "No active hub subscription in the database");
        }
        return Response.ok(hubList).build();
    }

    /**
     * Function will return the hub for id and will match following URL
     * GET http://host:port/DSAgreement/agreementManagement/hub/{id}
     * @param id
     * @return
     * @throws UnknownResourceException
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("id") String id) throws UnknownResourceException{
        Hub hub = hubFacade.find(id);
        return Response.ok(hub).build();
    }


}