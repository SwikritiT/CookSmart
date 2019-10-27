package ellere.cooksmart;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.nio.channels.InterruptedByTimeoutException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ellere.cooksmart.API_creator.BASE_URL;

public class SignUp extends AppCompatActivity {
    Button signupbtn;
    EditText user, pass, confirmpass,fname,email,phonenumber;
    String username,password,confirmpassword;
    String reg_url = BASE_URL+"signup.php";
    private Pattern pattern;
    private Matcher matcher;
    private static final String USERNAME_PATTERN = ".*[&%$#@!~]+.*";
    SharedPreferences pref;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signupbtn = (Button) findViewById(R.id.signup_btn);
        user = (EditText) findViewById(R.id.username_signup);
        pass = (EditText) findViewById(R.id.password_signup);
        confirmpass = (EditText) findViewById(R.id.confirmpassword_signup);
        fname=(EditText)findViewById(R.id.fullname);
        email=(EditText)findViewById(R.id.email);
        phonenumber=(EditText)findViewById(R.id.phonenumber);
        pattern = Pattern.compile(USERNAME_PATTERN);
        pref = getSharedPreferences("user_details",MODE_PRIVATE);
        intent = new Intent(SignUp.this,HomePage.class);
        if(pref.contains("username") && pref.contains("password")){
            startActivity(intent);
        }
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 username = user.getText().toString().trim();
                password = pass.getText().toString().trim();
                confirmpassword = confirmpass.getText().toString().trim();
                if (username.equals("") || password.equals("") || confirmpassword.equals("")) {
                    Toast.makeText(SignUp.this, "Fill up the field properly", Toast.LENGTH_SHORT).show();

                } else if (password.length() < 8) {
                     Toast.makeText(SignUp.this, "Password too short...", Toast.LENGTH_SHORT).show();

                }
                else if (!password.equals(confirmpassword)) {
                    Toast.makeText(SignUp.this, "Password donot match...", Toast.LENGTH_SHORT).show();
                }


                else if (!validate(username)) {
                    Toast.makeText(SignUp.this, "special characters not allowed in username...", Toast.LENGTH_SHORT).show();
                }
//                 If EditText is not empty and CheckEditText = True then this block will execute.
                else {
                    UserRegisterFunction(username,password);


                }

            }

        });
    }

    public void UserRegisterFunction(final String username, final String password) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, reg_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("flag");
//                            JSONObject myObj=new JSONObject(success);
                            if (success.equals("1")) {
                                SharedPreferences.Editor editor = pref.edit();
                                editor.putString("username",username);
                                editor.putString("password",password);
                                editor.commit();
                                Toast.makeText(SignUp.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignUp.this, HomePage.class);
                                startActivity(intent);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(SignUp.this, "Register failed" + e.toString(), Toast.LENGTH_SHORT).show();
                        }


                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SignUp.this, "Register Failed" + error.toString(), Toast.LENGTH_SHORT).show();

                    }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    public boolean validate(final String username) {
        boolean valid = true;
        matcher = pattern.matcher(username);
        if (matcher.matches()) {
            valid = false;

        }
        return valid;
    }
}
