package com.ejemplo.calculadora;

import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	public static  String numero1 = "";

	public static String numero2 = "";

	public static Double valor1;

	public static Double valor2;
	
	Button btnSuma;
	Button btnResta;
	Button btnMultiplicacion;
	Button btnDivision;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnSuma = (Button)findViewById(R.id.Suma);
		btnResta = (Button)findViewById(R.id.Resta);
		btnMultiplicacion = (Button)findViewById(R.id.Multipliacion);
		btnDivision = (Button)findViewById(R.id.Division);
		ButtonListener btnl = new ButtonListener();
		btnSuma.setOnClickListener(btnl);
		btnResta.setOnClickListener(btnl);
		btnMultiplicacion.setOnClickListener(btnl);
		btnDivision.setOnClickListener(btnl);
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	class ButtonListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			String respuesta = "";
			EditText number2field = (EditText)findViewById(R.id.Numero2);
			EditText number1field = (EditText)findViewById(R.id.Numero1);
			EditText anwserfield = (EditText)findViewById(R.id.Respuesta);
			numero1 = number1field.getText().toString();
			numero2 = number2field.getText().toString();
			valor1 = Double.valueOf(numero1);
			valor2 = Double.valueOf(numero2);
			Operaciones operaciones = new Operaciones();
			if (v.getId() == btnSuma.getId()){
				respuesta = operaciones.sumar(valor1, valor2);	
			}
			if (v.getId() == btnResta.getId()){
				respuesta = operaciones.restar(valor1, valor2);	
			}
			if (v.getId() == btnMultiplicacion.getId()){
				respuesta = operaciones.multiplicar(valor1, valor2);	
			}
			if (v.getId() == btnDivision.getId()){
				respuesta = operaciones.dividir(valor1, valor2);	
			}
			anwserfield.setText(respuesta);  
		}
		
		
	}

}
