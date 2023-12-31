import java.util.*;
import java.sql.*;
public class Login {
	private static final String db_url="jdbc:mysql://localhost:3306/event";
	private static final String db_username="root";
	private static final String db_password="12345";
	public static void main(String[] args)
	{
		Scanner ob=new Scanner(System.in);
		System.out.println("WELCOME TO EVENT MANAGEMENT SYSTEM!!!");
		System.out.println("Select your position:");
			System.out.println("1.Manager");
			System.out.println("2.Customer");
			System.out.println("Enter your choice");
			int choose=ob.nextInt();
			ob.nextLine();
			switch(choose)
			{
			     case 1:
			    	 managerLogin(ob);
			    	 break;
			     case 2:
			    	 employeeLogin(ob);
			    	
			    	 break;
			    default:
			    	System.out.println("Give another choice!!It's invalid");
			}
		}
	
	private static void employeeLogin(Scanner ob) {
		Customer customer=new Customer();
		System.out.println("Customer Login");
		System.out.println("Your id:");
		int id=ob.nextInt();
		ob.nextLine();
		System.out.println("Your password:");
		String password=ob.nextLine();
		if(EmployeecheckCredentials(id,password))
		{
			System.out.println("Logged in!.Welcome");
			customer.eventdetails();
			
			
		}
		else
		{
			System.out.println("Invalid username or password");
		}
		
	}
	private static void managerLogin(Scanner ob) {
		Manager manager=new Manager();
		System.out.println("Manager Login");
		System.out.println("Your id:");
		int id=ob.nextInt();
		ob.nextLine();
		System.out.println("Your password:");
		String password=ob.nextLine();
		if(ManagercheckCredentials(id,password))
		{
			System.out.println("Logged in!.Welcome");
			manager.managerDetails();
		}
		else
		{
			System.out.println("Invalid username or password");
		}
		
	}
	private static boolean EmployeecheckCredentials(int id, String password) {
	    try(Connection connection=DriverManager.getConnection(db_url,db_username,db_password))
	    		{
	    			String query="SELECT * FROM customerlogin where id=? and password=? ";
	    			PreparedStatement preparedStatement=connection.prepareStatement(query);
	    			preparedStatement.setInt(1, id);
	    			preparedStatement.setString(2, password);
	    			
	    			ResultSet resultSet=preparedStatement.executeQuery();
	    			return resultSet.next();
	    		}
	    catch(SQLException e)
	    {
	    	e.printStackTrace();
	    }
		return false;
	}
	private static boolean ManagercheckCredentials(int id, String password) {
	    try(Connection connection=DriverManager.getConnection(db_url,db_username,db_password))
	    		{
	    			String query="SELECT * FROM managerlogin where id=? and password=?";
	    			PreparedStatement preparedStatement=connection.prepareStatement(query);
	    			preparedStatement.setInt(1, id);
	    			preparedStatement.setString(2, password);
	    			
	    			ResultSet resultSet=preparedStatement.executeQuery();
	    			return resultSet.next();
	    		}
	    catch(SQLException e)
	    {
	    	e.printStackTrace();
	    }
		return false;
	}
	

}