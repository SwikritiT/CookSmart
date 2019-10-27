package ellere.cooksmart;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.textclassifier.TextClassification;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static ellere.cooksmart.API_creator.BASE_URL;

public class Login extends AppCompatActivity {
     Button loginbtn, gosignupbtn;
    EditText user, pass;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String UserName = "nameKey";
    public static final String Password = "passKey";
    public static final String Email = "emailKey";
    int PRIVATE_MODE = 0;
   // SessionManager sessionManager;

    SharedPreferences pref;
    Intent intent;
    String reg_url = BASE_URL+"login.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        gosignupbtn=(Button) findViewById(R.id.gotosignup_btn);
        loginbtn=(Button) findViewById(R.id.login_btn);
        user=(EditText) findViewById(R.id.username_login);
        pass=(EditText) findViewById(R.id.password_login);

        pref = getSharedPreferences("user_details",MODE_PRIVATE);
       intent = new Intent(Login.this,HomePage.class);
        if(pref.contains("username") && pref.contains("password")){
            startActivity(intent);
        }
        loginbtn.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {
                                          String username = user.getText().toString();
                                          String password = pass.getText().toString();



                                          if (username.equals("") || password.equals("")) {
                                              Toast.makeText(getBaseContext(), "Fill up all the field properly", Toast.LENGTH_SHORT).show();
                                              return;
                                          }
                                          else
                                          {

                                              UserLoginFunction(username, password);
                                          }

                                      }
                                  }
        );

        gosignupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Login.this,SignUp.class);
                startActivity(intent);
            }
        });
    }

    public void UserLoginFunction(final String username,  final String password){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, reg_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject= new JSONObject(response);
                            String success =jsonObject.getString("flag");
//                            JSONObject myObj=new JSONObject(success);
                            if (success.equals("1")){
                                 SharedPreferences.Editor editor = pref.edit();
                                editor.putString("username",username);
                                editor.putString("password",password);
                                editor.commit();
                                    Toast.makeText(Login.this,"Login Successful",Toast.LENGTH_SHORT).show();





                                Intent intent = new Intent(Login.this, HomePage.class);

                                    startActivity(intent);
                                }

                            else
                            {
                                Toast.makeText(Login.this,"Enter correct username or password",Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Login.this,"Login failed"+e.toString(),Toast.LENGTH_SHORT).show();
                        }


                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Login.this,"Login Failed"+error.toString(),Toast.LENGTH_SHORT).show();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("username",username);
                params.put("password",password);


                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }

}
