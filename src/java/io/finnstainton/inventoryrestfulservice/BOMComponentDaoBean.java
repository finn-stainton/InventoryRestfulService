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
public class BOMComponentDaoBean implements ProjectDao {
   @PersistenceContext
   private EntityManager entityManager;

    @Override
    public Project addNewProject(String title, String schematicId, int bomid) {
        try {
            Project project = new Project();
            project.setTitle(title);
            project.setSchematicId(schematicId);
            project.setBomid(bomid);
            entityManager.persist(project);
            return project;
        } catch(EntityExistsException e) {
            return null;
        }
    }
    
    @Override
    public Collection<Project> getAllProjects() {
        Collection<Project> projects = new ArrayList<Project>();
        
        return projects;
    }

    @Override
    public Project getProject(int idProject) {
        Project newProject= entityManager.find(Project.class, idProject);
        return newProject;
    }

    @Override
    public void updateProject(Project project) {
        entityManager.merge(project);
    }

    @Override
    public void removeProject(Project project) {
        entityManager.remove(project);
    }

}
