package com.example.colos.learnenglish;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class RegistroContenidoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_contenido);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.RContenido, new RegistrarContenidoFragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.segundo_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.item1:
                //Toast.makeText(this,"clic 1",Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent (this, CategoriaActivity.class);
                startActivity(intent2);
                return true;


            case R.id.item3:
                //Toast.makeText(this,"clic 2",Toast.LENGTH_SHORT).show();
                Intent intent4 = new Intent (this, RegistrarCategoriaActivity.class);
                startActivity(intent4);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
