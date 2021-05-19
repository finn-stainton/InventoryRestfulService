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
public interface ComponentDao {
    //Create
    public Component addNewComponent(String manuID, String description,
    String binId, String category, String dataSheetId, String manufacturer,
    String packageName, String type, String qtyUnit, Float qty, 
    Float restockLevel, boolean fav);
    //Retrieve 
    public Component getComponent(int idComponent);
    //Retrieve component Id's from manuID search
    
    //Update
    public void updateComponent(Component component);
    //Delete
    public void removeComponent(Component component);
}
