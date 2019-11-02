package ellere.cooksmart;



        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageButton;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.android.volley.Request;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.StringRequest;
        import com.android.volley.toolbox.Volley;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import static ellere.cooksmart.API_creator.BASE_URL;

/**
 * Created by swikriti on 10/28/2019.
 */


public class Profile extends AppCompatActivity {
    String url = BASE_URL + "profile.php";
    SharedPreferences prf;
    Intent intent;
    TextView user_name, fullname_textview, email_textview, phone_textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        user_name = findViewById(R.id.user_name);
        fullname_textview = findViewById(R.id.fullname);
        email_textview = findViewById(R.id.email);
        phone_textview = findViewById(R.id.phone);
        ImageButton btn = findViewById(R.id.logoutBtn);
        prf = getSharedPreferences("user_details", MODE_PRIVATE);
        SharedPreferences.Editor editor = prf.edit();

        user_name.setText(prf.getString("username", "default value"));
        String FULLNAME=prf.getString("fullname", "default");
        String EMAIL=prf.getString("email", "default");
        String  PHONENUMBER= prf.getString("phonenumber", "default");

        if(FULLNAME=="default")
        {loadProfile();}
        else
        {
            fullname_textview.setText(prf.getString("fullname", "default value"));
            email_textview.setText(prf.getString("email", "default value"));
            phone_textview.setText(prf.getString("phonenumber", "default value"));
        }


        intent = new Intent(Profile.this, Login.class);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = prf.edit();
                editor.clear();
                editor.commit();
                startActivity(intent);



            }
        });




    }
    private void loadProfile(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("profile", "onResponse: "+response);
                        try {
                            final String result = response.toString();
                            int i = 0;
                            Log.d("response", "profile_info: " + result);
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("response_list");



                            JSONObject details = jsonArray.getJSONObject(i);
                            String name = details.getString("username");

                            String fn = details.getString("fullname");
                            String em = details.getString("email");
                            String pn = details.getString("phonenumber");


                            fullname_textview.setText(fn);
                            email_textview.setText(em);
                            phone_textview.setText(pn);



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        volleyError.printStackTrace();
                        volleyError.getMessage();

                    }
                }
        );
        Volley.newRequestQueue(this).add(stringRequest);
    }

}

