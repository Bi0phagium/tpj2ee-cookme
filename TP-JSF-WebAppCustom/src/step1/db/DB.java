package step1.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import step1.model.UserModel;

public class DB {
	private static final String DB_HOST="db-tp.cpe.fr";
	private static final String DB_PORT="3306";
	private static final String DB_NAME="binome32";
	private static final String DB_USER="binome32";
	private static final String DB_PWD="binome32";
	private Connection connection;
	
	public DB() {
		try {
			// Chargement du Driver, puis établissement de la connexion
			Class.forName("com.mysql.jdbc.Driver");
		
			//create connection
			connection = 
				java.sql.DriverManager.getConnection("jdbc:mysql://"+DB_HOST+":"+DB_PORT+"/"+DB_NAME , DB_USER, DB_PWD);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<UserModel> getData(){
		
		//return value
		ArrayList<UserModel> userList=new ArrayList<UserModel>();
		
		// Création de la requête
		java.sql.Statement query;
		try {
			//Creation de l'élément de requète
			query = connection.createStatement();
			
			// Executer puis parcourir les résultats
			java.sql.ResultSet rs = query.executeQuery( "SELECT * FROM UserTestTP");
			while( rs.next() )
			{
				//Création de l'utilisateur
				UserModel user=new UserModel(rs.getString( "lastname" ), rs.getString("surname"), rs.getInt("age"), rs.getString("login"), rs.getString("pwd"));
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

	public void addUser(UserModel user) {
		
		// Création de la requête
		java.sql.Statement query;
		try {
			//Creation de l'élément de requète
			query = connection.createStatement();
			
			// Executer puis parcourir les résultats
			String sql="INSERT INTO `binome32`.`UserTestTP` (`surname`, `lastname`, `age`, `login`, `pwd`) VALUES ('"+user.getSurname()+"', '"+user.getLastname()+"', '"+user.getAge()+"', '"+user.getLogin()+"', '"+user.getPwd()+"');";
			int rs = query.executeUpdate(sql);
			
			query.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
