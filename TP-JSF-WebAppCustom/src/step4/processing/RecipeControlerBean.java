package step4.processing;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import step4.dao.fabric.DaoFabric;
import step4.dao.instance.RecipesDao;
import step4.model.RecipeListModelBean;
import step4.model.RecipeModel;


@ManagedBean(name="RecipeControlerBean")
@ApplicationScoped

public class RecipeControlerBean {
	private RecipesDao recipeDao;
	private RecipeListModelBean recipeList;
	
	public RecipeControlerBean() {
		System.out.println("coucou");
		this.recipeDao=DaoFabric.getInstance().createRecipesDao();
		
		
        ExternalContext externalContext = FacesContext.getCurrentInstance()
                .getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();

        
        sessionMap.put("test", "plop");
        
        
	}
	
	public void loadAllRecipe(){
		ArrayList<RecipeModel> list = this.recipeDao.getAllRecipes();
		
		RecipeListModelBean recipeList=new RecipeListModelBean();
		
		for(RecipeModel recipe:list){
			recipeList.addRecipeList(recipe);
		}
		
		//récupère l'espace de mémoire de JSF
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		
		//place la liste de recette dans l'espace de mémoire de JSF
		
		sessionMap.put("recipeList", recipeList);
		this.recipeList = recipeList;
		
	}

	public List<RecipeModel> getAllRecipe(){
		
		return this.recipeList.getRecipeList();
		
	}

	public int getNumberRecipe(){
		//return 0;
		
		return this.recipeList.getNumRecipe();
		
	}

}
