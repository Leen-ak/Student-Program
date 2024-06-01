 /**
  * Program Name: College.java
  * Purpose: The purpose of the College class is to represent and manage student information, including personal details, and contact information, and store this data in a list.
  * @author Leen Aboulhalil
  * Date:
 */
import java.util.*;
public class College {

	//Data members 
	private String userEmail;
	private String password;
	private String studentID; 
	private String firstName;
	private String middleName; 
	private String lastName; 
	private String dateOfBirth; 
	private String gender; 
	private String phone; 
	private String studentEmail;
	private String streetName; 
	private String city; 
	private String province; 
	private String postalCode;	
	private ArrayList <College> studentList = new ArrayList<>();
	
	
	public College(String studentID,String firstName, String middleName, String lastName
			, String dateOfBirth, String phone, String studentEmail , String streetName 
			, String city, String postalCode,  String gender,String province)
	{
		this.studentID = studentID; 
		this.firstName = firstName; 
		this.middleName = middleName; 
		this.lastName = lastName; 
		this.dateOfBirth = dateOfBirth;
		this.phone = phone; 
		this.studentEmail = studentEmail;
		this.streetName = streetName; 
		this.city = city; 
		this.postalCode = postalCode; 
		this.gender = gender; 
		this.province = province;
		studentList.add(this);
	}
	
	public College() {
		this.studentID = studentID; 
		this.firstName = firstName;
		this.lastName = lastName;
		studentList.add(this); 
	}

	
	/**
	 * Gets the studentList of this object  
	 * @return studentList
	 */
	
	public ArrayList<College> getStudentList() {
		return studentList;
	}


	/**
	 * Sets the studentList of this object
	 * @param studentList
	 */
	
	public void setStudentList(ArrayList<College> studentList) {
		this.studentList = studentList;
	}


	/**
	 * Gets the city of this object  
	 * @return city
	 */
	
	public String getCity() {
		return city;
	}

	/**
	 * Sets the city of this object
	 * @param city
	 */
	
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gets the userEmail of this object  
	 * @return userEmail
	 */
	
	public String getUserEmail() {
		return userEmail;
	}

	/**
	 * Sets the userEmail of this object
	 * @param userEmail
	 */
	
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	/**
	 * Gets the password of this object  
	 * @return password
	 */
	
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password of this object
	 * @param password
	 */
	
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the studentID of this object  
	 * @return studentID
	 */
	
	public String getStudentID() {
		return studentID;
	}

	/**
	 * Sets the studentID of this object
	 * @param studentID
	 */
	
	public void setStudentID(String studentID) {
		this.studentID = studentID; 
	}

	/**
	 * Gets the firstName of this object  
	 * @return firstName
	 */
	
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the firstName of this object
	 * @param firstName
	 */
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the middleName of this object  
	 * @return middleName
	 */
	
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * Sets the middleName of this object
	 * @param middleName
	 */
	
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * Gets the lastName of this object  
	 * @return lastName
	 */
	
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the lastName of this object
	 * @param lastName
	 */
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the dateOfBirth of this object  
	 * @return dateOfBirth
	 */
	
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * Sets the dateOfBirth of this object
	 * @param dateOfBirth
	 */
	
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * Gets the gender of this object  
	 * @return gender
	 */
	
	public String getGender() {
		return gender;
	}

	/**
	 * Sets the gender of this object
	 * @param gender
	 */
	
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Gets the studentEmail of this object  
	 * @return studentEmail
	 */
	
	public String getStudentEmail() {
		return studentEmail;
	}

	/**
	 * Sets the studentEmail of this object
	 * @param studentEmail
	 */
	
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}


	/**
	 * Gets the streetName of this object  
	 * @return streetName
	 */
	
	public String getStreetName() {
		return streetName;
	}

	/**
	 * Sets the streetName of this object
	 * @param streetName
	 */
	
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	/**
	 * Gets the province of this object  
	 * @return province
	 */
	
	public String getProvince() {
		return province;
	}

	/**
	 * Sets the province of this object
	 * @param province
	 */
	
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * Gets the pastalCode of this object  
	 * @return pastalCode
	 */
	
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * Sets the pastalCode of this object
	 * @param pastalCode
	 */
	
	public void setPostalCode(String pastalCode) {
		this.postalCode = pastalCode;
	}
	
	/**
	 * Gets the phone of this object  
	 * @return phone
	 */
	
	public String getPhone() {
		return phone;
	}


	/**
	 * Sets the phone of this object
	 * @param phone
	 */
	
	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String toString()
	{
		return "StudentID:       " + getStudentID() + "\nStudent name:    " + getLastName() + ", " + getFirstName()
		+ " " + getMiddleName() + "\nDate of Birth:   " + getDateOfBirth() + "\nPhone Number:    " + getPhone() +"\nEmail:           " + getStudentEmail() + 
		"\nStreet Name:     " + getStreetName() + "\nCity:            " + getCity() + "\nPostal Code:     " + getPostalCode() + "\nGender:          " 
		+ getGender() + "\nProvince:        " + getProvince();
	}
	
	
	
}
