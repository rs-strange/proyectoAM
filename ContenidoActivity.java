package com.example.colos.learnenglish;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class ContenidoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenido);

        FragmentManager manager=getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction t=manager.beginTransaction();
        ConsultarListaContenidoFragment m=new ConsultarListaContenidoFragment();

        Bundle extras = getIntent().getExtras();
        String Texto=extras.getString("CategoriaLista");

        Bundle bu1= new Bundle();
        bu1.putString("cate",Texto);
        m.setArguments(bu1);
        t.add(R.id.conte,m);
        t.commit();
    }
}
