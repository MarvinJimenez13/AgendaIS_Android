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
import java.util.Calendar;

public class ServicioTaskRegistroContacto extends AsyncTask<Void, Void, String>{

    private String linkAPI;
    private Context httpContext;
    private String nombre, apellidos, telefonoCelular, lugarComun, avenida, colonia, estado, pais, comentarios, resultadoAPI = "";
    private int idAdmin;
    private ProgressDialog progressDialog;

    public ServicioTaskRegistroContacto(Context httpContext, String linkAPI, int idAdmin, String nombre, String apellidos, String telefonoCelular, String lugarComun,
                                        String avenida, String colonia, String estado, String pais, String comentarios){

        this.httpContext = httpContext;
        this.linkAPI = linkAPI;
        this.idAdmin = idAdmin;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefonoCelular = telefonoCelular;
        this.lugarComun = lugarComun;
        this.avenida = avenida;
        this.colonia = colonia;
        this.estado = estado;
        this.pais = pais;
        this.comentarios = comentarios;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(httpContext, "Registrando Contacto", "Por favor, espere...");
    }

    @Override
    protected String doInBackground(Void... voids) {
       String resultado = null;

       String registroURL = this.linkAPI;

       try{

           URL url = new URL(registroURL);
           HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

           Calendar fechaC = Calendar.getInstance();
           int año = fechaC.get(Calendar.YEAR);
           int mes = fechaC.get(Calendar.MONTH);
           int dia = fechaC.get(Calendar.DAY_OF_MONTH);
           String fecha = año + "-" + (mes+1)  + "-" + dia;
           System.out.println(fecha);
           java.sql.Date fechaRegistro = java.sql.Date.valueOf(fecha);

           JSONObject jsonRegistro = new JSONObject();
           jsonRegistro.put("idAdmin", this.idAdmin);
           jsonRegistro.put("nombre", this.nombre);
           jsonRegistro.put("apellidos", this.apellidos);
           jsonRegistro.put("numeroCelular", this.telefonoCelular);
           jsonRegistro.put("lugarComun", this.lugarComun);
           jsonRegistro.put("avenida", this.avenida);
           jsonRegistro.put("colonia", this.colonia);
           jsonRegistro.put("estado", this.estado);
           jsonRegistro.put("pais", this.pais);
           jsonRegistro.put("comentarios", this.comentarios);
           jsonRegistro.put("fechaRegistro", fechaRegistro);

           //DEFINIR PARAMETROS DE CONEXIÓN.
           urlConnection.setReadTimeout(15000);
           urlConnection.setConnectTimeout(15000);
           urlConnection.setRequestMethod("POST");
           urlConnection.setRequestProperty("Content-Type", "application/json; charset=utf8");
           urlConnection.setDoInput(true);
           urlConnection.setDoOutput(true);

           OutputStream outputStream = urlConnection.getOutputStream();
           BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
           bufferedWriter.write(getPostDataString(jsonRegistro));
           bufferedWriter.close();
           outputStream.close();

           int responseCode = urlConnection.getResponseCode();
           if(responseCode == HttpURLConnection.HTTP_OK){

               BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

               StringBuffer stringBuffer = new StringBuffer();
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
        resultadoAPI = s;
        Toast.makeText(httpContext, resultadoAPI, Toast.LENGTH_LONG).show();

    }

    public String getPostDataString(JSONObject params) throws Exception {

        return params.toString();

    }

}
