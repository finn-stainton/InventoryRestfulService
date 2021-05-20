/*
 * Dreamt, Designed and Developed by Finn Stainton. 
 * Copyright (c) 2021. 
 */
package io.finnstainton.inventoryrestfulservice;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id; 
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * BOMComponent @code{"Entity"} related to mvc0044_BOMComponents table. 
 * Represents a BOM item, which is linked to one Component and Project. 
 * @author finnstainton (17982742)
 */
@Entity(name = "BOMComponent")
@Table(name = "mvc0044_BOMComponents")
public class BOMComponent implements Serializable{
    private static final long serialVersionUID = 1L;
    private static final String Q = "\"";
    
    @Id
    @GeneratedValue
    private int idBOMComponents;
    private float qty;
    private String refDes;
    private String notes;
    private int idProject;
//    private int idComponent;
//    @OneToOne(fetch = FetchType.LAZY)
//    private Component component;
//    (cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @ManyToOne
    @PrimaryKeyJoinColumn(name="idProject")
    private Project project;

    public BOMComponent() {
    }
    
    // Getters and Setters
    
    public int getIdBOMComponent() {
        return idBOMComponents;
    }

    public void setIdBOMComponent(int idBOMComponents) {
        this.idBOMComponents = idBOMComponents;
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

    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

//    public int getIdComponent() {
//        return idComponent;
//    }
//
//    public void setIdComponent(int idComponent) {
//        this.idComponent = idComponent;
//    }

    public int getIdBOMComponents() {
        return idBOMComponents;
    }

    public void setIdBOMComponents(int idBOMComponents) {
        this.idBOMComponents = idBOMComponents;
    }

//    public Component getComponent() {
//        return component;
//    }
//
//    public void setComponent(Component component) {
//        this.component = component;
//    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    /**
     * Prints json like String of BOMComponent including Component details. 
     * Example
     * { "sku": "U328", "qty": "1", "mfg p/n": "ATMEGA328", "manu": "Atmel", 
     *  "desc": "8-bit AVR Microcontroller", "value": "", "package": "DIP-24",
     *  "type": "thur-hole", "refdes": "U1", "notes": "Firmware rev 3.2" }
     * @return String 
     */
    public String jsonString() {
        return "{" + Q + "qty"+ Q + ":" + Q + qty + Q +", " 
            + Q + "refdes" + Q + ":" + Q + refDes + Q + ", "
            + Q + "notes" + Q + ":" + Q + notes + Q 
            + "}";
    }
    
    
}

//return "{" 
//            + Q + "sku" + Q + ":" + Q + component.getIdComponent() + Q + ", "
//            + Q + "qty"+ Q + ":" + Q + qty + Q +", " 
//            + Q + "mfg#" + Q + ":" + Q + component.getManuID() + Q + ", "
//            + Q + "manu" + Q + ":" + Q + component.getManufacturer() + Q + ", "
//            + Q + "desc" + Q + ":" + Q + component.getDescription() + Q + ", "
//            + Q + "value" + Q + ":" + Q + component.getVal() + Q + ", "
//            + Q + "package" + Q + ":" + Q + component.getPackageName() + Q + ", "
//            + Q + "type" + Q + ":" + Q + component.getType() + Q + ", "
//            + Q + "refdes" + Q + ":" + Q + refDes + Q + ", "
//            + Q + "notes" + Q + ":" + Q + notes + Q
//            + "}";