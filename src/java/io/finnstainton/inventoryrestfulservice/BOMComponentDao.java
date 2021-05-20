/*
 * Dreamt, Designed and Developed by Finn Stainton. 
 * Copyright 2021. 
 */
package io.finnstainton.inventoryrestfulservice;

import javax.ejb.Remote;

/**
 *
 * @author finnstainton (17982742)
 */
@Remote
public interface BOMComponentDao {
    //Create
    public BOMComponent addNewBOMComponent(Component component, Project project, 
            Float qty, String refDes, String notes);
    
    //Retrieve 
    public BOMComponent getBOMComponent(int idBOMComponent);
    
    //Retrieve component Id's from manuID search
    
    
    //Update
    public void updateBOMComponent(BOMComponent bomComponent);
    
    //Delete
    public void removeBOMComponent(BOMComponent component);
}
