package ellere.cooksmart;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by swikriti on 9/20/2019.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {
    private Context mCtx;
    private List<RecipeModel> recipeList;
    public  RecipeAdapter(Context mCtx,List<RecipeModel>recipeList){
        this.mCtx=mCtx;
        this.recipeList=recipeList;
    }
    public class RecipeViewHolder extends RecyclerView.ViewHolder {
        TextView recipeName;
        ImageView recipeImg;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeName=itemView.findViewById(R.id.recipe_name);
            recipeImg=itemView.findViewById(R.id.recipes_thumbnail);
        }
    }
    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(mCtx);
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recipes,parent,false);
        return new RecipeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder recipeViewHolder, int position) {
        RecipeModel recipeModel=recipeList.get(position);
        Glide.with(mCtx)
                .load(recipeModel.getRecipeImage())
                .into(recipeViewHolder.recipeImg);
        recipeViewHolder.recipeName.setText(recipeModel.getRecipeName());

    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }


}
