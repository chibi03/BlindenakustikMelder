package service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import model.Defects;

@Path("defects")
public interface IDefectService {

	@POST
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void addDefect(@QueryParam(value = "id") int id, Defects defect);
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Defects getLatestDefects(@QueryParam(value = "id") int id);
	
	

}
