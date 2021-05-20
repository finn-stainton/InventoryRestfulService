/*
 * Dreamt, Designed and Developed by Finn Stainton. 
 * Copyright (c) 2021. 
 */
package io.finnstainton.inventoryrestfulservice;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Component @code{"Entity"} related to mvc0044_Components table. 
 * Represents an electronic component, e.g. ATMEGA 328p
 * @author finnstainton (17982742)
 */
@Entity(name = "Component")
@Table(name = "mvc0044_Components")
@XmlRootElement
public class Component implements Serializable{
    private static final long serialVersionUID = 1L;
    private static String Q = "\"";
    
    @Id
    @GeneratedValue
    private int idComponent;
    private String manuID;
    private String description;
    private String val;
    private String binId;
    private String category;
    private String dataSheetId;
    private String manufacturer;
    @Column(name = "package")
    private String packageName;
    private String type;
    private String qtyUnit;
    private Float qty;
    private Float restockLevel;
    private boolean fav;

    public Component() {
        
    }
    
    // Getters and Setters
    
    public int getIdComponent() {
        return idComponent;
    }

    public void setIdComponent(int idComponent) {
        this.idComponent = idComponent;
    }

    public String getManuID() {
        return manuID;
    }

    public void setManuID(String manuID) {
        this.manuID = manuID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getBinId() {
        return binId;
    }

    public void setBinId(String binId) {
        this.binId = binId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDataSheetId() {
        return dataSheetId;
    }

    public void setDataSheetId(String dataSheetId) {
        this.dataSheetId = dataSheetId;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQtyUnit() {
        return qtyUnit;
    }

    public void setQtyUnit(String qtyUnit) {
        this.qtyUnit = qtyUnit;
    }

    public Float getQty() {
        return qty;
    }

    public void setQty(Float qty) {
        this.qty = qty;
    }

    public Float getRestockLevel() {
        return restockLevel;
    }

    public void setRestockLevel(Float restockLevel) {
        this.restockLevel = restockLevel;
    }

    public boolean isFav() {
        return fav;
    }

    public void setFav(boolean fav) {
        this.fav = fav;
    }

    
    public String jsonString() {
        return '{' + 
                Q + "idComponent" + Q + ": " + Q + idComponent + Q + ", " +
                Q + "manuID" + Q + ": " + Q + manuID + Q + ", " +
                Q + "description" + Q + ": " + Q + description + Q + ", " + 
                Q + "value" + Q + ": " + Q + val + Q + ", " + 
                Q + "binId=" + Q + ": " + Q + binId + Q + ", " +
                Q + "category=" + Q + ": " + Q + category + Q + ", " +
                Q + "dataSheetId=" + Q + ": " + Q + dataSheetId + Q + ", " +
                Q + "manufacturer=" + Q + ": " + Q + manufacturer + Q + ", " +
                Q + "packageName=" + Q + ": " + Q + packageName + Q + ", " +
                Q + "type=" + Q + ": " + Q + type + Q + ", " +
                Q + "qtyUnit=" + Q + ": " + Q + qtyUnit + Q + ", " +
                Q + "qty=" + Q + ": " + Q + qty + Q + ", " +
                Q + "restockLevel=" + Q + ": " + Q + restockLevel + Q + ", " +
                Q + "fav=" + Q + ": " + Q + fav + Q + 
                '}';
    }
    
}