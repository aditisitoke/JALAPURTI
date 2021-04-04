package com.aditi.hkp.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.aditi.hkp.Adapter.KhasraListAdapter;
import com.aditi.hkp.ModelClasses.GetKhasraNumber;
import com.aditi.hkp.R;
import com.aditi.hkp.Retrofit.ApiClient;
import com.aditi.hkp.Retrofit.ApiInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by LENOVO on 23-03-2018.
 */

public class KhasraSelect extends Fragment implements View.OnClickListener {

    View view;

    Button select;

    RecyclerView recylcerview;

    Context context;
    ApiInterface apiInterface; //For API
    ProgressDialog progressDialog; //For APi

    List<GetKhasraNumber> khasraNumberList;
    String halka_no;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        context = getActivity();

        view = inflater.inflate(R.layout.field_list, container, false);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        select = view.findViewById(R.id.select);
        recylcerview = view.findViewById(R.id.recyclerView);
        recylcerview.setLayoutManager(new LinearLayoutManager(context));
        //recylcerview.setAdapter(new KhasraListAdapter(context,new ArrayList<String>()));

        khasraNumberList = new ArrayList<>();

        Bundle bundle = getArguments();
        if (bundle!=null){
            halka_no = bundle.getString("halka_no");
            getKhasraList(halka_no);
        }

        return view;

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.select:
                select(v);
                break;
        }
    }

    public void select(View view) {

        ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.container, new Questions()).addToBackStack(null).commit();

    }

    public void getKhasraList(String id){
        showProgress("Loading....");
        Map<String ,String> map = new HashMap<>();
        map.put("id",id);

        apiInterface.getkhasralist(map).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<GetKhasraNumber>>() {
                    @Override
                    public void accept(List<GetKhasraNumber> getKhasraNumbers) throws Exception {
                        khasraNumberList.clear();
                        khasraNumberList.addAll(getKhasraNumbers);
                        recylcerview.setAdapter(new KhasraListAdapter(context,khasraNumberList,halka_no));

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

