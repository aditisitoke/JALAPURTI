package com.aditi.hkp.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.aditi.hkp.R;

/**
 * Created by LENOVO on 23-03-2018.
 */

public class KhetDetail extends Fragment implements View.OnClickListener{

    View view;

    Button update, delete;

    Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        context = getActivity();

        view = inflater.inflate(R.layout.activity_registered_khetdetail, container, false);

        update = view.findViewById(R.id.update);

        delete = view.findViewById(R.id.delete);

        update.setOnClickListener(this);

        delete.setOnClickListener(this);

        return view;

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.update:
                update(v);
                break;

            case R.id.delete:
                delete(v);
                break;
        }

    }
    public void update(View view){
        ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.container,new Questions()).addToBackStack(null).commit();

    }
    public void delete(View view){
        /*((AppCompatActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.container,new RegisterKhetList()).addToBackStack(null).commit();*/
        ((AppCompatActivity)context).getSupportFragmentManager().popBackStackImmediate("khet_detail", FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}
