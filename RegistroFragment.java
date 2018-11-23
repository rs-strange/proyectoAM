package com.example.colos.learnenglish;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class RegistroFragment extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener{

    private RequestQueue rq;
    JsonRequest jrq;
    EditText txtNombre;
    EditText txtApellidoP;
    EditText txtApellidoM;
    EditText txtCorreo;
    EditText txtPass;

    Button registrar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_sesion, container, false);

        View vista=inflater.inflate(R.layout.fragment_registro, container, false);
        txtNombre=(EditText) vista.findViewById(R.id.txtnombre);
        txtApellidoP=(EditText) vista.findViewById(R.id.txtapellidoP);
        txtApellidoM=(EditText) vista.findViewById(R.id.txtapellidoM);
        txtCorreo=(EditText) vista.findViewById(R.id.txtcorreo);
        txtPass=(EditText) vista.findViewById(R.id.txtpass);

        registrar=(Button) vista.findViewById(R.id.registrar);
        rq = Volley.newRequestQueue(getContext());

        registrar.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             registrarUsuario();
                                         }
                                     }
        );
        return vista;
    }

    @Override
    public void onErrorResponse(VolleyError error)
    {
        Toast.makeText(getContext(), "no se registro el usuario :"+error.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response)
    {
        Toast.makeText(getContext(), "se registro correctamente "+txtCorreo.getText().toString(), Toast.LENGTH_SHORT).show();

        limpiar();

        Intent intent=new Intent(getContext(),CategoriaActivity.class);
        startActivity(intent);
    }

    void limpiar()
    {
        txtNombre.setText("");
        txtApellidoP.setText("");
        txtApellidoM.setText("");
        txtCorreo.setText("");
        txtPass.setText("");
    }
    private void registrarUsuario()
    {
        //local
        //String url="http://192.168.1.71:8080/sesion/sesion.php?correo="+txtCorreo.getText().toString()+"&pass="+txtPass.getText().toString();
                    //https://pruebaservicioweb.000webhostapp.com
       String url="http://172.31.2.98:8080/sesion/registrar.php?nombre="
               +txtNombre.getText().toString()
               +"&apellidoP="+txtApellidoP.getText().toString()
               +"&apellidoM="+txtApellidoM.getText().toString()
               +"&correo="+txtCorreo.getText().toString()
               +"&pass="+txtPass.getText().toString();

        jrq= new JsonObjectRequest(Request.Method.GET, url, null,this,this);
        rq.add(jrq);

    }
}
