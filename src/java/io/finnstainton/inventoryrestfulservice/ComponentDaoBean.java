/*
 * Dreamt, Designed and Developed by Finn Stainton. 
 * Copyright (c) 2021. 
 */
package io.finnstainton.inventoryrestfulservice;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.jws.WebService;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/*
 * Stateless session bean of a Component Data Access Object
 * pattern with an JPA Component entity
 * @author finnstainton (17982742)
 */
//@WebService // TODO: Delete this after testing
@Stateless
@LocalBean
public class ComponentDaoBean implements ComponentDao {
   @PersistenceContext
   private EntityManager entityManager;

    @Override
    public Component addNewComponent(String manuID, String description, String value,
        String binId, String category, String dataSheetId, String manufacturer,
        String packageName, String type, String qtyUnit, Float qty, 
        Float restockLevel, boolean fav) {
        try {
            Component component = new Component();
            component.setManuID(manuID);
            component.setDescription(description);
            component.setVal(value);
            component.setBinId(binId);
            component.setCategory(category);
            component.setDataSheetId(dataSheetId);
            component.setManufacturer(manufacturer);
            component.setPackageName(packageName);
            component.setType(type);
            component.setQtyUnit(qtyUnit);
            component.setQty(qty);
            component.setRestockLevel(restockLevel);
            component.setFav(fav);
            entityManager.persist(component);
            return component;
        } catch(EntityExistsException e) {
            return null;
        }
    }

    @Override
    public Component getComponent(int idComponent) {
        Component newComponent = entityManager.find(Component.class, idComponent);
        return newComponent;
    }

    @Override
    public Component updateComponent(Component component) {
        return entityManager.merge(component);
    }

    @Override
    public Component removeComponent(Component component) {
        entityManager.remove(component);
        return component;
    }
}
