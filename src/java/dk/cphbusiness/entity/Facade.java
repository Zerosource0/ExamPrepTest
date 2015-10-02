/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.entity;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author sofus
 */
public class Facade {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExamPrepPU");

    private static EntityManager getEM() {
        return emf.createEntityManager();
    }

    public static Project createProject(Project project){
        EntityManager em = getEM();
        em.getTransaction().begin();
        em.persist(project);
        em.getTransaction().commit();
        return project;
    }
    
    public static Project findProject(String projectName){
      EntityManager em = getEM();
      return em.createNamedQuery("Project.findByName", Project.class).getSingleResult();
    }
    
    public static Project findProject(Long id){
      EntityManager em = getEM();
      return em.find(Project.class, id);
    }
    
    public static List<Project> getProjects(){
        EntityManager em = getEM();
        return em.createNamedQuery("Project.findAll",Project.class).getResultList();
    }
    
    public static void deleteProject(Long id){
        EntityManager em = getEM();
        em.getTransaction().begin();
        em.remove(em.find(Project.class, id));
        em.getTransaction().commit();
    }
    
    public static ProjectUser createUser(ProjectUser user) {
        EntityManager em = getEM();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        return user;
    }

    public static ProjectUser findUser(String userName) {
        EntityManager em = getEM();
        return em.createNamedQuery("ProjectUser.findByUsername", ProjectUser.class).getSingleResult();
    }

    public static ProjectUser findUser(Long id) {
        EntityManager em = getEM();
        return em.find(ProjectUser.class, id);
    }

    public static List<ProjectUser> getUsers() {
        EntityManager em = getEM();
        return em.createNamedQuery("ProjectUser.findAll", ProjectUser.class).getResultList();
    }

    public static void deleteUser(Long id) {
        EntityManager em = getEM();
        em.getTransaction().begin();
        em.remove(em.find(ProjectUser.class, id));
        em.getTransaction().commit();
    }


    public static void assignUserToProject(Long projectID, Long userID) {
        EntityManager em = getEM();
        ProjectUser pu = em.find(ProjectUser.class, userID);
        em.getTransaction().begin();
        pu.setContributor(em.find(Project.class, projectID));
        em.getTransaction().commit();
    }

}
