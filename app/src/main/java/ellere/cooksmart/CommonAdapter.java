package ellere.cooksmart;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.content.res.Resources;
import java.util.List;
import static ellere.cooksmart.API_creator.count;

/**
 * Created by swikriti on 8/22/2019.
 */

public class CommonAdapter extends RecyclerView.Adapter<CommonAdapter.DrinksViewHolder> {
    private Context mContext;


    private List<CommonModel> drinksList;
    private CommonClickListener drinksClickListener;
    private LinearLayout linearLayout;
    public class DrinksViewHolder extends RecyclerView.ViewHolder {
        private Button drinksbutton;
        public DrinksViewHolder(@NonNull View view) {
            super(view);

            drinksbutton=(Button) view.findViewById(R.id.common_button);

            linearLayout=(LinearLayout) view.findViewById(R.id.drinks_linearlayout);
            drinksbutton.setOnClickListener(new View.OnClickListener() {
//                Drawable dr = getResources().getDrawable(R.drawable.button_pressed);
//    dr.setColorFilter(Color.parseColor("#FF0000"), PorterDuff.Mode.SRC_ATOP);


                boolean isClicked=true;


                @Override
                public void onClick(View v) {


                    if(isClicked) {

                        v.setBackgroundResource((R.drawable.button_enabled));
                        //v.setBackgroundColor(R.drawable.button_pressed);
                        v.setSelected(true);


                        //v.setBackgroundColor(Color.parseColor("#D3D3D3"));
                     isClicked=false;
                     count=1;
                    } else {
                        v.setBackgroundResource((R.drawable.button_default));
                        //v.setBackgroundColor(R.drawable.button_default);
                        v.setSelected(false);
                        isClicked=true;
                        count=0;



                    }
                    //Toast.makeText(itemView.getContext(), "Position:" + Integer.toString(getPosition()), Toast.LENGTH_SHORT).show();
                    if(drinksClickListener !=null){

                        drinksClickListener.buttonClicked(v,getAdapterPosition());

                        //clickListener.itemClicked(getAdapterPosition(),thumbnail, categoryName);
                    }

                }

            });
        }
    }
    public void setClickListener(CommonClickListener drinksclickListener){
        this.drinksClickListener = drinksclickListener;
    }
    public CommonAdapter(Context mContext, List<CommonModel> drinksList){

        this.mContext=mContext;
        this.drinksList=drinksList;

    }


    @NonNull
    @Override
    public DrinksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.drinks_buttons,parent,false);
        return new CommonAdapter.DrinksViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinksViewHolder drinksViewHolder, int position) {
        final CommonModel drinksModel=drinksList.get(position);
        drinksViewHolder.drinksbutton.setText(drinksModel.getName());

    }

    @Override
    public int getItemCount() {
        return drinksList.size();
    }



}
