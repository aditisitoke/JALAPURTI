package com.aditi.hkp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aditi.hkp.R;

public class forgotPassActivity extends AppCompatActivity {
Button reenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        reenter=findViewById(R.id.btnnext);
    }
    public void reenter(View view){
        Intent intent=new Intent(forgotPassActivity.this,reenterPassActivity.class);
        startActivity(intent);
    }
}
