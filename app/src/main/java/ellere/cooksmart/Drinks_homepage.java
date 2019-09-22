package ellere.cooksmart;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static ellere.cooksmart.API_creator.BASE_URL;

/**
 * Created by swikriti on 9/8/2019.
 */

public class Drinks_homepage extends AppCompatActivity {
    String drinks_url = BASE_URL+"viewRecipe.php";
    List<RecipeModel> recipeModelList;
    RecyclerView recyclerView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.breakfast_homepage);

        recipeModelList=new ArrayList<>();



        loadRecipes();
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
        StringRequest stringRequest=new StringRequest(Request.Method.GET, drinks_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       // Log.d("response",response);
                        progressDialog.dismiss();
                        try {
                            final String result = response.toString();
                            Log.d("response","result1: " +result);
                            JSONObject jsonObject=new JSONObject(response);
                            JSONArray jsonArray=jsonObject.getJSONArray("response_list");
                            for (int i = 0; i < jsonArray.length(); i++) {

                                //getting product object from json array
                                JSONObject recipes = jsonArray.getJSONObject(i);
                                String name=recipes.getString("recipe_name");
                                String image_path=recipes.getString("picture_link");

                                //adding the product to product list
                                recipeModelList.add(new RecipeModel(image_path,name));
                            }
                            recyclerView = (RecyclerView) findViewById(R.id.recipes_recycler_view);
                            LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            recyclerView.setHasFixedSize(true);
                            recyclerView.setLayoutManager(layoutManager);

                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            RecipeAdapter recipeAdapter = new RecipeAdapter(Drinks_homepage.this, recipeModelList);
                            recyclerView.setAdapter(recipeAdapter);
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
