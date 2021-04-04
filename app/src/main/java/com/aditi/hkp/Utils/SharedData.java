package com.aditi.hkp.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by HP on 3/22/2018.
 */

public class SharedData {

    Context context;
    public static final String SharedName = "telemedicine_shared";
    public static final String LoginStatus = "login_status";
    public static final String Adhar = "adhar_no";
    public static final String Adhar_id = "adhar_id";
    public static final String Role_id = "role_id";
    public static final String Login_id = "login_id";

    SharedPreferences preferences;

    public SharedData(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(SharedName,Context.MODE_PRIVATE);
    }

    public boolean getLoginStatus(){
        return preferences.getBoolean(LoginStatus,false);
    }

    public void setLoginStatus(boolean status){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(LoginStatus,status);
        editor.commit();
    }

    public void setAdhar(String adhar){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Adhar,adhar);
        editor.commit();
    }

    public String getAdhar(){
        return preferences.getString(Adhar,"");
    }

    public void setAdhar_id(String adhar){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Adhar_id,adhar);
        editor.commit();
    }

    public String getAdhar_id(){
        return preferences.getString(Adhar_id,"");
    }

    public void setRole_id(String adhar){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Role_id,adhar);
        editor.commit();
    }

    public String getRole_id(){
        return preferences.getString(Role_id,"");
    }

    public void setLogin_id(String login_id){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Login_id,login_id);
        editor.commit();
    }

    public String getLogin_id(){
        return preferences.getString(Login_id,"");
    }

}
