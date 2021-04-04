package com.aditi.hkp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aditi.hkp.R;

public class reenterPassActivity extends AppCompatActivity {
Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reenter_pass);
        next=findViewById(R.id.btnnext);
    }
    public void next(View view){
        Intent intent=new Intent(reenterPassActivity.this,FarmerLoginActivity.class);
        startActivity(intent);
    }
}
