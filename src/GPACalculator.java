 /**
  * Program Name: GPACalculator.java
  * Purpose: GPA calculator uses Swing for the GUI. It includes custom exception handling for invalid inputs, file I/O for saving student data, and several methods for calculating
  * 		 and displaying GPA and CGPA. It also includes methods to handle various actions such as adding courses, calculating GPA, and clearing inputs.
  * @author  Leen Aboukhalil	
  * Date:    May 27, 2024
 */

import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*;
import java.util.*;
import java.io.*;
import javax.swing.table.DefaultTableModel;

public class GPACalculator extends JFrame{

	ArrayList <GPAMainClass> studentGrade ;
	ArrayList <Double> gpaList; 
	private JPanel titlePanel, bodyPanel, formPanel, spinnerPanel, buttonPanel; 
	private JLabel titleLab,cgpaLab,cCreditLab, courseNameLab , gradeLab, creditLab, gpaResultLab, cgpaResultLab, totalCreditLab, totalPointsLab, averageGradeLab;
	private JTextField cgpaTxt, courseNameTxt, cCreditTxt, gradeTxt, gpaResultTxt, cgpaResultTxt, totalCreditTxt, totalPointsTxt, averageGradeTxt;	
	private JButton calculateBtn, transcriptBtn, deleteBtn, exitBtn, backBtn, addBtn, clearBtn;
	private JTable table; 
	private DefaultTableModel tableModel;
	private JScrollPane tableScrollPane; 
	private SpinnerNumberModel creditSpinnerModel;
	private JSpinner creditSpinner; 
    private String cgpa, cCredit, courseName, grade, gradeLetter, gpaLetter;  
	private double creditDouble, gradeDouble, points, totalPoints, totalCredit, gpa, cgpaDouble, cCreditDouble, totalCurrentPoints, totalQualityPoints,cgpaResult, sumCredit;
	boolean yesAnswer, cancleAnswer;;
	private int answer;

	Font font = new Font("Serif" , Font.BOLD , 20); 
	Font font2 = new Font("Serif" , Font.BOLD , 10); 
	Container contentPane = getContentPane(); 

	public GPACalculator()
	{
		super("GPA Calculator");
		
		gpaList = new ArrayList<>(); 

		//Calling the method to create Panel that will need it
		CreatePanel();
		
		//Calling the method to create TextField 
		CreateTextField();
		
		CreateTable();
		//Calling the method to create buttons 
		CreateButton(); 
		
		SetAction action = new SetAction(); 
		
		//adding an ActionListener to each button
		addBtn.addActionListener(action);
		calculateBtn.addActionListener(action);
		backBtn.addActionListener(action);
		deleteBtn.addActionListener(action);
		clearBtn.addActionListener(action);
		transcriptBtn.addActionListener(action);
		exitBtn.addActionListener(action);
		
		this.setVisible(true);
		
		//calling MessageAnswer to get the showConfirmDialog with the user's answer 
		MessageAnswer();
				
	}
	
	//ActionListenr inner class
	private class SetAction implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent ev) 
		{			
			//if the user press addBtn button
			if(ev.getSource() == addBtn)
			{
			  try
			  {
				if(yesAnswer == true)
				{
					cgpa = cgpaTxt.getText();
					cCredit = cCreditTxt.getText();
					
					if(cgpa.isEmpty())
					{
						JOptionPane.showMessageDialog(contentPane, "CGPA feild is empty, please enter your CGPA." , "Error" , JOptionPane.ERROR_MESSAGE);
						cgpaTxt.requestFocus();
						return;
					}
					else
						cgpaDouble = Double.parseDouble(cgpa); //converting cgpaDouble from a string to double to calculate CGPA 

					//if the user input was less than zero or greater than 4.2 throw an error and show the error to the user
					if(cgpaDouble < 0 || cgpaDouble > 4.2)
						throw new NegativeValueException("Invalid GPA" , cgpaDouble);
					
					if(cCredit.isEmpty())
					{
						JOptionPane.showMessageDialog(contentPane, "Current credit feild is empty, please enter your credit." , "Error" , JOptionPane.ERROR_MESSAGE);
						cCreditTxt.requestFocus();
						return; 
					}
					else
						cCreditDouble = Double.parseDouble(cCredit); //converting Credit from a string to double for the calculation

					//credit should be greater than zero if it less than zero throw an error to the user
					if(cCreditDouble < 0)
						throw new NegativeValueException("Invalid Credit" , cCreditDouble);
				
					//if every input is correct then get the rest of the data from the JTextField
					courseName = courseNameTxt.getText(); 
					grade = gradeTxt.getText(); 
				}
				else 
				{
					//if the user answer for showConfirmDialog message then just store the courseName and grade from the user
					courseName = courseNameTxt.getText(); 
					grade = gradeTxt.getText(); 
				}
				
				if(courseName.isEmpty())
				{
					JOptionPane.showMessageDialog(contentPane, "Course Name feild is empty, please enter your course name", "Error", JOptionPane.ERROR_MESSAGE);
					courseNameTxt.requestFocus();
					return;
				}
				else if(grade.isEmpty())
				{
					JOptionPane.showMessageDialog(contentPane, "Grade field is empty, please enter a grade between (0 - 100)", "Error" , JOptionPane.ERROR_MESSAGE);
					gradeTxt.requestFocus();
					return;
				}
				
				gradeDouble = Double.parseDouble(grade); //storing the grade after converting it to a double in gradeDouble variable
				
				//make sure the grade is not less than zero and if it was zero catch it by NegativeValueException class 
				if(gradeDouble < 0 || gradeDouble > 100)
					throw new NegativeValueException("Invalid grade" , gradeDouble); 	
				
				creditDouble = ((Number) creditSpinner.getValue()).doubleValue(); 	//converting the value from String to double
			
				}
				catch(NumberFormatException ex)
				{
					//if the input did not convert to a double by using parseDouble then it is not a number and it will catch it and print a message for the user
					JOptionPane.showMessageDialog(contentPane, "Please enter a number!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;	
				}
				catch(NegativeValueException ex)
				{
					if(ex.getMessage().contains("GPA"))
					{
						JOptionPane.showMessageDialog(contentPane,  "GPA should be a number between (0 - 4.2)", "Invalid Input", JOptionPane.ERROR_MESSAGE);
						cgpaTxt.requestFocus();
						return;
					}
					else if(ex.getMessage().contains("Credit"))
					{
						JOptionPane.showMessageDialog(contentPane,  "Credit should be a number greater than (0)", "Invalid Input", JOptionPane.ERROR_MESSAGE);
						cCreditTxt.requestFocus();
						return;
					}
					else if(ex.getMessage().contains("grade"))
					{
						JOptionPane.showMessageDialog(contentPane,  "Grade should be a number between (0 - 100)", "Invalid Input", JOptionPane.ERROR_MESSAGE);
						gradeTxt.requestFocus();
						return;
					}
				}
				
				gradeLetter(gradeDouble);
				AddButton(contentPane, yesAnswer);
				SaveStudentTable();
				DeleteInput(yesAnswer);
			}
			else if(ev.getSource() == calculateBtn)
			{
				AverageGrade(gpa);
				setText();
				SaveStudentData();
			}
			else if(ev.getSource() == backBtn)
			{	
				dispose(); 
				new StudentInformation(); 
			}
			else if(ev.getSource() == clearBtn)
			{
				int answer = JOptionPane.showConfirmDialog(contentPane, "Are you sure you want to delete everything ?");
				
				if(answer == JOptionPane.YES_OPTION) {
					SetToZero();
					ClearAll();
				}				
				else
					return; 
			}
			else if(ev.getSource() == deleteBtn)
			{
				int answer = JOptionPane.showConfirmDialog(contentPane, "Are you sure you want to delete your last input ?");
				
				if(answer == JOptionPane.YES_OPTION)
					DeleteInput(yesAnswer); 
				else 
					return;
			}
			else if(ev.getSource() == transcriptBtn)
			{
				dispose(); 
				new TranscriptClass();
			}
			else if(ev.getSource() == exitBtn)
			{
				dispose(); 
				new StudentProgram(); 
			}	
		}
	}
	
	public static void main(String[] args) {
		new GPACalculator(); 
	}
	
	/**
	  
	Method Name:    CreatePanel
	Purpose:        To create a JPanel and add it to JFrame
	Accepts:        Nothing
	Returns:        Void
	Coder:          Leen Aboukhalil
	Date:           May 27, 2024
	*/
	private void CreatePanel()
	{
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(700, 700);
		this.setLocationRelativeTo(null);
		
		//creating titlePanel and add it to JFrame
		titlePanel = new JPanel(); 
		this.add(titlePanel, BorderLayout.NORTH);
		titleLab = new JLabel("GPA Calculator", SwingConstants.CENTER);
		titleLab.setFont(font);
		titleLab.setForeground(Color.WHITE);
		titleLab.setBorder(BorderFactory.createEmptyBorder(20,5,5,5));
		titlePanel.setBackground(Color.DARK_GRAY);
		titlePanel.add(titleLab, BorderLayout.NORTH); 
	
		//creating bodyPanel and add it to JFrame
		bodyPanel = new JPanel(new GridLayout(1,2,10,10));
		bodyPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		bodyPanel.setBackground(Color.DARK_GRAY);
		this.add(bodyPanel, BorderLayout.CENTER);
		
		//creating fromPanel and add it to bodyPanel 
		formPanel = new JPanel(new GridLayout(10,2,30,30));
		formPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		formPanel.setBackground(Color.DARK_GRAY);
		bodyPanel.add(formPanel);
	}
	
	/**
	  
	Method Name:    CreateTextField
	Purpose:        Creating JTextField and JLabel to add it to JPanel
	Accepts:        Nothing
	Returns:        Void
	Coder:          Leen Aboukhalil
	Date:           May 27, 2024
	*/
	private void CreateTextField()
	{
		//declaring JTextField and JLabel
		cgpaLab = new JLabel ("Current GPA ");
		cgpaTxt = new JTextField();
		cgpaTxt.setMinimumSize(new Dimension(100,20));
		cCreditLab = new JLabel("Current credits: ");
		cCreditTxt = new JTextField();
		courseNameLab = new JLabel ("Course Name: "); 
		courseNameTxt = new JTextField();
		gradeLab = new JLabel("Grade: ");
		gradeTxt = new JTextField(); 
		creditLab = new JLabel("Course Credit: ");
		
		//adding each JLabel and JTextField to an Arrays 
		JLabel [] labels = {cgpaLab , cCreditLab, courseNameLab, gradeLab};
		JTextField [] texts = {cgpaTxt, cCreditTxt, courseNameTxt, gradeTxt};
			
		for(int i = 0; i < texts.length ; i++)
		{
			//declaring each JLabel and add it to fromPanel
			JLabel label = labels[i];
			label.setForeground(Color.WHITE);
			formPanel.add(label);
			
			//Declaring each JTextField and add it to formPanel 
			JTextField text = texts[i];
			text.setForeground(Color.DARK_GRAY);
			formPanel.add(text); 
		}	
		
		//adding spinner panel
		spinnerPanel= new JPanel (new GridLayout(1,1,0,0));
		spinnerPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		spinnerPanel.setBackground(Color.DARK_GRAY);
		formPanel.add(spinnerPanel);
		creditLab = new JLabel("Credit: ");
		creditLab.setForeground(Color.WHITE);
		spinnerPanel.add(creditLab);
		creditSpinnerModel = new SpinnerNumberModel(1,1,15,1);
		creditSpinner = new JSpinner(creditSpinnerModel);
		formPanel.add(creditSpinner);	
		
		totalCreditLab = new JLabel("Total Credit");
		totalCreditTxt = new JTextField();
		totalPointsLab = new JLabel("Total Points");
		totalPointsTxt = new JTextField();
		averageGradeLab = new JLabel("Average Grade");
		averageGradeTxt = new JTextField();
		gpaResultLab = new JLabel("GPA: ");
		gpaResultTxt = new JTextField();
		cgpaResultLab = new JLabel("CGPA: ");
		cgpaResultTxt = new JTextField();
	
		JLabel [] labelsResult = {totalCreditLab, totalPointsLab, averageGradeLab, gpaResultLab, cgpaResultLab};
		JTextField [] textsResult = {totalCreditTxt, totalPointsTxt, averageGradeTxt, gpaResultTxt, cgpaResultTxt};
		
		for(int i = 0 ; i < textsResult.length ; i++)
		{
			JLabel label = new JLabel(labelsResult[i].getText());
			label.setForeground(Color.WHITE);
			formPanel.add(label);
			
			JTextField text = textsResult[i];
			text.setForeground(Color.DARK_GRAY);
			text.setEditable(false); 
			formPanel.add(text);
		}
	}

	/**
	  
	Method Name:	CreateTable
	Purpose:        A method to create a table and add it to JPanel
	Accepts:        Nothing
	Returns:        Void
	Coder:          Leen Aboukhalil
	Date:           May 27, 2024
	*/
	private void CreateTable()
	{
		//Creating the table and add it to bodyPanel
	    Object[][] data = {};
		String [] tableColumns = {"Course", "Grade" , "Credit"};
		tableModel = new DefaultTableModel(data, tableColumns);
		table = new JTable(tableModel);
		table.setBackground(Color.LIGHT_GRAY);
		tableScrollPane = new JScrollPane(table); 
		bodyPanel.add(tableScrollPane);		
	}
	
	/**
	  
	Method Name:    CreateButton
	Purpose:        A method to set up the button and add it to JPanel
	Accepts:        Nothing
	Returns:        Void
	Coder:          Leen Aboukhalil
	Date:           May 27, 2024
	*/
	private void CreateButton()
	{
		//Creating and adding buttonPanel to JFrame
		buttonPanel = new JPanel(new GridLayout(1,7,10,10));
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		buttonPanel.setBackground(Color.DARK_GRAY);
		this.add(buttonPanel, BorderLayout.SOUTH);
		
		//Declaring JButton
		addBtn = new JButton("Add");
		calculateBtn = new JButton("Calculate");
		backBtn = new JButton("Back");
		deleteBtn = new JButton("Delete");
		clearBtn = new JButton("Clear");
		transcriptBtn = new JButton("Transcript");
		exitBtn = new JButton("Exit");
		
		//storing each JButton in an array
		JButton [] buttonArray = {addBtn, calculateBtn, backBtn, clearBtn, deleteBtn, transcriptBtn, exitBtn};
		
		for(int i = 0; i< buttonArray.length ; i++)
		{
			//declaring each JButton and add it to buttonPanel 
			JButton button = buttonArray[i];
			button.setBackground(Color.GRAY);
			button.setForeground(Color.WHITE);
			buttonPanel.add(button);
			button.setFont(font2);
		}
	}
	
	/**
	  
	Method Name:	AddButton    
	Purpose:        Filling the table with the user input
	Accepts:        Container object, boolean
	Returns:        void
	Coder:          Leen Aboukhalil
	Date:           May 27, 2024
	*/
	private void AddButton(Container contentPane, boolean yesAnswer)
	{
	
		//if all the require JTextField is not empty, fill the table with the user input
		if(!(courseNameTxt.getText().isEmpty() || gradeTxt.getText().isEmpty()))
		{
			Object[] rowData = {courseName, gradeLetter, creditDouble}; 
			tableModel.addRow(rowData); 
		}
		else
			JOptionPane.showMessageDialog(contentPane, "Fill in all the text input, please!", "Error Message", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	  
	Method Name:    MessageAnswer
	Purpose:        Assigning each answer the user will chose from ConfirmKialog message to do something
	Accepts:        Nothing
	Returns:        Void
	Coder:          Leen Aboukhalil
	Date:           May 27, 2024
	*/
	private void MessageAnswer()
	{
		//storing the user answer in a int variable
		answer = JOptionPane.showConfirmDialog(contentPane,  "Do you want to calculate your cumulative GPA? or just your GPA");
		
		if(answer == JOptionPane.YES_OPTION)
		{
			yesAnswer = true; 
		}
		else if(answer == JOptionPane.CANCEL_OPTION)
		{
			cancleAnswer = true; 
		}
		else
		{	
			//if the user's answer is NO means the user does not want to calculate Cumulative GPA so set editable to false
			cgpaTxt.setEditable(false);
			cCreditTxt.setEditable(false);
		}
		
		if(yesAnswer == true)
		{
			//to allow the user write there CGPA and current credit
			cgpaTxt.setEditable(true);
			cCreditTxt.setEditable(true);			
		}
		else if(cancleAnswer == true)
		{
			//close the page
			this.dispose();
		}

	}	
		
	/**
	  
	Method Name:    GradeLetter
	Purpose:        A method for calculate the GPA and CGPA for the user 
	Accepts:        double
	Returns:        void
	Coder:          Leen Aboukhalil
	Date:           May 27, 2024
	*/
	private void gradeLetter(double gradeDouble)
	{
		if(gradeDouble >= 90 && gradeDouble <= 100)
		{
			gradeLetter = "A+";
			gpaList.add(4.2);
		}
		else if(gradeDouble >= 80 && gradeDouble <= 89)
		{
			gradeLetter = "A";
			gpaList.add(4.0);
		}
		else if(gradeDouble >= 75 && gradeDouble <= 79)
		{
			gradeLetter = "B+";
			gpaList.add(3.5);
		}
		else if(gradeDouble >= 70 && gradeDouble <= 74)
		{
			gradeLetter = "B";
			gpaList.add(3.0);
		}
		else if(gradeDouble >= 65 && gradeDouble <= 69)
		{
			gradeLetter = "C+";
			gpaList.add(2.5);
		}
		else if(gradeDouble >=60 && gradeDouble <= 64)
		{
			gradeLetter = "C";
			gpaList.add(2.0);
		}
		else if(gradeDouble >= 55 && gradeDouble <= 59)
		{
			gradeLetter = "D+";
			gpaList.add(1.5);
		}
		else if(gradeDouble >= 50 && gradeDouble <= 54 )
		{
			gradeLetter = "D";
			gpaList.add(1.0);
		}
		else if(gradeDouble <= 49 && gradeDouble >= 0)
		{
			gradeLetter = "F";
			gpaList.add(0.0);
		}
		
	   	//1. calculate current quality points
		totalCurrentPoints = cgpaDouble * cCreditDouble; 
    	//System.out.println("1. Current quality points: " + totalCurrentPoints);
	    	 
	   	//2. calculate the points for new course

	 	for(Double gpa : gpaList)
		{
			points = gpa * creditDouble; 
		}
	 		
	 	totalCredit += creditDouble;	 		
		totalPoints += points; 
	 		
	 	//calculating GPA
	 	gpa = totalPoints / totalCredit;  
	 		
		//3. sum the total quality points
		totalQualityPoints = totalPoints + totalCurrentPoints;
		
		//4. sum the credit hour
		sumCredit = cCreditDouble + totalCredit;
					    
		//5. calculate the cumulative GPA
	 	cgpaResult = totalQualityPoints / sumCredit;  
	 	gpa = totalPoints / totalCredit; 
  
	 	if(yesAnswer == true)
	 	{
	 		GPAMainClass student = new GPAMainClass(sumCredit, totalQualityPoints, gradeDouble,gpaLetter, gpa, cgpaResult); 
	 		studentGrade = student.getStudentList();
	 	}
	 	else
	 	{
	 		GPAMainClass student = new GPAMainClass(totalCredit, totalPoints, gradeDouble, gpaLetter, gpa, cgpaResult); 
	 		studentGrade = student.getStudentList(); 
	 	}
	 		
		for(GPAMainClass stu : studentGrade)
		{
			System.out.println(stu);
		}
		
	  }
	
	/**
	  
	Method Name     AverageGrade
	Purpose:        Assign the standard letter for the student's GPA
	Accepts:        double
	Returns:        void
	Coder:          Leen Aboukhalil
	Date:           May 27, 2024
	*/
	private void AverageGrade(double gpa)
	{
		//print a message for the user depending on which grade the user get
	    if(gpa >= 4.0 && gpa <= 4.2)
	    {
	    	gpaLetter = "A+";
	    	JOptionPane.showMessageDialog(contentPane, "congratulations!!, Your GPA is distinguished.");
	    }
	    else if(gpa == 4.0)
	    {
	    	gpaLetter = "A";
	    	JOptionPane.showMessageDialog(contentPane, "congratulations!!, Your GPA is excellent.");
	    }
	    else if(gpa >= 3.5 && gpa < 4.0)
	    {
	    	gpaLetter = "B+";
	    	JOptionPane.showMessageDialog(contentPane, "congratulations!!, Your GPA is very good.");
	    }
	    else if(gpa >= 3.0 && gpa < 3.5)
	    {
	    	gpaLetter = "B";
	    	JOptionPane.showMessageDialog(contentPane, "congratulations, Your GPA is Excellent.");
	    }
	    else if(gpa >= 2.5 && gpa < 3.0)
	    {
	    	gpaLetter = "C+";
	    	JOptionPane.showMessageDialog(contentPane, "congratulations, Your GPA is Satisfactory.");
	    }
	    else if(gpa >= 2.0 && gpa < 2.5)
	    {
	    	gpaLetter = "C";
	    	JOptionPane.showMessageDialog(contentPane, "congratulations, Your GPA is Satisfactory.");
	    }
	    else if(gpa >= 1.5 && gpa < 2.0)
	    {
	    	gpaLetter = "D+";
	    	JOptionPane.showMessageDialog(contentPane, "congratulations, Your GPA is Marginal.");
	    }
	    else if(gpa >= 1.0 && gpa < 1.5)
	    {
	    	gpaLetter = "D";
	    	JOptionPane.showMessageDialog(contentPane, "congratulations, Your GPA is Marginal.");
	    }
	    else if(gpa < 1.0 && gpa >= 0)
	    {
	    	gpaLetter ="F";
	    	JOptionPane.showMessageDialog(contentPane, "Good luck next time, work harder!");
	    }
	    
	    System.out.println(gpaLetter);
	}
	
	
	/**
	  
	Method Name    	SetText
	Purpose:        To print the data in a JTextField 
	Accepts:        nothing
	Returns:        void
	Coder:          Leen Aboukhalil
	Date:           May 27, 2024
	*/
	private void setText()
	{
		//setting the JTextField with the calculating data 
		if(yesAnswer == true){
			totalCreditTxt.setText(String.valueOf(sumCredit));
			totalPointsTxt.setText(String.valueOf(String.format("%.2f", totalQualityPoints)));
			averageGradeTxt.setText(gpaLetter);
			gpaResultTxt.setText(String.valueOf(String.format("%.2f", gpa)));
			cgpaResultTxt.setText(String.valueOf(String.format("%.2f", cgpaResult)));
		}
		else
		{
			totalCreditTxt.setText(String.valueOf(totalCredit));
			totalPointsTxt.setText(String.valueOf(String.format("%.2f", totalPoints)));
			averageGradeTxt.setText(gpaLetter);
			gpaResultTxt.setText(String.valueOf(String.format("%.2f",gpa)));
		}
	}
	
	/**
	  
	Method Name    DeletInput
	Purpose:       Delete the data that the user wrote just from JTextField 
	Accepts:       boolean
	Returns:       void
	Coder:         Leen Aboukhalil
	Date:          May 27, 2024
	*/
	private void DeleteInput(boolean yesAnswer)
	{
		//set all text field to an empty texts 
		courseNameTxt.setText("");
		gradeTxt.setText("");
		creditSpinner.setValue(1);
		totalCreditTxt.setText("");
		totalPointsTxt.setText("");
		averageGradeTxt.setText("");
		gpaResultTxt.setText("");
		cgpaResultTxt.setText("");
	}
	
	/**
	  
	Method Name     Clear all
	Purpose:        To clear all the data the user wrote in JTextField and the table
	Accepts:        nothing
	Returns:        void
	Coder:          Leen Aboukhalil
	Date:           May 27, 2024
	*/
	private void ClearAll()
	{
		//if the user chose yes for showConfirmDialog message, there will be some data there, so it will set texts to an empty text 
		if(yesAnswer == true)
		{
			cgpaTxt.setText("");
			cCreditTxt.setText("");
		}
		
		//if the user chose no for showConfirmDialog message, it will set the other texts to an empty text
		courseNameTxt.setText("");
		gradeTxt.setText("");
		creditSpinner.setValue(1);
		totalCreditTxt.setText("");
		totalPointsTxt.setText("");
		averageGradeTxt.setText("");
		gpaResultTxt.setText("");
		cgpaResultTxt.setText("");
		
		//set the table rows and columns to and empty table
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		
	}
	
	/**
	Method Name:    SetToZero
	Purpose:        set the variable to zero to reset all variables when the user click clear 
	Accepts:        nothing
	Returns:        void
	Coder:          Leen Aboukhalil
	Date:           May 27, 2024
	*/
	private void SetToZero() {
			totalCurrentPoints =0;
			points =0;
			totalCredit =0; 
			totalPoints=0;
			gpa=0;
			totalQualityPoints=0;
			sumCredit = 0;
			cgpaResult =0;
			
	}
	
	/**
	Method Name:    SaveStudentData
	Purpose:        To save the student data
	Accepts:        nothing
	Returns:        void
	Coder:          Leen Aboukhalil
	Date:           May 27, 2024
	*/
	private void SaveStudentData()
	{
		//writing the student data into "studentGPAData.txt" file
		try(PrintWriter printIn = new PrintWriter(new FileWriter("studentGPAData.txt")))
		{
			//write each data in a new line
			if(yesAnswer)
				printIn.println(String.valueOf(String.format("%.2f", cgpaResult)));  
			else
				printIn.println(String.valueOf(String.format("%.2f", gpa)));
			
			printIn.println(gpaLetter);
			
			if(yesAnswer)
				printIn.println(String.valueOf(sumCredit));
			else
				printIn.println(String.valueOf(totalCredit));
		}
		catch(IOException e)
		{
			e.getStackTrace();
		}
	}
	
	/**
	Method Name:    SaveStudentTable
	Purpose:        To save the data that is in a table in a file 
	Accepts:        nothing
	Returns:        void
	Coder:          Leen Aboukhalil
	Date:           May 27, 2024
	*/
	private void SaveStudentTable()
	{
		//writing the data from the table to "studentGPATable.txt" file
		try(PrintWriter tableIn = new PrintWriter(new FileWriter("studentGPATable.txt")))
		{
			//for loop for reading the information that is in the row and write the data to the file
			for(int row = 0; row < tableModel.getRowCount() ; row++)
			{
				//for loop for reading the information that is in the column and write the data to the file
				for(int col = 0; col < tableModel.getColumnCount(); col++) 
				{	
					//writing the data that we read from the row and column to the file 
					tableIn.print(tableModel.getValueAt(row, col));
					//to check if the column is not the last column in the column 
					if(col < tableModel.getColumnCount() -1)
					{
						//If the current column is not the last column, the code prints a comma (,) after printing the data of the current column
						tableIn.print(',');
					}
				}
				//after writing the row print new line
				tableIn.println();
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}
}

