/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.rest;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dk.cphbusiness.entity.Facade;
import dk.cphbusiness.entity.Project;
import dk.cphbusiness.entity.ProjectUser;
import exceptions.ProjectNotFoundException;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author sofus
 */
@Path("projects")
public class ProjectRestService {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RestService
     */
    Gson gson;

    public ProjectRestService() {
        gson = new GsonBuilder().setPrettyPrinting().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProject(String p) {
        Project project = gson.fromJson(p, Project.class);
        Facade.createProject(project);
        return Response.status(Response.Status.CREATED).type(MediaType.APPLICATION_JSON).entity(gson.toJson(project)).build();
    }
    
    @GET
    @Produces("application/json")
    @Path("{id}")
    public Response getProject(@PathParam("id")int id) {        
        Response r = Response.status(Response.Status.OK).entity(makeProject(Facade.findProject(new Long(id))).toString()).build();  
        return r;
    }
    
    @GET
    @Consumes("application/json")
    @Produces("application/json")
    public Response getProjects() {
        JsonArray out= new JsonArray();
        JsonObject jProject = new JsonObject();
        List<Project>projects= Facade.getProjects();
        System.out.println(projects.size());
        for (Project p : projects) {
            jProject=makeProject(p);
           
            out.add(jProject);
        }
        return Response.status(Response.Status.OK).entity(out.toString()).build();
    }
    
    @DELETE
    @Consumes("application/json")
    @Produces("application/json")
    @Path("{id}")
    public Response deleteProject(@PathParam("id") String id){
    Facade.deleteProject(new Long(id));
    return Response.status(Response.Status.OK).build();
    }
    
    @PUT
    @Path("{uid}/{pid}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response addUserToProject(@PathParam("uid") String uid, @PathParam("pid") String pid){
    Facade.assignUserToProject(new Long(pid), new Long(uid));
    return Response.status(Response.Status.OK).build();
    }
    
    private JsonObject makeProject(Project project){
          JsonObject jProject=new JsonObject();
            jProject.addProperty("id", project.getId());
            jProject.addProperty("projectName", project.getName());
            jProject.addProperty("description", project.getDescription());
            return jProject;
    }
}
