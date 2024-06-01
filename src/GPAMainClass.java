 /**
  * Program Name: GPAMainClass.java
  * Purpose: GPAMainClass is to represent and manage student GPA information, including total credits, total points, individual grades, grade letters, GPA, and CGPA while providing methods to access and modify this data.
  * @author Leen Aboukhalil
  * Date: May 27, 2024
 */

import java.util.*;
public class GPAMainClass {

	private double totalCredit;
	private double totalPoints; 
	private double grade; 
	private String gradeLetter;
	private double gpa;
	private double cgpa;
	private ArrayList <GPAMainClass> studentList = new ArrayList<>();
	
	public GPAMainClass(double totalCredit, double totalPoints, double grade,String gradeLetter, double gpa, double cgpa)
	{
		this.totalCredit = totalCredit;
		this.totalPoints = totalPoints;
		this.grade = grade;
		this.gradeLetter = gradeLetter; 
		this.gpa = gpa; 
		this.cgpa = cgpa; 
		studentList.add(this);
	}

	/**
	 * Gets the studentList of this object  
	 * @return studentList
	 */
	
	public ArrayList<GPAMainClass> getStudentList() {
		return studentList;
	}

	/**
	 * Sets the studentList of this object
	 * @param studentList
	 */
	
	public void setStudentList(ArrayList<GPAMainClass> studentList) {
		this.studentList = studentList;
	}

	/**
	 * Gets the totalCredit of this object  
	 * @return totalCredit
	 */
	
	public double getTotalCredit() {
		return totalCredit;
	}


	/**
	 * Sets the totalCredit of this object
	 * @param totalCredit
	 */
	
	public void setTotalCredit(double totalCredit) {
		this.totalCredit = totalCredit;
	}


	/**
	 * Gets the totalPoints of this object  
	 * @return totalPoints
	 */
	
	public double getTotalPoints() {
		return totalPoints;
	}


	/**
	 * Sets the totalPoints of this object
	 * @param totalPoints
	 */
	
	public void setTotalPoints(double totalPoints) {
		this.totalPoints = totalPoints;
	}


	/**
	 * Gets the averageGrade of this object  
	 * @return averageGrade
	 */
	
	public String getGradeLetter() {
		return gradeLetter;
	}


	/**
	 * Sets the averageGrade of this object
	 * @param averageGrade
	 */
	
	public void setGradeLetter(String gradeLetter) {
		this.gradeLetter = gradeLetter;
	}


	/**
	 * Gets the gpa of this object  
	 * @return gpa
	 */
	
	public double getGpa() {
		return gpa;
	}


	/**
	 * Sets the gpa of this object
	 * @param gpa
	 */
	
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}


	/**
	 * Gets the cgpa of this object  
	 * @return cgpa
	 */
	
	public double getCgpa() {
		return cgpa;
	}


	/**
	 * Sets the cgpa of this object
	 * @param cgpa
	 */
	
	public void setCgpa(double cgpa) {
		this.cgpa = cgpa;
	}


	/**
	 * Gets the grade of this object  
	 * @return grade
	 */
	
	public double getGrade() {
		return grade;
	}


	/**
	 * Sets the grade of this object
	 * @param grade
	 */
	
	public void setGrade(double grade) throws NegativeValueException
	{
		if(grade < 0)
		{
			throw new NegativeValueException("Invalid grade" , grade); 
		}
		else
		{
			this.grade = grade;
		}
	}


	public String toString()
	{
		return "Total credit:		" + getTotalCredit() 
		+ String.format("\nTotal Points:		%.2f", getTotalPoints())
		+ "\nGrade:			" + getGrade() 		 
		+ String.format("\nGPA:			%.3f", getGpa())
		+ String.format("\nCGPA:			%.3f\n",  getCgpa()); 	
	}
}
