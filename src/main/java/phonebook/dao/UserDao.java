package phonebook.dao;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import javax.persistence.Query;

import model.UserName;

@Model
public class UserDao {
	
	@Inject
	EntityManager em;
	
	public List<UserName> getUsers (){
		List<UserName> list;
		Query query = em.createQuery("select u from UserName u LEFT JOIN FETCH u.phoneNumbers2s");
		list = query.getResultList();
		return list;
	}
	
	
	
	

}
