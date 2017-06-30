package phonebook;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import common.exception.BaseException;
import hu.hugo.phonebook.PhoneBook;
import hu.hugo.phonebook.UpdatePhoneRequest;
import hu.hugo.phonebook.UpdatePhoneResponse;

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
	@GET
	public PhoneBook createUserGet () {
		
		PhoneBook pbook = new PhoneBook();
		pbook.setFirstname("vd");
		pbook.setLastname("fdsf");
		
		
		
		return pbook;
		
	}
	@Path("/list")
	@Consumes(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.TEXT_HTML})
	@Produces(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.TEXT_HTML})
	@GET
	public List<PhoneBook> listUsers() throws BaseException{
		return userAction.getUsers();
	}
	
	@Path("/user/{id}")
	@Consumes(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.TEXT_HTML})
	@Produces(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.TEXT_HTML})
	@GET
	public PhoneBook listUsersById(@PathParam("id") long id) throws BaseException{
		return userAction.getUserById(id);
	}
	
	@Path("/user")
	@Consumes(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.TEXT_HTML})
	@Produces(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.TEXT_HTML})
	@GET
	public PhoneBook listUsersByIdWithQueryParams(@QueryParam("id") long id) throws BaseException{
		return userAction.getUserById(id);
	}
	
	@Path("/nativuser/{id}")
	@Consumes(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.TEXT_HTML})
	@Produces(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.TEXT_HTML})
	@GET
	public PhoneBook UserByIdNative(@PathParam("id") long id) throws BaseException{
		return userAction.getUserById(id);
	}
	
	@Path("/nativlist")
	@Consumes(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.TEXT_HTML})
	@Produces(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.TEXT_HTML})
	@GET
	public List<PhoneBook> listUsersByIdNative(@PathParam("id") long id) throws BaseException{
		return userAction.getUsersWithNative();
	}
	
	@Path("/create")
	@Consumes(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.TEXT_HTML})
	@Produces(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.TEXT_HTML})
	@POST
	public PhoneBook createUser(PhoneBook phoneBook) throws BaseException{
		return userAction.createUser(phoneBook);
	}
	
//	@Path("/update/phone")
//	@Consumes(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.TEXT_HTML})
//	@Produces(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.TEXT_HTML})
//	@PUT
//	public PhoneBook updateUserPhone(UpdatePhoneRequest req ) throws BaseException{
//		
//		return userAction.updateUserPhone(req.getUserId(),req.getPhoneFrom(), req.getPhoneTo());
//	}
	
	@Path("/update/phone")
	@Consumes(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.TEXT_HTML})
	@Produces(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.TEXT_HTML})
	@PUT
	public UpdatePhoneResponse updateUserPhone(UpdatePhoneRequest req ) throws BaseException{
		return userAction.updateUserPhone(req.getUserId(), req.getPhoneFrom(), req.getPhoneTo());
	}
}
