package phonebook.action;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import hu.hugo.phonebook.PhoneBook;
import model.PhoneNumbers2;
import model.UserName;
import phonebook.dao.UserDao;

@Model
public class UserAction {
	
	@Inject
	private UserDao userDao;
	
	public List<PhoneBook> getUsers (){
		List<UserName> list = userDao.getUsers();
		List<PhoneBook> plist = new ArrayList<PhoneBook>();
		for (UserName u : list){
			PhoneBook pb = new PhoneBook();
			pb.setFirstname(u.getFirstname());
			pb.setLastname(u.getLastname());
			List<String> slist = pb.getPhonenumbers();
			for (PhoneNumbers2 pn : u.getPhoneNumbers2s()){
				slist.add(pn.getPhone());
			}
			plist.add(pb);
		}
		return plist;
	}
	

}
