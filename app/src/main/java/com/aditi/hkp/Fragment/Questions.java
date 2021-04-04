package com.aditi.hkp.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.aditi.hkp.ModelClasses.AadharVerification;
import com.aditi.hkp.ModelClasses.GetAdharDetails;
import com.aditi.hkp.ModelClasses.KishanRequest;
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

public class Questions extends Fragment implements OnClickListener {

    View view;

    SharedData sharedData;

    Button register;
    Spinner soil;
    EditText tv,sp1,sp2,sp3,ed,ed1;
    CheckBox gehu, chana, makka, sarso, chaval, bajra, river, borwell, pond, neher, baodi;
    RadioButton yes, no;

    Context context;

    boolean isFromKhetdetail = false;
    ApiInterface apiInterface; //API
    ProgressDialog progressDialog; //API

    String halka_no,khasra_id;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        context = getActivity();
        sharedData = new SharedData(context);
        view = inflater.inflate(R.layout.activity_question, container, false);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        tv = view.findViewById(R.id.tv);
        sp1 = view.findViewById(R.id.sp1);
        sp2 = view.findViewById(R.id.sp2);
        sp3 = view.findViewById(R.id.sp3);
        ed = view.findViewById(R.id.ed);
        ed1 = view.findViewById(R.id.ed1);

        soil = view.findViewById(R.id.sp);

        register = view.findViewById(R.id.btnnext);

        yes = view.findViewById(R.id.radioButton);

        no = view.findViewById(R.id.radioButton2);

        gehu = view.findViewById(R.id.checkBox);

        chana = view.findViewById(R.id.checkBox2);

        makka = view.findViewById(R.id.checkBox3);

        sarso = view.findViewById(R.id.checkBox4);

        chaval = view.findViewById(R.id.checkBox5);

        bajra = view.findViewById(R.id.checkBox6);

        river = view.findViewById(R.id.checkBox7);

        borwell = view.findViewById(R.id.checkBox8);

        pond = view.findViewById(R.id.checkBox9);

        neher = view.findViewById(R.id.checkBox10);

        baodi = view.findViewById(R.id.checkBox11);

        register.setOnClickListener(this);

        Bundle bundle = getArguments();

        if (bundle!=null){
            if (bundle.containsKey("from_khet")) {
                isFromKhetdetail = bundle.getBoolean("from_khet");
            }

            halka_no = bundle.getString("halka_no");
            khasra_id = bundle.getString("khasra_no");
            sp3.setText(halka_no);
            ed.setText(khasra_id);
            ed1.setText("मध्य प्रदेश");

            getAdharDetails(sharedData.getAdhar_id());

        }

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

    public void next(View view) {
        /*((AppCompatActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.container, new RegisterKhetList()).addToBackStack(null).commit();*/

        kishanRequest(sharedData.getAdhar_id(),khasra_id,halka_no,(String) soil.getSelectedItem(),"","","",""
        ,"");

        /*if (isFromKhetdetail)
            ((AppCompatActivity)context).getSupportFragmentManager().popBackStackImmediate("khet_detail", FragmentManager.POP_BACK_STACK_INCLUSIVE);
        else
            ((AppCompatActivity)context).getSupportFragmentManager().popBackStackImmediate("state_selection", FragmentManager.POP_BACK_STACK_INCLUSIVE);*/

    }

    public void getAdharDetails(String adhar_id){
        showProgress("Loading...");

        Map<String,String> map = new HashMap<>();
        map.put("adhar_id",adhar_id);

        apiInterface.GetAdharDetails(map).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GetAdharDetails>() {
                    @Override
                    public void accept(GetAdharDetails getAdharDetails) throws Exception {

                        GetAdharDetails.GetAdharObject object = getAdharDetails.getObject();
                        tv.setText(object.getName());
                        sp1.setText(object.getFather_name());
                        sp2.setText(object.getParmanent_address());
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

    public void kishanRequest(String adhar_id, String khasra_id, String halka_id, String soil_type, String water_resourse, String crops, String montly_availability, String max_require_month, String water_avalibility){
        showProgress("Loading...");

        Map<String,String> map = new HashMap<>();
        map.put("adhar_id",adhar_id);
        map.put("khasra_id",khasra_id);
        map.put("halka_id",halka_id);
        map.put("soil_type",soil_type);
        map.put("water_resourse",water_resourse);
        map.put("crops",crops);
        map.put("montly_availability",montly_availability);
        map.put("max_require_month",max_require_month);
        map.put("water_avalibility",water_avalibility);


        apiInterface.KisanRequest(map).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<KishanRequest>() {
                    @Override
                    public void accept(KishanRequest kishanRequest) throws Exception {
                        hideProgress();
                        if (isFromKhetdetail)
                            ((AppCompatActivity)context).getSupportFragmentManager().popBackStackImmediate("khet_detail", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                        else
                            ((AppCompatActivity)context).getSupportFragmentManager().popBackStackImmediate("state_selection", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

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

