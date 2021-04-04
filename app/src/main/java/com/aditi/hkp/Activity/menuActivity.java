package com.aditi.hkp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aditi.hkp.Fragment.GramSevak;
import com.aditi.hkp.R;

public class menuActivity extends AppCompatActivity {
Button farmer , thisyojana , otheryojna , gramsevak;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        farmer=findViewById(R.id.btnfarmer);
        thisyojana=findViewById(R.id.btnyojanaye);
        otheryojna=findViewById(R.id.btnthisyojana);
        gramsevak=findViewById(R.id.btngram);

    }

    public void farmerpage(View view) {
        Intent intent = new Intent(menuActivity.this,FarmerPage.class);
        startActivity(intent);
    }

    public void ThisYojana(View view) {
        Intent intent = new Intent(menuActivity.this,ThisYojnaActivity.class);
        startActivity(intent);

    }

    public void yojanaye(View view) {

        Intent intent = new Intent(menuActivity.this,OtherYojnaListActivity.class);
        startActivity(intent);
    }

    public void GramSevak(View view) {

        Intent intent = new Intent(menuActivity.this,gramSevakLoginActivity.class);
        startActivity(intent);
    }
}
