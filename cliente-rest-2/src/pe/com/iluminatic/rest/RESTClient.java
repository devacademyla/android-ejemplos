package pe.com.iluminatic.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import com.google.gson.Gson;

import android.util.Log;


public class RESTClient {

	static final String TAG = "RESTClient";
	
	// Given a URL, establishes an HttpUrlConnection and retrieves
	// the web page content as a InputStream, which it returns as
	// a string.
	public String downloadUrl(String myurl) throws IOException {
		HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, 60000);
		DefaultHttpClient http = new DefaultHttpClient(httpParams);
		
		HttpPost post = new HttpPost("http://192.168.11.96:8080/EjemplosRest/rest/pepito/recuperar");
		
		Usuario user = new Usuario();
		user.setApellido("BUGARIN");
		user.setNombre("Jose Luis");
		
		Gson g = new Gson();
		String envioGson = g.toJson(user);
		Usuario userRecuperado = new Usuario();
		
		try {
		
			StringEntity objetoJson = new StringEntity(envioGson);
			objetoJson.setContentType("application/json");
			post.setEntity(objetoJson);
			
			HttpResponse response = http.execute(post);
			
			if (response.getStatusLine().getStatusCode()!=200){
				//error
				
			}
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String mensajeRecibido = "";
			
			StringBuilder builder = new StringBuilder();
			
			while ((mensajeRecibido = reader.readLine())!=null){
				builder.append(mensajeRecibido);
				
			}
			
			Gson j = new Gson();
			 userRecuperado = j.fromJson(builder.toString(), Usuario.class);
	
			http.getConnectionManager().shutdown();
		
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			Log.i("clienteRest", "downloadUrl start: " + e.getMessage());
			System.out.println("e: " +e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userRecuperado.getMensaje();
	}
	
	// Reads an InputStream and converts it to a String.
	public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
	    Reader reader = null;
	    reader = new InputStreamReader(stream, "UTF-8");        
	    char[] buffer = new char[len];
	    reader.read(buffer);
	    return new String(buffer);
	}
	
	// Create JSON object from JSON string for easy
	// manipulation of JSON data.
	private static String parseJSON(String result) {
		
		// A Simple JSONObject Creation
		JSONObject json;
		String jsonResult = null;
		JSONArray values;
        try {
	        json = new JSONObject(result);
			
			// A Simple JSONObject Value Pushing
//			json.put("sample key", "sample value");
			json.put("geonames2", "sample value");
	        
			// A Simple JSONObject Parsing
			JSONArray nameArray = json.names();
			Log.v(TAG, "jsonNames: " + nameArray);
			jsonResult = "jsonNames: " + nameArray;
			
			values = json.getJSONArray("geonames"); 
			JSONObject objCountryInfo = values.getJSONObject(0);
			
			nameArray = objCountryInfo.names();
			Log.v(TAG, "jsonGEONames: " + nameArray);
			jsonResult += "\n\njsonGEONames: " + nameArray;
			
			jsonResult += "\n\ncountryName:\n"
					+ objCountryInfo.getString("countryName");
			
//			JSONObject objCountryInfo = json.getJSONObject("geonames");

			
			// A simple access to the first name
//			jsonResult = nameArray.getString(0);
//			jsonResult = valArray.getString(0);
//			jsonResult = json.getString("countryName");
//			jsonResult = jsonObj.toString();
//			jsonResult = json.getString("countryName");
			
        } catch (JSONException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
	
        return jsonResult;
	}

}
