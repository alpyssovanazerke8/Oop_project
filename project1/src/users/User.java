package users;

import java.io.IOException;
import java.io.Serializable;

import database.Data;

public class User implements Serializable {
	private String firstName;
	private String lastName;
	
	public User() {
		
	}
	
	public User(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	
	/* methods for Observer pattern like update(), subscribe(), unsubscribe()
	 * method where all users can see news, its just return method*/
	
	
	
	public int compareTo(User o) {
		if(this.lastName.equals(o.lastName)) {
			return this.firstName.compareTo(o.firstName);
		}
		return this.firstName.compareTo(o.lastName);
	}
	
	/**
	 * saves changes in database
	 * @throws IOException if some input error occurs
	 */
	public void save() throws IOException {
		Data.write();
	}
	/**
	 * generates new log and saves changes
	 */
	public void exit() {
		System.out.println("You've logged out succesfully");
		Data.INSTANCE.getLogs().add(this + " logged out system"); /* at + Date() */
		try {
			save();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
