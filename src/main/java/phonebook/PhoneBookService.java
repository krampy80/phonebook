package phonebook;

import javax.ws.rs.Path;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import hu.hugo.phonebook.PhoneBook;
import model.UserName;
import phonebook.action.UserAction;


@Path("")
@Consumes(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.TEXT_HTML})
@Produces(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.TEXT_HTML})
public class PhoneBookService {
	
	@Inject
	private UserAction userAction;
	
	@Path("/main")
	@Consumes(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.TEXT_HTML})
	@Produces(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.TEXT_HTML})
	@POST
	public PhoneBook createUser (PhoneBook phonebook) {
		
		return phonebook;
	}
	
	@Path("/main")
	@Consumes(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.TEXT_HTML})
	@Produces(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.TEXT_HTML})
	@GET
	public PhoneBook createUserGet () {
		
		PhoneBook pbook = new PhoneBook();
		pbook.setFirstname("vd");
		pbook.setLastname("fdsf");
		pbook.setPhonenumber("111");
		
		
		return pbook;
		
	}
	@Path("/list")
	@Consumes(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.TEXT_HTML})
	@Produces(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.TEXT_HTML})
	@GET
	public List<PhoneBook> listUsers(){
		return userAction.getUsers();
	}
	
}
