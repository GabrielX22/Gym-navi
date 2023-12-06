package com.example.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class manuales extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manuales);
    }

    public void volvi(View view) {
        finish();
    }

    public void opifa(View view) {
        Intent intent = new Intent(this, faqs.class);
        startActivity(intent);
    }

    public void opifolo(View view) {
        Intent intent = new Intent(this, manuteco.class);
        startActivity(intent);
    }
}