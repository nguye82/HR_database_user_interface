import javax.swing.JOptionPane;
import java.io.*;


public class signIn {
    public static void getAuth(){
        
        // Prompt for user to input their username
        String username =  JOptionPane.showInputDialog("Please provide your username");

        // Check if username was entered
        while(username.length() <= 0){
            JOptionPane.showMessageDialog(null,"No username was entered");
            username = JOptionPane.showInputDialog("Please provide your username");
        }

        // Check if password was entered
        String password = JOptionPane.showInputDialog("Please provide your password");
        while(password.length() <= 0){
            JOptionPane.showMessageDialog(null,"No password was entered");
            password = JOptionPane.showInputDialog("Please provide your password");
        }

        // Let user know username and password were entered
        JOptionPane.showMessageDialog(null,"Username and Password are successfully entered");

        // Save provided username and password to auth.cfg
        try {
			PrintWriter outFile = new PrintWriter(new FileWriter("auth.cfg"));
			
			outFile.println("username=" + username);
			outFile.print("password=" + password);
			outFile.close();
        }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
