package service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import model.User;

@Path("users")
public interface IAdminService {

	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_HTML)
	public int registerUser(User user);
	
	@GET
	@Path("/{id}")
	@Consumes(MediaType.TEXT_HTML)
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@QueryParam(value = "id") int id);
}
