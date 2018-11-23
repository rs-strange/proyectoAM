package com.example.colos.learnenglish;

import android.app.ProgressDialog;
import android.content.Context;
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
import com.example.colos.learnenglish.adapter.ContenidoAdapter;
import com.example.colos.learnenglish.adapter.UsuariosImagenAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ConsultarListaContenidoFragment extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener{


    RecyclerView recyclerUsuarios;
    ArrayList<Usuario> listaUsuarios;

    ProgressDialog dialog;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;


    public ConsultarListaContenidoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista= inflater.inflate(R.layout.fragment_consultar_lista_contenido, container, false);

        listaUsuarios=new ArrayList<>();

        recyclerUsuarios = (RecyclerView) vista.findViewById(R.id.idRecyclerContenido);
        recyclerUsuarios.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerUsuarios.setHasFixedSize(true);

        request= Volley.newRequestQueue(getContext());

        Bundle b = getArguments();
        String texto=b.getString("cate");
        //Toast.makeText(getContext(), "En fragment: "+texto,Toast.LENGTH_SHORT).show();


        cargarWebService(texto);

        return vista;
    }

    private void cargarWebService(String texto) {

        dialog=new ProgressDialog(getContext());
        dialog.setMessage("Consultando Contenido");
        dialog.show();

        //String ip=getString(R.string.ip);

        String url="http://172.31.2.98:8080/sesion/consultarListaContenido.php?nombre_categoria="+texto;
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);

        //VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(jsonObjectRequest);
    }

    @Override
    public void onResponse(JSONObject response) {
        Usuario usuario=null;

        JSONArray json=response.optJSONArray("contenido");

        try {

            for (int i=0;i<json.length();i++){
                usuario=new Usuario();
                JSONObject jsonObject=null;
                jsonObject=json.getJSONObject(i);

                usuario.setNombre(jsonObject.optString("nombre"));
                usuario.setDato(jsonObject.optString("imagen"));
                listaUsuarios.add(usuario);
            }
            dialog.hide();
            //ContenidoAdapter adapter=new ContenidoAdapter(listaUsuarios);

            ContenidoAdapter adapter= new ContenidoAdapter(listaUsuarios);

            adapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(),"nombre: "+
                            listaUsuarios.get(recyclerUsuarios.getChildAdapterPosition(v))
                                    .getNombre(),Toast.LENGTH_SHORT).show();
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