package pe.com.iluminatic.cliente.rest;

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
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class RESTClient {

	static final String TAG = "RESTClient";
	
	// Given a URL, establishes an HttpUrlConnection and retrieves
	// the web page content as a InputStream, which it returns as
	// a string.
	public String downloadUrl(String myurl) throws IOException {
		Log.d(TAG, "downloadUrl: " + myurl);
		InputStream is = null;
	    // Only display the first 500 characters of the retrieved
	    // web page content.
	    int len = 500;
	        
	    try {
	        URL url = new URL(myurl);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setReadTimeout(10000 /* milliseconds */);
	        conn.setConnectTimeout(15000 /* milliseconds */);
	        conn.setRequestMethod("GET");
	        conn.setDoInput(true);	
	        // account setup for mashape webservice
//	        conn.setRequestProperty("X-Mashape-Authorization", "xxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
	        Log.d(TAG, "downloadUrl start: " + myurl);
	        // Starts the query
	        conn.connect();
	        Log.d(TAG, "downloadUrl connect: " + myurl);
	        int response = conn.getResponseCode();
	        
	        Log.d(TAG, "The response is: " + response);
	        is = conn.getInputStream();

	        // Convert the InputStream into a string
	        String contentAsString = readIt(is, len);

//	        return contentAsString;
	        return parseJSON(contentAsString);
	        
	    // Makes sure that the InputStream is closed after the app is
	    // finished using it.
	    } finally {
	        if (is != null) {
	            is.close();
	        } 
	    }
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
