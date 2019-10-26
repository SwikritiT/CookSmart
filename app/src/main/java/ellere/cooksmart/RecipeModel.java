package ellere.cooksmart;

import static ellere.cooksmart.API_creator.BASE_URL;

/**
 * Created by swikriti on 9/20/2019.
 */

public class RecipeModel {
    private String recipeName;
    private String recipeCategory;
   private String  recipeIngredients;
    private String recipeInstructions;

    private String recipeImage;
    RecipeModel(String recipeImage,String recipeName,String recipeCategory,String recipeIngredients,String recipeInstructions){
        this.recipeImage=recipeImage;
        this.recipeName=recipeName;
        this.recipeCategory=recipeCategory;
        this.recipeIngredients=recipeIngredients;
        this.recipeInstructions=recipeInstructions;
    }
    public  String getRecipeImage(){
        return BASE_URL+recipeImage;
    }
//    public void setRecipeImage(String recipeImage){
//        this.recipeImage=recipeImage;
//    }
    public  String getRecipeName(){
        return recipeName;
    }
//    public void setRecipeName(String recipeName){
//        this.recipeName=recipeName;
//    }
public  String getRecipeCategory(){
    return recipeCategory;
}
    public  String getRecipeIngredients(){
        return recipeIngredients;
    }
    public  String getRecipeInstructions(){
        return recipeInstructions;
    }

}
