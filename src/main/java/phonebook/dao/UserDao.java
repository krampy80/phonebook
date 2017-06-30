package phonebook.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import common.exception.BaseException;
import common.exception.BusinessException;
import common.exception.TechnicalException;
import hu.hugo.common.FunctionCodes;
import hu.hugo.common.ReasonCodeType;
import hu.hugo.phonebook.PhoneBook;
import hu.hugo.phonebook.UpdatePhoneResponse;
import model.PhoneNumbers2;
import model.UserName;

@Model
public class UserDao {
	
	@Inject
	EntityManager em;
	
	private final String QUERY_EXCEPTION ="Lekérdezés hiba!";
	
	public List<PhoneBook> getUsers() throws BaseException {
		try {
			TypedQuery<UserName> query = em.createQuery("select u from UserName u LEFT JOIN FETCH u.phoneNumbers2s", UserName.class);
			List<UserName> userNameList = query.getResultList();
			return createPhoneBookList(userNameList);
		} catch (Exception e) {
			throw new TechnicalException(QUERY_EXCEPTION);
		}
	}
	
	public List<PhoneBook> getUsersWithNative2() throws BaseException {
		List<PhoneBook> pbook = new ArrayList<PhoneBook>();
		try {
			Query query = em.createNativeQuery("SELECT u.id, u.firstname, u.lastname, p.phone FROM USER_NAMES u LEFT JOIN PHONE_NUMBERS2 p ON u.id = p.user_id");
			List<Object[]> userNameList = query.getResultList();
			int i = 0;
			while (i < userNameList.size()) {
				PhoneBook singlePhoneBook = new PhoneBook();
				BigDecimal currentId = (BigDecimal) userNameList.get(i)[0];
				singlePhoneBook.setFirstname((String) userNameList.get(i)[1]);
				singlePhoneBook.setLastname((String) userNameList.get(i)[2]);
				List<String> phoneList = singlePhoneBook.getPhonenumbers();
				while (i < userNameList.size() && currentId.equals((BigDecimal) (userNameList.get(i)[0]))) {
					if (userNameList.get(i)[3] != null) {
						phoneList.add((String) userNameList.get(i)[3]);
					}
					i++;
				}
				pbook.add(singlePhoneBook);
			}
			return pbook;

		} catch (Exception e) {
			throw new TechnicalException(QUERY_EXCEPTION + " " + e.getMessage());
		}
	}
	
	public List<PhoneBook> getUsersWithNative() throws BaseException {
		List<PhoneBook> pbook = new ArrayList<PhoneBook>();
		try {
			Query query = em.createNativeQuery("SELECT u.id, u.firstname, u.lastname, p.phone FROM USER_NAMES u LEFT JOIN PHONE_NUMBERS2 p ON u.id = p.user_id");
			List<Object[]> userNameList = query.getResultList();
			PhoneBook singlePhoneBook = null;
			BigDecimal currentId = new BigDecimal("-1");
			for (Object[] sor : userNameList) {
				if (!currentId.equals((BigDecimal) sor[0])) {
					if (singlePhoneBook != null) {
						pbook.add(singlePhoneBook);
					}
					singlePhoneBook = new PhoneBook();
					singlePhoneBook.setFirstname((String) sor[1]);
					singlePhoneBook.setLastname((String) sor[2]);
				}
				List<String> slist = singlePhoneBook.getPhonenumbers();
				if (sor[3] != null) {
					slist.add((String) sor[3]);
				}
				currentId=(BigDecimal)sor[0];
			}
			if (singlePhoneBook != null) {
				pbook.add(singlePhoneBook);
			}
			return pbook;
		} catch (Exception e) {
			throw new TechnicalException(QUERY_EXCEPTION + " " + e.getMessage());
		}
	}	
			



	
	
	public PhoneBook getUsersById(long id) throws BaseException {
		PhoneBook pbook = new PhoneBook();
		try {
			Query query = em.createNativeQuery("select u.id, u.firstname, u.lastname, p.phone from USER_NAMES u LEFT JOIN PHONE_NUMBERS2 p ON u.id=p.user_id where u.id=:id");
			query.setParameter("id", id);
			List<Object[]> userName = query.getResultList();
			pbook.setFirstname((String) userName.get(0)[1]);
			pbook.setLastname((String) userName.get(0)[2]);
			List<String> slist = pbook.getPhonenumbers();
			for (Object[] row : userName) {
				slist.add((String) row[3]);
			}
			return pbook;
		} catch (NoResultException nrex) {
			return null;
		} catch (Exception e) {
			throw new TechnicalException(QUERY_EXCEPTION + " " + e.getMessage());
		}
	}
	
	
	private PhoneBook createPhoneBook (UserName userName){
		PhoneBook phoneBook = new PhoneBook();
		phoneBook.setFirstname(userName.getFirstname());
		phoneBook.setLastname(userName.getLastname());
		List<String> slist = phoneBook.getPhonenumbers();
		for (PhoneNumbers2 pn : userName.getPhoneNumbers2s()){
			slist.add(pn.getPhone());
		}
		return phoneBook;
	}
	
	private List<PhoneBook> createPhoneBookList (List<UserName> userNameList){
		List<PhoneBook> plist = new ArrayList<PhoneBook>();
		for (UserName u : userNameList){
			plist.add(createPhoneBook(u));
		}
		return plist;
	}

	public UserName saveUser(UserName userName) throws BusinessException{
		try{
		em.persist(userName);
		em.flush();
		}
		catch( Exception e){
			System.out.println(e.getMessage());
			throw new BusinessException(ReasonCodeType.QUERY_ERROR, "Saving the user name was not succesfull");
		}
		return userName;
	}
	

	public PhoneNumbers2 savePhone(PhoneNumbers2 phoneNumber) throws BaseException{
		try{
		em.persist(phoneNumber);
		em.flush();
		}
		catch (Exception e){
			System.out.println(e.getMessage());
			throw new BusinessException(ReasonCodeType.QUERY_ERROR, "Saving the phone number name was not succesfull");
		}
	
		return phoneNumber;
	}
	
	public UserName updateUser (UserName userName) throws BaseException{
		try{
		em.merge(userName);
		em.flush();
		}
		catch (Exception e){
			System.out.println(e.getMessage());
			throw new BusinessException(ReasonCodeType.QUERY_ERROR, "Updating the user name was not succesfull");
		}
		return userName;	
	}
	
	public UpdatePhoneResponse updateUserPhone(long id, String phoneFrom, String phoneTo) throws BaseException {
		UpdatePhoneResponse response = new UpdatePhoneResponse();
		TypedQuery<UserName> query = em.createQuery("select u from UserName u JOIN FETCH u.phoneNumbers2s p where u.id=:id and p.phone=:phone", UserName.class);
		query.setParameter("id", id);
		query.setParameter("phone", phoneFrom);
		UserName userName;
		try {
			userName = query.getSingleResult();
		} catch (NoResultException e) {
			response.setFuncCode(FunctionCodes.ERROR);
			response.setMessage("No matched phone number!");
			return response;
		}
		userName.getPhoneNumbers2s().get(0).setPhone(phoneTo);
		updateUser(userName);
		response.setPhoneBook(createPhoneBook(userName));
		response.setFuncCode(FunctionCodes.SUCCESS);
		return response;
	}

}
