 /**
  * Program Name: StudentInformation.java
  * Purpose:	  The StudentInformation class creates a GUI application for collecting and validating student information, including personal details and address, and saving this data to a file.
  * Leen Aboukhalil
  * Date: May 22, 2024
 */
import java.awt.*; 
import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import java.util.*;
import java.util.regex.*;
public class StudentInformation extends JFrame{

	//crating all the JFrame component that i will need it
	private ArrayList <College> studentList; 
	private JTextField studentIDTxt, firstNameTxt, middleNameTxt, lastNameTxt, dateOfBirthTxt, genderTxt, emailTxt, phoneTxt, streetNameTxt, cityTxt,provinceTxt,postalCodeTxt;
	private JLabel studentInfoLab ,studentIDLab, firstNameLab, middleNameLab, lastNameLab, dateOfBirthLab, emailLab, phoneLab, streetNameLab, cityLab,postalCodeLab;
	private JMenuBar menuBar, genderBar; 
	private JMenu provinceMenu, genderMenu;
	private JMenuItem AB, BC, MB, NB, NF, NT, NS, NU, ON, PE, PQ, SK, YT; 
	private JMenuItem male, female, other; 
	private JPanel titlePanel, bodyPanel, buttonPanel; 
	private JButton submitBtn, clearBtn, nextBtn,exitBtn; 

	//setting the font object
	Font font = new Font ("Serif", Font.BOLD , 20); 
	
	//public constructor
	public StudentInformation()
	{
		super("Leen Aboukhalil - Student Information Page");
		
	    //creating an object from listing class 
        SetAction action = new SetAction();
		
		//calling CreateJPanel to create 3 JPanel in the window 
		CreateJPanel();
		
		//calling CreateLabelText method to create JLabel and JText to the window 
		CreateLabelText();
			
		//calling CreateGenderMenu method to create gender menu
		CreateGenderMenu(action);
		
	    //calling CreateProvinceMenu method to create province menu
	    CreateProvinceMenu(action); 
		
        //Calling CreateButton method to create 3 buttons 
        CreateButton();
        
        //setting the action listener to the JFrame component
        submitBtn.addActionListener(action);
        clearBtn.addActionListener(action);
        exitBtn.addActionListener(action);
        nextBtn.addActionListener(action);
        
        //to make the window visible 
		this.setVisible(true);
	}
	
    private class SetAction implements ActionListener
    {
		@Override
		public void actionPerformed(ActionEvent ev) 
		{
			// Call the parent container a reference to it from the JFrame host object
			Container contentPane = getContentPane(); 
			
			//create a variables to store the user input
			String studentID, firstName, middleName, lastName, dateOfBirth, gender, phone, email, streetName, city, province, postalCode ;  
			studentList = new ArrayList<>();
			
			//if the user select any of the genderItem then what the user chose will be displayed to the text field 
			if(ev.getSource() == male)
				genderTxt.setText(male.getText());
			else if(ev.getSource() == female)
				genderTxt.setText(female.getText());
			else if(ev.getSource() == other)
				genderTxt.setText(other.getText());
			
			//if the user select any of the provinceItem then what the user chose will be display to the text field 
			if(ev.getSource() == AB)
				provinceTxt.setText(AB.getText());
			else if(ev.getSource() == BC)
				provinceTxt.setText(BC.getText());
			else if(ev.getSource() == MB)
				provinceTxt.setText(MB.getText());
			else if(ev.getSource() == NB)
				provinceTxt.setText(NB.getText());
			else if(ev.getSource() == NF)
				provinceTxt.setText(NF.getText());
			else if(ev.getSource() == NT)
				provinceTxt.setText(NT.getText());
			else if(ev.getSource() == NS)
				provinceTxt.setText(NS.getText());
			else if(ev.getSource() == NU)
				provinceTxt.setText(NU.getText());
			else if(ev.getSource() == ON)
				provinceTxt.setText(ON.getText());
			else if(ev.getSource() == PE)
				provinceTxt.setText(PE.getText());
			else if(ev.getSource() == PQ)
				provinceTxt.setText(PQ.getText());
			else if(ev.getSource() == SK)
				provinceTxt.setText(SK.getText());
			else if(ev.getSource() == YT)
				provinceTxt.setText(YT.getText());
				
			//if the user presses the submitting button it will take all the information from the JTextField and make sure all the inputs are validated 
			if(ev.getSource()== submitBtn)
			{
				studentID = studentIDTxt.getText(); 
				firstName = firstNameTxt.getText();
				middleName = middleNameTxt.getText(); 
				lastName = lastNameTxt.getText(); 
				phone = phoneTxt.getText();
				email = emailTxt.getText(); 
				postalCode = postalCodeTxt.getText();
				dateOfBirth = dateOfBirthTxt.getText();
				streetName = streetNameTxt.getText();
				city = cityTxt.getText(); 
				province = provinceTxt.getText();
				gender = genderTxt.getText();
				
				//calling the CheckValikation method to check each input the user type is a correct input
				CheckValidation (studentID, firstName, middleName, lastName, dateOfBirth , phone, email, streetName, city , postalCode, gender, province, contentPane); 	
				SaveStudentData(); 
			}
			else if (ev.getSource() == clearBtn)
			{
				//clear button to delete all the information the user enter in the JTextField
				ClearButton();
			}
			else if(ev.getSource() == nextBtn)
			{
				//next button to open the next page which is a GPA calculator
				dispose(); 
				new GPACalculator(); 
			}
			else if(ev.getSource() == exitBtn)
			{
				//exit button to exit from the program and open the login page
				dispose(); 
				new StudentProgram(); 
			}
		}
    }

	public static void main(String[] args) {
		new StudentInformation(); 

	}
	
	/**
	  
	Method Name:	CreateJPanel
	Purpose:        To create JPanel for the page
	Accepts:        nothing
	Returns:        void
	Coder:          Leen Aboukhalil
	Date:           May 22, 2024
	*/
	private void CreateJPanel()
	{
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(700, 700);
		this.setLocationRelativeTo(null);
		
		//first Panel for the title
		titlePanel = new JPanel(new FlowLayout());
		titlePanel.setBackground(Color.DARK_GRAY);
		this.add(titlePanel, BorderLayout.NORTH);
 		titlePanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));	
 		
		//Display title of the page 
		studentInfoLab = new JLabel("Student Inoformation");
		studentInfoLab.setForeground(Color.WHITE);
		titlePanel.add(studentInfoLab); 
		studentInfoLab.setFont(font);
 		
 		//second Panel for the body 
		bodyPanel = new JPanel(new GridLayout(12 ,2,20,20));
		bodyPanel.setBackground(Color.DARK_GRAY);
		this.add(bodyPanel, BorderLayout.CENTER);
		bodyPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		
		//Third Panel for the buttons 
        buttonPanel = new JPanel(new GridLayout(1,4,40,40)); 
        buttonPanel.setBackground(Color.DARK_GRAY);
        this.add(buttonPanel, BorderLayout.SOUTH); 
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
 		
	}
	
	/*
	Method Name:	CreateLabelText
	Purpose:        Creating the JTextField to the window
	Accepts:        nothing
	Returns:        void
	Coder:          Leen Aboukhalil
	Date:           May 22, 2024
	*/
	private void CreateLabelText()
	{
		//declaring JLabel and JTextField 
		studentIDLab = new JLabel("Student ID: ");
		studentIDTxt = new JTextField(); 
		firstNameLab = new JLabel("First Name: "); 
		firstNameTxt = new JTextField(); 
		middleNameLab = new JLabel("Middle Name: "); 
		middleNameTxt = new JTextField(); 
		lastNameLab = new JLabel("Last Name: ");
		lastNameTxt = new JTextField(); 
		dateOfBirthLab = new JLabel("Date of Birth: "); 
		dateOfBirthTxt = new JTextField("DD/MMM/YYYY");
		phoneLab = new JLabel("Phone Number: "); 
		phoneTxt = new JTextField();
		emailLab = new JLabel ("Email: ");
		emailTxt = new JTextField();
		streetNameLab = new JLabel ("Street Name: ");
		streetNameTxt = new JTextField();
		cityLab = new JLabel("City: ");
		cityTxt = new JTextField(); 
		postalCodeLab = new JLabel("Postal Code: "); 
		postalCodeTxt = new JTextField(); 
		
		//creating HashMap to store JLable and JTextField
		HashMap<JLabel[], JTextField[]> hashName = new HashMap<>();
		//creating a textName and textLabel arrays to store the texts Variables and use the arrays in the HashMap object
		JTextField[] textName = {studentIDTxt, firstNameTxt, middleNameTxt, lastNameTxt, dateOfBirthTxt, phoneTxt, emailTxt, streetNameTxt,cityTxt, postalCodeTxt};
		JLabel[] textLabel = {studentIDLab, firstNameLab, middleNameLab, lastNameLab, dateOfBirthLab, phoneLab, emailLab, streetNameLab, cityLab,postalCodeLab};
		hashName.put(textLabel, textName);

		// Iterate over the entries in the HashMap to add JLabel and JTextField to the window 
		for (Map.Entry<JLabel[], JTextField[]> entry : hashName.entrySet()) {
			//get the key and the value from the HashMap to iterate over it
		    JLabel[] key = entry.getKey();
		    JTextField[] fields = entry.getValue();
		 
		    for(int i = 0 ; i < key.length ; ++i)
		    {
		    	//store the JLable to keyLable variable and then add it to bodyPanel
		      	JLabel keyLable = key[i];
		      	keyLable.setForeground(Color.WHITE);
		    	bodyPanel.add(keyLable); 
		    	
		    	//store the JTextField to field variable and then add it to bodyPanel
		        JTextField field = fields[i];
		        field.setForeground(Color.DARK_GRAY);
		        bodyPanel.add(field);
		    }   
		}
	}
	
	/**
	  
	Method Name:	CreateGenderMenu
	Purpose:        To create a menu for the user to chose the gender type
	Accepts:        ActionLister object
	Returns:        void
	Coder:          Leen Aboukhail
	Date:           May 22, 2024
	*/
	private void CreateGenderMenu (ActionListener action)
	{
		//add JMenue bar to gender menu
	    genderBar = new JMenuBar();
	    genderBar.setBackground(Color.GRAY);
	    genderMenu = new JMenu("Choose Gender");
	    genderMenu.setForeground(Color.WHITE);
	    genderBar.add(genderMenu);
	    bodyPanel.add(genderBar);
	    genderTxt = new JTextField();
	    bodyPanel.add(genderTxt); 

	    // Array of genders
	    String[] genderItems = {"Male", "Female", "Other"};
	    //Array to store the time of the genderItems Array and use it in a for loop
	    JMenuItem[] genderMenuItems = new JMenuItem[genderItems.length];

	    // Create menu items, register them with action listener, and add to menu
	    for (int i = 0; i < genderItems.length; i++) {
	        genderMenuItems[i] = new JMenuItem(genderItems[i]);
	        genderMenuItems[i].setForeground(Color.DARK_GRAY);
	        genderMenuItems[i].addActionListener(action);
	        genderMenu.add(genderMenuItems[i]);
	    }

	    // Assign each JMenuItem to the indexes for easy access
	    male = genderMenuItems[0];
	    female = genderMenuItems[1];
	    other = genderMenuItems[2];
	
	}
	
	/**
	  
	Method Name:	CreateProvincceMenu
	Purpose:        To create a menu for the user to chose which province they lived in
	Accepts:        ActionListener object
	Returns:        void
	Coder:          Leen Aboukhalil
	Date:           May 22, 2024
	*/
	private void CreateProvinceMenu(ActionListener action)
	{
		
		//add province menu
		menuBar = new JMenuBar(); 
		menuBar.setBackground(Color.GRAY);
		bodyPanel.add(menuBar);
		provinceMenu = new JMenu("Choose Province");
		provinceMenu.setForeground(Color.WHITE);
		menuBar.add(provinceMenu);
		provinceTxt = new JTextField();
		bodyPanel.add(provinceTxt);
		
		//Add the menu item to the menu by using an Array
        String [] province = {"AB", "BC", "MB", "NB", "NF", "NT", "NS", "NU", "ON", "PE", "PQ", "SK", "YT"};
        JMenuItem[] provinceMenuItems = new JMenuItem[province.length];
        
		//Iterate through the array and add each province as a menu item
        for(int i = 0 ; i < provinceMenuItems.length ; i++)
        {
           provinceMenuItems[i] = new JMenuItem(province[i]);
           provinceMenuItems[i].setForeground(Color.DARK_GRAY);
           provinceMenuItems[i].addActionListener(action);
           provinceMenu.add(provinceMenuItems[i]);
        }
        
        AB = provinceMenuItems[0];
        BC = provinceMenuItems[1];
        MB = provinceMenuItems[2];
        NB = provinceMenuItems[3];
        NF = provinceMenuItems[4];
        NT = provinceMenuItems[5];
        NS = provinceMenuItems[6];
        NU = provinceMenuItems[7];
        ON = provinceMenuItems[8];
        PE = provinceMenuItems[9];
        PQ = provinceMenuItems[10];
        SK = provinceMenuItems[11];
        YT = provinceMenuItems[12]; 
	}

	/**
	  
	Method Name:	CreateButton
	Purpose:        To create the button component to the window 
	Accepts:        nothing
	Returns:        void
	Coder:          Leen Aboukahlil
	Date:           May 22, 2024
	*/
	private void CreateButton()
	{
      
        submitBtn = new JButton("Submit");
        clearBtn = new JButton("Clear"); 
        nextBtn = new JButton("Next");
        exitBtn = new JButton("Exit"); 

        //Array to create the button in a for loop
      	JButton [] buttons = {submitBtn, clearBtn, nextBtn, exitBtn}; 
      		
        for(int i = 0; i < buttons.length ; i++)
        {
        	JButton button = buttons[i];
        	button.setBackground(Color.GRAY);
        	button.setForeground(Color.WHITE);
        	buttonPanel.add(button);
        }
	}
	
	/**
	Method Name:	CheckValikation
	Purpose:        A validation method to make sure all the user input are correct
	Accepts:        String, String, String, String, String, String, String, String, String, String, String, Container object
	Returns:        void
	Coder:          Leen Aboukahlil
	Date:           May 22, 2024
	*/
	private void CheckValidation(String studentID, String firstName, String middleName, String lastName,String dateOfBirth ,String phone,String email, String streetName, String city ,String postalCode, String gender, String province,Container contentPane) 
	{
		//declaring the Container object
		contentPane = getContentPane(); 
		
		//Regex for the studentID to make sure the studentID contains just a number and the length of it is 7 numbers
		Pattern pattern = Pattern.compile("[0-9]{7}");
		Matcher match = pattern.matcher(studentID);
		boolean studentIDCheck = match.matches();
		
		//Regex for phone number to make sure the user input will be in this format (000) 000-0000 
		pattern = Pattern.compile("^\\(\\d{3}\\)\\s\\d{3}-\\d{4}$");
		match = pattern.matcher(phone);
		boolean phoneCheck = match.matches();
		
		//Regex for the email address to make sure the user enter a validate email address 
		pattern = Pattern.compile("^[a-z]+@[a-z]+\\.(com|edu|gov|ca)$", Pattern.CASE_INSENSITIVE);
		match = pattern.matcher(email); 
		boolean emailCheck = match.matches(); 
		
		//Regex for postal code to make sure the user will input a validate postal code
		pattern = Pattern.compile("[A-Z][0-9][A-Z]\\s[0-9][A-Z][0-9]", Pattern.CASE_INSENSITIVE); //N6J 2N4
		match = pattern.matcher(postalCode); 
		boolean checkPC = match.matches(); 
		
		//Regext for date of birth to make sure it is a vaildate date and should be in this format 00/JAN/0000
		pattern = Pattern.compile("\\d{2}/(JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)/\\d{4}", Pattern.CASE_INSENSITIVE);
		match = pattern.matcher(dateOfBirth);
		boolean checkDate = match.matches();
	
		if(!studentIDCheck)
		{
			JOptionPane.showMessageDialog(contentPane, "StudentID should be 7 numbers, Please re-enter a valid studentID.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
			studentIDTxt.requestFocus();
			return;
		}
		else if(firstName.isEmpty())
		{
			JOptionPane.showMessageDialog(contentPane, "First Name field is empty. Please enter your FIRST name.", "Empty Input", JOptionPane.ERROR_MESSAGE);
			return;
		}
		else if(middleName.isEmpty())
		{
			JOptionPane.showMessageDialog(contentPane, "Middle Name field is empty. Please enter your MIDDLE name.", "Empty Input", JOptionPane.ERROR_MESSAGE);
			return;
		}
		else if(lastName.isEmpty())
		{
			JOptionPane.showMessageDialog(contentPane, "Last Name field is empty.  Enter your Last name.", "Empty Input", JOptionPane.ERROR_MESSAGE);
			return;
		}
		else if(!checkDate)
		{
			JOptionPane.showMessageDialog(contentPane, "Invalid Date, Please re-enter a correct date with a correct format (dd/mmm/yyyy).", "Invalid Input", JOptionPane.ERROR_MESSAGE); 
			dateOfBirthTxt.requestFocus();
			return;
		}
		else if(!phoneCheck)
		{
			JOptionPane.showMessageDialog(contentPane, "Phone number format should be (999) 999-9999, please re-enter a correct phone number with the right format.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
			phoneTxt.requestFocus();
			return;
		}
		else if(!emailCheck)
		{
			JOptionPane.showMessageDialog(contentPane, "Eamil address should be in this format example(.com)(.org)(.ca)(.edu). Please re-enter a correct Email Address.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
			emailTxt.requestFocus();
			return;
		}
		else if(email.isEmpty())
		{
			JOptionPane.showMessageDialog(contentPane, "Email field is empty. Please enter your Email.", "Empty Input", JOptionPane.ERROR_MESSAGE);
			return;
		}
		else if(streetName.isEmpty())
		{
			JOptionPane.showMessageDialog(contentPane, "Street Name field is empty. Please enter your Address.", "Empty Input", JOptionPane.ERROR_MESSAGE);
			return;
		}
		else if(city.isEmpty())
		{
			JOptionPane.showMessageDialog(contentPane, "City field is empty. Please enter your Address.", "Empty Input", JOptionPane.ERROR_MESSAGE);
			return;
		}
		else if(!checkPC)
		{
			JOptionPane.showMessageDialog(contentPane, "Invalid Postal Code", "Invalisd Input", JOptionPane.ERROR_MESSAGE);
			postalCodeTxt.requestFocus();
			return;
		}
		else if(gender.isEmpty())
		{
			JOptionPane.showMessageDialog(contentPane, "Gender field is empty. Please select your gender.", "Empty Input", JOptionPane.ERROR_MESSAGE);
			return;
		}
		else if(province.isEmpty())
		{
			JOptionPane.showMessageDialog(contentPane, "Province field is empty. Please select your Province.", "Empty Input", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		College student = new College(studentID,firstName, middleName, lastName, dateOfBirth, phone, email, streetName, city, postalCode, gender, province);
		
		//adding all the information to an ArrayList to store the student information and print the student Information to the console
		studentList = student.getStudentList();
		for(College studentInfo : studentList)
		{
			System.out.println(studentInfo);
		}
	}
	
	/**
	Method Name:    ClearButton
	Purpose:        to make all the JTextFeild empty
	Accepts:        nothing
	Returns:        void
	Coder:          Leen Aboukhalil
	Date:           May 22, 2024
	*/
	private void ClearButton() 		
	{
		studentIDTxt.setText("");
		firstNameTxt.setText("");
		middleNameTxt.setText("");
		lastNameTxt.setText("");
		dateOfBirthTxt.setText("");
		phoneTxt.setText("");
		emailTxt.setText("");
		streetNameTxt.setText("");
		cityTxt.setText("");
		postalCodeTxt.setText("");
		genderTxt.setText("");
		provinceTxt.setText("");
	}
	
	/**
	Method Name:    SaveStudentData()
	Purpose:        to save the student information in a file 
	Accepts:        nothing
	Returns:        void
	Coder:          Leen Aboukhalil
	Date:           May 22, 2024
	*/
	private void SaveStudentData()
	{
		try(PrintWriter printIn = new PrintWriter(new FileWriter("studentData.txt")))
		{
			printIn.println(studentIDTxt.getText());
			printIn.println(firstNameTxt.getText());
			printIn.println(lastNameTxt.getText());
		}
		catch(IOException e)
		{
			e.getStackTrace();
		}
	}
	
}