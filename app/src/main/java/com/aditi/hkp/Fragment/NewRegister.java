package com.aditi.hkp.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aditi.hkp.Helper.CommonHelper;
import com.aditi.hkp.ModelClasses.AadharVerification;
import com.aditi.hkp.ModelClasses.PostRegistration;
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
 * Created by LENOVO on 22-03-2018.
 */

public class NewRegister extends Fragment implements View.OnClickListener {

    View view;

    Context context;
    SharedData sharedData;

    EditText adhar, name, dob, mob, add, password, reenter;
    Button submit;

    ApiInterface apiInterface; //For API

    ProgressDialog progressDialog; //For APi
    AadharVerification.AdharVerificationObject verificationObject;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        context = getActivity();
        sharedData = new SharedData(context);
        view = inflater.inflate(R.layout.activity_aadhar, container, false);

        apiInterface = ApiClient.getClient().create(ApiInterface.class); //For API

        adhar = view.findViewById(R.id.editText1);
        name = view.findViewById(R.id.editText2);
        dob = view.findViewById(R.id.editText3);
        mob = view.findViewById(R.id.editText4);
        add = view.findViewById(R.id.editText5);
        password=view.findViewById(R.id.editText6);
        reenter =view.findViewById(R.id.editText7);

        submit = view.findViewById(R.id.btnnext);

        submit.setOnClickListener(this);

        Bundle bundle = getArguments();
        if (bundle!=null){
            verificationObject = (AadharVerification.AdharVerificationObject) bundle.getSerializable("adhar_detail");
            adhar.setText(verificationObject.getAdhar_no());
            name.setText(verificationObject.getName());

            String str1 = verificationObject.getDob();
            String long_time1 = str1.substring(str1.indexOf('(')+1,str1.lastIndexOf(')'));

            dob.setText(CommonHelper.convertLongToDate(long_time1));
            mob.setText(verificationObject.getMobile_no());
            add.setText(verificationObject.getParmanent_address());
        }

        return view;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnnext:
                next(v);
                break;

        }
    }

    public void next(View view) {
        /*Intent intent=new Intent(FarmerLoginActivity.this,profileActivity.class);
        startActivity(intent);*/

        if (password.getText().toString().equalsIgnoreCase("")) {
            password.setError("enter password");
        }else if (reenter.getText().toString().equalsIgnoreCase("")){
            reenter.setError("reenter password");
        }else if (!password.getText().toString().equalsIgnoreCase(reenter.getText().toString())){
            Toast.makeText(context,"password not matched !!!!",Toast.LENGTH_SHORT).show();
        }else {
            /*((AppCompatActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.container,new LoginPage()).addToBackStack(null).commit();*/
            postRegistration(verificationObject.getAdhar_id(),password.getText().toString(),"3");
        }
    }

    //For API
//    public void Login(String category_id){
//        showProgress("Loading....");
//
//        Map<String,String> map = new HashMap<>();
//
//        map.put("category_id",category_id);
//
//        apiInterface.Login(map).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<RegistrationData>() {
//                    @Override
//                    public void accept(RegistrationData registrationData) throws Exception {
//
//                        SharedData sharedData = new SharedData(context);
//                        sharedData.setR_id(registrationData.getId());
//
//                        hideProgress();
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        Log.e("tag",throwable.getMessage());
//
//                        hideProgress();
//
//                        throwable.printStackTrace();
//                    }
//                });
//    }

    public void hideProgress(){
        if (progressDialog!=null&&progressDialog.isShowing())
            progressDialog.dismiss();
    }
    public void showProgress(String message){
        progressDialog = ProgressDialog.show(context,message,"Please Wait",true);
    }

    public void postRegistration(String adhar_id,String password,String role_id){
        showProgress("Loading....");

        Map<String,String> map = new HashMap<>();
        map.put("adhar_id",adhar_id);
        map.put("password",password);
        map.put("role_id",role_id);

        apiInterface.PostRegistration(map).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<PostRegistration>() {
                    @Override
                    public void accept(PostRegistration postRegistration) throws Exception {
                        if (postRegistration!=null){
                            Toast.makeText(context,postRegistration.getMessage(),Toast.LENGTH_SHORT).show();
                            getFragmentManager().popBackStack();
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

}