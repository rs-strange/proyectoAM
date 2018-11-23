package com.example.colos.learnenglish;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.colos.learnenglish.adapter.UsuariosImagenAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ConsultarListaUsuarioImagenFragment extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener{


    RecyclerView recyclerUsuarios;
    ArrayList<Usuario> listaUsuarios;

    ProgressDialog dialog;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;



    public ConsultarListaUsuarioImagenFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista= inflater.inflate(R.layout.fragment_consultar_lista_usuario_imagen, container, false);;

        listaUsuarios=new ArrayList<>();

        recyclerUsuarios = (RecyclerView) vista.findViewById(R.id.idRecyclerImagen);
        recyclerUsuarios.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerUsuarios.setHasFixedSize(true);

        request= Volley.newRequestQueue(getContext());

        cargarWebService();

        return vista;
    }

    private void cargarWebService() {

        dialog=new ProgressDialog(getContext());
        dialog.setMessage("Consultando Imagenes");
        dialog.show();

        //String ip=getString(R.string.ip);

        String url="http://172.31.2.98:8080/sesion/consultarListaImagenes.php";
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);

        //VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(jsonObjectRequest);
    }

    @Override
    public void onResponse(JSONObject response) {
        Usuario usuario=null;

        JSONArray json=response.optJSONArray("usuario");

        try {

            for (int i=0;i<json.length();i++){
                usuario=new Usuario();
                JSONObject jsonObject=null;
                jsonObject=json.getJSONObject(i);

                usuario.setId(jsonObject.optInt("id_categoria"));
                usuario.setNombre(jsonObject.optString("nombre"));
                usuario.setProfesion(jsonObject.optString("profesion"));
                usuario.setDato(jsonObject.optString("imagen"));
                listaUsuarios.add(usuario);
            }
            dialog.hide();
            UsuariosImagenAdapter adapter=new UsuariosImagenAdapter(listaUsuarios);

            adapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(),"contenido de categoria "+
                            listaUsuarios.get(recyclerUsuarios.getChildAdapterPosition(v)).getNombre()
                            ,Toast.LENGTH_SHORT).show();

                    String texto=listaUsuarios.get(recyclerUsuarios.getChildAdapterPosition(v)).getNombre();

                    Intent intent=new Intent(getContext(),ContenidoActivity.class);
                    intent.putExtra("CategoriaLista",texto);
                    startActivity(intent);
                }
            });

            recyclerUsuarios.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "No se ha podido establecer conexión con el servidor" +
                    " "+response, Toast.LENGTH_LONG).show();
            dialog.hide();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

}