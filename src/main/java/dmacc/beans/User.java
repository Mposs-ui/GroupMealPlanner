package dmacc.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table(name="user_table")
public class User {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="user_id")
	private int userID;

	@Column(name="user_name")
	private String userName;
	
	public User() {
		super();

		// TODO Auto-generated constructor stub
	}
	
	public User(int userID, String userName) {
		super();
		this.userID = userID;
		this.userName = userName;
	}
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {

		return "User [userID=" + userID + ", userName=" + userName + "]";
	}
	

}
