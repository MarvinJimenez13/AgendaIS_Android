package com.unam.agendais.controladores;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ServicioTaskActualizarAdmin extends AsyncTask<Void, Void, String> {

    private ProgressDialog progressDialog;
    private Context httContext;
    private String linkAPI, nombre, contrasena, respuesta = "";
    private int idAdmin, tipoAdmin;

    public ServicioTaskActualizarAdmin(Context httContext, String linkAPI, int idAdmin, String nombre, int tipoAdmin, String contrasena){

        this.httContext = httContext;
        this.linkAPI = linkAPI;
        this.idAdmin = idAdmin;
        this.nombre = nombre;
        this.tipoAdmin = tipoAdmin;
        this.contrasena = contrasena;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(this.httContext, "Actualizando", "Por favor, espere...");
    }

    @Override
    protected String doInBackground(Void... voids) {

        String resultado = "";

        String urlREST = this.linkAPI;

        try{

            URL url = new URL(urlREST);
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();

            JSONObject jsonDatos = new JSONObject();
            jsonDatos.put("idAdmin", this.idAdmin);
            jsonDatos.put("nombre", this.nombre);
            jsonDatos.put("tipoAdmin", this.tipoAdmin);
            if(contrasena != null)
                jsonDatos.put("contrasena", this.contrasena);

            //DEFINIR PARAMETROS DE CONEXIPON.
            urlConnection.setReadTimeout(15000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("PUT");
            urlConnection.setRequestProperty("Content-Type", "application/json; utf=8");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);

            OutputStream outputStream = urlConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            bufferedWriter.write(getPostDataString(jsonDatos));
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            int responseCode = urlConnection.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuffer stringBuffer = new StringBuffer("");
                String linea = "";

                while ((linea = bufferedReader.readLine()) != null){

                    stringBuffer.append(linea);
                    break;

                }

                bufferedReader.close();
                resultado = stringBuffer.toString();

            }else{

                resultado = new String("Error" + responseCode);

            }

        }catch(MalformedURLException ex){
            ex.printStackTrace();
        }catch(IOException ex){
            ex.printStackTrace();
        }catch (JSONException ex){
            ex.printStackTrace();
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return resultado;

    }

    @Override
    protected void onPostExecute(String s) {

        super.onPostExecute(s);
        progressDialog.dismiss();
        respuesta = s;
        Toast.makeText(this.httContext, respuesta, Toast.LENGTH_LONG).show();

    }

    public String getPostDataString(JSONObject params) throws Exception {

        return params.toString();

    }

}
