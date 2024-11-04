## HR Database User Interface
This repository contains code for a Java-based user interface designed to interact with an HR database using pre-defined SQL queries. The application enables users to retrieve, insert, update, and delete records from the HR database through a graphical interface built with Java Swing.

# Project Description
This project provides a user-friendly interface for interacting with an HR database, streamlining operations for HR staff to access and modify employee records, department details, job positions, and other HR-related data.

# Features
- Retrieve Records: View employee and department information from the HR database.
- Insert New Data: Add new employees, departments, and job positions.
- Update Existing Data: Modify details of existing records, such as employee information and job roles.
- Delete Records: Safely remove entries from the database.

# Project Structure
The project is organized into three main files:

Database.java

Contains all code related to database operations, including populating the database and executing SQL queries.
Handles CRUD operations and returns resulting tables to the gui file for display.

gui.java

Contains code for the graphical user interface (GUI) built with Java Swing.
Interacts with Database.java to retrieve tables and display them to the user.
Includes form components, buttons, and event handling for user interactions.

signIn.java

Manages the sign-in process for users accessing the application.
Ensures authentication before users can interact with the HR database through the GUI.

# Setup and Installation

Libraries and Imports

The project uses the following Java libraries:

- Swing: For creating the graphical user interface (javax.swing.*).
- AWT: To handle GUI event listeners and components (java.awt.*).
- JDBC: To connect to and interact with the SQL database (java.sql.*).
- File Handling: To read configuration and data files (java.io.*).

# Usage
Once the application is running:

Sign In:
Start by signing in using SignIn.java. After successful authentication, access is granted to the main interface.

Database Operations through GUI:
The GUI in Gui.java allows users to:

View employee and department information.
Insert, update, and delete records by interacting with the HR database through easy-to-use forms and buttons.
