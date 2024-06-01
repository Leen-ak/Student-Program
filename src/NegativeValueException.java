 /**
  * Program Name: NegativeValueException.java
  * Purpose: NegativeValueException class is to define a custom exception for handling invalid grade values, specifically when a grade is negative or 
  	outside the valid range of 0 to 100. This exception includes a descriptive error message and an invalid grade value.
  * @author Leen Aboukhalil
  * Date: May 27, 2024
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
