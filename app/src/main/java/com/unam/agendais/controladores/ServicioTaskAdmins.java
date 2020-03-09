package com.unam.agendais.controladores;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import com.unam.agendais.UsuariosAdmins;
import com.unam.agendais.fragments.DetallesFragment;
import com.unam.agendais.utils.Constantes;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ServicioTaskAdmins extends AsyncTask<Void, Void, String>{

    private ProgressDialog progressDialog;
    private String resultadoAPI = "", linkAPI, respuesta = "";
    private Context httpContext;

    public ServicioTaskAdmins(Context httpContext, String linkAPI){

        this.httpContext = httpContext;
        this.linkAPI = linkAPI;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(httpContext, "Cargando Admins y Capturistas", "Por favor, espere...");
    }

    @Override
    protected String doInBackground(Void... voids) {

        String result = null;

        String linkURL = this.linkAPI;
        URL url = null;

        try{

            url = new URL(linkURL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            //DEFINIR PARAMETROS DE CONEXIÓN.
            urlConnection.setReadTimeout(15000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Content-Type", "application/json; charset=utf8");

            int responseCode = urlConnection.getResponseCode();

            if(responseCode == HttpURLConnection.HTTP_OK){

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuffer stringBuffer = new StringBuffer("");
                String linea = "";

                while((linea = bufferedReader.readLine()) != null){

                    stringBuffer.append(linea);
                    break;

                }

                bufferedReader.close();
                result = stringBuffer.toString();
                this.respuesta = result;

            }else{

                result = new String("Error" + responseCode);
                respuesta = null;

            }

        }catch (MalformedURLException ex){
            ex.printStackTrace();
        }catch (IOException ex){
            ex.printStackTrace();
        }

        return result;

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        progressDialog.dismiss();
        resultadoAPI = s;

        if(resultadoAPI == null){

            Toast.makeText(this.httpContext, "Error del servidor", Toast.LENGTH_LONG).show();

        }else{

            if(!resultadoAPI.equals("null")){

                try{

                    JSONObject jsonObject = new JSONObject(resultadoAPI);
                    JSONArray jsonArray = jsonObject.getJSONArray("usuarios");
                    for (int i = 0; i < jsonArray.length(); i++){

                        JSONObject usuarioJSON = jsonArray.getJSONObject(i);
                        int tipoINT = Integer.parseInt(String.valueOf(usuarioJSON.getInt("tipoAdmin")));
                        String tipo = "";

                        if(tipoINT == Constantes.ADMIN)
                            tipo = "Administrador";
                        else if(tipoINT == Constantes.CAPTURISTA)
                            tipo = "Capturista";

                        UsuariosAdmins.myAdapter.add(DetallesFragment.getmInstance(usuarioJSON.getInt("idAdmin"), String.valueOf(usuarioJSON.getString("nombre")), tipoINT));

                    }

                }catch(JSONException ex){
                    ex.printStackTrace();
                }

            }else{

                Toast.makeText(this.httpContext, "Sin Usuarios.",Toast.LENGTH_LONG).show();

            }

        }

    }
}
