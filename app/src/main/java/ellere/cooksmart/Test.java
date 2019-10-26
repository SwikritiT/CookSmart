//package ellere.cooksmart;
//
//import android.app.ProgressDialog;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//
//import com.android.volley.AuthFailureError;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//import com.google.gson.Gson;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static ellere.cooksmart.API_creator.BASE_URL;
//import static ellere.cooksmart.Breakfast.inputBreakfast;
//
///**
// * Created by swikriti on 9/25/2019.
// */
//
//public class Test extends AppCompatActivity {
//    private static String url = BASE_URL + "newHandler.php";
//    private static String url1 = BASE_URL + "requestLini.php";
//
//    List<RecipeModel> recipeModelList;
//    RecyclerView recyclerView;
//    RecipeAdapter recipeAdapter;
//
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.breakfast_homepage);
//        recipeModelList = new ArrayList<>();
//        sendIngredients();
//
//        loadRecipes();
//    }
// public void sendIngredients() {
//    Gson gson = new Gson();
//    final String newDataArray = gson.toJson(inputBreakfast);
//
////    RequestQueue requestQueue= Volley.newRequestQueue(this);
////        requestQueue.add(stringRequest);
//
//    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//
//        @Override
//        public void onResponse(String response) {
//            try {
////                                    JSONObject jsonObject = new JSONObject(response);
////                                    String success = jsonObject.getString("flag");
//                final String result = response.toString();
//                Log.d("response", "sendStringReq: " + result);
//
//
//
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//    },
//            new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    error.printStackTrace();
//                    error.getMessage();
//
//                }
//
//            }) {@Override
//    protected Map<String, String> getParams() throws AuthFailureError {
//        Map<String, String> params = new HashMap<>();
//        params.put("ingredients_array", newDataArray); // array is a key which will be used in server side
//        return params;
//    }};
//
//
//        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//        requestQueue.add(stringRequest);
//
//
//}
//
//
//public void loadRecipes(){
//    final ProgressDialog progressDialog = new ProgressDialog(this);
//    progressDialog.setMessage("loading data");
//    progressDialog.show();
//        /*
//        * Creating a String Request
//        * The request type is GET defined by first parameter
//        * The URL is defined in the second parameter
//        * Then we have a Response Listener and a Error Listener
//        * In response listener we will get the JSON response as a String
//        * */
//    StringRequest stringRequest=new StringRequest(Request.Method.GET,url1,new Response.Listener<String>() {
//        @Override
//        public void onResponse(String response) {
//            //String result=response;
//            Log.d("response","RecipeList: "+response);
//            progressDialog.dismiss();
//
//            try {
//                int i=0;
//                JSONObject jsonObject=new JSONObject(response);
//                JSONArray jsonArray=jsonObject.getJSONArray("response_list");
//
//                while(i<jsonArray.length())
//                {
//
//                    JSONObject recipes=jsonArray.getJSONObject(i);
//                    String name=recipes.getString("recipe_name");
//                    String image_path=recipes.getString("picture_link");
//
//                    //adding the product to product list
//                    recipeModelList.add(new RecipeModel(image_path,name));
//                    i++;
//                }
//
//                recyclerView =(RecyclerView) findViewById(R.id.recipes_recycler_view);
//                recyclerView.setLayoutManager(new LinearLayoutManager(Test.this));
//                recyclerView.setHasFixedSize(true);
//                recipeAdapter = new RecipeAdapter(Test.this, recipeModelList);
//
//
//                recyclerView.setAdapter(recipeAdapter);
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//
//        }
//    }, new Response.ErrorListener() {
//        @Override
//        public void onErrorResponse(VolleyError error) {
//            Log.d("error=", error.toString());
//
//        }
//    });
//
//    RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
//    requestQueue.add(stringRequest);
//}
//
//}
//
