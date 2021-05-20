/*
 * Dreamt, Designed and Developed by Finn Stainton. 
 * Copyright (c) 2021. 
 */
package io.finnstainton.inventoryrestfulservice;

import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.jws.WebService;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/*
 * Stateless session bean of a Project Data Access Object
 * pattern with an JPA Project entity
 * @author finnstainton (17982742)
 */
//@WebService // TODO: Delete this after testing
@Stateless
@LocalBean
public class BOMComponentDaoBean implements BOMComponentDao {
   @PersistenceContext
   private EntityManager entityManager;

    @Override
    public BOMComponent addNewBOMComponent(Component component, Project project, 
            Float qty, String refDes, String notes) {
        try{
            BOMComponent bomComponent = new BOMComponent();
            bomComponent.setQty(qty);
            bomComponent.setRefDes(refDes);
//            bomComponent.setComponent(component);
            bomComponent.setProject(project);

            entityManager.persist(bomComponent);
            return bomComponent;
        } catch(EntityExistsException e) {
            return null;
        }
    }

    @Override
    public BOMComponent getBOMComponent(int idBOMComponent) {
        BOMComponent bomComponent = entityManager.find(BOMComponent.class, idBOMComponent);
        return bomComponent;
    }

    @Override
    public void updateBOMComponent(BOMComponent bomComponent) {
        entityManager.merge(bomComponent);
    }

    @Override
    public void removeBOMComponent(BOMComponent component) {
        entityManager.remove(component);
    }

 

}
