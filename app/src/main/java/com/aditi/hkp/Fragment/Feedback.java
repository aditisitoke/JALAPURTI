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
 * Created by LENOVO on 23-03-2018.
 */

public class Feedback extends Fragment implements View.OnClickListener {

    View view;

    EditText feedback;
    Button submit;

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

        view = inflater.inflate(R.layout.activity_feedback, container, false);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        feedback = view.findViewById(R.id.feedback);

        submit = view.findViewById(R.id.submit);

        submit.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.submit:
                submit(v);
                break;
        }
    }

    public void submit(View view) {
        /*Intent intent=new Intent(FarmerLoginActivity.this,profileActivity.class);
        startActivity(intent);*/

        if (feedback.getText().toString().equalsIgnoreCase("")) {
            feedback.setError("प्रतिक्रिया डाले ");
        } else {
//            startActivity(new Intent(context, profileActivity.class));
//            getActivity().finish();
            /*((AppCompatActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.container,new Profile()).addToBackStack(null).commit();*/

            postFeedback(sharedData.getLogin_id(),feedback.getText().toString());

        }
    }

    public void postFeedback(String login_id,String message){
        showProgress("Loading....");

        Map<String,String> map = new HashMap<>();
        map.put("login_id",login_id);
        map.put("message",message);

        apiInterface.postFeedback(map).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<com.aditi.hkp.ModelClasses.Feedback>() {
                    @Override
                    public void accept(com.aditi.hkp.ModelClasses.Feedback feedback) throws Exception {
                        if (feedback!=null){
                            com.aditi.hkp.ModelClasses.Feedback.FeedbackObject object = feedback.getObject();
                            if (object!=null){
                                Toast.makeText(context,"Submitted successfully",Toast.LENGTH_SHORT).show();
                                getFragmentManager().popBackStack();
                            }
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