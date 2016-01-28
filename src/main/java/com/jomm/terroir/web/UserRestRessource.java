package com.jomm.terroir.web;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.jomm.terroir.business.UserEntity;
import com.jomm.terroir.business.UserEntityServiceInterface;

@Path("/animal")
public class UserRestRessource {
	
	@Inject
	private UserEntityServiceInterface userService;
	
	// This method is called if XML or JSON is requested MediaType.APPLICATION_JSON, 
//	@GET
//	@Produces({MediaType.APPLICATION_XML})
//	public List<UserEntity> getListUsers() {
//		return userService.getAllUsers();
//	}
}
