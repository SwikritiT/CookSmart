package ellere.cooksmart;

import java.io.Serializable;

/**
 * Created by swikriti on 8/22/2019.
 */

public class CommonModel implements Serializable {
    private String name;
    public CommonModel(){}
    public CommonModel(String name){
        this.name=name;}
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }

}
