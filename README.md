## Student Information and GPA Calculator

This project is a comprehensive desktop application designed to manage and display student information, calculate GPA (Grade Point Average), and generate a student transcript. It is built using Java Swing, providing a user-friendly graphical user interface (GUI) for data input, validation, and display.

## Project Overview
The application allows students to log in, input personal and academic information, and calculate their GPA. The app also includes custom exception handling for validation and features methods for saving and retrieving student data from text files.

## Features
- **User Authentication:** Students log in using their student ID and password.
- **Student Information Management:** Input and save personal details like name, date of birth, and contact information.
- **GPA Calculation:** Add courses, grades, and credits to calculate the GPA and CGPA.
- **Transcript Generation:** View and save a student's transcript, including a table of courses and grades.
- **Custom Exception Handling:** Ensures data integrity with validation and error handling for negative values.
- **File Management:** Save and load student information, GPA data, and transcripts to and from text files.

## Prerequisites
**To run this project, you need:**
- Java Development Kit (JDK) 8 or higher.
- An IDE such as IntelliJ IDEA, Eclipse, or NetBeans.

## Installation
**Clone the repository:**
- git clone https://github.com/Leen-ak/Student-Program.git
- Open the project using your preferred IDE.
- Build the project to ensure all dependencies are correctly configured.

## Usage
- **Login:** Start the application by running StudentProgram.java. Enter the student ID and password to log in.
![login-page](https://github.com/user-attachments/assets/94fda963-076c-4cad-904c-995f71f31944)
![password-error](https://github.com/user-attachments/assets/bb2c252d-8b77-4df3-87c1-2d517cf72fd3)

- **Student Information:** After logging in, fill in the personal details and click Submit to save the data.
![info-page](https://github.com/user-attachments/assets/019a7230-cf2a-4837-b57a-11301e909d3a)
![studentID-error](https://github.com/user-attachments/assets/0fe09aa3-ee76-4a91-b0b7-7381d3d7fc38)
![name-error](https://github.com/user-attachments/assets/c26cd2c3-ab0f-4dfc-a6b0-9d9fda7765cb)
![date1-error](https://github.com/user-attachments/assets/9ff98366-ed89-4c8d-aa03-d120b51ac344)
![phone-error](https://github.com/user-attachments/assets/5cf85f3b-1f20-43cd-b7c2-89a29480ff0c)
![email-error](https://github.com/user-attachments/assets/48e78d8e-a20a-49a3-807a-e9632c103f9c)
![postalcode-error](https://github.com/user-attachments/assets/1882b236-e8a4-42bb-b4db-3f882ffde3cc)

- **GPA Calculation:** Enter the course name, grade, and credits, then click Add to include the course in GPA calculation. Click Calculate to view the GPA and CGPA.
![gpaORcgpa](https://github.com/user-attachments/assets/bae87f5b-9c31-4dd8-b54e-4a5e65145da9)
![gpa-page](https://github.com/user-attachments/assets/78de94fd-6e5d-4b3d-ab22-8541904da8b7)
![gpa-stand](https://github.com/user-attachments/assets/ec322932-176b-4a0c-9db1-02af144db300)
![gpa-output](https://github.com/user-attachments/assets/a75d7d9c-6513-495e-af82-2979593c0fb0)
![delete-button](https://github.com/user-attachments/assets/909b91a7-20cc-4ffd-950e-2fd6b816c286)
![clear-button](https://github.com/user-attachments/assets/22521390-d188-4c6a-ad98-95387eff945d)

- **Transcript:** View the transcript by clicking on the Transcript button. You can navigate through courses and view the detailed GPA information
![trans-page](https://github.com/user-attachments/assets/c75afc2f-0048-42a9-aef9-bfaf271ca7f3)

## File Management
The application saves and loads student data using text files:
- studentData.txt: Stores personal information like student ID, name, etc.
- studentGPAData.txt: Stores calculated GPA and related data.
- studentGPATable.txt: Stores course names, grades, and credits in a tabular format.

## Author
Leen Aboukhalil
