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
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by swikriti on 8/14/2019.
 */

public class Dessert extends AppCompatActivity implements CommonClickListener{
    private RecyclerView recyclerView;
    private CommonAdapter dessertAdapter;
    private List<CommonModel> dessertModelList;
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
        //button=(Button) findViewById(R.id.common_button);
        dessertModelList=new ArrayList<>();
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
        CommonModel d = new CommonModel("lemon");
        dessertModelList.add(d);
        d=new CommonModel("mint");
        dessertModelList.add(d);
        d=new CommonModel("sugar");
        dessertModelList.add(d);
        d=new CommonModel("salt");
        dessertModelList.add(d);
        d=new CommonModel("ice cubes");
        dessertModelList.add(d);
        d=new CommonModel("water");
        dessertModelList.add(d);
        d=new CommonModel("soda");
        dessertModelList.add(d);
        d=new CommonModel("black salt");
        dessertModelList.add(d);
        d=new CommonModel("sugarcane juice");
        dessertModelList.add(d);
        d=new CommonModel("banana");
        dessertModelList.add(d);
        d=new CommonModel("yoghurt");
        dessertModelList.add(d);
        d=new CommonModel("cashew nut");
        dessertModelList.add(d);
        d=new CommonModel("honey");
        dessertModelList.add(d);
        d=new CommonModel("milk");
        dessertModelList.add(d);
        d=new CommonModel("tea");
        dessertModelList.add(d);
        d=new CommonModel("coffee");
        dessertModelList.add(d);
        d=new CommonModel("ice cream");
        dessertModelList.add(d);
        d=new CommonModel("chocolate syrup");
        dessertModelList.add(d);
        d=new CommonModel("mango");
        dessertModelList.add(d);
        d=new CommonModel("coconut milk");
        dessertModelList.add(d);
        d=new CommonModel("coconut water");
        dessertModelList.add(d);
        d=new CommonModel("black pepper");
        dessertModelList.add(d);
        dessertAdapter.notifyDataSetChanged();
    }

    @Override
    public void buttonClicked(View view, int position) {
        if (position==0){
            final CommonModel drinkModel1=dessertModelList.get(position);
            editText.setText(editText.getText()+drinkModel1.getName()+", ");



        }
        if (position==1){
            final CommonModel drinkModel2=dessertModelList.get(position);
            editText.setText(editText.getText()+drinkModel2.getName()+", ");



        }
        if (position==2){
            final CommonModel drinkModel3=dessertModelList.get(position);
            editText.setText(editText.getText()+drinkModel3.getName()+", ");



        }
        if (position==3){
            final CommonModel drinkModel4=dessertModelList.get(position);
            editText.setText(editText.getText()+drinkModel4.getName()+", ");



        }
        if (position==4){
            final CommonModel drinkModel5=dessertModelList.get(position);
            editText.setText(editText.getText()+drinkModel5.getName()+", ");



        }
        if (position==5){
            final CommonModel drinkModel6=dessertModelList.get(position);
            editText.setText(editText.getText()+drinkModel6.getName()+", ");



        }
        if (position==6){
            final CommonModel drinkModel7=dessertModelList.get(position);
            editText.setText(editText.getText()+drinkModel7.getName()+", ");



        }
        if (position==7){
            final CommonModel drinkModel8=dessertModelList.get(position);
            editText.setText(editText.getText()+drinkModel8.getName()+", ");


        }
        if (position==8){
            final CommonModel drinkModel9=dessertModelList.get(position);
            editText.setText(editText.getText()+drinkModel9.getName()+", ");



        }
        if (position==9){
            final CommonModel drinkModel10=dessertModelList.get(position);
            editText.setText(editText.getText()+drinkModel10.getName()+", ");



        }
        if (position==10){
            final CommonModel drinkModel11=dessertModelList.get(position);
            editText.setText(editText.getText()+drinkModel11.getName()+", ");



        }
        if (position==11){
            final CommonModel drinkModel12=dessertModelList.get(position);
            editText.setText(editText.getText()+drinkModel12.getName()+", ");



        }
        if (position==12){
            final CommonModel drinkModel13=dessertModelList.get(position);
            editText.setText(editText.getText()+drinkModel13.getName()+", ");



        }
        if (position==13){
            final CommonModel drinkModel14=dessertModelList.get(position);
            editText.setText(editText.getText()+drinkModel14.getName()+", ");



        }
        if (position==14){
            final CommonModel drinkModel15=dessertModelList.get(position);
            editText.setText(editText.getText()+drinkModel15.getName()+", ");



        }
        if (position==15){
            final CommonModel drinkModel16=dessertModelList.get(position);
            editText.setText(editText.getText()+drinkModel16.getName()+", ");



        }
        if (position==16){
            final CommonModel drinkModel17=dessertModelList.get(position);
            editText.setText(editText.getText()+drinkModel17.getName()+", ");



        }
        if (position==17){
            final CommonModel drinkModel18=dessertModelList.get(position);
            editText.setText(editText.getText()+drinkModel18.getName()+", ");



        }
        if (position==18){
            final CommonModel drinkModel19=dessertModelList.get(position);
            editText.setText(editText.getText()+drinkModel19.getName()+", ");



        }
        if (position==19){
            final CommonModel drinkModel20=dessertModelList.get(position);
            editText.setText(editText.getText()+drinkModel20.getName()+", ");



        }
        if (position==20){
            final CommonModel drinkModel21=dessertModelList.get(position);
            editText.setText(editText.getText()+drinkModel21.getName()+", ");



        }
        if (position==21){
            final CommonModel drinkModel22=dessertModelList.get(position);
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
