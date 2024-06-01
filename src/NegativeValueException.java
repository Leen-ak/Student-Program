 /**
  * Program Name: NegativeValueException.java
  * Purpose: OK DUMMY, PUT SOMETHING DESCRIPTIVE HERE!
  * @author Your Name and Student Number goes here
  * Date: May 13, 2024
 */

public class NegativeValueException extends Exception{

	private double grade;
	private static String message = "Invalid Value"; 
	
	public NegativeValueException(String message, double grade)
	{
		super(message + ", the grade should be a postive number between 0 to 100" + grade); 
		this.grade = grade;
	}
}
