import java.io.IOException;
import java.sql.DriverManager;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {

	public static void main(String[] args){		
		Scanner input = new Scanner(System.in);
		
		System.out.print("Username: ");   String username = input.next();
		System.out.print("Password: ");   String password = input.next();
		System.out.print("Age: "); 		  int age = input.nextInt();
		System.out.print("First Name: "); String firstName = input.next();
		System.out.print("Last Name: ");  String lastName = input.next();
		java.sql.Date birthDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		
		//INSERT
		insertUser(username, password, age, firstName, lastName, birthDate);
		System.out.println(birthDate);
		
		//SELECT
		System.out.print("Qual usuario deseja buscar?");
		username = input.next();
		selectUser(username);
		
		//UPDATE
		System.out.print("Qual usuario deseja editar?");
		username = input.next();
		updateUser(username);
		
	}
	
	public static Connection getConnection() {
		try {
			//Versão do mysql a usar
			String driver = "com.mysql.jdbc.Driver";
			//Endereço do servidor
			String url = "jdbc:mysql://localhost/foicebook";
			//Usuário do mysql
			String mysqlUser = "root";
			//Senha do mysql
			String mysqlPass = "root";
			Class.forName(driver);
			
			Connection con = DriverManager.getConnection(url, mysqlUser, mysqlPass);
			System.out.println("Conectado!");
			
			return con;	
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	public static void insertUser(String username, String password, int age, String firstName, String lastName, java.sql.Date birthDate) {
		try {
			Connection con = getConnection();
			PreparedStatement insert = con.prepareStatement("INSERT INTO users (username, password, age, firstname, lastname, birthday) "
					+ "VALUES ('"+username+"','"+password+"','"+age+"','"+firstName+"','"+lastName+"','"+birthDate+"')");
			insert.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static void selectUser(String username) {
		try {
			Connection con = getConnection();
			PreparedStatement select = con.prepareStatement("SELECT * FROM users WHERE username = '"+username+"'");
			
			ResultSet queryResult = select.executeQuery();
			
			while (queryResult.next()) {
				System.out.print(queryResult.getString("username") + " ");
				System.out.print(queryResult.getString("password") + " ");
				System.out.print(queryResult.getString("age") + " ");
				System.out.print(queryResult.getString("firstName") + " ");
				System.out.println(queryResult.getString("lastName"));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static void updateUser(String username) {
		try {
			Connection con = getConnection();
			PreparedStatement update = con.prepareStatement("UPDATE users SET username = ? WHERE username = ?");
			update.setString(1, "edited" + username);
			update.setString(2, username);
			
			update.executeUpdate();
			update.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
