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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.aditi.hkp.ModelClasses.GetHalkaNumber;
import com.aditi.hkp.R;
import com.aditi.hkp.Retrofit.ApiClient;
import com.aditi.hkp.Retrofit.ApiInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by LENOVO on 23-03-2018.
 */

public class StateSelection extends Fragment implements View.OnClickListener {
    View view;
    Context context;

    Spinner halka;
    Button submit;
    Spinner state,district,tehsil,ri;

    ApiInterface apiInterface; //For API
    ProgressDialog progressDialog; //For APi

    List<GetHalkaNumber> halkaNumberList;
    List<String> halkaList;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        context = getActivity();

        view = inflater.inflate(R.layout.activity_select, container, false);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        halka = view.findViewById(R.id.halka);

        submit = view.findViewById(R.id.btnnext);

        state = view.findViewById(R.id.state);

        district = view.findViewById(R.id.district);

        tehsil = view.findViewById(R.id.tehsil);

        ri = view.findViewById(R.id.ri);

        submit.setOnClickListener(this);

        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==13){

                    district.setSelection(0);
                    tehsil.setSelection(0);
                    ri.setSelection(0);
                    halka.setSelection(0);

                    district.setEnabled(true);
                    tehsil.setEnabled(false);
                    ri.setEnabled(false);
                    halka.setEnabled(false);
                }else {

                    district.setSelection(0);
                    tehsil.setSelection(0);
                    ri.setSelection(0);
                    halka.setSelection(0);

                    district.setEnabled(false);
                    tehsil.setEnabled(false);
                    ri.setEnabled(false);
                    halka.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        state.setAdapter(ArrayAdapter.createFromResource(context,R.array.statelist,R.layout.spinner_text));

        district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==23){

                    tehsil.setSelection(0);
                    ri.setSelection(0);
                    halka.setSelection(0);

                    tehsil.setEnabled(true);
                    ri.setEnabled(false);
                    halka.setEnabled(false);
                }else {
                    tehsil.setSelection(0);
                    ri.setSelection(0);
                    halka.setSelection(0);

                    tehsil.setEnabled(false);
                    ri.setEnabled(false);
                    halka.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        district.setAdapter(ArrayAdapter.createFromResource(context,R.array.distlist,R.layout.spinner_text));

        tehsil.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==9){

                    ri.setSelection(0);
                    //halka.setSelection(0);

                    ri.setEnabled(true);
                    //halka.setEnabled(false);

                }else {
                    ri.setSelection(0);
                    //halka.setSelection(0);

                    ri.setEnabled(false);
                    //halka.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        tehsil.setAdapter(ArrayAdapter.createFromResource(context,R.array.tehsillist,R.layout.spinner_text));

        ri.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position>0){

                    halka.setSelection(0);
                    halka.setEnabled(true);

                }else {
                    halka.setSelection(0);
                    halka.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ri.setAdapter(ArrayAdapter.createFromResource(context,R.array.rilist,R.layout.spinner_text));

        halkaNumberList = new ArrayList<>();
        halkaList = new ArrayList<>();

        getHalkaNumber();

        return view;
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnnext:
                submit(v);
                break;
        }
    }
    public void submit(View view) {
        if (state.getSelectedItemPosition()<1){
            Toast.makeText(context,"select state",Toast.LENGTH_SHORT).show();
        }else if (district.getSelectedItemPosition()<1){
            Toast.makeText(context,"select district",Toast.LENGTH_SHORT).show();
        }else if (tehsil.getSelectedItemPosition()<1){
            Toast.makeText(context,"select tehsil",Toast.LENGTH_SHORT).show();
        }else if (ri.getSelectedItemPosition()<1){
            Toast.makeText(context,"select ri",Toast.LENGTH_SHORT).show();
        }/*else if (halka.getText().toString().equalsIgnoreCase("")) {
            halka.setError("हल्का न. डाले ");
        }*/else if (halka.getSelectedItemPosition()<1){
            Toast.makeText(context,"select Halka no",Toast.LENGTH_SHORT).show();
        }else {
            Bundle bundle = new Bundle();
            bundle.putString("halka_no",halkaNumberList.get(halka.getSelectedItemPosition()-1).getValue());

            KhasraSelect select = new KhasraSelect();
            select.setArguments(bundle);

            ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.container,select).addToBackStack(null).commit();
        }
    }

    public void getHalkaNumber(){
        showProgress("Loading....");

        apiInterface.gethalkano(new HashMap<String, String>()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<GetHalkaNumber>>() {
                    @Override
                    public void accept(List<GetHalkaNumber> getHalkaNumbers) throws Exception {

                        halkaNumberList.clear();

                        halkaNumberList.addAll(getHalkaNumbers);

                        halkaList.add("select Halka No");

                        for (GetHalkaNumber h : getHalkaNumbers){
                            halkaList.add(h.getText());
                        }

                        halka.setAdapter(new ArrayAdapter<String>(context,R.layout.spinner_text,halkaList));

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