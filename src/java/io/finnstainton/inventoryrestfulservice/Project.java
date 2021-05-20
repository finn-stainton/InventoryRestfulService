/*
 * Dreamt, Designed and Developed by Finn Stainton. 
 * Copyright (c) 2021. 
 */
package io.finnstainton.inventoryrestfulservice;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Project @code{"Entity"} related to mvc0044_Projects table. 
 * Represents project which has a bill of materials.
 * @author finnstainton (17982742)
 */
@Entity(name = "Project")
@Table(name = "mvc0044_Projects")
public class Project implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String QUOTE = "\"";
    
    @Id
    @GeneratedValue
    private int idProject;
    private String title;
    private String schematicId;
    //@ElementCollection    
    @OneToMany(mappedBy = "project")
    private ArrayList<BOMComponent> BOM; 

    public Project() {
        
    }
    
    // Getters and Setters

    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSchematicId() {
        return schematicId;
    }

    public void setSchematicId(String schematicId) {
        this.schematicId = schematicId;
    }

    public ArrayList<BOMComponent> getBOM() {
        return BOM;
    }

    public void setBOM(ArrayList<BOMComponent> BOM) {
        this.BOM = BOM;
    }

    public String jsonString() {
        return '{' + 
            QUOTE + "idProject" + QUOTE + ":" + QUOTE + idProject + QUOTE + ", " +
            QUOTE + "title=" + QUOTE + ":" + QUOTE + title + QUOTE +  ", " +
            QUOTE + "schematicId=" + QUOTE + ":" + QUOTE + schematicId + QUOTE +", " +
            jsonBOMString() + 
            '}' ;
    }
    
    public String jsonBOMString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(QUOTE).append("bom").append(QUOTE).append(":");
        buffer.append('[');
        for(BOMComponent c : BOM) {
            buffer.append(c.jsonString());
        }
        buffer.append(']');
        return buffer.toString();
    }
    
    
}