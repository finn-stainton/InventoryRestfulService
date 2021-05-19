/*
 * Dreamt, Designed and Developed by Finn Stainton. 
 * Copyright (c) 2021. 
 */
package io.finnstainton.inventoryrestfulservice;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Component @code{"Entity"} related to mvc0044_Components table. 
 * Represents an electronic component, e.g. ATMEGA 328p
 * @author finnstainton (17982742)
 */
@Entity
@Table(name = "mvc0044_BOMComponents")
public class BOMComponent implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private int idBOMComponents;
    private int bomId;
    private int idComponent;
    private float qty;
    private String refDes;
    private String notes;

    public BOMComponent() {
    }
    
    // Getters and Setters
    
    public int getIdBOMComponent() {
        return idBOMComponents;
    }

    public void setIdBOMComponent(int idBOMComponents) {
        this.idBOMComponents = idBOMComponents;
    }

    public int getBomId() {
        return bomId;
    }

    public void setBomId(int bomId) {
        this.bomId = bomId;
    }

    public int getIdComponent() {
        return idComponent;
    }

    public void setIdComponent(int idComponent) {
        this.idComponent = idComponent;
    }

    public float getQty() {
        return qty;
    }

    public void setQty(float qty) {
        this.qty = qty;
    }

    public String getRefDes() {
        return refDes;
    }

    public void setRefDes(String refDes) {
        this.refDes = refDes;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    
}