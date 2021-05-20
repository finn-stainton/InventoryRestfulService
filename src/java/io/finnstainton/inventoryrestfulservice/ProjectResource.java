/*
 * Dreamt, Designed and Developed by Finn Stainton. 
 * Copyright 2021. 
 */
package io.finnstainton.inventoryrestfulservice;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author finn
 */
@Named
@Path("/project")
public class ProjectResource {
    private static String ITEM_NOT_FOUND = "{\"error\":\"item-not-found\"}";
    private static String PROJECT_NOT_CREATED = "{\"error\":\"project-not-created\"}";
    @EJB
    private ProjectDaoBean projectBean;
    @Context
    private UriInfo context;

    public ProjectResource() {
    }

    /**
     * Get single project by projectID
     * @param projectID
     * @return 
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getProject(@QueryParam("id") @DefaultValue("-1") int projectID) {
        Project project = projectBean.getProject(projectID);
        if (project != null) {
            StringBuilder buffer = new StringBuilder();
            buffer.append(project.jsonString());
            return buffer.toString();
        } else {
            return ITEM_NOT_FOUND;
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/bom")
    public String getBOM(@QueryParam("id") @DefaultValue("-1") int projectID) {
        return projectBean.getProject(projectID).jsonBOMString();
    }
    
    /**
     * Create Project from JSON input
     * @param json 
     * @return 
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
//    @Path("/newproject")
    public Response addNewProject(String json) {
        try {
            JSONParser jsonParser = new JSONParser();
            Object jsonObj = jsonParser.parse(json);
            JSONObject jsonObject = (JSONObject) jsonObj;

            String title = (String) jsonObject.get("title");
            String schematicID = (String) jsonObject.get("schematicID");

            Project project = projectBean.addNewProject(title, schematicID);
            // Try retrieve project just created
            if(project != null) { // Success, return json of project
                return Response.ok(project.jsonString()).build();
            } else {
                return Response.ok(PROJECT_NOT_CREATED).build();
            }  
        } catch (NullPointerException nullPointerExc) {
            System.err.println("NullPointerException");
            return Response.status(500).build();
        } catch (ParseException ex) {
            Logger.getLogger(ProjectResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * Update
     * @param projectID
     * @param formParams 
     * @return  
     */
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String updateProject(@QueryParam("id") @DefaultValue("-1") int projectID, 
            MultivaluedMap<String, String> formParams) {
        Project project = projectBean.getProject(projectID);
        if (project != null) {
            StringBuilder buffer = new StringBuilder();
            buffer.append(project.jsonString());
            return buffer.toString();
        } else {
            return ITEM_NOT_FOUND;
        }
//        String petName = formParams.getFirst("petName");
//        String petOwner = formParams.getFirst("ownerName");
//        String petSpecies = formParams.getFirst("petSpecies");
//        projectBean.updateProject(project);
   }
}
