/**********************
* COMP3380 Project
* HRDatabase
* Group 7
* This file contains all the code relating to populating the database
* as well as gettting the resulting tables from executing the queries.
***********************/

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.sql.*;
import java.util.Properties;
import java.io.BufferedReader;
import javax.swing.*;



public class HRDatabase {

    // Connect to your database.
    // Replace server name, username, and password with your credentials
    public static void main(String[] args) {

        Database theDatabase = new Database();
        //theDatabase.fillDatabase();
        JTable table = theDatabase.university();
        table.setBounds(30, 40, 200, 300);

        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JScrollPane sp = new JScrollPane(table);
        f.add(sp);
        // Frame Size
        f.setSize(500, 200);
        // Frame Visible = true
        f.setVisible(true);
        
    }

}

class Database {

    Connection connection;

    public Database() {

        Properties prop = new Properties();
        String fileName = "auth.cfg";
        try {
            FileInputStream configFile = new FileInputStream(fileName);
            prop.load(configFile);
            configFile.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Could not find config file.");
            System.exit(1);
        } catch (IOException ex) {
            System.out.println("Error reading config file.");
            System.exit(1);
        }
        String username = (prop.getProperty("username"));
        String password = (prop.getProperty("password"));

        if (username == null || password == null){
            System.out.println("Username or password not provided.");
            System.exit(1);
        }

        String connectionUrl =
                "jdbc:sqlserver://uranium.cs.umanitoba.ca:1433;"
                + "database=cs3380;"
                + "user=" + username + ";"
                + "password="+ password +";"
                + "encrypt=false;"
                + "trustServerCertificate=false;"
                + "loginTimeout=30;";


        try {
            connection = DriverManager.getConnection(connectionUrl);
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

    }

    // This method reads in the Database.sql table and populates the database with
    // our tables
    // and the data that goes into those tables.
    public void fillDatabase() {

        // To read in the file we keep appending to the end of thr string until we
        // get a ; while ignoring lines that start with --.

        try {
            // Open the file
            BufferedReader input = new BufferedReader(new FileReader("Database.sql"));
            String line;
            String sqlStatement = "";
            PreparedStatement prepare;

            System.out.println("Please wait. Database is getting populated...");

            line = input.readLine();

            while (line != null) {
                line = line.trim();

                // Check for comment then parse the line if needed
                if (!(line.length() >= 2 && line.charAt(0) == '-' && line.charAt(1) == '-')) {
                    sqlStatement += line;// if not a comment add the append the line to the statement.
                }

                // Now check the statement for the semi-colon in which case we execute the line.
                if (line.length() >= 1 && line.charAt(line.length() - 1) == ';') {
                    prepare = connection.prepareStatement(sqlStatement);
                    prepare.execute();

                    // Clear out the sqlStatement
                    sqlStatement = "";

                }

                line = input.readLine();// Get the next line.
            }

            input.close();

        } catch (IOException ioe) {
            System.out.println("Unable to read file Database.sql");
        } catch (SQLException sqle) {
            sqle.printStackTrace(System.out);
        }

        System.out.println("\nDatabase filled successfully!");
    }// fill Database

    // This method is used to output the query which returns all the employees who
    // make over a specified amount.
    public JTable employeeMakes(int overAmount) {
        String sql = "select employeeID, employee.hourlyRate, dailySalary.dailyRate, monthlySalary.monthlyRate from employee join dailySalary"
                +
                " on employee.hourlyRate = dailySalary.hourlyRate join monthlySalary on dailySalary.dailyRate = monthlySalary.dailyRate"
                +
                " where monthlySalary.monthlyRate > ?" +
                " order by employee.hourlyRate desc;";
                
                JTable display = null;


        try {
            PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.setInt(1, overAmount);
            ResultSet result = statement.executeQuery();


            display = getJTable(result);


            statement.close();
            result.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace(System.out);
        }

        return display;
    }

    // This method is used to execute the sql query that returns all the employees
    // who work in the same
    // department and attended the same university.
    public JTable employeeWorksAttends(String department, String university) {
        String sql = "select employeeID from employee join department on employee.departmentID = department.departmentID"
                +
                " join university on employee.universityID = university.universityID" +
                " where departmentName like '?' and universityName like '?';";
                
        JTable display = null;


        try {
            PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.setString(1, department);
            statement.setString(2, university);
            ResultSet result = statement.executeQuery();


            display = getJTable(result);

            statement.close();
            result.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace(System.out);
        }
        
        return display;
    }

    // This method executes the sql query which returns the average salary for each
    // department.
    public JTable averageSalaryForDepartment() {
        String sql = "select departmentName, avg(monthlyRate) as monthlyAvg from employee join department" +
                " on employee.departmentID = department.departmentID join dailySalary on employee.hourlyRate = dailySalary.hourlyRate"
                +
                " join monthlySalary on monthlySalary.dailyRate = dailySalary.dailyRate" +
                " group by departmentName;";
                
        JTable display = null;

        try {
            PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet result = statement.executeQuery();

            display = getJTable(result);

            statement.close();
            result.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace(System.out);
        }
        
        return display;
    }

    // This method executes the sql query which returns average monthly rate for
    // employees who attended each university and work in the same department.
    public JTable averageSalaryForUniversityAndDepartment() {
        String sql = "select universityName, departmentName, avg(monthlyRate) as monthlyAvg from employee join department"
                +
                " on employee.departmentID = department.departmentID join university on employee.universityID = university.universityID"
                +
                " join dailySalary on dailySalary.hourlyRate = employee.hourlyRate join monthlySalary on" +
                " monthlySalary.dailyRate = dailySalary.dailyRate" +
                " group by universityName, departmentName;";
                
        JTable display = null;

        try {
            PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet result = statement.executeQuery();

            display = getJTable(result);

            statement.close();
            result.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace(System.out);
        }
        
        return display;
    }

    // This method is used to output the query which returns employees who attended
    // a [univeristy] and do not have [seniority].
    public JTable employeeWhoAttendedUniverisyNotSameSeniority(String uni, String seniority) {
        String sql = "select employeeID, seniority from employee" +
                " join university on employee.universityID = university.universityID" +
                " join experience on employee.totalWorkingYears = experience.totalWorkingYears" +
                " where universityName like ?" +
                " EXCEPT" +
                " select employeeID, seniority from employee" +
                " join experience on experience.totalWorkingYears = employee.totalWorkingYears" +
                " where seniority not like ?;";

	JTable display = null;
	
        try {
            PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.setString(1, uni);
            statement.setString(2, seniority);
            ResultSet result = statement.executeQuery();

            display = getJTable(result);

            statement.close();
            result.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace(System.out);
        }
        
        return display;
    }

    // This method is used to output the query which returns employees who studied
    // at the same [univerity] and have the same [education field]
    public JTable employeeStudiedAndSameField(String uni, String eduField) {
        String sql = "select employeeID from employee" +
                " join university on university.universityID = employee.universityID" +
                " where universityName like ?" +
                " INTERSECT" +
                " select employeeID from employee" +
                " where educationField like ?;";
                
	JTable display = null;
	
        try {
            PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.setString(1, uni);
            statement.setString(2, eduField);
            ResultSet result = statement.executeQuery();

            display = getJTable(result);
            
            statement.close();
            result.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace(System.out);
        }
        
        return display;
    }

    // This method is used to output the query which returns the highest
    // managerSatisfaction score for each group of seniority
    public JTable highestManagerSatisfactionBySenoirity() {
        String sql = "select seniority , max(managerSatisfaction) as highestSatisfaction" +
                " from employee" +
                " join experience on experience.totalWorkingYears = employee.totalWorkingYears" +
                " join jobRating on employee.jobInvolvement = jobRating.jobInvolvement" +
                " join performance on jobRating.jobSatisfaction = performance.jobSatisfaction" +
                " join managerRating on performance.performanceRating = managerRating.performanceRating" +
                " group by seniority;";
                
        JTable display = null;        

        try {
            PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet result = statement.executeQuery();

            display = getJTable(result);

            statement.close();
            result.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace(System.out);
        }
        
        return display;
    }

    // This method is used to output the query which returns the most common
    // transportation (vehicle) taken in a department
    public JTable mostCommonTransportation() {
        String sql = "with orderTransportation as (" +
                " select transportation.vehicle,departmentName , ROW_NUMBER() OVER (PARTITION by departmentName order by count(*) DESC ) as row_number"
                +
                " from transportation" +
                " join employee on transportation.vehicleID = employee.vehicle" +
                " join department on employee.departmentID = department.departmentID" +
                " group by departmentName, transportation.vehicle" +
                " )" +
                " SELECT vehicle,departmentName" +
                " from orderTransportation" +
                " where row_number = 1;";
                
        JTable display = null;

        try {
            PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet result = statement.executeQuery();

            display = getJTable(result);

            statement.close();
            result.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace(System.out);
        }
        
        return display;
    }

    // This method is used to output the query which returns the average job
    // satisfaction in each seniority group
    public JTable avgJobSatisfaction() {
        String sql = "select seniority , AVG(jobSatisfaction) as jobSatisfactionAverage from jobRating join employee "
                + "on employee.jobInvolvement = jobRating.jobInvolvement join experience on employee.totalWorkingYears = experience.totalWorkingYears"
                + " group by seniority order by jobSatisfactionAverage DESC;";
                
        JTable display = null;        

        try {
            PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet result = statement.executeQuery();

            display = getJTable(result);

            statement.close();
            result.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace(System.out);
        }
        
        return display;
    }

    // this method is used to output the query which returns the number of employees
    // in each department
    public JTable employeesInDept() {
        String sql = "select departmentName, count(employeeID) as employeeInDept from department join employee "
                + "on department.departmentID = employee.departmentID GROUP by departmentName order by employeeInDept ;";
                
        JTable display = null;        

        try {
            PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet result = statement.executeQuery();

            display = getJTable(result);

            statement.close();
            result.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace(System.out);
        }
        
        return display;
    }

    // this method is used to output the query which returns the highest salaries in
    // each department
    public JTable highestSalaryInDept() {
        String sql = "SELECT departmentName, max(monthlySalary.monthlyRate) as highestSalary from department join employee"
                +
                " on department.departmentID = employee.departmentID join dailySalary on employee.hourlyRate = dailySalary.hourlyRate "
                +
                "join monthlySalary on dailySalary.dailyRate = monthlySalary.dailyRate GROUP by departmentName order by highestSalary;";
                
        JTable display = null;        

        try {
            PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet result = statement.executeQuery();

            display = getJTable(result);

            statement.close();
            result.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace(System.out);
        }
        
        return display;
    }

    // this method is used to output the query which returns the number of employees
    // with a specific managerSatisfaction Score from a specific departmentName
    public JTable employeeScore(String dept, int mngrSatisfaction) {
        String sql = "select departmentName,managerSatisfaction, count(employeeID) as numOfEmployees from employee join department on employee.departmentID = department.departmentID join jobRating on employee.jobInvolvement = jobRating.jobInvolvement join performance on jobRating.jobSatisfaction = performance.jobSatisfaction join managerRating on performance.performanceRating = managerRating.performanceRating where departmentName like ? and managerSatisfaction = ? group by departmentName, managerSatisfaction;";
        
        JTable display = null;

        try {
            PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.setString(1, dept);
            statement.setInt(2, mngrSatisfaction);
            ResultSet result = statement.executeQuery();

            display = getJTable(result);

            statement.close();
            result.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace(System.out);
        }
        
        return display;
    }
    
    //This method returns the entire dailySalary Table.
    public JTable dailySalary() {
        String sql = "select * from dailySalary;";
                
        JTable display = null;        

        try {
            PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet result = statement.executeQuery();

            display = getJTable(result);

            statement.close();
            result.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace(System.out);
        }
        
        return display;
    }
    
     //This method returns the entire department Table.
    public JTable department() {
        String sql = "select * from department;";
                
        JTable display = null;        

        try {
            PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet result = statement.executeQuery();

            display = getJTable(result);

            statement.close();
            result.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace(System.out);
        }
        
        return display;
    }
    
    //This method returns the entire employee Table.
    public JTable employee() {
        String sql = "select * from employee;";
                
        JTable display = null;        

        try {
            PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet result = statement.executeQuery();

            display = getJTable(result);

            statement.close();
            result.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace(System.out);
        }
        
        return display;
    }
    
    //This method returns the entire experience Table.
    public JTable experience() {
        String sql = "select * from experience;";
                
        JTable display = null;        

        try {
            PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet result = statement.executeQuery();

            display = getJTable(result);

            statement.close();
            result.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace(System.out);
        }
        
        return display;
    }
    
    //This method returns the entire jobRating Table.
    public JTable jobRating() {
        String sql = "select * from jobRating;";
                
        JTable display = null;        

        try {
            PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet result = statement.executeQuery();

            display = getJTable(result);

            statement.close();
            result.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace(System.out);
        }
        
        return display;
    }
    
    //This method returns the entire managerRating Table.
    public JTable managerRating() {
        String sql = "select * from managerRating;";
                
        JTable display = null;        

        try {
            PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet result = statement.executeQuery();

            display = getJTable(result);

            statement.close();
            result.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace(System.out);
        }
        
        return display;
    }
    
    //This method returns the entire monthlySalary Table.
    public JTable monthlySalary() {
        String sql = "select * from monthlySalary;";
                
        JTable display = null;        

        try {
            PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet result = statement.executeQuery();

            display = getJTable(result);

            statement.close();
            result.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace(System.out);
        }
        
        return display;
    }
    
    //This method returns the entire monthlySalary Table.
    public JTable performance() {
        String sql = "select * from performance;";
                
        JTable display = null;        

        try {
            PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet result = statement.executeQuery();

            display = getJTable(result);

            statement.close();
            result.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace(System.out);
        }
        
        return display;
    }
    
    //This method returns the entire monthlySalary Table.
    public JTable transportation() {
        String sql = "select * from transportation;";
                
        JTable display = null;        

        try {
            PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet result = statement.executeQuery();

            display = getJTable(result);

            statement.close();
            result.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace(System.out);
        }
        
        return display;
    }
    
    //This method returns the entire monthlySalary Table.
    public JTable university() {
        String sql = "select * from university;";
                
        JTable display = null;        

        try {
            PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet result = statement.executeQuery();

            display = getJTable(result);

            statement.close();
            result.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace(System.out);
        }
        
        return display;
    }


    //This method will take in a resultSet as a parameter and return a 2d array with the data.
    private String[][] getTable(ResultSet result) throws SQLException
    {
        String[][] table;

        result.last();//Want to get last row number.
        int numRows = result.getRow();//Returns the row number of last row.
        result.first();//Moves the curser back to the begining.

        ResultSetMetaData gettingColNum = result.getMetaData();
        int numCols = gettingColNum.getColumnCount();

        table = new String[numRows][numCols];
        int rowIndex = 0;

        if(numRows > 0)
        {
            do{
                for(int i = 1; i <= numCols; i++)
                {
                    table[rowIndex][i-1] = result.getString(i);
                }

                rowIndex++;
            } while(result.next());
        }

        return table;
    }
    
    //This method is used to create a JTable from a ResultSet that we get after executing a Query.
    //This method takes in a ResultSet and returns the corresponding JTable.
    //Note that this method will throw an SQLException from working with the ResultSet.
    private JTable getJTable(ResultSet result) throws SQLException
    {
    	    //These are the tables that we need in order to create a JTable
   	    String[][] table = null;
            JTable display = null;
       	    String[] names = null;
       	    
       	    //Here we are getting the number of columns
       	    //so that we can create our column name array for the JTable
    	    ResultSetMetaData gettingName = result.getMetaData();
            names = new String[gettingName.getColumnCount()];

            for(int i = 0; i < names.length; i++)
            {
                names[i] = gettingName.getColumnName(i + 1);
            }
            
	    //Now we grab the 2D array with our data.	
            table = getTable(result);
            
            //Create the JTable
            display = new JTable(table, names);
            
            return display;
    }

}
