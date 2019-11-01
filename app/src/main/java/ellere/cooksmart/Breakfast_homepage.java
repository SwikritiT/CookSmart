package ellere.cooksmart;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static ellere.cooksmart.API_creator.BASE_URL;

/**
 * Created by swikriti on 9/8/2019.
 */

public class Breakfast_homepage extends AppCompatActivity {
    private Toolbar toolbar;

    LinearLayout linearLayout;
    String breakfast_url = BASE_URL+"viewRecipe.php";
    List<RecipeModel> recipeModelList;
    private Animation animationUp, animationDown;
    RecyclerView recyclerView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.breakfast_homepage);
        toolbar=(Toolbar)findViewById(R.id.break_tool);
        linearLayout=(LinearLayout)findViewById(R.id.breakfast_homepage) ;
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("Breakfast");

        }

        recipeModelList=new ArrayList<>();
        animationUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        animationDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);




        loadRecipes();
    }

    public void openDialog() {

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        ViewGroup popupView = (ViewGroup)inflater.inflate(R.layout.dialog_demo, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(linearLayout, Gravity.CENTER, 0, 0);

        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }

    private void loadRecipes(){

        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("loading data");
        progressDialog.show();
        /*
        * Creating a String Request
        * The request type is GET defined by first parameter
        * The URL is defined in the second parameter
        * Then we have a Response Listener and a Error Listener
        * In response listener we will get the JSON response as a String
        * */
        StringRequest stringRequest=new StringRequest(Request.Method.GET, breakfast_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Log.d("response",response);
                        progressDialog.dismiss();
                        try {
                            final String result = response.toString();
                            if(result.equals("")){
                                //Toast.makeText(Breakfast_homepage.this, "No recipe found.Please select other ingredients.", Toast.LENGTH_SHORT).show();
                                //openDialog();

                                AlertDialog.Builder builder1 = new AlertDialog.Builder(Breakfast_homepage.this);
                                builder1.setMessage("Sorry, no recipe found. Please select other ingredients.");
                                builder1.setCancelable(true);

                                builder1.setPositiveButton(
                                        "Okay",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                                Intent intent=new Intent(Breakfast_homepage.this,Breakfast.class);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                startActivity(intent);
                                            }

                                        });

                                builder1.setNegativeButton(
                                        "Cancle",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        });

                                AlertDialog alert11 = builder1.create();
                                alert11.show();

                            }
                            else {
                            int i =0;
                            Log.d("response","result1: " +result);
                            JSONObject jsonObject=new JSONObject(response);
                            JSONArray jsonArray=jsonObject.getJSONArray("response_list");


                                while (i < jsonArray.length()) {

                                    //getting product object from json array
                                    JSONObject recipes = jsonArray.getJSONObject(i);
                                    String name = recipes.getString("name");
                                    String image_path = recipes.getString("link");
                                    String ingredients = recipes.getString("ingredient");
                                    String instructions = recipes.getString("instruction");

                                    //adding the product to product list
                                    RecipeModel recipeModel = new RecipeModel(image_path, name, ingredients, instructions);
                                    recipeModelList.add(new RecipeModel(image_path, name, ingredients, instructions));
                                    i++;

                                }
                            }
                            recyclerView = (RecyclerView) findViewById(R.id.recipes_recycler_view);
                            LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            recyclerView.setHasFixedSize(true);
                            recyclerView.setLayoutManager(layoutManager);

                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            RecipeAdapter recipeAdapter = new RecipeAdapter(Breakfast_homepage.this, recipeModelList,animationUp,animationDown);
                            recyclerView.setAdapter(recipeAdapter);
                            //recipeAdapter.recipeDataSetChanged();
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