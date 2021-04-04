package com.aditi.hkp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aditi.hkp.R;

public class FarmerLoginActivity extends AppCompatActivity {
Button next , register ;
TextView forgot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_login);
        forgot=findViewById(R.id.textView11);
        register=findViewById(R.id.button3);
        next=findViewById(R.id.btnnext);
    }
    public void register(View view) {
        Intent intent = new Intent(FarmerLoginActivity.this,AadharActivity.class);
        startActivity(intent);

    }
    public void next(View view){
        Intent intent=new Intent(FarmerLoginActivity.this,ProfileActivity.class);
        startActivity(intent);
    }
    public void forgot(View view){
        Intent intent=new Intent(FarmerLoginActivity.this,forgotPassActivity.class);
        startActivity(intent);
    }
}



