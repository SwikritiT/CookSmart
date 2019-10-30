package ellere.cooksmart;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class FirstPage extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_first_page);
//        button=(Button) findViewById(R.id.startbtn);
//        button.setOnClickListener(new View.OnClickListener() {
//                                      @Override
//                                      public void onClick(View view) {
//                                          Intent intent= new Intent(FirstPage.this,Login.class);
//                                          startActivity(intent);
//                                      }
//                                  }
//        );

        int secondsDelayed = 1;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(FirstPage.this, Login.class));
                finish();
            }
        }, secondsDelayed * 1000);
    }
}
