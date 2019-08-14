package ellere.cooksmart;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity implements ClickListener {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private CategoriesAdapter categoriesAdapter;
    private List<CategoriesModel> categoriesModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        toolbar.inflateMenu(R.menu.main_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String title=(String) item.getTitle();
                Toast.makeText(HomePage.this,title+" selected!",Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            toolbar.setElevation(10f);}
        initCollapsingToolbar();
        recyclerView=(RecyclerView) findViewById(R.id.recycler_view);
        categoriesModelList=new ArrayList<>();
        categoriesAdapter= new CategoriesAdapter(this,categoriesModelList);
        categoriesAdapter.setClickListener(this);
        RecyclerView.LayoutManager mlayoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(mlayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(categoriesAdapter);

        prepareCategories();
        try {
            Glide.with(this).load(R.drawable.categoriesheader).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /*initializing collapsing toolbar
    hide or show toolbar title while scrolling
     */
    private void initCollapsingToolbar(){
        final CollapsingToolbarLayout collapsingToolbarLayout=(CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("");
        AppBarLayout appBarLayout=(AppBarLayout) findViewById(R.id.appbar_layout);
        appBarLayout.setExpanded(true);
        //hiding and showing title when toolbar is extended or collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow=false;
            int scrollRange=-1;
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(scrollRange==-1){
                    scrollRange=appBarLayout.getTotalScrollRange();

                }
                if(scrollRange+verticalOffset==0){
                    collapsingToolbarLayout.setTitle(getString(R.string.app_name));
                    isShow=true;

                }
                else if (isShow){
                    collapsingToolbarLayout.setTitle("");
                    isShow=false;
                }

            }
        });
    }
    private void prepareCategories(){
        int[] covers= new int[]{
                R.drawable.breakfast,
                R.drawable.snacks,
                R.drawable.dinner,
                R.drawable.curry,
                R.drawable.dessert,
                R.drawable.drinks
        };
        CategoriesModel c = new CategoriesModel("Breakfast",covers[0]);
        categoriesModelList.add(c);
        c=new CategoriesModel("Snacks",covers[1]);
        categoriesModelList.add(c);
        c=new CategoriesModel("Dinner",covers[2]);
        categoriesModelList.add(c);
        c=new CategoriesModel("Curry",covers[3]);
        categoriesModelList.add(c);
        c=new CategoriesModel("Dessert",covers[4]);
        categoriesModelList.add(c);
        c=new CategoriesModel("Drinks",covers[5]);
        categoriesModelList.add(c);

        categoriesAdapter.notifyDataSetChanged();
    }

    @Override
    public void itemClicked(View view, int position) {
        if(position==0){
            Intent intent = new Intent(HomePage.this, Breakfast.class);
            intent.putExtra("ItemPosition", position);
            startActivity(intent);
        }
        if(position==1){
            Intent intent = new Intent(HomePage.this, Snacks.class);
            intent.putExtra("ItemPosition", position);
            startActivity(intent);
        }
        if(position==2){
            Intent intent = new Intent(HomePage.this, Dinner.class);
            intent.putExtra("ItemPosition", position);
            startActivity(intent);
        }
        if(position==3){
            Intent intent = new Intent(HomePage.this, Curry.class);
            intent.putExtra("ItemPosition", position);
            startActivity(intent);
        }
        if(position==4){
            Intent intent = new Intent(HomePage.this, Dessert.class);
            intent.putExtra("ItemPosition", position);
            startActivity(intent);
        }
        if(position==5){
            Intent intent = new Intent(HomePage.this, Drinks.class);
            intent.putExtra("ItemPosition", position);
            startActivity(intent);
        }

    }

    /*
            * RecyclerView item decoration - give equal margin around grid item
     */
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


