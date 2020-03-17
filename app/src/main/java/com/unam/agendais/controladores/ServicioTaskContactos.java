package com.unam.agendais.controladores;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import com.unam.agendais.AdministrarContactos;
import com.unam.agendais.fragments.DetallesContactoFragment;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ServicioTaskContactos extends AsyncTask<Void, Void, String> {

    private Context httpContext;
    private ProgressDialog progressDialog;
    private String linkAPI, respuesta, resultadoAPI;

    public ServicioTaskContactos(Context httpContext, String linkAPI){

        this.httpContext = httpContext;
        this.linkAPI = linkAPI;

    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();
        progressDialog = ProgressDialog.show(this.httpContext, "Cargando Contactos", "Por favor, espere...");

    }

    @Override
    protected String doInBackground(Void... voids) {
        String resultado = null;

        try{

            String urlREST = this.linkAPI;
            URL url = new URL(urlREST);

            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            //PARAMETROS DE CONEXIÓN.
            httpURLConnection.setReadTimeout(15000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=utf8");

            int responseCode = httpURLConnection.getResponseCode();

            if(responseCode == HttpURLConnection.HTTP_OK){

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                StringBuffer stringBuffer = new StringBuffer("");
                String linea = "";

                while((linea = bufferedReader.readLine()) != null){

                    stringBuffer.append(linea);
                    break;

                }

                bufferedReader.close();
                resultado = stringBuffer.toString();
                this.respuesta = resultado;

            }else{

                resultado = new String("Error: " + responseCode);
                this.respuesta = null;

            }


        }catch (MalformedURLException ex){
            ex.printStackTrace();
        }catch (IOException ex){
            ex.printStackTrace();
        }

        return resultado;
    }

    @Override
    protected void onPostExecute(String s) {

        super.onPostExecute(s);
        progressDialog.dismiss();
        this.resultadoAPI = s;

        if(resultadoAPI == null){

            Toast.makeText(this.httpContext, "Error del servidor", Toast.LENGTH_LONG).show();

        }else{

            if (!resultadoAPI.equals("null")){

                try{

                    JSONObject jsonObject = new JSONObject(resultadoAPI);
                    JSONArray jsonArray = jsonObject.getJSONArray("contactos");
                    for (int i = 0; i < jsonArray.length(); i++){

                        JSONObject contactoJSON = jsonArray.getJSONObject(i);
                        AdministrarContactos.myAdapter.add(DetallesContactoFragment.getmInstance(contactoJSON.getInt("idContacto"), contactoJSON.getString("nombre"), contactoJSON.getString("apellidos"), contactoJSON.getString("numeroCelular"),
                                contactoJSON.getString("lugarComun"), contactoJSON.getString("avenida"), contactoJSON.getString("colonia"), contactoJSON.getString("estado"), contactoJSON.getString("pais"), contactoJSON.getString("comentario")));

                    }

                }catch(JSONException ex){
                    ex.printStackTrace();
                }

            }else{

                Toast.makeText(this.httpContext, "Sin contatcos.", Toast.LENGTH_LONG).show();

            }

        }

    }

}
