package com.unam.agendais.controladores;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.unam.agendais.HomeAdmin;
import com.unam.agendais.UsuariosAdmins;

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

public class ServicioTaskEliminarAdmin extends AsyncTask<Void, Void, String> {

    private ProgressDialog progressDialog;
    private Context httpContext;
    private String linkAPI, respuesta = "";
    private int idAdmin;

    public ServicioTaskEliminarAdmin(Context httpContext, String linkAPI, int idAdmin){

        this.httpContext = httpContext;
        this.linkAPI = linkAPI;
        this.idAdmin = idAdmin;

    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();
        progressDialog = ProgressDialog.show(this.httpContext, "Eliminando Usuario", "Por favor, espere...");

    }

    @Override
    protected String doInBackground(Void... voids) {

        String resultado = "";
        String urlREST = linkAPI;

        try{

            URL url = new URL(urlREST);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            JSONObject jsonDatos = new JSONObject();
            jsonDatos.put("idAdmin", this.idAdmin);

            urlConnection.setConnectTimeout(15000);
            urlConnection.setReadTimeout(15000);
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);

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

                while((linea = bufferedReader.readLine()) != null){

                    stringBuffer.append(linea);
                    break;

                }

                bufferedReader.close();
                resultado = stringBuffer.toString();

            }else{

                resultado = new String("Error:" + responseCode);

            }

        }catch(MalformedURLException ex){
            ex.printStackTrace();
        }catch (IOException ex){
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
        Toast.makeText(this.httpContext, respuesta, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this.httpContext, UsuariosAdmins.class);
        this.httpContext.startActivity(intent);

    }

    public String getPostDataString(JSONObject params) throws Exception {

        return params.toString();

    }

}
