//package ellere.cooksmart;
//
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//
//import java.util.HashMap;
//
///**
// * Created by swikriti on 9/4/2019.
// */
//
//
//    public class SessionManager {
//
//        SharedPreferences sharedPreferences;
//        public SharedPreferences.Editor editor;
//        public Context context;
//        int PRIVATE_MODE = 0;
//
//        private static final String PREF_NAME = "LOGIN";
//        private static final String LOGIN = "IS_LOGIN";
//        public static final String NAME = "NAME";
//        public static final String PASSWORD = "PASSWORD";
//        //public static final String ID = "ID";
//
//        public SessionManager(Context context) {
//            this.context = context;
//            sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
//            editor = sharedPreferences.edit();
//        }
//
//        public void createSession(String name, String password){
//
//            editor.putBoolean(LOGIN, true);
//            editor.putString(NAME, name);
//            editor.putString(PASSWORD, password);
//            //editor.putString(ID, id);
//            editor.apply();
//
//        }
//
//        public boolean isLoggin(){
//            return sharedPreferences.getBoolean(LOGIN, false);
//        }
//
////        public void checkLogin(){
////
////            if (!this.isLoggin()){
////                Intent i = new Intent(context, Login.class);
////                context.startActivity(i);
////                ((HomePage) context).finish();
////            }
////        }
////
////        public HashMap<String, String> getUserDetail(){
////
////            HashMap<String, String> user = new HashMap<>();
////            user.put(NAME, sharedPreferences.getString(NAME, null));
////            user.put(PASSWORD, sharedPreferences.getString(PASSWORD, null));
////            //user.put(ID, sharedPreferences.getString(ID, null));
////
////            return user;
////        }
////
////        public void logout(){
////
////            editor.clear();
////            editor.commit();
////            Intent i = new Intent(context, Login.class);
////            context.startActivity(i);
////            ((HomePage) context).finish();
////
////        }
//
//    }
//
