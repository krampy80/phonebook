package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PHONE_NUMBERS2 database table.
 * 
 */
@Entity
@Table(name="PHONE_NUMBERS2")
@NamedQuery(name="PhoneNumbers2.findAll", query="SELECT p FROM PhoneNumbers2 p")
public class PhoneNumbers2 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String phone;

	//bi-directional many-to-one association to UserName
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USER_ID")
	private UserName userName;

	public PhoneNumbers2() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public UserName getUserName() {
		return this.userName;
	}

	public void setUserName(UserName userName) {
		this.userName = userName;
	}

}