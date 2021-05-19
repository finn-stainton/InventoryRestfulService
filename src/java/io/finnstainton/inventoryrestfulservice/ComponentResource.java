/*
 * Dreamt, Designed and Developed by Finn Stainton. 
 * Copyright 2021. 
 */
package io.finnstainton.inventoryrestfulservice;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

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
}
