package ellere.cooksmart;

/**
 * Created by yamuna on 10/26/2019.
 */

public class IngredientsModel {
    private String name;
    public IngredientsModel(){}
    public IngredientsModel(String name){
        this.name=name;}
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }

}
