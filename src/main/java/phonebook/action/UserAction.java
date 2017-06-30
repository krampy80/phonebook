package phonebook.action;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import org.apache.deltaspike.jpa.api.transaction.Transactional;

import common.exception.BaseException;
import hu.hugo.phonebook.PhoneBook;
import hu.hugo.phonebook.UpdatePhoneResponse;
import model.PhoneNumbers2;
import model.UserName;
import phonebook.dao.UserDao;

@Model
public class UserAction {
	
	@Inject
	private UserDao userDao;
	
	public List<PhoneBook> getUsers () throws BaseException{
		return userDao.getUsers();
	}
	public List<PhoneBook> getUsersWithNative () throws BaseException{
		return userDao.getUsersWithNative();
	}
	
	public PhoneBook getUserById (long id) throws BaseException{
		PhoneBook user = userDao.getUsersById(id);
		return user;
	}
	@Transactional
	public PhoneBook createUser(PhoneBook phoneBook) {
		UserName userName = new UserName();
		userName.setFirstname(phoneBook.getFirstname());
		userName.setLastname(phoneBook.getLastname());
		
		//userName = userDao.saveUser(userName);
		userName.setPhoneNumbers2s(new ArrayList<PhoneNumbers2>());
		for (String phonenumber : phoneBook.getPhonenumbers()){
			PhoneNumbers2 pn = new PhoneNumbers2();
			pn.setPhone(phonenumber);
			//pn.setUserName(userName);
			userName.addPhoneNumbers2(pn);
			//userDao.savePhone(pn);
		}
		userName = userDao.saveUser(userName);
		return phoneBook;
	}
	@Transactional
	public UpdatePhoneResponse updateUserPhone(long id, String phoneFrom, String phoneTo) throws BaseException{		
		return userDao.updateUserPhone(id, phoneFrom, phoneTo);
	}

}
