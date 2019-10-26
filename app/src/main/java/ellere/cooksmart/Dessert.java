package ellere.cooksmart;

import android.content.Intent;
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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

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

public class Dessert extends AppCompatActivity implements CommonClickListener{
    String dessert_url = BASE_URL+"dessert.php";
    private RecyclerView recyclerView;
    private CommonAdapter dessertAdapter;
    private List<CommonModel> dessertModelList;
    private List<BreakfastModel> inputDessert;
    private ImageButton sbutton;
    private EditText editText;
    private Toolbar dtoolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dessert);
        dtoolbar=(Toolbar) findViewById(R.id.dessert_toolbar);
        setSupportActionBar(dtoolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            dtoolbar.setElevation(10f);}
        initCollapsingToolbar();
        recyclerView=(RecyclerView) findViewById(R.id.dessert_recycler_view);
        editText=(EditText) findViewById(R.id.dessert_edittext);
        editText.setSelection(editText.getText().length());
        editText.setCursorVisible(false);
        editText.setFocusable(false);
        //button=(Button) findViewById(R.id.common_button);
        editText.setCursorVisible(false);
        editText.setFocusable(false);
        dessertModelList=new ArrayList<>();
        sbutton=(ImageButton) findViewById(R.id.searchDessert);
        inputDessert=new ArrayList<>();
        dessertAdapter= new CommonAdapter(this,dessertModelList);
        dessertAdapter.setClickListener(this);
        RecyclerView.LayoutManager mlayoutManager=new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(mlayoutManager);
        recyclerView.addItemDecoration(new Dessert.GridSpacingItemDecoration(3, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(dessertAdapter);
        prepareCurry();
        try {
            Glide.with(this).load(R.drawable.dessert).into((ImageView) findViewById(R.id.dessert_backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }}
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.dessert_collapsing_toolbar);
        collapsingToolbarLayout.setTitle("");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.dessert_appbar_layout);
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
                    collapsingToolbarLayout.setTitle(getString(R.string.dessert));
                    isShow = true;

                } else if (isShow) {
                    collapsingToolbarLayout.setTitle("");
                    isShow = false;
                }

            }
        });
    }
    private void prepareCurry(){
        CommonModel d = new CommonModel("carrot");
        dessertModelList.add(d);
        d=new CommonModel("milk");
        dessertModelList.add(d);
        d=new CommonModel("cardamom powder");
        dessertModelList.add(d);
        d=new CommonModel("salt");
        dessertModelList.add(d);
        d=new CommonModel("ice cubes");
        dessertModelList.add(d);
        d=new CommonModel("ghee");
        dessertModelList.add(d);
        d=new CommonModel("gundh");
        dessertModelList.add(d);
        d=new CommonModel("nut meg");
        dessertModelList.add(d);
        d=new CommonModel("pistachio");
        dessertModelList.add(d);
        d=new CommonModel("almonds");
        dessertModelList.add(d);
        d=new CommonModel("flour");
        dessertModelList.add(d);
        d=new CommonModel("rice flour");
        dessertModelList.add(d);
        d=new CommonModel("baking powder");
        dessertModelList.add(d);
        d=new CommonModel("cashew nuts");
        dessertModelList.add(d);
        d=new CommonModel("lemon juice");
        dessertModelList.add(d);
        d=new CommonModel("milk powder");
        dessertModelList.add(d);
        d=new CommonModel("baking soda");
        dessertModelList.add(d);
        d=new CommonModel("keshar");
        dessertModelList.add(d);
        d=new CommonModel("raisins");
        dessertModelList.add(d);
        d=new CommonModel("black pepper");
        dessertModelList.add(d);
        d=new CommonModel("khuwa");
        dessertModelList.add(d);
        d=new CommonModel("bread");
        dessertModelList.add(d);
        d=new CommonModel("gram flour");
        dessertModelList.add(d);
        dessertAdapter.notifyDataSetChanged();
    }

    @Override
    public void buttonClicked(View view, int position) {
        if (position==0){
            final CommonModel drinkModel1=dessertModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel1.getName();
            String text= text1+text2+", ";
            editText.setText(text);
            BreakfastModel d=new BreakfastModel(text2);
            inputDessert.add(d);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }





        }
        if (position==1){
            final CommonModel drinkModel2=dessertModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel2.getName();
            String text= text1+text2+", ";
            editText.setText(text);
            BreakfastModel d=new BreakfastModel(text2);
            inputDessert.add(d);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }



        }
        if (position==2){
            final CommonModel drinkModel3=dessertModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel3.getName();
            String text= text1+text2+", ";
            editText.setText(text);
            BreakfastModel d=new BreakfastModel(text2);
            inputDessert.add(d);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }


        }
        if (position==3){
            final CommonModel drinkModel4=dessertModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel4.getName();
            String text= text1+text2+", ";
            editText.setText(text);
            BreakfastModel d=new BreakfastModel(text2);
            inputDessert.add(d);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }



        }
        if (position==4){
            final CommonModel drinkModel5=dessertModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel5.getName();
            String text= text1+text2+", ";
            editText.setText(text);
            BreakfastModel d=new BreakfastModel(text2);
            inputDessert.add(d);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }



        }
        if (position==5){
            final CommonModel drinkModel6=dessertModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel6.getName();
            String text= text1+text2+", ";
            editText.setText(text);
            BreakfastModel d=new BreakfastModel(text2);
            inputDessert.add(d);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }



        }
        if (position==6){
            final CommonModel drinkModel7=dessertModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel7.getName();
            String text= text1+text2+", ";
            editText.setText(text);
            BreakfastModel d=new BreakfastModel(text2);
            inputDessert.add(d);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }



        }
        if (position==7){
            final CommonModel drinkModel8=dessertModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel8.getName();
            String text= text1+text2+", ";
            editText.setText(text);
            BreakfastModel d=new BreakfastModel(text2);
            inputDessert.add(d);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }


        }
        if (position==8){
            final CommonModel drinkModel9=dessertModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel9.getName();
            String text= text1+text2+", ";
            editText.setText(text);
            BreakfastModel d=new BreakfastModel(text2);
            inputDessert.add(d);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }



        }
        if (position==9){
            final CommonModel drinkModel10=dessertModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel10.getName();
            String text= text1+text2+", ";
            editText.setText(text);
            BreakfastModel d=new BreakfastModel(text2);
            inputDessert.add(d);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }



        }
        if (position==10){
            final CommonModel drinkModel11=dessertModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel11.getName();
            String text= text1+text2+", ";
            editText.setText(text);
            BreakfastModel d=new BreakfastModel(text2);
            inputDessert.add(d);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }



        }
        if (position==11){
            final CommonModel drinkModel12=dessertModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel12.getName();
            String text= text1+text2+", ";
            editText.setText(text);
            BreakfastModel d=new BreakfastModel(text2);
            inputDessert.add(d);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }


        }
        if (position==12){
            final CommonModel drinkModel13=dessertModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel13.getName();
            String text= text1+text2+", ";
            editText.setText(text);
            BreakfastModel d=new BreakfastModel(text2);
            inputDessert.add(d);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }



        }
        if (position==13){
            final CommonModel drinkModel14=dessertModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel14.getName();
            String text= text1+text2+", ";
            editText.setText(text);
            BreakfastModel d=new BreakfastModel(text2);
            inputDessert.add(d);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }



        }
        if (position==14){
            final CommonModel drinkModel15=dessertModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel15.getName();
            String text= text1+text2+", ";
            editText.setText(text);
            BreakfastModel d=new BreakfastModel(text2);
            inputDessert.add(d);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }




        }
        if (position==15){
            final CommonModel drinkModel16=dessertModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel16.getName();
            String text= text1+text2+", ";
            editText.setText(text);
            BreakfastModel d=new BreakfastModel(text2);
            inputDessert.add(d);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }



        }
        if (position==16){
            final CommonModel drinkModel17=dessertModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel17.getName();
            String text= text1+text2+", ";
            editText.setText(text);
            BreakfastModel d=new BreakfastModel(text2);
            inputDessert.add(d);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }


        }
        if (position==17){
            final CommonModel drinkModel18=dessertModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel18.getName();
            String text= text1+text2+", ";
            editText.setText(text);
            BreakfastModel d=new BreakfastModel(text2);
            inputDessert.add(d);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }



        }
        if (position==18){
            final CommonModel drinkModel19=dessertModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel19.getName();
            String text= text1+text2+", ";
            editText.setText(text);
            BreakfastModel d=new BreakfastModel(text2);
            inputDessert.add(d);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }



        }
        if (position==19){
            final CommonModel drinkModel20=dessertModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel20.getName();
            String text= text1+text2+", ";
            editText.setText(text);
            BreakfastModel d=new BreakfastModel(text2);
            inputDessert.add(d);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }



        }
        if (position==20){
            final CommonModel drinkModel21=dessertModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel21.getName();
            String text= text1+text2+", ";
            editText.setText(text);
            BreakfastModel d=new BreakfastModel(text2);
            inputDessert.add(d);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }



        }
        if (position==21){
            final CommonModel drinkModel22=dessertModelList.get(position);
            String text1=editText.getText().toString();
            String text2=drinkModel22.getName();
            String text= text1+text2+", ";
            editText.setText(text);
            BreakfastModel d=new BreakfastModel(text2);
            inputDessert.add(d);
            if(count==0){
                text=text.replace(text2,"");
                editText.setText(text);



            }




        }
        if (position==22) {
            final CommonModel drinkModel23 = dessertModelList.get(position);
            String text1 = editText.getText().toString();
            String text2 = drinkModel23.getName() ;
            String text = text1 + text2+ ", ";
            editText.setText(text);
            BreakfastModel d=new BreakfastModel(text2);
            inputDessert.add(d);
            if (count == 0) {
                text = text.replace(text2, "");
                editText.setText(text);


            }
        }
        if (position==23) {
            final CommonModel drinkModel24 = dessertModelList.get(position);
            String text1 = editText.getText().toString();
            String text2 = drinkModel24.getName() + ", ";
            String text = text1 + text2;
            editText.setText(text);
            BreakfastModel d=new BreakfastModel(text2);
            inputDessert.add(d);
            if (count == 0) {
                text = text.replace(text2, "");
                editText.setText(text);


            }

        }
        if (position==24) {
            final CommonModel drinkModel25 = dessertModelList.get(position);
            String text1 = editText.getText().toString();
            String text2 = drinkModel25.getName() + ", ";
            String text = text1 + text2;
            editText.setText(text);
            BreakfastModel d=new BreakfastModel(text2);
            inputDessert.add(d);
            if (count == 0) {
                text = text.replace(text2, "");
                editText.setText(text);


            }
        }
        if (position==25) {
            final CommonModel drinkModel26 = dessertModelList.get(position);
            String text1 = editText.getText().toString();
            String text2 = drinkModel26.getName() + ", ";
            String text = text1 + text2;
            editText.setText(text);
            BreakfastModel d=new BreakfastModel(text2);
            inputDessert.add(d);
            if (count == 0) {
                text = text.replace(text2, "");
                editText.setText(text);


            }
        }
        if (position==26) {
            final CommonModel drinkModel27 = dessertModelList.get(position);
            String text1 = editText.getText().toString();
            String text2 = drinkModel27.getName() + ", ";
            String text = text1 + text2;
            editText.setText(text);
            BreakfastModel d=new BreakfastModel(text2);
            inputDessert.add(d);
            if (count == 0) {
                text = text.replace(text2, "");
                editText.setText(text);


            }
        }
        if (position==27) {
            final CommonModel drinkModel28 = dessertModelList.get(position);
            String text1 = editText.getText().toString();
            String text2 = drinkModel28.getName();
            String text = text1 + text2+",";
            editText.setText(text);
            //DessertModel d=new DessertModel(text2);
            //inputDessert.add(d);
            BreakfastModel d=new BreakfastModel(text2);
            inputDessert.add(d);
            if (count == 0) {
                text = text.replace(text2, "");
                editText.setText(text);


            }
        }
        String finalList = editText.getText().toString();
        BreakfastModel d1 =new BreakfastModel(finalList);
        inputDessert.add(d1);

        sbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gson gson = new Gson();
                final String newDataArray = gson.toJson(inputDessert);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, dessert_url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
//                                    JSONObject jsonObject = new JSONObject(response);
//                                    String success = jsonObject.getString("flag");
                                    final String result = response.toString();
                                    Log.d("response","result: " +result);
                                    Intent intent = new Intent(Dessert.this, Dessert_homepage.class);
                                    startActivity(intent);

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
