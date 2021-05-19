/*
 * Dreamt, Designed and Developed by Finn Stainton. 
 * Copyright 2021. 
 */
package io.finnstainton.inventoryrestfulservice;

import java.util.Collection;

/**
 *
 * @author finnstainton (17982742)
 */
public interface ProjectDao {
    //Create
    public Project addNewProject(String title, String schematicId, int bomid);
    
    //Retrieve
    public Collection<Project> getAllProjects();
    
    //Retrieve project by ID
    public Project getProject(int idProject);
    
    //Retrieve Project BOM
    //public Map<String, BOMComponent> getProjectBOM(int idProject);
    
    //Update
    public void updateProject(Project project);
    
    //Delete
    public void removeProject(Project project);
}
