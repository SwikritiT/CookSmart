package ellere.cooksmart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

/**
 * Created by swikriti on 8/14/2019.
 */

public class Breakfast extends AppCompatActivity {
    private Toolbar btoolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakfast);
        btoolbar=(Toolbar)findViewById(R.id.breakfast_toolbar);
        setSupportActionBar(btoolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("Breakfast");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }



}
