package com.aditi.hkp.Fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aditi.hkp.Activity.ProfileActivity;
import com.aditi.hkp.ModelClasses.AadharVerification;
import com.aditi.hkp.ModelClasses.Login;
import com.aditi.hkp.R;
import com.aditi.hkp.Retrofit.ApiClient;
import com.aditi.hkp.Retrofit.ApiInterface;
import com.aditi.hkp.Utils.SharedData;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by LENOVO on 21-03-2018.
 */

public class LoginPage extends Fragment implements View.OnClickListener {

    View view;

    EditText adhar, password;
    TextView forgot;
    Button submit, register;

    Context context;
    SharedData sharedData;

    ApiInterface apiInterface; //API
    ProgressDialog progressDialog; //API

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        context = getActivity();

        sharedData = new SharedData(context);

        view = inflater.inflate(R.layout.activity_farmer_login, container, false);

        apiInterface = ApiClient.getClient().create(ApiInterface.class); //API

        adhar = view.findViewById(R.id.aadhar);
        password = view.findViewById(R.id.editText6);

        forgot=view.findViewById(R.id.textView11);

        submit = view.findViewById(R.id.btnnext);

        register = view.findViewById(R.id.button3);

        submit.setOnClickListener(this);
        register.setOnClickListener(this);
        forgot.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnnext:
                next(v);
                break;

            case R.id.button3:
                register(v);
                break;

            case R.id.textView11:
                forgot(v);
                break;
        }
    }

    public void next(View view) {
        /*Intent intent=new Intent(FarmerLoginActivity.this,profileActivity.class);
        startActivity(intent);*/

        if (adhar.getText().toString().equalsIgnoreCase("")) {
            adhar.setError("कृपया आधार न. डाले ");
        }else if (password.getText().toString().equalsIgnoreCase("")) {
            adhar.setError("कृपया आधार न. डाले ");
        }else  {
            /*startActivity(new Intent(context,ProfileActivity.class));
            getActivity().finish();*/
            getLogin(adhar.getText().toString(),password.getText().toString());
        }
    }

    public void register(View view) {
//        Intent intent = new Intent(FarmerLoginActivity.this,AadharActivity.class);
//        startActivity(intent);
        /*if (adhar.getText().toString().equalsIgnoreCase("")) {


        } else {

        }*/

        showDialog();

        //((AppCompatActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.container,new NewRegister()).addToBackStack(null).commit();

    }
    public void forgot(View view){
//        Intent intent=new Intent(FarmerLoginActivity.this,forgotPassActivity.class);
//        startActivity(intent);
        /*if (adhar.getText().toString().equalsIgnoreCase("")) {


        } else {
            ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.container,new ForgotPassword()).addToBackStack(null).commit();

        }*/

        ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.container,new ForgotPassword()).commit();

    }


    public void showDialog(){
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_adhar_verified);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        final EditText adhar,otp;
        Button btnnext;

        adhar = dialog.findViewById(R.id.aadhar);
        otp = dialog.findViewById(R.id.otp);
        btnnext = dialog.findViewById(R.id.btnnext);

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(adhar.getText().toString())){
                    adhar.setError("कृपया आधार न. डाले");
                }else if (!otp.getText().toString().equalsIgnoreCase("1185")){
                    Toast.makeText(context,"गलत ओ.टी.पी ",Toast.LENGTH_SHORT).show();
                }else {
                    /*((AppCompatActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.container,new NewRegister()).addToBackStack(null).commit();*/
                    getAdharVerification(adhar.getText().toString());
                    dialog.dismiss();
                }
            }
        });

        dialog.show();
    }

    public void getLogin(String adhar_no, String password){

        showProgress("Loading...");

        Map<String, String> map = new HashMap<>();
        map.put("login_id",adhar_no);
        map.put("password",password);
        map.put("role_id","1");

        apiInterface.getLogin(map).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Login>() {
                    @Override
                    public void accept(Login login) throws Exception {
                        Login.LoginObject object = login.getObject();
                        sharedData.setLoginStatus(true);
                        sharedData.setAdhar(object.getAdhar());
                        sharedData.setAdhar_id(object.getAdhar_id());
                        sharedData.setRole_id(object.getRole_id());
                        sharedData.setLogin_id(object.getLogin_id());

                        hideProgress();
                        startActivity(new Intent(context,ProfileActivity.class));
                        getActivity().finish();
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

    public void getAdharVerification(String adhar){
        showProgress("Loading....");
        Map<String,String> map = new HashMap<>();
        map.put("adharNo",adhar);

        apiInterface.adharverification(map).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AadharVerification>() {
                    @Override
                    public void accept(AadharVerification aadharVerification) throws Exception {
                        if (aadharVerification!=null) {
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("adhar_detail", aadharVerification.getObject());
                            NewRegister fragment = new NewRegister();
                            fragment.setArguments(bundle);

                            ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).addToBackStack(null).commit();
                        }
                        hideProgress();

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