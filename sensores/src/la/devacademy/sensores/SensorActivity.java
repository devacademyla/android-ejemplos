package la.devacademy.sensores;

import java.util.List;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.widget.TextView;

public class SensorActivity extends Activity {

	TextView infoSensores;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        
        //usar la etiqueta de texto
        infoSensores =  (TextView) findViewById(R.id.textView1);
        
        //uso de sensor manager
        SensorManager manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        
        List<Sensor> listaSensores = manager.getSensorList(Sensor.TYPE_ALL);
        infoSensores.append(""+listaSensores.size());
        
        for (int i=0; i<listaSensores.size();i++){
        	
        	Sensor sen = listaSensores.get(i);
        	
        	
        	infoSensores.append("\n Nombre Sensor: "+sen.getName());
        	
        }
        
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sensor, menu);
        return true;
    }
    
}
