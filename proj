import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.io.BufferedReader;
import java.util.Scanner;
import java.sql.PreparedStatement;


public class HRDatabase {

    // Connect to your database.
    // Replace server name, username, and password with your credentials
    public static void main(String[] args) {

        Database theDatabase = new Database();
       // theDatabase.fillDatabase();
       theDatabase.employeeMakes(10000);
    }

}


class Database {
        
    Connection connection;

    public Database(){

        //Need to get the credentials from the user to get into the database.
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

        try{
            connection = DriverManager.getConnection(connectionUrl);
        }
        catch(SQLException e){
            e.printStackTrace(System.out);
		}
        
    }

    //This method reads in the Database.sql table and populates the database with our tables
    //and the data that goes into those tables.
    public void fillDatabase(){

        //To read in the file we keep appending to the end of thr string until we 
        //get a ; while ignoring lines that start with --.

        try{
            //Open the file
            BufferedReader input = new BufferedReader(new FileReader("Database.sql"));
            String line;
            String sqlStatement = "";
            PreparedStatement prepare;

            System.out.println("Please wait. Database is getting populated...");

            line = input.readLine();

            while(line != null){
                line = line.trim();

                //Check for comment then parse the line if needed
                if(!(line.length() >= 2 && line.charAt(0) == '-' && line.charAt(1) == '-'))
                {
                    sqlStatement += line;//if not a comment add the append the line to the statement.
                }

                //Now check the statement for the semi-colon in which case we execute the line.
                if(line.length() >= 1 && line.charAt(line.length() - 1) == ';')
                {
                    prepare = connection.prepareStatement(sqlStatement);
                    prepare.execute();

                    //Clear out the sqlStatement
                    sqlStatement = "";

                }

                line = input.readLine();//Get the next line.
            }

            input.close();


        }
        catch(IOException ioe)
        {
            System.out.println("Unable to read file Database.sql");
        }
        catch(SQLException sqle)
        {
            sqle.printStackTrace(System.out);
        }

        System.out.println("\nDatabase filled successfully!");
    }//fill Database

    //This method is used to output the query which returns all the employees who make over a specified amount.
    public void employeeMakes(int overAmount)
    {
        String sql = "select employeeID, employee.hourlyRate, dailySalary.dailyRate, monthlySalary.monthlyRate from employee join dailySalary" +
        " on employee.hourlyRate = dailySalary.hourlyRate join monthlySalary on dailySalary.dailyRate = monthlySalary.dailyRate" +
        " where monthlySalary.monthlyRate > ?" +
        " order by employee.hourlyRate desc;";

        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, overAmount);
            ResultSet result = statement.executeQuery();

            System.out.println("Returning all employees who make over the monthly rate of: " +
                                overAmount + ".\nemployeeID hourlyRate dailyRate monthlyRate");
            while(result.next())
            {
                System.out.println(result.getInt("employeeID") + " " + result.getInt("hourlyRate") +
                                    " " + result.getInt("dailyRate") + " " + result.getInt("monthlyRate"));
            }

            statement.close();
            result.close();

        }
        catch(SQLException sqle)
        {
            sqle.printStackTrace(System.out);
        }
    }

    //This method is used to execute the sql query that returns all the employees who work in the same
    //department and attended the same university.
    public void employeeWorksAttends(String department, String university)
    {
        String sql = "select employeeID from employee join department on employee.departmentID = department.departmentID" +
                        " join university on employee.universityID = university.universityID" +
                        " where departmentName like '?' and universityName like '?';";

        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, department);
            statement.setString(2, university);
            ResultSet result = statement.executeQuery();

            System.out.println("Returning all employees who work in: " +
                                department + " and attended: " + university + ".\nemployeeID");
            while(result.next())
            {
                System.out.println(result.getInt("employeeID"));
            }

            statement.close();
            result.close();

        }
        catch(SQLException sqle)
        {
            sqle.printStackTrace(System.out);
        }
    }

    //This method executes the sql query which returns the average salary for each department.
    public void averageSalaryForDepartment()
    {
        String sql = "select departmentName, avg(monthlyRate) as monthlyAvg from employee join department" +
                     " on employee.departmentID = department.departmentID join dailySalary on employee.hourlyRate = dailySalary.hourlyRate" +
                    " join monthlySalary on monthlySalary.dailyRate = dailySalary.dailyRate" +
                    " group by departmentName;";

        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            System.out.println("Returning average salary for each department: " +
                                "\ndepartmentName monthlyAvg");
            while(result.next())
            {
                System.out.println(result.getString("departmentName") + " " + result.getInt("MonthlyAvg"));
            }

            statement.close();
            result.close();

        }
        catch(SQLException sqle)
        {
            sqle.printStackTrace(System.out);
        }
    }

    //This method executes the sql query which returns average monthly rate for employees who attended each university and work in the same department.
    public void averageSalaryForUniversityAndDepartment()
    {
        String sql = "select universityName, departmentName, avg(monthlyRate) as monthlyAvg from employee join department" +
                        " on employee.departmentID = department.departmentID join university on employee.universityID = university.universityID" +
                        " join dailySalary on dailySalary.hourlyRate = employee.hourlyRate join monthlySalary on" + 
                        " monthlySalary.dailyRate = dailySalary.dailyRate" +
                        " group by universityName, departmentName;";

        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            System.out.println("Returning average salary for employees who attended each univeristy and work in the same department: " +
                                "\nuniversityName departmentName monthlyAvg");
            while(result.next())
            {
                System.out.println(result.getString("universityName") + " " + result.getInt("departmentName") + " " + result.getInt("monthlyAvg"));
            }

            statement.close();
            result.close();

        }
        catch(SQLException sqle)
        {
            sqle.printStackTrace(System.out);
        }
    }

        //This method is used to output the query which returns employees who attended a [univeristy] and do not have [seniority].
        public void employeeWhoAttendedUniverisyNotSameSeniority(String uni, String seniority)
        {
            String sql = "select employeeID, seniority from employee" + 
            " join university on employee.universityID = university.universityID" +
            " join experience on employee.totalWorkingYears = experience.totalWorkingYears" +
            " where universityName like ?" +
            " EXCEPT" +
            " select employeeID, seniority from employee" + 
            " join experience on experience.totalWorkingYears = employee.totalWorkingYears" +
            " where seniority not like ?;";
    
            try{
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, uni);
                statement.setString(2, seniority);
                ResultSet result = statement.executeQuery();
    
                System.out.println("EmployeeID - Seniority");
                while(result.next())
                {
                    System.out.println(result.getInt("employeeID") + " " + result.getString("seniority"));
                }
    
                statement.close();
                result.close();
    
            }
            catch(SQLException sqle)
            {
                sqle.printStackTrace(System.out);
            }
        }
    
        //This method is used to output the query which returns employees who studied at the same [univerity] and have the same [education field]
        public void employeeStudiedAndSameField(String uni, String eduField)
        {
            String sql = "select employeeID from employee" + 
            " join university on university.universityID = employee.universityID" +
            " where universityName like ?" +
            " INTERSECT" +
            " select employeeID from employee" +
            " where educationField like ?;";
    
            try{
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, uni);
                statement.setString(2, eduField);
                ResultSet result = statement.executeQuery();
    
                System.out.println("EmployeeID");
                while(result.next())
                {
                    System.out.println(result.getInt("employeeID"));
                }
    
                statement.close();
                result.close();
    
            }
            catch(SQLException sqle)
            {
                sqle.printStackTrace(System.out);
            }
        }
    
        //This method is used to output the query which returns the highest managerSatisfaction score for each group of seniority
        public void highestManagerSatisfactionBySenoirity()
        {
            String sql = "select seniority , max(managerSatisfaction) as highestSatisfaction" + 
            " from employee" + 
            " join experience on experience.totalWorkingYears = employee.totalWorkingYears" +
            " join jobRating on employee.jobInvolvement = jobRating.jobInvolvement" + 
            " join performance on jobRating.jobSatisfaction = performance.jobSatisfaction" +
            " join managerRating on performance.performanceRating = managerRating.performanceRating" +
            " group by seniority;";
    
            try{
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet result = statement.executeQuery();
    
                System.out.println("Seniority - Highest Satisfaction");
                while(result.next())
                {
                    System.out.println(result.getString("seniority") + " " + result.getInt("highestSatisfaction"));
                }
    
                statement.close();
                result.close();
    
            }
            catch(SQLException sqle)
            {
                sqle.printStackTrace(System.out);
            }
        }
    
        //This method is used to output the query which returns the most common transportation (vehicle) taken in a department
        public void mostCommonTransportation()
        {
            String sql = "with orderTransportation as (" +
                " select transportation.vehicle,departmentName , ROW_NUMBER() OVER (PARTITION by departmentName order by count(*) DESC ) as row_number" +
                " from transportation" +
                " join employee on transportation.vehicleID = employee.vehicle" +
                " join department on employee.departmentID = department.departmentID" +
                " group by departmentName, transportation.vehicle" +
                " )" +
                " SELECT vehicle,departmentName" +
                " from orderTransportation" +
                " where row_number = 1;";
    
            try{
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet result = statement.executeQuery();
    
                System.out.println("Vehicle - Department");
                while(result.next())
                {
                    System.out.println(result.getString("vehicle") + " " + result.getString("departmentName"));
                }
    
                statement.close();
                result.close();
    
            }
            catch(SQLException sqle)
            {
                sqle.printStackTrace(System.out);
            }
        }
}
