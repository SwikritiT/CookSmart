package ellere.cooksmart;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
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
    private LayoutInflater layoutInflater;
    private Animation animationUp, animationDown;
    private final int COUNTDOWN_RUNNING_TIME = 500;
    public  RecipeAdapter(Context mCtx,List<RecipeModel>recipeList,Animation animationUp, Animation animationDown){
        this.mCtx=mCtx;
        this.recipeList=recipeList;
        this.animationDown = animationDown;
        this.animationUp = animationUp;
    }
    public class RecipeViewHolder extends RecyclerView.ViewHolder {
        TextView recipeName;
        TextView recipeCategory;
        TextView recipeInstructions;
        TextView recipeIngredients;
        ImageView recipeImg;
        TextView ingredients;
        TextView instructions;


        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeName=itemView.findViewById(R.id.recipe_name);
            recipeImg=itemView.findViewById(R.id.recipes_thumbnail);

            recipeIngredients=itemView.findViewById(R.id.recipe_ingredients);
            recipeInstructions=itemView.findViewById(R.id.recipe_instructions);
            ingredients=itemView.findViewById(R.id.ingredients);
            instructions=itemView.findViewById(R.id.instructions);

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
    public void onBindViewHolder(@NonNull final RecipeViewHolder recipeViewHolder, int position) {
        RecipeModel recipeModel=recipeList.get(position);
        Glide.with(mCtx)
                .load(recipeModel.getRecipeImage())
                .into(recipeViewHolder.recipeImg);
        recipeViewHolder.recipeName.setText(recipeModel.getRecipeName());
        recipeViewHolder.recipeIngredients.setText(recipeModel.getRecipeIngredients());
        recipeViewHolder.recipeInstructions.setText(recipeModel.getRecipeInstructions());
        //setting visibility
        recipeViewHolder.ingredients.setVisibility(View.GONE);
        recipeViewHolder.recipeIngredients.setVisibility(View.GONE);
        recipeViewHolder.instructions.setVisibility(View.GONE);
        recipeViewHolder.recipeInstructions.setVisibility(View.GONE);
        recipeViewHolder.recipeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (recipeViewHolder.recipeIngredients.isShown() && recipeViewHolder.recipeInstructions.isShown()) {
                    recipeViewHolder.ingredients.startAnimation(animationUp);
                    recipeViewHolder.recipeIngredients.startAnimation(animationUp);
                    recipeViewHolder.instructions.startAnimation(animationUp);
                    recipeViewHolder.recipeInstructions.startAnimation(animationUp);


                    CountDownTimer countDownTimerStatic = new CountDownTimer(COUNTDOWN_RUNNING_TIME, 16) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                        }

                        @Override
                        public void onFinish() {

                            recipeViewHolder.ingredients.setVisibility(View.GONE);
                            recipeViewHolder.recipeIngredients.setVisibility(View.GONE);
                            recipeViewHolder.instructions.setVisibility(View.GONE);
                            recipeViewHolder.recipeInstructions.setVisibility(View.GONE);
                        }
                    };
                    countDownTimerStatic.start();

                    //recipeViewHolder.showMore.setText(context.getString(R.string.show));
                    //holder.showMore.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_down, 0);
                } else {
                    recipeViewHolder.ingredients.setVisibility(View.VISIBLE);
                    recipeViewHolder.recipeIngredients.setVisibility(View.VISIBLE);
                    recipeViewHolder.instructions.setVisibility(View.VISIBLE);
                    recipeViewHolder.recipeInstructions.setVisibility(View.VISIBLE);
                    //animation down

                    recipeViewHolder.ingredients.startAnimation(animationDown);
                    recipeViewHolder.recipeIngredients.startAnimation(animationDown);
                    recipeViewHolder.instructions.startAnimation(animationDown);
                    recipeViewHolder.recipeInstructions.startAnimation(animationDown);

                    //recipeViewHolder.showMore.setText(context.getString(R.string.hide));
                    //holder.showMore.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_up, 0);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }


}
