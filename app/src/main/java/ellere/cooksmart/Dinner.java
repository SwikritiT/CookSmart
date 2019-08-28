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
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by swikriti on 8/14/2019.
 */

public class Dinner extends AppCompatActivity implements CommonClickListener {
    private RecyclerView recyclerView;
    private CommonAdapter dinnerAdapter;
    private List<CommonModel> dinnerModelList;
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
        button=(Button) findViewById(R.id.common_button);
        dinnerModelList=new ArrayList<>();
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
        CommonModel d = new CommonModel("lemon");
        dinnerModelList.add(d);
        d=new CommonModel("mint");
        dinnerModelList.add(d);
        d=new CommonModel("sugar");
        dinnerModelList.add(d);
        d=new CommonModel("salt");
        dinnerModelList.add(d);
        d=new CommonModel("ice cubes");
        dinnerModelList.add(d);
        d=new CommonModel("water");
        dinnerModelList.add(d);
        d=new CommonModel("soda");
        dinnerModelList.add(d);
        d=new CommonModel("black salt");
        dinnerModelList.add(d);
        d=new CommonModel("sugarcane juice");
        dinnerModelList.add(d);
        d=new CommonModel("banana");
        dinnerModelList.add(d);
        d=new CommonModel("yoghurt");
        dinnerModelList.add(d);
        d=new CommonModel("cashew nut");
        dinnerModelList.add(d);
        d=new CommonModel("honey");
        dinnerModelList.add(d);
        d=new CommonModel("milk");
        dinnerModelList.add(d);
        d=new CommonModel("tea");
        dinnerModelList.add(d);
        d=new CommonModel("coffee");
        dinnerModelList.add(d);
        d=new CommonModel("ice cream");
        dinnerModelList.add(d);
        d=new CommonModel("chocolate syrup");
        dinnerModelList.add(d);
        d=new CommonModel("mango");
        dinnerModelList.add(d);
        d=new CommonModel("coconut milk");
        dinnerModelList.add(d);
        d=new CommonModel("coconut water");
        dinnerModelList.add(d);
        d=new CommonModel("black pepper");
        dinnerModelList.add(d);
        dinnerAdapter.notifyDataSetChanged();
    }

    @Override
    public void buttonClicked(View view, int position) {
        if (position==0){
            final CommonModel drinkModel1=dinnerModelList.get(position);
            editText.setText(editText.getText()+drinkModel1.getName()+", ");



        }
        if (position==1){
            final CommonModel drinkModel2=dinnerModelList.get(position);
            editText.setText(editText.getText()+drinkModel2.getName()+", ");



        }
        if (position==2){
            final CommonModel drinkModel3=dinnerModelList.get(position);
            editText.setText(editText.getText()+drinkModel3.getName()+", ");



        }
        if (position==3){
            final CommonModel drinkModel4=dinnerModelList.get(position);
            editText.setText(editText.getText()+drinkModel4.getName()+", ");



        }
        if (position==4){
            final CommonModel drinkModel5=dinnerModelList.get(position);
            editText.setText(editText.getText()+drinkModel5.getName()+", ");



        }
        if (position==5){
            final CommonModel drinkModel6=dinnerModelList.get(position);
            editText.setText(editText.getText()+drinkModel6.getName()+", ");



        }
        if (position==6){
            final CommonModel drinkModel7=dinnerModelList.get(position);
            editText.setText(editText.getText()+drinkModel7.getName()+", ");



        }
        if (position==7){
            final CommonModel drinkModel8=dinnerModelList.get(position);
            editText.setText(editText.getText()+drinkModel8.getName()+", ");


        }
        if (position==8){
            final CommonModel drinkModel9=dinnerModelList.get(position);
            editText.setText(editText.getText()+drinkModel9.getName()+", ");



        }
        if (position==9){
            final CommonModel drinkModel10=dinnerModelList.get(position);
            editText.setText(editText.getText()+drinkModel10.getName()+", ");



        }
        if (position==10){
            final CommonModel drinkModel11=dinnerModelList.get(position);
            editText.setText(editText.getText()+drinkModel11.getName()+", ");



        }
        if (position==11){
            final CommonModel drinkModel12=dinnerModelList.get(position);
            editText.setText(editText.getText()+drinkModel12.getName()+", ");



        }
        if (position==12){
            final CommonModel drinkModel13=dinnerModelList.get(position);
            editText.setText(editText.getText()+drinkModel13.getName()+", ");



        }
        if (position==13){
            final CommonModel drinkModel14=dinnerModelList.get(position);
            editText.setText(editText.getText()+drinkModel14.getName()+", ");



        }
        if (position==14){
            final CommonModel drinkModel15=dinnerModelList.get(position);
            editText.setText(editText.getText()+drinkModel15.getName()+", ");



        }
        if (position==15){
            final CommonModel drinkModel16=dinnerModelList.get(position);
            editText.setText(editText.getText()+drinkModel16.getName()+", ");



        }
        if (position==16){
            final CommonModel drinkModel17=dinnerModelList.get(position);
            editText.setText(editText.getText()+drinkModel17.getName()+", ");



        }
        if (position==17){
            final CommonModel drinkModel18=dinnerModelList.get(position);
            editText.setText(editText.getText()+drinkModel18.getName()+", ");



        }
        if (position==18){
            final CommonModel drinkModel19=dinnerModelList.get(position);
            editText.setText(editText.getText()+drinkModel19.getName()+", ");



        }
        if (position==19){
            final CommonModel drinkModel20=dinnerModelList.get(position);
            editText.setText(editText.getText()+drinkModel20.getName()+", ");



        }
        if (position==20){
            final CommonModel drinkModel21=dinnerModelList.get(position);
            editText.setText(editText.getText()+drinkModel21.getName()+", ");



        }
        if (position==21){
            final CommonModel drinkModel22=dinnerModelList.get(position);
            editText.setText(editText.getText()+drinkModel22.getName()+", ");



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
