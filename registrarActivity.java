package com.example.colos.learnenglish;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class registrarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.registrarU, new RegistroFragment()).commit();
    }
}
