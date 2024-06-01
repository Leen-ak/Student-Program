 /**
  * Program Name: StudentProgram.java
  * Purpose: The class provides a graphical user interface (GUI) for students to log in using their student ID and password.
  * Leen Aboukhalil
  * Date: May 22, 2024
 */
import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*;
import java.util.*;
import java.util.regex.*; 
public class StudentProgram extends JFrame{
	
	//JFrame members
	private JLabel studentIDLoginLab, passwordLab; 
	private JTextField studentIDLoginTxt;  
	private JPanel loginPanel, titlePanel; 
	private JPasswordField passwordTxt;
	private JButton loginBtn;
	
	//declare variables 
	String studentIDLogin; 
	String passwordLogin; 
	
	public StudentProgram()
	{
		super("Leen Aboukhalil - Student Information Program");
		
		//create the object from the action listener class
		SetAction action = new SetAction(); 
		
		//Calling CreatePanel to create the window and add all the JFrame that we needed 
		CreatePanel(); 
		
		
		//setting up actionListener
		loginBtn.addActionListener(action);
		
		//To make the window visible
		this.setVisible(true);
	}
	
    //create an inner class to set ActionListener for the button and some validation for email and password 
		private class SetAction implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent ev) {
				
				//if the user press loginBtn button
				if(ev.getSource() == loginBtn)
				{
					//to get the text from the JTextFiled and store it in string variables 
					studentIDLogin = studentIDLoginTxt.getText(); 
					passwordLogin = passwordTxt.getText();
					
					//call check method to validate the input of the user
					checkInput(studentIDLogin, passwordLogin); 
					
				}
			}
	}
		
		
	public static void main(String[] args) {
		
	//calling the constructor
    new StudentProgram();
	}

	/**  
	Method Name:	checkInput
	Purpose:        checking the user input for the userID and password
	Accepts:        String, String 
	Returns:        void 
	Coder:          leen Aboukhalil
	Date:           May 22, 2024
	*/
    private void checkInput(String studentIDLogin, String password )
    {
    	//creating an object container
    	Container contentPane = getContentPane();
    	
    	//Create a Regex for validation of the user input for the password
    	Pattern pattern = Pattern.compile("[A-Z]+[0-9]+(!|@|#|$|%|^|&|'*'|_|-|'+'|=|,|.|:|;|'?'|>|<)?", Pattern.CASE_INSENSITIVE); 
    	Matcher match = pattern.matcher(password);
    	boolean passwordCheck = match.matches();
    	
	    //studentID has no input show an error message to the user
		if(studentIDLogin.isEmpty())
		{
			JOptionPane.showMessageDialog(contentPane, "StudentID field is empty, please enter a valid ID", "Invalid Input", JOptionPane.ERROR_MESSAGE);
			studentIDLoginTxt.requestFocus();
		}
		//if the input is less than 7 show an error message to the user
		else if(studentIDLogin.length() != 7)
		{
			JOptionPane.showMessageDialog(contentPane, "StudentID must be 7 numbers, please enter a valid ID", "Invalid StudentID", JOptionPane.ERROR_MESSAGE);
			studentIDLoginTxt.requestFocus();
		}
		//if password has no value show an error message
		else if(passwordLogin.isEmpty())
		{
			JOptionPane.showMessageDialog(contentPane, "Password field is empty, please enter valid Password", "Invalid password", JOptionPane.ERROR_MESSAGE);
			studentIDLoginTxt.requestFocus(); 
		}
		//if the user did not write any character in the password field show an error message
		else if(!passwordCheck || password.length() < 8)
			JOptionPane.showMessageDialog(contentPane, "The password must contain one letter or more, numbers, and special characters. Eampale:(ab12345?)", "Invalid password", JOptionPane.ERROR_MESSAGE);
		//if password less than 8 numbers show an error message
		else if(passwordLogin.length() < 8)
			JOptionPane.showMessageDialog(contentPane, "Password length should be 8 or more", "Invalid Password", JOptionPane.ERROR_MESSAGE);
		else
		{
			//to make sure when the user presses login it will go to the next page and close the current page
			dispose();		
			//creating an object for SudentInformation class to open the page that contains the student information  
			new StudentInformation();
		}
    }
    
	/**
	  
	Method Name:	CreatePanel
	Purpose:        To crate JPanel for the window 
	Accepts:        nothing
	Returns:        void
	Coder:          Leen Aboukhail
	Date:                May 22, 2024
	*/
    private void CreatePanel()
    {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(700, 700);
		this.setLocationRelativeTo(null);
		
		//add title panel for the title 
		titlePanel = new JPanel(new FlowLayout());
		titlePanel.setBackground(Color.DARK_GRAY);
		this.add(titlePanel, BorderLayout.NORTH); 
		
		//creating the title for the page, set the background color and set the font label
		JLabel title = new JLabel("Welcome to Student Progress Program");
		title.setFont(new Font("Serif", Font.BOLD, 25)); 
		title.setForeground(Color.WHITE);
		titlePanel.add(title);
		
		//create JPanel for email and password 
		loginPanel = new JPanel(new GridBagLayout());
		loginPanel.setBackground(Color.DARK_GRAY);
		GridBagConstraints gridSize = new GridBagConstraints();
		
		//padding between components 
		gridSize.insets = new Insets(10, 10, 10, 10);
		
		//grid column and row index position 
		gridSize.gridx = 0;
		gridSize.gridy = 0; 
		
		//create the studentID label 
		studentIDLoginLab = new JLabel("StudentID");
		studentIDLoginLab.setForeground(Color.WHITE);
		loginPanel.add(studentIDLoginLab, gridSize);
		//to make a space between the JLabel and JTextFiled
		gridSize.gridx++;
		
		//making the field that the user will enter the studentID
		studentIDLoginTxt = new JTextField(25);
		studentIDLoginTxt.setForeground(Color.DARK_GRAY);
		loginPanel.add(studentIDLoginTxt, gridSize);
		gridSize.gridx =0;
		gridSize.gridy++;
		
		//create the password label and the text field to make the user enter the values 
		passwordLab = new JLabel("Password");
		passwordLab.setForeground(Color.WHITE);
		loginPanel.add(passwordLab, gridSize);
		gridSize.gridx++;
		passwordTxt = new JPasswordField(25); 
		passwordTxt.setForeground(Color.DARK_GRAY);
		loginPanel.add(passwordTxt, gridSize);
		gridSize.gridx = 0;
		gridSize.gridy++; 
		
		//create login Button 
		loginBtn = new JButton("Login");
		loginBtn.setBackground(Color.GRAY);
		loginBtn.setForeground(Color.WHITE);
		loginPanel.add(loginBtn, gridSize); 
		this.add(loginPanel, BorderLayout.CENTER); 
		
    }
}