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

public class SesionFragment extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener{

    private RequestQueue rq;
    JsonRequest jrq;
    EditText txtCorreo;
    EditText txtPass;
    Button consultar;
    Button registrarse;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_sesion, container, false);

        View vista=inflater.inflate(R.layout.fragment_sesion, container, false);
        txtCorreo=(EditText) vista.findViewById(R.id.txtcorreo);
        txtPass=(EditText) vista.findViewById(R.id.txtpass);
        consultar=(Button) vista.findViewById(R.id.consultar);
        registrarse=(Button) vista.findViewById(R.id.registrarse);

        rq = Volley.newRequestQueue(getContext());

        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarsesion();
            }
        }
        );

        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),registrarActivity.class);
                startActivity(intent);
            }
        }
        );

        return vista;
    }

    @Override
    public void onErrorResponse(VolleyError error)
    {
        Toast.makeText(getContext(), "no se encontraron los datos :"+error.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response)
    {
        Toast.makeText(getContext(), "se encontraron los datos "+txtCorreo.getText().toString(), Toast.LENGTH_SHORT).show();
        Usuario usuario= new Usuario();
        JSONArray jsonArray =response.optJSONArray("datos");
        JSONObject jsonObject=null;

        try
        {
            jsonObject =jsonArray.getJSONObject(0);
            usuario.setCorreo(jsonObject.optString("correo"));
            usuario.setPass(jsonObject.optString("pass"));
            usuario.setNombre(jsonObject.optString("nombre"));
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        if (usuario.getCorreo().equals("rsymor123@gmail.com"))
        {
            Toast.makeText(getContext(), "correo de admi "+txtCorreo.getText().toString(), Toast.LENGTH_SHORT).show();

            Intent intent=new Intent(getContext(),RegistrarCategoriaActivity.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(getContext(), "correo de usuario "+txtCorreo.getText().toString(), Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(getContext(),CategoriaActivity.class);
            startActivity(intent);
        }

    }
    private void iniciarsesion()
    {
        //local
        //String url="http://172.31.2.98:8080/sesion/sesion.php?correo="+txtCorreo.getText().toString()+"&pass="+txtPass.getText().toString();

        String url="http://172.31.2.98:8080/sesion/sesion.php?correo="
                +txtCorreo.getText().toString()+"&pass="+txtPass.getText().toString();

        jrq= new JsonObjectRequest(Request.Method.GET, url, null,this,this);
        rq.add(jrq);
    }
}
