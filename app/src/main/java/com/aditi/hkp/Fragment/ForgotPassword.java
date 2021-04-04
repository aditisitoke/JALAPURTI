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

import com.aditi.hkp.ModelClasses.AadharVerification;
import com.aditi.hkp.R;
import com.aditi.hkp.Retrofit.ApiClient;
import com.aditi.hkp.Retrofit.ApiInterface;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by LENOVO on 22-03-2018.
 */

public class ForgotPassword extends Fragment implements View.OnClickListener {

    View view;
    EditText aadhar , otp;
    Button next,btngetotp;

    Context context;

    ApiInterface apiInterface; //API
    ProgressDialog progressDialog; //API

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);

        view = inflater.inflate(R.layout.activity_forgot_pass, container, false);

        context = getActivity();

        aadhar = view.findViewById(R.id.aadhar);

        otp = view.findViewById(R.id.otp);

        otp.setEnabled(false);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        next=view.findViewById(R.id.btnnext);
        btngetotp = view.findViewById(R.id.btngetotp);

        next.setOnClickListener(this);
        btngetotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aadhar.getText().toString().equalsIgnoreCase("")){
                    aadhar.setError("कृपया आधार न. डाले");
                }else {
                    getAdharVerification(aadhar.getText().toString());
                }
            }
        });

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnnext:
               next(v);
                break;
        }
    }

    public void next(View view){
        if (aadhar.getText().toString().equalsIgnoreCase("")) {
           aadhar.setError("कृपया आधार न. डाले");
        }
        else if (!otp.getText().toString().equalsIgnoreCase("1185")){
            Toast.makeText(context,"गलत ओ.टी.पी ",Toast.LENGTH_SHORT).show();
        }
        else {
            Bundle bundle = new Bundle();
            bundle.putString("adhar_no",aadhar.getText().toString());

            NewPassword newPassword = new NewPassword();
            newPassword.setArguments(bundle);

            ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.container,newPassword).commit();
        }
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

                            Toast.makeText(context,"Otp sent to your registered mobile no.",Toast.LENGTH_SHORT).show();

                            otp.setEnabled(true);

                        }else{
                            Toast.makeText(context,"Adhar is not valid",Toast.LENGTH_SHORT).show();
                        }
                        hideProgress();

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("Tag",throwable.getMessage());
                        hideProgress();
                        Toast.makeText(context,"Adhar is not valid",Toast.LENGTH_SHORT).show();
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

