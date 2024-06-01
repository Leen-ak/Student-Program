 /**
  * Program Name: TranscriptClass.java
  * Purpose: The TranscriptClass program is designed to display a student's academic transcript, including personal details, GPA, and a table of courses and grades, in a graphical user interface (GUI).
  *  @author Leen Aboukhalil
  * Date: May 27, 2024
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import javax.swing.table.*; 
public class TranscriptClass extends JFrame{

	private JPanel titlePanel, studentPanel, summaryPanel, buttonPanel;
	private JLabel titleLab ,studentIDlab, firstNameLab, lastNameLab, cgpaLab, averageGradeLab, creditLab;
	private JTextField studentIDTxt, firstNameTxt, lastNameTxt, cgpaTxt, averageGradeTxt, creditTxt;
	//private
	private JTable table;
	private DefaultTableModel tableModel;
	private JScrollPane tableScrollPane;
	private JButton exitBtn, backBtn;
	private String studentID, firstName, lastName;
	Font font = new Font("serif", Font.BOLD, 22);
	Font font2 = new Font("serif", Font.BOLD, 15);
	
	public TranscriptClass()
	{
		super("Studnet Transcript");
		
		//calling the method 
		CreatePanels();
		CreateWindow(); 
		LoadStudentData();
		LoadGPATable(); 
		LoadGPA();
		
		//creating the object for inner class
		SetAction action = new SetAction();
		
		//add actionListener to the buttons
		backBtn.addActionListener(action);
		exitBtn.addActionListener(action);
		
		
		this.setVisible(true);
	}
	
	private class SetAction implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent ev) {
			
			if(ev.getSource() == backBtn)
			{
				//if the user click back button it will open the GPACalculatr page
				dispose();
				new GPACalculator();
			}
			else if(ev.getSource() == exitBtn)
			{
				//if the user click exit button it will open the login page
				dispose();
				new StudentProgram();
			}
			
		}
		
	}
	
	/**
	Method Name:    CreatePanels
	Purpose:        Create panels and add the panels to JFrame
	Accepts:        nothing
	Returns:        void
	Coder:          Leen Aboukhalil
	Date:           May 22, 2024
	*/
	private void CreatePanels()
	{
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(700, 700);
		this.setLocationRelativeTo(null);

		titlePanel = new JPanel();
		titlePanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		this.add(titlePanel, BorderLayout.NORTH);
		titlePanel.setBackground(Color.DARK_GRAY);
		
		studentPanel = new JPanel(new GridLayout(3, 4, 20, 20));
		studentPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		studentPanel.setBackground(Color.DARK_GRAY);
		this.add(studentPanel, BorderLayout.CENTER);
		
		summaryPanel = new JPanel(new BorderLayout());
		summaryPanel.setBackground(Color.DARK_GRAY);
		summaryPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		this.add(summaryPanel, BorderLayout.SOUTH);
		
		buttonPanel = new JPanel();
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10,5,0,5));	
		
		summaryPanel.add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setBackground(Color.DARK_GRAY);
		
	}
	
	/**
	Method Name:    CreateWindow
	Purpose:        To create JLabel and JTextField
	Accepts:        nothing
	Returns:        void
	Coder:          Leen Aboukhalil
	Date:           May 22, 2024
	*/
	private void CreateWindow()
	{
		titleLab = new JLabel("Fanshawe College Studnet Information");
		titleLab.setFont(font);
		titleLab.setForeground(Color.WHITE); 
		titlePanel.add(titleLab);
		
		studentIDlab = new JLabel("Student ID: ");
		studentIDTxt = new JTextField();
		firstNameLab = new JLabel("First Name: ");
		firstNameTxt = new JTextField();
		lastNameLab = new JLabel("Last Name: ");
		lastNameTxt = new JTextField();
		cgpaLab = new JLabel("Student GPA: ");
		cgpaTxt = new JTextField();
		averageGradeLab = new JLabel("Student Stand: ");
		averageGradeTxt = new JTextField();
		creditLab = new JLabel("Credit: ");
		creditTxt = new JTextField();
		
		//creating table
		JLabel [] labels = {studentIDlab, firstNameLab, lastNameLab, cgpaLab, averageGradeLab, creditLab};
		JTextField [] texts = {studentIDTxt, firstNameTxt, lastNameTxt, cgpaTxt, averageGradeTxt, creditTxt};
		for(int i = 0; i < texts.length; i++)
		{
			JLabel label = labels[i];
			studentPanel.add(label);
			label.setForeground(Color.WHITE);
			
			JTextField text = texts[i];
			text.setEditable(false);
			studentPanel.add(text);
		}
		Object [] [] data = {};
		String [] tableColumns = {"Course Name", "Mark", "Credit"};		
		tableModel = new DefaultTableModel(data, tableColumns);
		table = new JTable(tableModel);
		tableScrollPane = new JScrollPane(table);			
		
		//making JTabelHeader background light gray and the font color dark gray 
		JTableHeader header = table.getTableHeader(); 
		header.setFont(font2);
		header.setBackground(Color.LIGHT_GRAY);
		header.setForeground(Color.DARK_GRAY);
		//add the table to summaryPanel
		summaryPanel.add(tableScrollPane, BorderLayout.CENTER);

		//creating buttons 
		exitBtn = new JButton("Exit");
		exitBtn.setBackground(Color.GRAY);
		exitBtn.setForeground(Color.WHITE);
		buttonPanel.add(exitBtn);
		
		backBtn = new JButton("Back");
		backBtn.setBackground(Color.GRAY);
		backBtn.setForeground(Color.WHITE);
		buttonPanel.add(backBtn);

	}
	
	/**
	Method Name:  	LoadStudentData  
	Purpose:        to load studentID, firstName, lastName
	Accepts:        nothing
	Returns:        void
	Coder:          Leen Aboukhalil
	Date:           May 22, 2024
	*/
	private void LoadStudentData()
	{
		try(BufferedReader bufferIn = new BufferedReader (new FileReader("studentData.txt")))
		{
			studentIDTxt.setText(bufferIn.readLine());
			firstNameTxt.setText(bufferIn.readLine());
			lastNameTxt.setText(bufferIn.readLine());
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	
	/**
	Method Name:    LoadGPATable
	Purpose:        To load final GPA, credit, and student stand
	Accepts:        nothing
	Returns:        void
	Coder:          Leen Aboukhalil
	Date:           May 22, 2024
	*/
	private void LoadGPATable()
	{
		try(BufferedReader bufferGpa = new BufferedReader (new FileReader("studentGPAData.txt")))
		{
			cgpaTxt.setText(bufferGpa.readLine());
			averageGradeTxt.setText(bufferGpa.readLine());
			creditTxt.setText(bufferGpa.readLine());
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	Method Name:    LoadGPA
	Purpose:        To load the courses, grades, credits to the file 
	Accepts:        nothing
	Returns:        void
	Coder:          Leen Aboukhalil
	Date:           May 22, 2024
	*/
	private void LoadGPA()
	{
		try(BufferedReader bufferTable = new BufferedReader (new FileReader("StudentGPATable.txt")))
		{
			String line;
			while ((line = bufferTable.readLine()) != null)
			{
				//loading the data from the file into a table
				String [] rowData = line.split(",");
				tableModel.addRow(rowData);
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
			new TranscriptClass();
	}
}
