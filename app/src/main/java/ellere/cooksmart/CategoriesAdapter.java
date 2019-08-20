package ellere.cooksmart;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import ellere.cooksmart.R;

/**
 * Created by swikriti on 8/14/2019.
 */

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.MyViewHolder> {
    private Context mContext;
    private List<CategoriesModel> categoriesList;
    private  ClickListener clickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder  {
        public TextView categoryName;
        public ImageView thumbnail;
        public RelativeLayout relativeLayout;
        public MyViewHolder(View view) {
            super(view);
            categoryName=(TextView) view.findViewById(R.id.category_name);
            thumbnail=(ImageView) view.findViewById(R.id.categories_thumbnail);
            relativeLayout=(RelativeLayout)view.findViewById(R.id.relative_cardview);

            categoryName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(itemView.getContext(), "Position:" + Integer.toString(getPosition()), Toast.LENGTH_SHORT).show();
                    if(clickListener !=null){
                        clickListener.itemClicked(v,getAdapterPosition());
                        //clickListener.itemClicked(getAdapterPosition(),thumbnail, categoryName);
                    }
                }
            });
            thumbnail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(itemView.getContext(), "Position:" + Integer.toString(getPosition()), Toast.LENGTH_SHORT).show();
                    if(clickListener !=null){
                        clickListener.thumbnailClicked(v,getAdapterPosition());
                        //clickListener.itemClicked(getAdapterPosition(),thumbnail, categoryName);
                    }
                }
            });
       }
    }




    public CategoriesAdapter(Context mContext,List<CategoriesModel> categoriesList,ClickListener clickListener){

        this.mContext=mContext;
        this.categoriesList=categoriesList;
        this.clickListener=clickListener;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_cardlayout,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final CategoriesModel categoriesModel=categoriesList.get(position);
        holder.categoryName.setText(categoriesModel.getName());
        /* loading album cover using glide library */
        Glide.with(mContext).load(categoriesModel.getThumbnail()).into(holder.thumbnail);

       ViewCompat.setTransitionName(holder.thumbnail, categoriesModel.getName());

      ViewCompat.setTransitionName(holder.categoryName, categoriesModel.getName());
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //clickListener.itemClicked(holder.getAdapterPosition(), categoriesModel, holder.thumbnail, holder.categoryName);
//                clickListener.itemClicked(holder.getAdapterPosition(), holder.thumbnail, holder.categoryName);
//            }
//        });


    }
        public void setClickListener(ClickListener clickListener){
            this.clickListener = clickListener;
        }


        @Override
    public int getItemCount() {
        return categoriesList.size();
    }


}
