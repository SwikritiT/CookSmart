package ellere.cooksmart;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;


/**
 * Created by swikriti on 8/14/2019.
 */

public class CategoriesModel implements Serializable {
    private String name;
    private int thumbnail;
    public CategoriesModel(){}
    public CategoriesModel(String name, int thumbnail){
        this.name=name;
        this.thumbnail=thumbnail;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public int getThumbnail(){
        return thumbnail;
    }
    public void setThumbnail(int thumbnail){
        this.thumbnail=thumbnail;
    }


}

