package ellere.cooksmart;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * Created by swikriti on 11/1/2019.
 */

public class InternetCheck extends AppCompatActivity{
    private  boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


//    private Activity activity;
//    private InternetCheckListener listener;
//
//    public InternetCheck(Activity x){
//
//        activity= x;
//
//    }
//
//    @Override
//    protected Void doInBackground(Void... params) {
//
//
//        boolean b = hasInternetAccess();
//        listener.onComplete(b);
//
//        return null;
//    }
//
//
//    public void isInternetConnectionAvailable(InternetCheckListener x){
//        listener=x;
//        execute();
//    }
//
//    private boolean isNetworkAvailable() {
//        ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService(CONNECTIVITY_SERVICE);
//        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
//        return activeNetworkInfo != null;
//    }
//    private boolean hasInternetAccess() {
//        if (isNetworkAvailable()) {
//            try {
//                HttpURLConnection urlc = (HttpURLConnection) (new URL("http://clients3.google.com/generate_204").openConnection());
//                urlc.setRequestProperty("User-Agent", "Android");
//                urlc.setRequestProperty("Connection", "close");
//                urlc.setConnectTimeout(1500);
//                urlc.connect();
//                return (urlc.getResponseCode() == 204 &&
//                        urlc.getContentLength() == 0);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else {
//            Log.d("TAG", "No network available!");
//            Toast.makeText(activity,"No network available! ",Toast.LENGTH_SHORT).show();
//        }
//        return false;
//    }
//
//    public interface InternetCheckListener{
//        void onComplete(boolean connected);
//    }

}
