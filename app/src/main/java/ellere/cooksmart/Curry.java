package ellere.cooksmart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

/**
 * Created by swikriti on 8/14/2019.
 */

public class Curry extends AppCompatActivity {
    private Toolbar ctoolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curry);
        ctoolbar=(Toolbar)findViewById(R.id.curry_toolbar);
        setSupportActionBar(ctoolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("Curry");
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
