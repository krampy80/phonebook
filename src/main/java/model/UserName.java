package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the USER_NAMES database table.
 * 
 */
@Entity
@Table(name="USER_NAMES")
@NamedQuery(name="UserName.findAll", query="SELECT u FROM UserName u")
public class UserName implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String firstname;

	private String lastname;

	//bi-directional many-to-one association to PhoneNumbers2
	@OneToMany(mappedBy="userName")
	private List<PhoneNumbers2> phoneNumbers2s;

	public UserName() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public List<PhoneNumbers2> getPhoneNumbers2s() {
		return this.phoneNumbers2s;
	}

	public void setPhoneNumbers2s(List<PhoneNumbers2> phoneNumbers2s) {
		this.phoneNumbers2s = phoneNumbers2s;
	}

	public PhoneNumbers2 addPhoneNumbers2(PhoneNumbers2 phoneNumbers2) {
		getPhoneNumbers2s().add(phoneNumbers2);
		phoneNumbers2.setUserName(this);

		return phoneNumbers2;
	}

	public PhoneNumbers2 removePhoneNumbers2(PhoneNumbers2 phoneNumbers2) {
		getPhoneNumbers2s().remove(phoneNumbers2);
		phoneNumbers2.setUserName(null);

		return phoneNumbers2;
	}

}