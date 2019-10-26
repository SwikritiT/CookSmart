package ellere.cooksmart;

import static ellere.cooksmart.API_creator.BASE_URL;

/**
 * Created by swikriti on 9/20/2019.
 */

public class RecipeModel {
    private String recipeName;
//    private String recipeCategory;
//    private String  recipeIngredients;
//    private String recipeInstructions;

    private String recipeImage;
    RecipeModel(String recipeImage,String recipeName){
        this.recipeImage=recipeImage;
        this.recipeName=recipeName;
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
}
