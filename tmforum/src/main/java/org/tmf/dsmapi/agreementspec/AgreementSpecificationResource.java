package org.tmf.dsmapi.agreementspec;

import org.apache.commons.lang3.StringUtils;
import org.tmf.dsmapi.agreement.model.AgreementSpecification;
import org.tmf.dsmapi.commons.exceptions.BadUsageException;
import org.tmf.dsmapi.commons.exceptions.UnknownResourceException;
import org.tmf.dsmapi.commons.utils.Jackson;
import org.tmf.dsmapi.commons.utils.PropertyFilter;
import org.tmf.dsmapi.commons.utils.TMFFilter;
import org.tmf.dsmapi.commons.utils.URIParser;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.*;

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
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
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
    public Response find(@Context UriInfo uriInfo) throws BadUsageException, Exception{
        //get all queryparams
        MultivaluedMap<String, String> queryParameters = uriInfo.getQueryParameters();

        Map<String, List<String>> mutableMap = new HashMap();
        for (Map.Entry<String, List<String>> e : queryParameters.entrySet()) {
            mutableMap.put(e.getKey(), e.getValue());
        }

        //System.out.println(mutableMap.toString());

        // fields to filter view

        Set<String> fieldSet = URIParser.getFieldsSelection(mutableMap);

        //get only matched entities in future as of now I am pulling all.


        Object entity;

        System.out.println("Query Param Field Set" + fieldSet.toString());
        if(fieldSet.isEmpty()){
            System.out.println("Query param is empty");
        }



            // construct filter as id,name, serviceCategory, serviceCategory.id, serviceCategory.name...

         if (fieldSet.isEmpty() || fieldSet.contains(URIParser.ALL_FIELDS)) {
                // construct filter as id,name, serviceCategory, serviceCategory.id, serviceCategory.name...
             List<AgreementSpecification>specifications = agreementSpecificationFacade.findAll();

             entity = specifications;
            }else{

                Set<AgreementSpecification> agreementSpecifications = findByCriteria(mutableMap);
                Set<String> modifiedFieldset = getFilterFields(fieldSet);
                //Apply filter on the entities
                entity = TMFFilter.applyFilter(agreementSpecifications,modifiedFieldset);
        }


        //Check what I am getting inside the filter map.



        //System.out.println(entity.toString());
        Response response;
        if(entity!=null){
           response = Response.ok(entity).build();
        }else{
            response = Response.status(Response.Status.NOT_FOUND).build();
        }
        return response;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findbyID( @PathParam("id") String id, @Context UriInfo uriInfo) throws BadUsageException, UnknownResourceException,Exception{

        MultivaluedMap<String, String> queryParameters = uriInfo.getQueryParameters();

        Map<String, List<String>> mutableMap = new HashMap();
        for (Map.Entry<String, List<String>> e : queryParameters.entrySet()) {
            mutableMap.put(e.getKey(), e.getValue());
        }

        //System.out.println(mutableMap.toString());

        // fields to filter view

        Set<String> fieldSet = URIParser.getFieldsSelection(mutableMap);

        AgreementSpecification specification = agreementSpecificationFacade.find(id);

        Object entity;


        if (fieldSet.isEmpty() || fieldSet.contains(URIParser.ALL_FIELDS)) {
            // construct filter as id,name, serviceCategory, serviceCategory.id, serviceCategory.name...
            entity = specification;
        }else{


            Set<String> modifiedFieldset = getFilterFields(fieldSet);
            //Apply filter on the entities
            entity = TMFFilter.applyFilter(specification,modifiedFieldset);
        }

        Response response;
        if(specification!=null){
            response = Response.ok(entity).build();
        }else{
            response = Response.status(Response.Status.NOT_FOUND).build();
        }
        return response;
    }

     /*
         * This fieldSet will have sub-class or sub node as serviceCategory.id
         * will fill not match this field, so we need to create a new Set, which will have serviceCategory and id as fields
         * field might have assigned values as well like id=4031, we need to strip assigned value as well.
         */


    private Set<String> getFilterFields(Set<String> fieldSet){

        Set<String> modifiedFieldset = new HashSet<String>();

        for(String field:fieldSet){
            if(StringUtils.countMatches(field,".")>0){
                while (StringUtils.countMatches(field,".")>0){
                    String[] subfield = StringUtils.split(field,".",2);
                    if(subfield[0].contains("=")){
                        String [] fieldName = StringUtils.split(subfield[0],'=');
                        modifiedFieldset.add(fieldName[0]);
                    }else{
                        modifiedFieldset.add(subfield[0]);


                    }
                    modifiedFieldset.add(field);
                    field = subfield[1];
                }
                if(field.contains("=")){
                    String [] fieldName = StringUtils.split(field,'=');
                    modifiedFieldset.add(fieldName[0]);
                }else{
                    modifiedFieldset.add(field);
                }
            }else {
                if(field.contains("=")){
                    String [] fieldName = StringUtils.split(field,'=');
                    modifiedFieldset.add(fieldName[0]);
                }else{
                    modifiedFieldset.add(field);
                }
            }

        }
        return modifiedFieldset;

    }


    // return Set of unique elements to avoid List with same elements in case of join
    private Set<AgreementSpecification> findByCriteria(Map<String, List<String>> criteria) throws BadUsageException {

        List<AgreementSpecification> resultList = null;
        if (criteria != null && !criteria.isEmpty()) {
            resultList = agreementSpecificationFacade.findByCriteria(criteria, AgreementSpecification.class);
        } else {
            resultList = agreementSpecificationFacade.findAll();
        }
        if (resultList == null) {
            return new LinkedHashSet<AgreementSpecification>();
        } else {
            return new LinkedHashSet<AgreementSpecification>(resultList);
        }
    }

}