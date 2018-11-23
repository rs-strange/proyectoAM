package com.example.colos.learnenglish;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class CategoriaIFragment extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener{


        EditText campoDocumento;
        TextView txtNombre,txtProfesion;
        Button btnConsultarUsuario;
        ProgressDialog progreso;
        ImageView campoImagen;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View vista=inflater.inflate(R.layout.fragment_categoria_i, container, false);

        campoDocumento= (EditText) vista.findViewById(R.id.campoid);
        txtNombre= (TextView) vista.findViewById(R.id.txtnombre);
        txtProfesion= (TextView) vista.findViewById(R.id.txtprofesion);
        btnConsultarUsuario= (Button) vista.findViewById(R.id.consultarI);
        campoImagen=(ImageView) vista.findViewById(R.id.imagenid);


        // request= Volley.newRequestQueue(getContext());

        btnConsultarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarWebService();
            }
        });


        return vista;


    }

    private void cargarWebService() {

        progreso=new ProgressDialog(getContext());
        progreso.setMessage("Consultando...");
        progreso.show();

        String url="http://172.31.2.98:8080/sesion/consultarimagen.php?id_categoria="+campoDocumento.getText().toString();

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        // request.add(jsonObjectRequest);
        VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

        progreso.hide();
        Toast.makeText(getContext(),"No se pudo Consultar "+error.toString(),Toast.LENGTH_SHORT).show();
        Log.i("ERROR",error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        progreso.hide();

        //    Toast.makeText(getContext(),"Mensaje: "+response,Toast.LENGTH_SHORT).show();

        Usuario miUsuario=new Usuario();
        JSONArray json=response.optJSONArray("usuario");
        JSONObject jsonObject=null;

        try {
            jsonObject=json.getJSONObject(0);
            miUsuario.setNombre(jsonObject.optString("nombre"));
            miUsuario.setProfesion(jsonObject.optString("profesion"));
            miUsuario.setDato(jsonObject.optString("imagen"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        txtNombre.setText("Nombre :"+miUsuario.getNombre());
        txtProfesion.setText("Profesion :"+miUsuario.getProfesion());

        if (miUsuario.getImagen()!=null){
            campoImagen.setImageBitmap(miUsuario.getImagen());
        }else{
            campoImagen.setImageResource(R.drawable.ic_camera_b);
        }
    }
}
