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

public class ServicioTaskRegistro extends AsyncTask<Void, Void, String> {

    private Context httpContext;
    private String linkrequestAPI, nombre, contrasena, resultadoAPI = "";
    private int tipoAdmin;
    private ProgressDialog progressDialog;

    public ServicioTaskRegistro(Context contexto, String linkAPI, String nombre, String contrasena, int tipoAdmin){

        this.httpContext = contexto;
        this.linkrequestAPI = linkAPI;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.tipoAdmin = tipoAdmin;

    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();
        progressDialog = ProgressDialog.show(httpContext, "Registrando Usuario.", "Por favor, espere...");

    }

    @Override
    protected String doInBackground(Void... params) {

        String result = null;

        String wsURL = this.linkrequestAPI;
        URL url = null;
        try {

            url = new URL(wsURL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            JSONObject parametrosPost= new JSONObject();
            parametrosPost.put("nombre", this.nombre);
            parametrosPost.put("contrasena", this.contrasena);
            parametrosPost.put("tipoAdmin", this.tipoAdmin);

            //DEFINIR PARAMETROS DE CONEXION
            urlConnection.setReadTimeout(15000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json; charset=utf8");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);


            //OBTENER EL RESULTADO DEL REQUEST
            OutputStream outputStream = urlConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            writer.write(getPostDataString(parametrosPost));
            writer.flush();
            writer.close();
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
                result = stringBuffer.toString();

            }
            else{

                result = new String("Error: "+ responseCode);

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  result;

    }

    @Override
    protected void onPostExecute(String respuesta) {

        super.onPostExecute(respuesta);
        progressDialog.dismiss();
        resultadoAPI = respuesta;
        Toast.makeText(httpContext, resultadoAPI, Toast.LENGTH_LONG).show();

    }

    public String getPostDataString(JSONObject params) throws Exception {

        return params.toString();

    }

}
