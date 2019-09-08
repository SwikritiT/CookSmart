package ellere.cooksmart;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ellere.cooksmart.API_creator.BASE_URL;
import static ellere.cooksmart.API_creator.count;


/**
 * Created by swikriti on 8/14/2019.
 */

public class Dinner extends AppCompatActivity implements CommonClickListener {
    String dinner_url = BASE_URL+"drinks.php";
    private RecyclerView recyclerView;
    private CommonAdapter dinnerAdapter;
    private List<CommonModel> dinnerModelList;
    private List<DrinksModel> inputDinner;
    private ImageButton sbutton;
    private EditText editText;
    private Button button;
    private CommonClickListener drinksClickListener;
    private LinearLayout linearLayout;
    private Toolbar dtoolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dinner);
        dtoolbar=(Toolbar)findViewById(R.id.dinner_toolbar);
        setSupportActionBar(dtoolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            dtoolbar.setElevation(10f);}
        initCollapsingToolbar();
        recyclerView=(RecyclerView) findViewById(R.id.dinner_recycler_view);
        editText=(EditText) findViewById(R.id.dinner_edittext);
        editText.setSelection(editText.getText().length());
        editText.setCursorVisible(false);
        editText.setFocusable(false);
        button=(Button) findViewById(R.id.common_button);
        dinnerModelList=new ArrayList<>();
        sbutton=(ImageButton) findViewById(R.id.searchDinner);
        inputDinner=new ArrayList<>();
        dinnerAdapter= new CommonAdapter(this,dinnerModelList);
        dinnerAdapter.setClickListener(this);
        RecyclerView.LayoutManager mlayoutManager=new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(mlayoutManager);
        recyclerView.addItemDecoration(new Dinner.GridSpacingItemDecoration(3, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(dinnerAdapter);
        prepareDinner();
        try {
            Glide.with(this).load(R.drawable.dinner).into((ImageView) findViewById(R.id.dinner_backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.dinner_collapsing_toolbar);
        collapsingToolbarLayout.setTitle("");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.dinner_appbar_layout);
        appBarLayout.setExpanded(true);
        //hiding and showing title when toolbar is extended or collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();

                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle(getString(R.string.dinner));
                    isShow = true;

                } else if (isShow) {
                    collapsingToolbarLayout.setTitle("");
                    isShow = false;
                }

            }
        });
    }
    private void prepareDinner(){
        CommonModel d = new CommonModel("chicken breast");
        dinnerModelList.add(d);
        d=new CommonModel("onion");
        dinnerModelList.add(d);
        d=new CommonModel("bell pepper");
        dinnerModelList.add(d);
        d=new CommonModel("green chili");
        dinnerModelList.add(d);
        d=new CommonModel("meat masala");
        dinnerModelList.add(d);
        d=new CommonModel("ginger garlic paste");
        dinnerModelList.add(d);
        d=new CommonModel("flour");
        dinnerModelList.add(d);
        d=new CommonModel("chicken drumstick");
        dinnerModelList.add(d);
        d=new CommonModel("egg");
        dinnerModelList.add(d);
        d=new CommonModel("gram flour");
        dinnerModelList.add(d);
        d=new CommonModel("chili powder");
        dinnerModelList.add(d);
        d=new CommonModel("soya sauce");
        dinnerModelList.add(d);
        d=new CommonModel("yoghurt");
        dinnerModelList.add(d);
        d=new CommonModel("lemon");
        dinnerModelList.add(d);
        d=new CommonModel("onion");
        dinnerModelList.add(d);
        d=new CommonModel("bell pepper");
        dinnerModelList.add(d);
        d=new CommonModel("chicken");
        dinnerModelList.add(d);
        d=new CommonModel("rice");
        dinnerModelList.add(d);
        d=new CommonModel("butter");
        dinnerModelList.add(d);
        d=new CommonModel("coriander powder");
        dinnerModelList.add(d);
        d=new CommonModel("goat meat");
        dinnerModelList.add(d);
        d=new CommonModel("turmeric");
        dinnerModelList.add(d);
        d=new CommonModel("bamboo stick");
        dinnerModelList.add(d);
        d=new CommonModel("paneer");
        dinnerModelList.add(d);
        d=new CommonModel("tomato");
        dinnerModelList.add(d);
        d=new CommonModel("vinegar");
        dinnerModelList.add(d);
        d=new CommonModel("corn flour");
        dinnerModelList.add(d);
        d=new CommonModel("daal");
        dinnerModelList.add(d);
        d=new CommonModel("ginger");
        dinnerModelList.add(d);
        d=new CommonModel("garlic");
        dinnerModelList.add(d);
        d=new CommonModel("ghee");
        dinnerModelList.add(d);
        d=new CommonModel("hing");
        dinnerModelList.add(d);
        d=new CommonModel("cumin seed");
        dinnerModelList.add(d);
        d=new CommonModel("gundruk");
        dinnerModelList.add(d);
        d=new CommonModel("soyabean");
        dinnerModelList.add(d);
        d=new CommonModel("noodle");
        dinnerModelList.add(d);
        d=new CommonModel("chicken broth");
        dinnerModelList.add(d);
        d=new CommonModel("potato");
        dinnerModelList.add(d);
        d=new CommonModel("sesame powder");
        dinnerModelList.add(d);
        d=new CommonModel("coconut");
        dinnerModelList.add(d);
        d=new CommonModel("coriander leaves");
        dinnerModelList.add(d);
        d=new CommonModel("coconut milk");
        dinnerModelList.add(d);
        d=new CommonModel("chicken thigh");
        dinnerModelList.add(d);
        d=new CommonModel("cinnamon stick");
        dinnerModelList.add(d);
        dinnerAdapter.notifyDataSetChanged();
    }

    @Override
    public void buttonClicked(View view, int position) {
        if (position==0){
            final CommonModel drinkModel1=dinnerModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel1.getName()+", ";
            String text= text1+text2;
            editText.setText(text);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }





        }
        if (position==1){
            final CommonModel drinkModel2=dinnerModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel2.getName()+", ";
            String text= text1+text2;
            editText.setText(text);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }



        }
        if (position==2){
            final CommonModel drinkModel3=dinnerModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel3.getName()+", ";
            String text= text1+text2;
            editText.setText(text);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }


        }
        if (position==3){
            final CommonModel drinkModel4=dinnerModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel4.getName()+", ";
            String text= text1+text2;
            editText.setText(text);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }



        }
        if (position==4){
            final CommonModel drinkModel5=dinnerModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel5.getName()+", ";
            String text= text1+text2;
            editText.setText(text);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }



        }
        if (position==5){
            final CommonModel drinkModel6=dinnerModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel6.getName()+", ";
            String text= text1+text2;
            editText.setText(text);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }



        }
        if (position==6){
            final CommonModel drinkModel7=dinnerModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel7.getName()+", ";
            String text= text1+text2;
            editText.setText(text);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }



        }
        if (position==7){
            final CommonModel drinkModel8=dinnerModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel8.getName()+", ";
            String text= text1+text2;
            editText.setText(text);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }


        }
        if (position==8){
            final CommonModel drinkModel9=dinnerModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel9.getName()+", ";
            String text= text1+text2;
            editText.setText(text);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }



        }
        if (position==9){
            final CommonModel drinkModel10=dinnerModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel10.getName()+", ";
            String text= text1+text2;
            editText.setText(text);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }



        }
        if (position==10){
            final CommonModel drinkModel11=dinnerModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel11.getName()+", ";
            String text= text1+text2;
            editText.setText(text);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }



        }
        if (position==11){
            final CommonModel drinkModel12=dinnerModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel12.getName()+", ";
            String text= text1+text2;
            editText.setText(text);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }


        }
        if (position==12){
            final CommonModel drinkModel13=dinnerModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel13.getName()+", ";
            String text= text1+text2;
            editText.setText(text);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }



        }
        if (position==13){
            final CommonModel drinkModel14=dinnerModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel14.getName()+", ";
            String text= text1+text2;
            editText.setText(text);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }



        }
        if (position==14){
            final CommonModel drinkModel15=dinnerModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel15.getName()+", ";
            String text= text1+text2;
            editText.setText(text);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }




        }
        if (position==15){
            final CommonModel drinkModel16=dinnerModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel16.getName()+", ";
            String text= text1+text2;
            editText.setText(text);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }



        }
        if (position==16){
            final CommonModel drinkModel17=dinnerModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel17.getName()+", ";
            String text= text1+text2;
            editText.setText(text);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }


        }
        if (position==17){
            final CommonModel drinkModel18=dinnerModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel18.getName()+", ";
            String text= text1+text2;
            editText.setText(text);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }



        }
        if (position==18){
            final CommonModel drinkModel19=dinnerModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel19.getName()+", ";
            String text= text1+text2;
            editText.setText(text);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }



        }
        if (position==19){
            final CommonModel drinkModel20=dinnerModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel20.getName()+", ";
            String text= text1+text2;
            editText.setText(text);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }



        }
        if (position==20){
            final CommonModel drinkModel21=dinnerModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel21.getName()+", ";
            String text= text1+text2;
            editText.setText(text);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }



        }
        if (position==21){
            final CommonModel drinkModel22=dinnerModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel22.getName()+", ";
            String text= text1+text2;
            editText.setText(text);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }




        }
        if (position==22) {
            final CommonModel drinkModel23 = dinnerModelList.get(position);
            String text1 = editText.getText().toString();
            String text2 = drinkModel23.getName() + ", ";
            String text = text1 + text2;
            editText.setText(text);
            if (count == 0) {
                text = text.replace(text2, "");
                editText.setText(text);


            }
        }
        if (position==23) {
            final CommonModel drinkModel24 = dinnerModelList.get(position);
            String text1 = editText.getText().toString();
            String text2 = drinkModel24.getName() + ", ";
            String text = text1 + text2;
            editText.setText(text);
            if (count == 0) {
                text = text.replace(text2, "");
                editText.setText(text);


            }

        }
        if (position==24) {
            final CommonModel drinkModel25 = dinnerModelList.get(position);
            String text1 = editText.getText().toString();
            String text2 = drinkModel25.getName() + ", ";
            String text = text1 + text2;
            editText.setText(text);
            if (count == 0) {
                text = text.replace(text2, "");
                editText.setText(text);


            }
        }
        if (position==25) {
            final CommonModel drinkModel26 = dinnerModelList.get(position);
            String text1 = editText.getText().toString();
            String text2 = drinkModel26.getName() + ", ";
            String text = text1 + text2;
            editText.setText(text);
            if (count == 0) {
                text = text.replace(text2, "");
                editText.setText(text);


            }
        }
        if (position==26) {
            final CommonModel drinkModel27 = dinnerModelList.get(position);
            String text1 = editText.getText().toString();
            String text2 = drinkModel27.getName() + ", ";
            String text = text1 + text2;
            editText.setText(text);
            if (count == 0) {
                text = text.replace(text2, "");
                editText.setText(text);


            }
        }
        if (position==27){
            final CommonModel drinkModel28=dinnerModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel28.getName()+", ";
            String text= text1+text2;
            editText.setText(text);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }





        }
        if (position==28){
            final CommonModel drinkModel29=dinnerModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel29.getName()+", ";
            String text= text1+text2;
            editText.setText(text);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }



        }
        if (position==29){
            final CommonModel drinkModel30=dinnerModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel30.getName()+", ";
            String text= text1+text2;
            editText.setText(text);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }


        }
        if (position==30){
            final CommonModel drinkModel31=dinnerModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel31.getName()+", ";
            String text= text1+text2;
            editText.setText(text);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }



        }
        if (position==31){
            final CommonModel drinkModel32=dinnerModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel32.getName()+", ";
            String text= text1+text2;
            editText.setText(text);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }



        }
        if (position==32){
            final CommonModel drinkModel33=dinnerModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel33.getName()+", ";
            String text= text1+text2;
            editText.setText(text);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }



        }
        if (position==33){
            final CommonModel drinkModel34=dinnerModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel34.getName()+", ";
            String text= text1+text2;
            editText.setText(text);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }



        }
        if (position==34){
            final CommonModel drinkModel35=dinnerModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel35.getName()+", ";
            String text= text1+text2;
            editText.setText(text);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }


        }
        if (position==36){
            final CommonModel drinkModel37=dinnerModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel37.getName()+", ";
            String text= text1+text2;
            editText.setText(text);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }



        }
        if (position==37){
            final CommonModel drinkModel38=dinnerModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel38.getName()+", ";
            String text= text1+text2;
            editText.setText(text);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }



        }
        if (position==38){
            final CommonModel drinkModel39=dinnerModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel39.getName()+", ";
            String text= text1+text2;
            editText.setText(text);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }



        }
        if (position==40){
            final CommonModel drinkModel41=dinnerModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel41.getName()+", ";
            String text= text1+text2;
            editText.setText(text);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }


        }
        if (position==41){
            final CommonModel drinkModel42=dinnerModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel42.getName()+", ";
            String text= text1+text2;
            editText.setText(text);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }



        }
        if (position==42){
            final CommonModel drinkModel43=dinnerModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel43.getName()+", ";
            String text= text1+text2;
            editText.setText(text);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }



        }
        if (position==43){
            final CommonModel drinkModel44=dinnerModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel44.getName()+", ";
            String text= text1+text2;
            editText.setText(text);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }




        }
        if (position==44){
            final CommonModel drinkModel145=dinnerModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel145.getName()+", ";
            String text= text1+text2;
            editText.setText(text);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }



        }
        if (position==45){
            final CommonModel drinkModel46=dinnerModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel46.getName()+", ";
            String text= text1+text2;
            editText.setText(text);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }


        }
        if (position==46){
            final CommonModel drinkModel47=dinnerModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel47.getName()+", ";
            String text= text1+text2;
            editText.setText(text);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }



        }
        if (position==47){
            final CommonModel drinkModel48=dinnerModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel48.getName()+", ";
            String text= text1+text2;
            editText.setText(text);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }



        }
        if (position==48){
            final CommonModel drinkModel49=dinnerModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel49.getName()+", ";
            String text= text1+text2;
            editText.setText(text);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }



        }
        if (position==49){
            final CommonModel drinkModel50=dinnerModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel50.getName()+", ";
            String text= text1+text2;
            editText.setText(text);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }



        }
        if (position==50){
            final CommonModel drinkModel51=dinnerModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel51.getName()+", ";
            String text= text1+text2;
            editText.setText(text);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }




        }
        if (position==51) {
            final CommonModel drinkModel52 = dinnerModelList.get(position);
            String text1 = editText.getText().toString();
            String text2 = drinkModel52.getName() + ", ";
            String text = text1 + text2;
            editText.setText(text);
            if (count == 0) {
                text = text.replace(text2, "");
                editText.setText(text);


            }
        }
        if (position==52) {
            final CommonModel drinkModel53 = dinnerModelList.get(position);
            String text1 = editText.getText().toString();
            String text2 = drinkModel53.getName() + ", ";
            String text = text1 + text2;
            editText.setText(text);
            if (count == 0) {
                text = text.replace(text2, "");
                editText.setText(text);


            }

        }
        if (position==53) {
            final CommonModel drinkModel54 = dinnerModelList.get(position);
            String text1 = editText.getText().toString();
            String text2 = drinkModel54.getName() + ", ";
            String text = text1 + text2;
            editText.setText(text);
            if (count == 0) {
                text = text.replace(text2, "");
                editText.setText(text);


            }
        }
        if (position==54) {
            final CommonModel drinkModel55= dinnerModelList.get(position);
            String text1 = editText.getText().toString();
            String text2 = drinkModel55.getName() + ", ";
            String text = text1 + text2;
            editText.setText(text);
            if (count == 0) {
                text = text.replace(text2, "");
                editText.setText(text);


            }
        }
        if (position==55) {
            final CommonModel drinkModel56 = dinnerModelList.get(position);
            String text1 = editText.getText().toString();
            String text2 = drinkModel56.getName() + ", ";
            String text = text1 + text2;
            editText.setText(text);
            if (count == 0) {
                text = text.replace(text2, "");
                editText.setText(text);


            }
        }
        String finalList = editText.getText().toString();
        DrinksModel d1 =new DrinksModel(finalList);
        inputDinner.add(d1);

        sbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gson gson = new Gson();
                final String newDataArray = gson.toJson(inputDinner);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, dinner_url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
//                                    JSONObject jsonObject = new JSONObject(response);
//                                    String success = jsonObject.getString("flag");
                                    final String result = response.toString();
                                    Log.d("response","result: " +result);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                                error.getMessage();

                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("ingredients_array", newDataArray); // array is a key which will be used in server side


                        return params;
                    }
                };
//                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//                requestQueue.add(stringRequest);
                Vconnection.getnInstance(getApplicationContext()).addRequestQue(stringRequest);
            }


        });



    }
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


}
