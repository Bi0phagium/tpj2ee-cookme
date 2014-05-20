package step4.dao.instance;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import step4.model.RecipeModel;

public class RecipesDao {

	private Connection connection;
	private String dB_HOST;
	private String dB_PORT;
	private String dB_NAME;
	private String dB_USER;
	private String dB_PWD;

	public RecipesDao(String DB_HOST,String DB_PORT, String DB_NAME,String DB_USER,String DB_PWD) {
		dB_HOST = DB_HOST;
		dB_PORT = DB_PORT;
		dB_NAME = DB_NAME;
		dB_USER = DB_USER;
		dB_PWD = DB_PWD;
	}

	public void addRecipe(RecipeModel recipe) {
		// Cr�ation de la requ�te
		java.sql.Statement query;
		
		// create connection
		try {
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
					+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
			// Creation de l'�l�ment de requ�te
			query = connection.createStatement();

			// Executer puis parcourir les r�sultats
			String sql = "INSERT INTO `binome32`.`RecipeTestTP` (`title`, `description`, `expertise`, `duration`, `nbpeople`,`type`) VALUES ('"
					+ recipe.getTitle()
					+ "', '"
					+ recipe.getDescription()
					+ "', '"
					+ recipe.getExpertise()
					+ "', '"
					+ recipe.getDuration()
					+ "', '"
					+ recipe.getNbpeople()
					+ "', '" + recipe.getType() + "');";
			int rs = query.executeUpdate(sql);
			query.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<RecipeModel> getAllRecipes() {
		ArrayList<RecipeModel> recipeList = new ArrayList<RecipeModel>();

		// Cr�ation de la requ�te
		java.sql.Statement query;

		try {
		
		// create connection
		connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
				+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);

			// Creation de l'�l�ment de requ�te
			query = connection.createStatement();

			// Executer puis parcourir les r�sultats
			java.sql.ResultSet rs = query
					.executeQuery("SELECT * FROM recipe;");
			while (rs.next()) {
				// Cr�ation de  la recette
				RecipeModel recipe = new RecipeModel(
						rs.getString("title"), rs.getString("description"),
						rs.getInt("expertise"), rs.getInt("duration"),
						rs.getInt("nbpeople"), rs.getString("type"));
				System.out.println("Recipe : " + recipe);

				// ajout de la recette r�cup�r�e � la liste
				recipeList.add(recipe);
			}
			rs.close();
			query.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return recipeList;
	}
	
	public RecipeModel getRecipeById(int id) {
		// Cr�ation de la requ�te
		java.sql.Statement query;

		try {
		
		// create connection
		connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
				+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);

			// Creation de l'�l�ment de requ�te
			query = connection.createStatement();

			// Executer puis parcourir les r�sultats
			//attention avec les injections
			java.sql.ResultSet rs = query
					.executeQuery("SELECT * FROM recipe where id="+id+";");
			while (rs.next()) {
				// Cr�ation de  la recette
				RecipeModel recipe = new RecipeModel(
						rs.getString("title"), rs.getString("description"),
						rs.getInt("expertise"), rs.getInt("duration"),
						rs.getInt("nbpeople"), rs.getString("type"));
				System.out.println("Recipe : " + recipe);

				rs.close();
				query.close();
				connection.close();
			
				return recipe;
			}
			rs.close();
			query.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/*
	
	public ArrayList<RecipeModel> searchRecipes() {
		ArrayList<RecipeModel> recipeList = new ArrayList<RecipeModel>();

		// Cr�ation de la requ�te
		java.sql.Statement query;

		try {
		
		// create connection
		connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
				+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);

			// Creation de l'�l�ment de requ�te
			query = connection.createStatement();

			// Executer puis parcourir les r�sultats
			//n�cessite une v�rification pour empecher les injections SQL
			String requete = "SELECT * FROM recipe where 1";
			if(recipe.getTitre()!=null){
				requete = requete.concat(" and titre=\""+recipe.getTitre()+"\"");
			}
			if(recipe.getExpertise()!=null){
				requete = requete.concat(" and expertise=\""+recipe.getExpertise()+"\"");
			}
			if(recipe.getNbpeople()!=null){
				requete = requete.concat(" and nbpeople <= "+recipe.getNbpeople());
			}
			if(recipe.getDuration()!=null){
				requete = requete.concat(" and duration <= "+recipe.getDuration());
			}
			if(recipe.getType()!=null){
				requete = requete.concat(" and type=\""+recipe.getType()+"\"");
			}
			requete = requete.concat(";");
			
			
			
			java.sql.ResultSet rs = query
					.executeQuery(requete);
					
			while (rs.next()) {
				// Cr�ation de  la recette
				RecipeModel recipe = new RecipeModel(
						rs.getString("title"), rs.getString("description"),
						rs.getInt("expertise"), rs.getInt("duration"),
						rs.getInt("nbpeople"), rs.getString("type"));
				System.out.println("Recipe : " + recipe);

				// ajout de la recette r�cup�r�e � la liste
				recipeList.add(recipe);
			}
			rs.close();
			query.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return recipeList;
	}
	
	
	public ArrayList<RecipeModel> updateRecipes(int id, RecipeModel recipe) {	

		// Cr�ation de la requ�te
		java.sql.Statement query;

		try {
		
		// create connection
		connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
				+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);

			// Creation de l'�l�ment de requ�te
			query = connection.createStatement();

			// Executer puis parcourir les r�sultats
			//n�cessite une v�rification pour empecher les injections SQL
			String requete = "SELECT recipe ";
			if(recipe.getTitre()!=null){
				requete = requete.concat(" set titre=\""+recipe.getTitre()+"\"");
			}
			if(recipe.getExpertise()!=null){
				requete = requete.concat(" set expertise=\""+recipe.getExpertise()+"\"");
			}
			if(recipe.getNbpeople()!=null){
				requete = requete.concat(" set nbpeople <= "+recipe.getNbpeople());
			}
			if(recipe.getDuration()!=null){
				requete = requete.concat(" set duration <= "+recipe.getDuration());
			}
			if(recipe.getType()!=null){
				requete = requete.concat(" set type=\""+recipe.getType()+"\"");
			}
			
			requete = requete.concat(" where id="+id+";");
			
			
			
			java.sql.ResultSet rs = query
					.executeQuery(requete);
				
			rs.close();
			query.close();
			connection.close();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
	*/

}
