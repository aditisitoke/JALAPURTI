package com.aditi.hkp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.aditi.hkp.R;

public class ThisYojnaActivity extends AppCompatActivity {
    TextView what, aim, achieve, work, expense, profit, vidhayak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_this_yojna);
        what = findViewById(R.id.tvwhat);
        aim = findViewById(R.id.tvuddesh);
        achieve = findViewById(R.id.tvlaksh);
        work = findViewById(R.id.tvkarya);
        expense = findViewById(R.id.tvkharch);
        profit = findViewById(R.id.tvfayada);
        vidhayak = findViewById(R.id.tvbhumika);
    }

    public void tvwhat(View view) {
        Intent intent = new Intent(ThisYojnaActivity.this, WhatYojnaActivity.class);
        startActivity(intent);
    }

    public void tvuddesh(View view) {
        Intent intent = new Intent(ThisYojnaActivity.this, staticAimActivity.class);
        startActivity(intent);
    }

    public void tvlaksh(View view) {
        Intent intent = new Intent(ThisYojnaActivity.this, yojnaAchieveActivity.class);
        startActivity(intent);
    }

    public void tvkarya(View view) {
        Intent intent = new Intent(ThisYojnaActivity.this, staticWorkActivity.class);
        startActivity(intent);
    }

    public void tvkharch(View view) {
        Intent intent = new Intent(ThisYojnaActivity.this, staticExpenseActivity.class);
        startActivity(intent);
    }

    public void tvfayada(View view) {
        Intent intent = new Intent(ThisYojnaActivity.this, staticProfitActivity.class);
        startActivity(intent);
    }

    public void tvbhumika(View view) {
        Intent intent = new Intent(ThisYojnaActivity.this, staticVidhayakActivity.class);
        startActivity(intent);
    }
}