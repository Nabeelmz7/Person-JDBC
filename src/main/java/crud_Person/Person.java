package crud_Person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Person {
	static String url="jdbc:postgresql://localhost:5432/simple_jdbc";
	static String user="postgres";
	static String password="root";
	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		boolean flag = true;
		while (flag) {
		System.out.println("Enter the choice");
		System.out.println("1.Save the person data\n 2.Update the person data\n 3.Delete the person data\n 4.Fetch all");
		int choice=scanner.nextInt();
		switch(choice) {
		case 1: 
			try {
				Class.forName("org.postgresql.Driver");
				Connection connection=DriverManager.getConnection(url, user, password);
				PreparedStatement preparedStatement=connection.prepareStatement("insert into person values(?,?,?)");
				System.out.println("Enter the id");
				int id=scanner.nextInt();
				preparedStatement.setInt(1, id);
				System.out.println("Enter the name");
				String name=scanner.next();
				preparedStatement.setString(2, name);
				System.out.println("Enter the age");
				int age=scanner.nextInt();
				preparedStatement.setInt(3, age);
				
				preparedStatement.execute();
				connection.close();
				System.out.println("Data saved successfully..!!");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case 2:
			try {
				Class.forName("org.postgresql.Driver");
				Connection connection = DriverManager.getConnection(url, user, password);
				PreparedStatement preparedStatement = connection.prepareStatement("update person set name=? where person_id=?");
				System.out.println("Enter the id of a person which has to be updated");
				int id = scanner.nextInt();
				System.out.println("Enter the new name that has to updated");
				String name = scanner.next();
				preparedStatement.setString(1, name);
				preparedStatement.setInt(2, id);

				preparedStatement.execute();
				connection.close();

				System.out.println("succesfully updated");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
			
		case 3:
			try {
				Class.forName("org.postgresql.Driver");
				Connection connection = DriverManager.getConnection(url, user, password);
				PreparedStatement preparedStatement = connection.prepareStatement("delete from person where person_id=?");
				System.out.println("Please enter the id of the person to be deleted");
				int id = scanner.nextInt();
				preparedStatement.setInt(1, id);
				preparedStatement.execute();
				connection.close();
				System.out.println("successfully deleted");

			} catch (ClassNotFoundException e) {

				e.printStackTrace();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			break;
			
		case 4:
			try {
				Class.forName("org.postgresql.Driver");
				Connection connection=DriverManager.getConnection(url, user, password);
				String sql="select * from person";
				Statement statement=connection.createStatement();
				ResultSet resultSet=statement.executeQuery(sql);
				while(resultSet.next()) {
					int id=resultSet.getInt(1);
					String name=resultSet.getString(2);
					int age=resultSet.getInt(3);
					
					System.out.println(id);
					System.out.println(name);
					System.out.println(age);
					System.out.println("========================");
				}
				connection.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		case 5: 
			flag=false;
		}	
		}
	}
}
