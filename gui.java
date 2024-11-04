
/**********************
* COMP3380 Project
* HRDatabase
* Group 7
* Farah Hegazi (7903335)- hegazif@myumanitoba.ca 
* Braden Yablonski (7890012) - yablonsb@myumanitoba.ca 
* Mai Nguyen (7924202) - nguye82@myumanitoba.ca
* Instructor: Dr. Patrick Dubois
* Section: A01
* This file contains all the code relating to the interface
* as well as gettting the resulting tables from Database.java file
***********************/
import javax.imageio.plugins.bmp.BMPImageWriteParam;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class gui extends JFrame {

    // Main JFrame with all buttons
    static JFrame f;

    // default constructor
    gui() {
    }

    // main class
    public static void main(String[] args) {

        // create a new database object
        Database data = new Database();
        // fill the database object with the data from the database
        //data.fillDatabase();

        // creating an instance of panel class to display the tables buttons
        panel tables = new panel();

        // creating an instance of queries class to display the queries buttons
        queries qtables = new queries();

        f = new JFrame("Database");

        // creating the buttons
        tables.create(f);
        qtables.create(f);

        // creating labels to display text
        JLabel label = new JLabel("Select a table to view");
        JLabel label2 = new JLabel("Select a query to run");

        // add label
        f.add(label);
        f.add(label2);

        // set the position of the labels
        label.setBounds(250, 150, 300, 50);
        label2.setBounds(250, 400, 150, 50);

        // set the size of frame
        f.setSize(900, 600);

        f.setVisible(true);

        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // set action when employee button is pressed
        tables.getbutton1().addActionListener(
                new ActionListener() {
                    @Override
                    // This method will be called whenever you click the button.
                    public void actionPerformed(ActionEvent e) {
                        NewWindow nw = new NewWindow(); // create a new window
                        nw.createtable(data.employee(), 1500, 900, "Employee"); // create a table in the new window
                        f.setVisible(true);
                    }

                });

        // set action when department button is pressed
        tables.getbutton2().addActionListener(
                new ActionListener() {
                    @Override
                    // This method will be called whenever you click the button.
                    public void actionPerformed(ActionEvent e) {
                        NewWindow nw = new NewWindow(); // create a new window
                        nw.createtable(data.department(), 600, 300, "Department"); // create a table in the new window
                        f.setVisible(true);
                    }

                });

        // set action when dailySalary button is pressed
        tables.getbutton3().addActionListener(
                new ActionListener() {
                    @Override
                    // This method will be called whenever you click the button.
                    public void actionPerformed(ActionEvent e) {
                        NewWindow nw = new NewWindow(); // create a new window
                        nw.createtable(data.dailySalary(), 600, 300, "Daily Salary"); // create a table in the new
                                                                                      // window
                        f.setVisible(true);
                    }

                });

        // set action when monthlySalary button is pressed
        tables.getbutton4().addActionListener(
                new ActionListener() {
                    @Override
                    // This method will be called whenever you click the button.
                    public void actionPerformed(ActionEvent e) {
                        NewWindow nw = new NewWindow(); // create a new window
                        nw.createtable(data.monthlySalary(), 600, 300, "Monthly Salary"); // create a table in the new
                                                                                          // window
                        f.setVisible(true);
                    }

                });
        // set action when transportation button is pressed
        tables.getbutton5().addActionListener(
                new ActionListener() {
                    @Override
                    // This method will be called whenever you click the button.
                    public void actionPerformed(ActionEvent e) {
                        NewWindow nw = new NewWindow(); // create a new window
                        nw.createtable(data.transportation(), 600, 300, "Transportation"); // create a table in the new
                                                                                           // window
                        f.setVisible(true);
                    }

                });
        // set action when university button is pressed
        tables.getbutton6().addActionListener(
                new ActionListener() {
                    @Override
                    // This method will be called whenever you click the button.
                    public void actionPerformed(ActionEvent e) {
                        NewWindow nw = new NewWindow(); // create a new window
                        nw.createtable(data.university(), 600, 300, "University"); // create a table in the new window
                        f.setVisible(true);
                    }

                });
        // set action when experience button is pressed
        tables.getbutton7().addActionListener(
                new ActionListener() {
                    @Override
                    // This method will be called whenever you click the button.
                    public void actionPerformed(ActionEvent e) {
                        NewWindow nw = new NewWindow(); // create a new window
                        nw.createtable(data.experience(), 600, 300, "Experience"); // create a table in the new window
                        f.setVisible(true);
                    }

                });
        // set action when managerRating button is pressed
        tables.getbutton8().addActionListener(
                new ActionListener() {
                    @Override
                    // This method will be called whenever you click the button.
                    public void actionPerformed(ActionEvent e) {
                        NewWindow nw = new NewWindow(); // create a new window
                        nw.createtable(data.managerRating(), 600, 300, "Manager Rating"); // create a table in the new
                                                                                          // window
                        f.setVisible(true);
                    }

                });
        // set action when performance button is pressed
        tables.getbutton9().addActionListener(
                new ActionListener() {
                    @Override
                    // This method will be called whenever you click the button.
                    public void actionPerformed(ActionEvent e) {
                        NewWindow nw = new NewWindow(); // create a new window
                        nw.createtable(data.performance(), 600, 300, "Performance"); // create a table in the new window
                        f.setVisible(true);
                    }

                });

        // set action when JobRatinng button is pressed
        tables.getbutton10().addActionListener(
                new ActionListener() {
                    @Override
                    // This method will be called whenever you click the button.
                    public void actionPerformed(ActionEvent e) {
                        NewWindow nw = new NewWindow(); // create a new window
                        nw.createtable(data.jobRating(), 600, 300, "Job Rating"); // create a table in the new window
                        f.setVisible(true);
                    }

                });

        // set action when first query button is pressed
        qtables.getbutton1().addActionListener(
                new ActionListener() {
                    @Override
                    // This method will be called whenever you click the button.
                    public void actionPerformed(ActionEvent e) {
                        Q1 Query1 = new Q1(); // create a new window
                        int salary = Query1.getSalary();
                        Query1.createTable("EmployeeMakes", data.employeeMakes(salary));
                        // Query1.input();
                        f.setVisible(true);

                    }

                });
        // set action when second query button is pressed
        qtables.getbutton2().addActionListener(
                new ActionListener() {
                    @Override
                    // This method will be called whenever you click the button.
                    public void actionPerformed(ActionEvent e) {
                        Q2 Query2 = new Q2(); // create a new window
                        String department = Query2.getDepartment();
                        String university = Query2.getUniversity();
                        Query2.createTable("EmployeesWorkSameUni", data.employeeWorksAttends(department, university));
                        f.setVisible(true);

                    }

                });
        // set action when third query button is pressed
        qtables.getbutton3().addActionListener(
                new ActionListener() {
                    @Override
                    // This method will be called whenever you click the button.
                    public void actionPerformed(ActionEvent e) {
                        Q3 Query2 = new Q3(); // create a new window
                        Query2.createTable("DeptAvgSalary", data.averageSalaryForDepartment());
                        f.setVisible(true);

                    }

                });
        // set action when fourth query button is pressed
        qtables.getbutton4().addActionListener(
                new ActionListener() {
                    @Override
                    // This method will be called whenever you click the button.
                    public void actionPerformed(ActionEvent e) {
                        Q4 Query4 = new Q4(); // create a new window
                        Query4.createTable("AvgSalaryDeptUni", data.averageSalaryForUniversityAndDepartment());
                        f.setVisible(true);

                    }
                });
        // set action when fifth query button is pressed
        qtables.getbutton5().addActionListener(
                new ActionListener() {
                    @Override
                    // This method will be called whenever you click the button.
                    public void actionPerformed(ActionEvent e) {
                        Q5 Query5 = new Q5(); // create a new window
                        String university = Query5.getUniversity();
                        String seniority = Query5.getSeniority();
                        Query5.createTable("AttendUniNotSeniority",
                                data.employeeWhoAttendedUniverisyNotSameSeniority(university, seniority));
                        f.setVisible(true);

                    }
                });
        // set action when sixth query button is pressed
        qtables.getbutton6().addActionListener(
                new ActionListener() {
                    @Override
                    // This method will be called whenever you click the button.
                    public void actionPerformed(ActionEvent e) {
                        Q6 Query6 = new Q6(); // create a new window
                        String university = Query6.getUniversity();
                        String edufield = Query6.getEdu();
                        Query6.createTable("SameUniSameEducation",
                                data.employeeStudiedAndSameField(university, edufield));
                        f.setVisible(true);

                    }
                });
        // set action when seventh query button is pressed
        qtables.getbutton7().addActionListener(
                new ActionListener() {
                    @Override
                    // This method will be called whenever you click the button.
                    public void actionPerformed(ActionEvent e) {
                        Q7 Query7 = new Q7(); // create a new window
                        Query7.createTable("HighestManagerSatisfaction", data.highestManagerSatisfactionBySenoirity());
                        f.setVisible(true);

                    }
                });

        // set action when eigth query button is pressed
        qtables.getbutton8().addActionListener(
                new ActionListener() {
                    @Override
                    // This method will be called whenever you click the button.
                    public void actionPerformed(ActionEvent e) {
                        Q8 Query8 = new Q8(); // create a new window
                        Query8.createTable("CommonTransportation", data.mostCommonTransportation());
                        f.setVisible(true);

                    }
                });
        // set action when ninth query button is pressed
        qtables.getbutton9().addActionListener(
                new ActionListener() {
                    @Override
                    // This method will be called whenever you click the button.
                    public void actionPerformed(ActionEvent e) {
                        Q9 Query9 = new Q9(); // create a new window
                        Query9.createTable("avgJobSatisfaction", data.avgJobSatisfaction());
                        f.setVisible(true);

                    }
                });
        // set action when tenth query button is pressed
        qtables.getbutton10().addActionListener(
                new ActionListener() {
                    @Override
                    // This method will be called whenever you click the button.
                    public void actionPerformed(ActionEvent e) {
                        Q10 Query10 = new Q10(); // create a new window
                        Query10.createTable("employeesInDept", data.employeesInDept());
                        f.setVisible(true);

                    }
                });
        // set action when eleventh query button is pressed
        qtables.getbutton11().addActionListener(
                new ActionListener() {
                    @Override
                    // This method will be called whenever you click the button.
                    public void actionPerformed(ActionEvent e) {
                        Q11 Query11 = new Q11(); // create a new window
                        Query11.createTable("highestSalaryInDept", data.highestSalaryInDept());
                        f.setVisible(true);

                    }
                });

        // set action when twelefth query button is pressed
        qtables.getbutton12().addActionListener(
                new ActionListener() {
                    @Override
                    // This method will be called whenever you click the button.
                    public void actionPerformed(ActionEvent e) {
                        Q12 Query12 = new Q12(); // create a new window
                        String dept = Query12.getDept();
                        int MngrSatisfaction = Query12.getMngrSatisfaction();
                        Query12.createTable("EmployeeScore", data.employeeScore(dept, MngrSatisfaction));
                        f.setVisible(true);

                    }
                });

        // set action when more info button is pressed
        qtables.getbutton13().addActionListener(
                new ActionListener() {
                    @Override
                    // This method will be called whenever you click the button.
                    public void actionPerformed(ActionEvent e) {
                        info moreinfo = new info(); // create a new window
                        moreinfo.create();
                        f.setVisible(true);
                    }

                });

    }

}

// this class is used to create a new window for tables to be displayed
class NewWindow extends JFrame {
    NewWindow() {
    }

    // create table function takes the jtable, width and height of the frame
    // and name of frame and creates a new frame for the table
    public void createtable(JTable j, int width, int height, String name) {
        JFrame f = new JFrame(name);
        j.setBounds(30, 40, 200, 300);
        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);
        f.add(sp);
        f.setSize(width, height);
        f.setVisible(true);
    }

}

// class to create buttons for tables in the main frame
class panel {
    // creating the buttons
    JButton b1 = new JButton("employee");
    JButton b2 = new JButton("department");
    JButton b3 = new JButton("dailySalary");
    JButton b4 = new JButton("monthlySalary");
    JButton b5 = new JButton("transportation");
    JButton b6 = new JButton("university");
    JButton b7 = new JButton("experience");
    JButton b8 = new JButton("managerRating");
    JButton b9 = new JButton("performance");
    JButton b10 = new JButton("jobRating");

    // default constructor
    public panel() {

    }

    // add butions to frame
    public void create(JFrame frameObj) {

        frameObj.add(b1);
        frameObj.add(b2);
        frameObj.add(b3);
        frameObj.add(b4);
        frameObj.add(b5);
        frameObj.add(b6);
        frameObj.add(b7);
        frameObj.add(b8);
        frameObj.add(b9);
        frameObj.add(b10);
        b1.setBounds(30, 10, 160, 50);
        b2.setBounds(200, 10, 160, 50);
        b3.setBounds(370, 10, 160, 50);
        b4.setBounds(540, 10, 160, 50);
        b5.setBounds(30, 60, 160, 50);
        b6.setBounds(200, 60, 160, 50);
        b7.setBounds(370, 60, 160, 50);
        b8.setBounds(540, 60, 160, 50);
        b9.setBounds(30, 110, 160, 50);
        b10.setBounds(200, 110, 160, 50);

        frameObj.setLayout(null);
        frameObj.setVisible(true);
    }

    // getters
    public JButton getbutton1() {
        return b1;
    }

    public JButton getbutton2() {
        return b2;
    }

    public JButton getbutton3() {
        return b3;
    }

    public JButton getbutton4() {
        return b4;
    }

    public JButton getbutton5() {
        return b5;
    }

    public JButton getbutton6() {
        return b6;
    }

    public JButton getbutton7() {
        return b7;
    }

    public JButton getbutton8() {
        return b8;
    }

    public JButton getbutton9() {
        return b9;
    }

    public JButton getbutton10() {
        return b10;
    }

}

// class to create query buttons in the main frame
class queries {
    // creating the buttons
    JButton b1 = new JButton("EmployeeMakes");
    JButton b2 = new JButton("WorksAndAttends");
    JButton b3 = new JButton("DeptAvgSalary");
    JButton b4 = new JButton("AvgSalaryDeptUni");
    JButton b5 = new JButton("AttendUniNotSeniority");
    JButton b6 = new JButton("SameUniSameEducation");
    JButton b7 = new JButton("HighestManagerSatisfaction");
    JButton b8 = new JButton("CommonTransportation");
    JButton b9 = new JButton("AvgJobSatisfaction");
    JButton b10 = new JButton("EmployeesInDept");
    JButton b11 = new JButton("HighestSalaryInDept");
    JButton b12 = new JButton("EmployeeScore");
    JButton b13 = new JButton("More Info");

    // default constructor
    public queries() {

    }

    // add butions to frame
    public void create(JFrame frameObj) {

        frameObj.add(b1);
        frameObj.add(b2);
        frameObj.add(b3);
        frameObj.add(b4);
        frameObj.add(b5);
        frameObj.add(b6);
        frameObj.add(b7);
        frameObj.add(b8);
        frameObj.add(b9);
        frameObj.add(b10);
        frameObj.add(b11);
        frameObj.add(b12);
        frameObj.add(b13);
        frameObj.setLayout(null);
        b1.setBounds(30, 200, 200, 50);
        b2.setBounds(240, 200, 200, 50);
        b3.setBounds(450, 200, 200, 50);
        b4.setBounds(660, 200, 200, 50);
        b5.setBounds(30, 250, 200, 50);
        b6.setBounds(240, 250, 230, 50);
        b7.setBounds(480, 250, 250, 50);
        b10.setBounds(30, 300, 200, 50);
        b9.setBounds(240, 300, 230, 50);
        b8.setBounds(480, 300, 250, 50);
        b11.setBounds(30, 350, 200, 50);
        b12.setBounds(240, 350, 200, 50);
        b13.setBounds(250, 450, 130, 50);
        frameObj.setVisible(true);
    }

    // getters
    public JButton getbutton1() {
        return b1;
    }

    public JButton getbutton2() {
        return b2;
    }

    public JButton getbutton3() {
        return b3;
    }

    public JButton getbutton4() {
        return b4;
    }

    public JButton getbutton5() {
        return b5;
    }

    public JButton getbutton6() {
        return b6;
    }

    public JButton getbutton7() {
        return b7;
    }

    public JButton getbutton8() {
        return b8;
    }

    public JButton getbutton9() {
        return b9;
    }

    public JButton getbutton10() {
        return b10;
    }

    public JButton getbutton11() {
        return b11;
    }

    public JButton getbutton12() {
        return b12;
    }

    public JButton getbutton13() {
        return b13;
    }

}

// class to display query one table
class Q1 {

    // creating a new frame
    JFrame newframe = new JFrame("Query1");

    // default constructor
    public Q1() {

    }

    // method to get the salary from user
    public int getSalary() {

        int number = 0;

        try {
            number = Integer.parseInt(JOptionPane.showInputDialog("Enter a salary:"));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Input is not an integer press ok to try again.");
            number = getSalary();
        }

        return number;
    }

    // method to display the table
    public void createTable(String name, JTable j) {
        j.setBounds(30, 100, 200, 300);
        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);
        newframe.add(sp);
        newframe.setSize(300, 300);
        newframe.setVisible(true);
    }

}

// class to display query two table
class Q2 {
    // creating a new frame
    JFrame newframe = new JFrame("Query2");

    // default constructor
    public Q2() {

    }

    // method to get the department from user
    public String getDepartment() {
        return JOptionPane.showInputDialog("Enter a department name:");
    }

    // method to get the unievrsity from user
    public String getUniversity() {
        return JOptionPane.showInputDialog("Enter a university name:");
    }

    // method to display the table
    public void createTable(String name, JTable j) {

        j.setBounds(30, 100, 200, 300);
        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);
        newframe.add(sp);
        newframe.setSize(300, 300);
        newframe.setVisible(true);
    }

}

// class to display query three table
class Q3 {
    // creating a new frame
    JFrame newframe = new JFrame("Query3");

    // default constructor
    public Q3() {

    }

    // method to display the table
    public void createTable(String name, JTable j) {
        j.setBounds(30, 100, 200, 300);
        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);
        newframe.add(sp);
        newframe.setSize(300, 300);
        newframe.setVisible(true);
    }

}

// class to display query four table
class Q4 {
    // creating a new frame
    JFrame newframe = new JFrame("Query4");

    // default constructor
    public Q4() {

    }

    // method to display the table
    public void createTable(String name, JTable j) {

        j.setBounds(30, 100, 200, 300);
        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);
        newframe.add(sp);
        newframe.setSize(600, 600);
        newframe.setVisible(true);
    }
}

// class to display query five table
class Q5 {
    // creating a new frame
    JFrame newframe = new JFrame("Query5Test");

    // default constructor
    public Q5() {

    }

    // method to get the university from user
    public String getUniversity() {
        return JOptionPane.showInputDialog("Enter a University name:");
    }

    // method to get the seniority from user
    public String getSeniority() {
        return JOptionPane.showInputDialog("Enter a seniority:");
    }

    // method to display the table
    public void createTable(String name, JTable j) {

        j.setBounds(30, 100, 200, 300);
        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);
        newframe.add(sp);
        newframe.setSize(300, 300);
        newframe.setVisible(true);
    }

}

// class to display query six table
class Q6 {
    // creating a new frame
    JFrame newframe = new JFrame("Query6");

    // default constructor
    public Q6() {

    }

    // method to get the university from user
    public String getUniversity() {
        return JOptionPane.showInputDialog("Enter a University name:");
    }

    // method to get the education field from user
    public String getEdu() {
        return JOptionPane.showInputDialog("Enter an Education Field:");
    }

    // method to display the table
    public void createTable(String name, JTable j) {

        j.setBounds(30, 100, 200, 300);
        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);
        newframe.add(sp);
        newframe.setSize(300, 300);
        newframe.setVisible(true);
    }

}

// class to display query seven table
class Q7 {
    // creating a new frame
    JFrame newframe = new JFrame("Query7");

    // default constructor
    public Q7() {

    }

    // method to display the table
    public void createTable(String name, JTable j) {

        j.setBounds(30, 100, 200, 300);
        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);
        newframe.add(sp);
        newframe.setSize(300, 300);
        newframe.setVisible(true);
    }

}

// class to display query eight table
class Q8 {
    // creating a new frame
    JFrame newframe = new JFrame("Query8");

    // default constructor
    public Q8() {

    }

    // method to display the table
    public void createTable(String name, JTable j) {

        j.setBounds(30, 100, 200, 300);
        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);
        newframe.add(sp);
        newframe.setSize(300, 300);
        newframe.setVisible(true);
    }

}

// class to display query nine table
class Q9 {
    // creating a new frame
    JFrame newframe = new JFrame("Query9");

    // default constructor
    public Q9() {

    }

    // method to display the table
    public void createTable(String name, JTable j) {

        j.setBounds(30, 100, 200, 300);
        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);
        newframe.add(sp);
        newframe.setSize(300, 300);
        newframe.setVisible(true);
    }

}

// class to display query ten table
class Q10 {
    // creating a new frame
    JFrame newframe = new JFrame("Query10");

    // default constructor
    public Q10() {

    }

    // method to display the table
    public void createTable(String name, JTable j) {

        j.setBounds(30, 100, 200, 300);
        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);
        newframe.add(sp);
        newframe.setSize(300, 300);
        newframe.setVisible(true);
    }

}

// class to display query eleven table
class Q11 {
    // creating a new frame
    JFrame newframe = new JFrame("Query11");

    // default constructor
    public Q11() {

    }

    // method to display the table
    public void createTable(String name, JTable j) {

        j.setBounds(30, 100, 200, 300);
        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);
        newframe.add(sp);
        newframe.setSize(300, 300);
        newframe.setVisible(true);
    }

}

// class to display query twelve table
class Q12 {
    // creating a new frame
    JFrame newframe = new JFrame("Query12");

    // default constructor
    public Q12() {

    }

    // method to get the department from user
    public String getDept() {
        return JOptionPane.showInputDialog("Enter a Department name:");
    }

    // method to get the manager satisfaction rate from user
    public int getMngrSatisfaction() {

        int number = 0;

        try {
            number = Integer.parseInt(JOptionPane.showInputDialog("Enter a Manager Satisfaction score:"));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Input is not an integer press ok to try again.");
            number = getMngrSatisfaction();
        }

        return number;
    }

    // method to display the table
    public void createTable(String name, JTable j) {

        j.setBounds(30, 100, 200, 300);
        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);
        newframe.add(sp);
        newframe.setSize(300, 300);
        newframe.setVisible(true);
    }

}

// class to display a new frame with information on what each button displays
// explains the queries functionality
class info {
    // creating the labels
    JLabel l1 = new JLabel(
            "<html><font color='red'> EmployeeMakes shows </font>: employees who make over a specified amount.</html>");
    JLabel l2 = new JLabel(
            "<html><font color='red'>WorksAndAttends shows </font>: employees who work in the same department and attended the same university.</html>");
    JLabel l3 = new JLabel(
            "<html><font color='red'>DeptAvgSalary shows</font> : the average salary for each department.</html>");
    JLabel l4 = new JLabel(
            "<html><font color='red'>AvgSalaryDeptUni shows </font>: average monthly rate for employees who attended a university </html>");
    JLabel l13 = new JLabel("and work in the same department.");
    JLabel l5 = new JLabel(
            "<html><font color='red'> AttendUniNotSeniority shows </font>: employees who attended a univeristy and do not have a  </html>");
    JLabel l14 = new JLabel("specific seniority.");
    JLabel l6 = new JLabel(
            "<html><font color='red'> SameUniSameEducation shows </font>: employees who attended the same university and have the </html>");
    JLabel l15 = new JLabel("same education field.");
    JLabel l7 = new JLabel(
            "<html><font color='red'> HighestManagerSatisfaction shows </font>: the highest managerSatisfaction score for each</html> ");
    JLabel l16 = new JLabel("group of seniority.");
    JLabel l8 = new JLabel(
            "<html><font color='red'>CommonTransportation shows </font>: the most common transportation (vehicle) taken in a department. </html>");
    JLabel l9 = new JLabel(
            "<html><font color='red'>AvgJobSatisfaction shows </font>: the average jobSatisfaction score for each group of seniority.</html>");
    JLabel l10 = new JLabel(
            "<html><font color='red'>EmployeesInDept shows</font> : the number of employees in each department.</html>");
    JLabel l11 = new JLabel(
            "<html><font color='red'>HighestSalaryInDept shows </font> : the highest salary in each department.</html>");
    JLabel l12 = new JLabel(
            "<html><font color='red'>EmployeeScore shows</font> : the number of employees with a specific managerSatisfaction</html> ");
    JLabel l17 = new JLabel("Score from a specific departmentName.");

    // default constructor
    public info() {

    }

    // method to display the labels in a new frame
    public void create() {
        JFrame frame = new JFrame("Info");
        frame.add(l1);
        frame.add(l2);
        frame.add(l3);
        frame.add(l4);
        frame.add(l13);
        frame.add(l5);
        frame.add(l14);
        frame.add(l6);
        frame.add(l15);
        frame.add(l7);
        frame.add(l16);
        frame.add(l8);
        frame.add(l9);
        frame.add(l10);
        frame.add(l11);
        frame.add(l12);
        frame.add(l17);
        frame.setLayout(null);
        l1.setBounds(10, 0, 600, 50);
        l2.setBounds(10, 40, 600, 50);
        l3.setBounds(10, 70, 600, 50);
        l4.setBounds(10, 100, 600, 50);
        l13.setBounds(10, 120, 600, 50);
        l5.setBounds(10, 150, 600, 50);
        l14.setBounds(10, 170, 600, 50);
        l6.setBounds(10, 200, 600, 50);
        l15.setBounds(10, 220, 600, 50);
        l7.setBounds(10, 250, 600, 50);
        l16.setBounds(10, 270, 600, 50);
        l8.setBounds(10, 310, 600, 50);
        l9.setBounds(10, 350, 600, 50);
        l10.setBounds(10, 380, 600, 50);
        l11.setBounds(10, 410, 600, 50);
        l12.setBounds(10, 440, 600, 50);
        l17.setBounds(10, 460, 600, 50);

        frame.setVisible(true);
        frame.setSize(600, 600);
    }
}
