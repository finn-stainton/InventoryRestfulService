/*
 * Dreamt, Designed and Developed by Finn Stainton. 
 * Copyright 2021. 
 */
package io.finnstainton.inventoryrestfulservice;

import javax.ejb.Remote;

/**
 *
 * @author finn
 */
@Remote
public interface BOMComponentDao {
    //Create
    public BOMComponent addNewBOMComponent(int bomId, int idComponent, Float qty, 
            String refDes, String notes);
    //Retrieve 
    public BOMComponent getBOMComponent(int idComponent);
    //Retrieve component Id's from manuID search
    
    //Update
    public void updateBOMComponent(BOMComponent component);
    //Delete
    public void removeBOMComponent(BOMComponent component);
}
