package com.unam.agendais.controladores;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.widget.Toast;

import com.unam.agendais.HomeAdmin;
import com.unam.agendais.HomeCapturista;
import com.unam.agendais.utils.Constantes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ServicioTaskLogin extends AsyncTask<Void, Void, String> {

    private Context httpContext;
    ProgressDialog progressDialog;
    public String resultadoapi = "", linkrequestAPI, respuesta = "", contra, nombre;
    public int idAdmin, tipoAdmin;

    public ServicioTaskLogin(Context contexto, String linkAPI, String nombre, String contrasena){

        this.httpContext = contexto;
        this.nombre = nombre;
        this.contra = contrasena;
        this.linkrequestAPI = linkAPI + "/" + nombre + "/" + contrasena;

    }
    @Override
    protected void onPreExecute() {

        super.onPreExecute();
        progressDialog = ProgressDialog.show(httpContext, "Iniciando Sesión.", "Por favor, espere...");

    }

    @Override
    protected String doInBackground(Void... params) {

        String result= null;

        String wsURL = linkrequestAPI;
        URL url = null;
        try {

            url = new URL(wsURL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            //DEFINIR PARAMETROS DE CONEXION
            urlConnection.setReadTimeout(15000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Content-Type", "application/json; charset=utf8");

            int responseCode=urlConnection.getResponseCode();
            if(responseCode== HttpURLConnection.HTTP_OK){

                InputStream responseBody = urlConnection.getInputStream();
                InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
                JsonReader jsonReader = new JsonReader(responseBodyReader);
                jsonReader.beginObject();

                while (jsonReader.hasNext()) {

                    String key = jsonReader.nextName();

                    if(key.equals("nombre")) {

                        this.nombre = jsonReader.nextString();

                    }else if(key.equals("idAdmin")){

                        this.idAdmin = jsonReader.nextInt();

                    }else if(key.equals("tipoAdmin")){

                        this.tipoAdmin = jsonReader.nextInt();

                    }else {

                        jsonReader.skipValue();

                    }

                }

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                StringBuffer stringBuffer = new StringBuffer("");
                String linea = "";

                while ((linea = bufferedReader.readLine())!= null){

                    stringBuffer.append(linea);
                    break;

                }

                bufferedReader.close();
                result = stringBuffer.toString();
                respuesta = result;


            }
            else{

                result= new String("Error: "+ responseCode);
                respuesta = null;

            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  result;

    }

    @Override
    protected void onPostExecute(String s) {

        super.onPostExecute(s);
        progressDialog.dismiss();
        resultadoapi=s;

        if(resultadoapi == null){

            Toast.makeText(httpContext, "Error del servidor.", Toast.LENGTH_LONG).show();

        }else {

            if(!resultadoapi.equals("null")){

                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(httpContext, "sesion", null, 1);
                SQLiteDatabase baseDeDatos = admin.getWritableDatabase();
                ContentValues registro = new ContentValues();
                registro.put("id", "1");
                registro.put("idAdmin", this.idAdmin);
                registro.put("nombre", this.nombre);
                registro.put("tipoAdmin" , this.tipoAdmin);
                baseDeDatos.insert("sesion", null, registro);
                baseDeDatos.close();

                Intent intent;

                if(this.tipoAdmin == Constantes.ADMIN){

                    intent = new Intent(httpContext, HomeAdmin.class);
                    httpContext.startActivity(intent);

                }else if(this.tipoAdmin == Constantes.CAPTURISTA){

                    intent = new Intent(httpContext, HomeCapturista.class);
                    httpContext.startActivity(intent);

                }else{

                    Toast.makeText(httpContext, "Error inesperado." + tipoAdmin, Toast.LENGTH_LONG).show();

                }

            }else{

                Toast.makeText(httpContext, "Usuario no registrado.",Toast.LENGTH_LONG).show();

            }

        }

    }

}
