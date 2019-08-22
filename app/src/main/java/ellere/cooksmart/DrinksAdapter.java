package ellere.cooksmart;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.List;

/**
 * Created by swikriti on 8/22/2019.
 */

public class DrinksAdapter extends RecyclerView.Adapter<DrinksAdapter.DrinksViewHolder> {
    private Context mContext;
    private List<DrinksModel> drinksList;
    private DrinksClickListener drinksClickListener;
    private LinearLayout linearLayout;
    public class DrinksViewHolder extends RecyclerView.ViewHolder {
        private Button drinksbutton;
        public DrinksViewHolder(@NonNull View view) {
            super(view);
            drinksbutton=(Button) view.findViewById(R.id.drinks_button);
            linearLayout=(LinearLayout) view.findViewById(R.id.drinks_linearlayout);
            drinksbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(itemView.getContext(), "Position:" + Integer.toString(getPosition()), Toast.LENGTH_SHORT).show();
                    if(drinksClickListener !=null){
                        drinksClickListener.buttonClicked(v,getAdapterPosition());
                        //clickListener.itemClicked(getAdapterPosition(),thumbnail, categoryName);
                    }
                }
            });
        }
    }
    public void setClickListener(DrinksClickListener drinksclickListener){
        this.drinksClickListener = drinksclickListener;
    }
    public DrinksAdapter(Context mContext,List<DrinksModel> drinksList){

        this.mContext=mContext;
        this.drinksList=drinksList;

    }


    @NonNull
    @Override
    public DrinksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.drinks_buttons,parent,false);
        return new DrinksAdapter.DrinksViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinksViewHolder drinksViewHolder, int position) {
        final DrinksModel drinksModel=drinksList.get(position);
        drinksViewHolder.drinksbutton.setText(drinksModel.getName());

    }

    @Override
    public int getItemCount() {
        return drinksList.size();
    }



}
