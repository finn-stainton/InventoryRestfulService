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
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Project @code{"Entity"} related to mvc0044_Projects table. 
 * Represents project which has a bill of materials.
 * @author finnstainton (17982742)
 */
@Entity
@Table(name = "mvc0044_Projects")
@XmlRootElement
public class Project implements Serializable {
    private static final long serialVersionUID = 1L;
    private static String QUOTE = "\"";
    @Id
    @GeneratedValue
    private int idProject;
    private String title;
    private String schematicId;
    private int bomid;

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

    public int getBomid() {
        return bomid;
    }

    public void setBomid(int bomid) {
        this.bomid = bomid;
    }
    

    public String jsonString() {
        return "{" + 
            QUOTE + "idProject" + QUOTE + ":" + QUOTE + idProject + QUOTE + ", " +
            QUOTE + "title=" + QUOTE + ":" + QUOTE + title + QUOTE +  ", " +
            QUOTE + "schematicId=" + QUOTE + ":" + QUOTE + schematicId + QUOTE +", " +
            QUOTE + "bomid=" + QUOTE + ":" + QUOTE + bomid + QUOTE + "}" ;
    }
    
    
}