package pe.com.iluminatic.rest;

import java.io.IOException;


import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	final String TAG = "MainActivity";
	String response;
	EditText mEditText;
	TextView mTextView;
	
	RESTClient restClient = new RESTClient();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


        mEditText = (EditText) findViewById(R.id.edit_text);   
   
       mEditText.setText("Ejercicio para Consumir nuestro propio servicio rest");
        
        final Button mButton = (Button) findViewById(R.id.button);
        mTextView = (TextView) findViewById(R.id.content);

        mButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                try {
                	
                	String stringUrl = mEditText.getText().toString();
                	
                	Log.v(TAG, mEditText.getText().toString());
                	
                	ConnectivityManager connMgr = (ConnectivityManager) 
                            getSystemService(Context.CONNECTIVITY_SERVICE);
                        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                        if (networkInfo != null && networkInfo.isConnected()) {
                            new DownloadWebpageTask().execute(stringUrl);
                        } else {
                        	mTextView.setText("No network connection available.");
                        }
                	
                } catch (Exception e) {
                    response += "\n" + e.getMessage();
                    e.printStackTrace();
                }
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	
    // Uses AsyncTask to create a task away from the main UI thread. This task takes a 
    // URL string and uses it to create an HttpUrlConnection. Once the connection
    // has been established, the AsyncTask downloads the contents of the webpage as
    // an InputStream. Finally, the InputStream is converted into a string, which is
    // displayed in the UI by the AsyncTask's onPostExecute method.
    private class DownloadWebpageTask extends AsyncTask<String, Void, String> {
       @Override
       protected String doInBackground(String... urls) {
             
           // params comes from the execute() call: params[0] is the url.
           try {
               return restClient.downloadUrl(urls[0]);
           } catch (IOException e) {
               return "Unable to retrieve web page. URL may be invalid.";
           }
       }
       // onPostExecute displays the results of the AsyncTask.
       @Override
       protected void onPostExecute(String result) {
    	   mTextView.setText(result);
      }
   }

}
