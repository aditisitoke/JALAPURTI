package com.aditi.hkp.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aditi.hkp.Activity.ProfileActivity;
import com.aditi.hkp.R;

/**
 * Created by LENOVO on 25-03-2018.
 */

public class GramSevak extends Fragment implements View.OnClickListener {

    View view;

    EditText halka, password;
    Button next;
    Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        context = getActivity();

        view = inflater.inflate(R.layout.activity_gram_sevak_login, container, false);

        halka = view.findViewById(R.id.halka);

        password = view.findViewById(R.id.editText6);

        next = view.findViewById(R.id.btnnext);

        next.setOnClickListener(this);

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
        /*Intent intent=new Intent(FarmerLoginActivity.this,profileActivity.class);
        startActivity(intent);*/

        if (halka.getText().toString().equalsIgnoreCase("")) {
            halka.setError("कृपया हल्का न. डाले ");
        }
        else if (!password.getText().toString().equalsIgnoreCase("aditi")){
            Toast.makeText(context,"गलत पासवर्ड  ",Toast.LENGTH_SHORT).show();
        }
        else {
            ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.container, new VerifiedKhet()).addToBackStack(null).commit();
        }
    }



}