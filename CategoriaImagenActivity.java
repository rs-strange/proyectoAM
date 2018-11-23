package com.example.colos.learnenglish;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CategoriaImagenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_imagen);

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.informacion, new ConsultarListaUsuarioImagenFragment()).commit();
    }
}
