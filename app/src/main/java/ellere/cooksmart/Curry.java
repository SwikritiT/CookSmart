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
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import static ellere.cooksmart.API_creator.count;

/**
 * Created by swikriti on 8/14/2019.
 */

public class Curry extends AppCompatActivity implements CommonClickListener{
    private RecyclerView recyclerView;
    private CommonAdapter curryAdapter;
    private List<CommonModel> curryModelList;
    private Toolbar dtoolbar;
    private EditText editText;
    private Toolbar ctoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curry);
        ctoolbar = (Toolbar) findViewById(R.id.curry_toolbar);
        setSupportActionBar(ctoolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ctoolbar.setElevation(10f);
        }
        initCollapsingToolbar();
        recyclerView=(RecyclerView) findViewById(R.id.curry_recycler_view);
        editText=(EditText) findViewById(R.id.curry_edittext);
        editText.setSelection(editText.getText().length());
        editText.setCursorVisible(false);
        editText.setFocusable(false);
        //button=(Button) findViewById(R.id.common_button);
        editText.setCursorVisible(false);
        editText.setFocusable(false);
        curryModelList=new ArrayList<>();
        curryAdapter= new CommonAdapter(this,curryModelList);
        curryAdapter.setClickListener(this);
        RecyclerView.LayoutManager mlayoutManager=new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(mlayoutManager);
        recyclerView.addItemDecoration(new Curry.GridSpacingItemDecoration(3, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(curryAdapter);
        prepareCurry();
        try {
            Glide.with(this).load(R.drawable.curry).into((ImageView) findViewById(R.id.curry_backdrop));
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
        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.curry_collapsing_toolbar);
        collapsingToolbarLayout.setTitle("");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.curry_appbar_layout);
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
                    collapsingToolbarLayout.setTitle(getString(R.string.curry));
                    isShow = true;

                } else if (isShow) {
                    collapsingToolbarLayout.setTitle("");
                    isShow = false;
                }

            }
        });

    }
    private void prepareCurry(){
        CommonModel d = new CommonModel("taro");
        curryModelList.add(d);
        d=new CommonModel("peas");
        curryModelList.add(d);
        d=new CommonModel("lemon");
        curryModelList.add(d);
        d=new CommonModel("bottle gourd");
        curryModelList.add(d);
        d=new CommonModel("potato");
        curryModelList.add(d);
        d=new CommonModel("yoghurt");
        curryModelList.add(d);
        d=new CommonModel("bayleaf");
        curryModelList.add(d);
        d=new CommonModel("banana");
        curryModelList.add(d);
        d=new CommonModel("bitter gourd");
        curryModelList.add(d);
        d=new CommonModel("ginger");
        curryModelList.add(d);
        d=new CommonModel("garlic");
        curryModelList.add(d);
        d=new CommonModel("onion");
        curryModelList.add(d);
        d=new CommonModel("tomato");
        curryModelList.add(d);
        d=new CommonModel("yam");
        curryModelList.add(d);
        d=new CommonModel("lentil flour");
        curryModelList.add(d);
        d=new CommonModel("bamboo shoots");
        curryModelList.add(d);
        d=new CommonModel("black eyed peas");
        curryModelList.add(d);
        d=new CommonModel("kidney beans");
        curryModelList.add(d);
        d=new CommonModel("mustard leaves");
        curryModelList.add(d);
        d=new CommonModel("besan");
        curryModelList.add(d);
        d=new CommonModel("lady finger");
        curryModelList.add(d);
        d=new CommonModel("pumpkin");
        curryModelList.add(d);
        d=new CommonModel("gahat");
        curryModelList.add(d);
        d=new CommonModel("masyaura");
        curryModelList.add(d);
        d=new CommonModel("gundruk");
        curryModelList.add(d);
        d=new CommonModel("soya bean");
        curryModelList.add(d);
        curryAdapter.notifyDataSetChanged();
    }

    @Override
    public void buttonClicked(View view, int position) {
        if (position==0){
            final CommonModel drinkModel1=curryModelList.get(position);
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
            final CommonModel drinkModel2=curryModelList.get(position);
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
            final CommonModel drinkModel3=curryModelList.get(position);
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
            final CommonModel drinkModel4=curryModelList.get(position);
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
            final CommonModel drinkModel5=curryModelList.get(position);
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
            final CommonModel drinkModel6=curryModelList.get(position);
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
            final CommonModel drinkModel7=curryModelList.get(position);
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
            final CommonModel drinkModel8=curryModelList.get(position);
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
            final CommonModel drinkModel9=curryModelList.get(position);
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
            final CommonModel drinkModel10=curryModelList.get(position);
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
            final CommonModel drinkModel11=curryModelList.get(position);
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
            final CommonModel drinkModel12=curryModelList.get(position);
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
            final CommonModel drinkModel13=curryModelList.get(position);
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
            final CommonModel drinkModel14=curryModelList.get(position);
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
            final CommonModel drinkModel15=curryModelList.get(position);
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
            final CommonModel drinkModel16=curryModelList.get(position);
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
            final CommonModel drinkModel17=curryModelList.get(position);
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
            final CommonModel drinkModel18=curryModelList.get(position);
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
            final CommonModel drinkModel19=curryModelList.get(position);
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
            final CommonModel drinkModel20=curryModelList.get(position);
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
            final CommonModel drinkModel21=curryModelList.get(position);
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
            final CommonModel drinkModel22=curryModelList.get(position);
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
            final CommonModel drinkModel23 = curryModelList.get(position);
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
            final CommonModel drinkModel24 = curryModelList.get(position);
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
            final CommonModel drinkModel25 = curryModelList.get(position);
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
            final CommonModel drinkModel26 = curryModelList.get(position);
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
            final CommonModel drinkModel27 = curryModelList.get(position);
            String text1 = editText.getText().toString();
            String text2 = drinkModel27.getName() + ", ";
            String text = text1 + text2;
            editText.setText(text);
            if (count == 0) {
                text = text.replace(text2, "");
                editText.setText(text);


            }
        }



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
