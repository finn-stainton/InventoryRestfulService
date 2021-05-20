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
import javax.ws.rs.POST;
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
@Path("/component")
public class ComponentResource {
    private static String ITEM_NOT_FOUND = "{\"error\":\"item-not-found\"}";
    private static String COMPONENT_NOT_CREATED = "{\"error\":\"component-not-created\"}";
    @EJB
    private ComponentDaoBean componentBean;
    @Context
    private UriInfo context;
    
    public ComponentResource() {
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getComponent(@QueryParam("sku") @DefaultValue("-1") int sku) {
        Component component = componentBean.getComponent(sku);
        if (component != null) {
            StringBuilder buffer = new StringBuilder();
            buffer.append(component.jsonString());
            return buffer.toString();
        } else {
            return ITEM_NOT_FOUND;
        }
    }

    /**
     * Create new Component from JSON input
     * Example:
     * {
            "manuID": "ATMEGA328P", 
            "desc": "8-bit AVR Microcontroller", 
            "value": "", 
            "binId": "A8573",
            "category": "microcontroller", 
            "dataSheetId": "", 
            "manu": "Atmel", 
            "package": "DIP-24", 
            "type": "thur-hole", 
            "qtyUnit": "unit", 
            "qty": 4.0, 
            "restock": 0.0, 
            "fav": true
        }
     * @param json 
     * @return 
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
//    @Path("/newcomponent")
    public Response addNewComponent(String json) {
        try {
            JSONParser jsonParser = new JSONParser();
            Object jsonObj = jsonParser.parse(json);
            JSONObject jsonObject = (JSONObject) jsonObj;

            String manuID = (String) jsonObject.get("manuID");
            String description = (String) jsonObject.get("description");
            String value = (String) jsonObject.get("value");
            String binId = (String) jsonObject.get("binId");
            String category = (String) jsonObject.get("category");
            String dataSheetId = (String) jsonObject.get("dataSheetId");
            String manufacturer = (String) jsonObject.get("manufacturer");
            String packageName = (String) jsonObject.get("package");
            String type = (String) jsonObject.get("type");
            String qtyUnit = (String) jsonObject.get("qtyUnit");
            Double qty = (Double) jsonObject.get("qty");
            Double restockLevel = (Double) jsonObject.get("restockLevel");
            boolean fav = (boolean) jsonObject.get("fav");

            Component component = componentBean.addNewComponent(manuID, description, value, 
                    binId, category, dataSheetId, manufacturer, packageName, type, qtyUnit, 
                    qty.floatValue(), restockLevel.floatValue(), fav);
            // Try retrieve project just created
            if(component != null) { // Success, return json of component
                return Response.ok(component.jsonString()).build();
            } else {
                return Response.ok(COMPONENT_NOT_CREATED).build();
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
     * Update Component
     * @param sku
     * @param json
     * @return 
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateComponent(@QueryParam("sku") @DefaultValue("-1") int sku, String json) {
        if(sku == -1) {
            
        } else if(!getComponent(sku).equals(ITEM_NOT_FOUND)) { //Check if Component Exists
            try {
                JSONParser jsonParser = new JSONParser();
                Object jsonObj = jsonParser.parse(json);
                JSONObject jsonObject = (JSONObject) jsonObj;

                Component newComponent = new Component();
                newComponent.setManuID((String) jsonObject.get("manuID"));
                newComponent.setDescription((String) jsonObject.get("description"));
                newComponent.setVal((String) jsonObject.get("value"));
                newComponent.setBinId((String) jsonObject.get("binId"));
                newComponent.setCategory((String) jsonObject.get("category"));
                newComponent.setDataSheetId((String) jsonObject.get("dataSheetId"));
                newComponent.setManufacturer((String) jsonObject.get("manufacturer"));
                newComponent.setPackageName((String) jsonObject.get("package"));
                newComponent.setType((String) jsonObject.get("type"));
                newComponent.setQtyUnit((String) jsonObject.get("qtyUnit"));
                Double qty = (Double) jsonObject.get("qty");
                Double restock = (Double) jsonObject.get("restockLevel");
                newComponent.setQty(qty.floatValue());
                newComponent.setRestockLevel(restock.floatValue());
                newComponent.setFav((boolean) jsonObject.get("fav"));

                Component component = componentBean.updateComponent(newComponent);
                // Try retrieve project just created
                if(component != null) { // Success, return json of component
                    return Response.ok(component.jsonString()).build();
                } else {
                    return Response.ok(COMPONENT_NOT_CREATED).build();
                }  
            } catch (NullPointerException nullPointerExc) {
                System.err.println("NullPointerException");
                return Response.status(500).build();
            } catch (ParseException ex) {
                Logger.getLogger(ComponentResource.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
}
