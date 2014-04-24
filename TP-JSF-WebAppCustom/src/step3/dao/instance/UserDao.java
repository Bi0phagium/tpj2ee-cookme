package step3.dao.instance;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import step3.model.UserModelBean;


public class UserDao {

	private Connection connection;
	private String dB_HOST;
	private String dB_PORT;
	private String dB_NAME;
	private String dB_USER;
	private String dB_PWD;

	public UserDao(String DB_HOST,String DB_PORT, String DB_NAME,String DB_USER,String DB_PWD) {
		dB_HOST = DB_HOST;
		dB_PORT = DB_PORT;
		dB_NAME = DB_NAME;
		dB_USER = DB_USER;
		dB_PWD = DB_PWD;
	}

	public void addUser(UserModelBean user) {
		// Création de la requête
		java.sql.Statement query;
		try {
		// create connection
					connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
							+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
		
			// Creation de l'élément de requète
			query = connection.createStatement();

			// Executer puis parcourir les résultats
			String sql = "INSERT INTO `binome32`.`UserTestTP` (`surname`, `lastname`, `age`, `login`, `pwd`) VALUES ('"
					+ user.getSurname()
					+ "', '"
					+ user.getLastname()
					+ "', '"
					+ user.getAge()
					+ "', '"
					+ user.getLogin()
					+ "', '"
					+ user.getPwd() + "');";
			int rs = query.executeUpdate(sql);
			query.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
public ArrayList<UserModelBean> getAllUser(){
		
		//return value
		ArrayList<UserModelBean> userList=new ArrayList<UserModelBean>();
		
		try {
		// create connection
		connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
				+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
		
		// Création de la requête
		java.sql.Statement query;
		
			//Creation de l'élément de requète
			query = connection.createStatement();
			
			// Executer puis parcourir les résultats
			java.sql.ResultSet rs = query.executeQuery( "SELECT * FROM UserTestTP");
			while( rs.next() )
			{
				//Création de l'utilisateur
				UserModelBean user=new UserModelBean(rs.getString( "lastname" ), rs.getString("surname"), rs.getInt("age"), rs.getString("login"), rs.getString("pwd"));
				System.out.println("User : " + user );
				
				//ajout de l'utilisateur récupéré à la liste
				userList.add(user);
			}
			rs.close();
			query.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}

}
