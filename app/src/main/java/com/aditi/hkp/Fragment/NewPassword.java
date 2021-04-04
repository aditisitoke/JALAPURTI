package com.aditi.hkp.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aditi.hkp.ModelClasses.UpdatePassword;
import com.aditi.hkp.R;
import com.aditi.hkp.Retrofit.ApiClient;
import com.aditi.hkp.Retrofit.ApiInterface;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by LENOVO on 22-03-2018.
 */

public class NewPassword extends Fragment implements View.OnClickListener {
    View view;
Context context;
    EditText password, reenter;
    Button submit;

    String adhar_no;

    ApiInterface apiInterface; //API
    ProgressDialog progressDialog; //API

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        context = getActivity();
        view = inflater.inflate(R.layout.activity_reenter_pass, container, false);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        password = view.findViewById(R.id.password);

        reenter = view.findViewById(R.id.reenter);

        submit = view.findViewById(R.id.btnnext);

        submit.setOnClickListener(this);

        Bundle bundle = getArguments();

        if (bundle!=null){
            adhar_no = bundle.getString("adhar_no");
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnnext:
                submit(v);
                break;
        }
    }

    public void submit(View view) {
//        if (enter.getText().toString().equalsIgnoreCase("")) {
//
//
//        } else {
//            ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.container,new LoginPage()).addToBackStack(null).commit();
//
//        }

        if (!TextUtils.isEmpty(password.getText().toString())&&!TextUtils.isEmpty(reenter.getText().toString())){
            if(password.getText().toString().length()<8 && password.getText().toString().equalsIgnoreCase(reenter.getText().toString()) /*&&!isValidPassword(password.getText().toString())*/){
                Toast.makeText(context,"password didnt matched minimum 8 characters in password",Toast.LENGTH_SHORT).show();

            }else{
                /*((AppCompatActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.container,new LoginPage()).addToBackStack(null).commit();*/
                updatePassword(adhar_no,password.getText().toString());
            }
        }else {
            Toast.makeText(context,"Enter all field",Toast.LENGTH_SHORT).show();
        }

    }

    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

    public void updatePassword(String adhar_no,String password){

        showProgress("Loading....");
        Map<String,String> map = new HashMap<>();
        map.put("adhar_no",adhar_no);
        map.put("password",password);

        apiInterface.updatePassword(map).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UpdatePassword>() {
                    @Override
                    public void accept(UpdatePassword updatePassword) throws Exception {
                        if (updatePassword!=null) {
                            /*((AppCompatActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.container, new LoginPage()).addToBackStack(null).commit();*/
                            getFragmentManager().popBackStack();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("Tag",throwable.getMessage());
                        hideProgress();
                        throwable.printStackTrace();
                    }
                });

    }

    public void hideProgress(){
        if (progressDialog!=null&&progressDialog.isShowing())
            progressDialog.dismiss();
    }
    public void showProgress(String message){
        progressDialog = ProgressDialog.show(context,message,"Please Wait",true);
    }

}