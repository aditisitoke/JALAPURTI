package com.aditi.hkp.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.aditi.hkp.R;

/**
 * Created by LENOVO on 25-03-2018.
 */

public class Profile extends Fragment implements View.OnClickListener {

    View view;

    Button new1 , reg , feedback ;

    Context context;

    public View  onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        context = getActivity();

        view = inflater.inflate(R.layout.activity_profile, container, false);

        new1 = view.findViewById(R.id.btnnew);

        reg = view.findViewById(R.id.btnreg);

        feedback = view.findViewById(R.id.btnfeed);

        new1.setOnClickListener(this);
        reg.setOnClickListener(this);
        feedback.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnnew:
                new1(v);
                break;

            case R.id.btnreg:
                reg(v);
                break;

            case R.id.btnfeed:
                feedback(v);
                break;
        }
    }

    public void new1(View view) {
        ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.container,new StateSelection()).addToBackStack("state_selection").commit();
    }

    public void reg(View view) {
        ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.container,new RegisterKhetList()).addToBackStack(null).commit();
    }
    public void feedback(View view) {
        ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.container,new Feedback()).addToBackStack(null).commit();
    }
}
