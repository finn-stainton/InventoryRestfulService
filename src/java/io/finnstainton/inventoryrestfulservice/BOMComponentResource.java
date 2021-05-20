/*
 * Dreamt, Designed and Developed by Finn Stainton. 
 * Copyright 2021. 
 */
package io.finnstainton.inventoryrestfulservice;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author finn
 */
@Named
@Path("/bomcomponent")
public class BOMComponentResource {
    private static String ITEM_NOT_FOUND = "{\"error\":\"item-not-found\"}";
    private static String BOMCOMPONENT_NOT_ADDED = "{\"error\":\"bomcomponent-not-added\"}";
    @EJB
    private BOMComponentDaoBean bomComponentBean;
    @Context
    private UriInfo context;

    public BOMComponentResource() {
    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getBOMComponent(@QueryParam("id") @DefaultValue("-1") int id) {
        BOMComponent bomComponent = bomComponentBean.getBOMComponent(id);
        if (bomComponent != null) {
            StringBuilder buffer = new StringBuilder();
            buffer.append(bomComponent.jsonString());
            return buffer.toString();
        } else {
            return ITEM_NOT_FOUND;
        }
    }
    
    /**
     * Create new BOMComponent from JSON input
     * @param json 
     * @return 
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
//    @Path("/newbomcomponent")
    public Response addNewProject(String json) {
        try {
            JSONParser jsonParser = new JSONParser();
            Object jsonObj = jsonParser.parse(json);
            JSONObject jsonObject = (JSONObject) jsonObj;

            String refdes = (String) jsonObject.get("title");
            String notes = (String) jsonObject.get("schematicID");
            Double qty = (Double) jsonObject.get("qty");
            int idProject = (int) jsonObject.get("idProject");
            int idComponent = (int) jsonObject.get("idProject");
            Project project = null;
            Component component = null;
            
            BOMComponent bomComponent = bomComponentBean.addNewBOMComponent(component, project, qty.floatValue(), refdes, notes);
            // Try retrieve project just created
            if(bomComponent != null) { // Success, return json of project
                return Response.ok(bomComponent.jsonString()).build();
            } else {
                return Response.ok(BOMCOMPONENT_NOT_ADDED).build();
            }  
        } catch (NullPointerException nullPointerExc) {
            System.err.println("NullPointerException");
            return Response.status(500).build();
        } catch (ParseException ex) {
            Logger.getLogger(ProjectResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
