package la.devacademy.bd;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class DBActivity extends Activity {

	private String DATABASE_NAME = "myDb.db";
	private String DATABASE_TABLE = "PAISES";
	private String CREATE_TABLE = "create table if not exists "
			+ DATABASE_TABLE + "(_id integer primary key autoincrement,"
			+ "country_name text not null," + "capital_city text not null"
			+ ")";

	Button btnCrear;
	Button btnInsertar;
	Button btnActualizar;
	Button btnTotalFilas;
	Button btnRecuperarTodos;
	Button btnBorrar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_db);
		btnCrear = (Button) findViewById(R.id.crearDbTabla);

		btnCrear.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				SQLiteDatabase dataBase = openOrCreateDatabase(DATABASE_NAME,
						Context.MODE_PRIVATE, null);

				dataBase.execSQL(CREATE_TABLE);
				dataBase.close();

				Toast.makeText(getApplicationContext(),
						"la Base de datos ha sido creada si no existe",
						Toast.LENGTH_SHORT).show();

			}
		});

		// hacer insert
		btnInsertar = (Button) findViewById(R.id.agregarNuevaFila);
		btnInsertar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				SQLiteDatabase db = openOrCreateDatabase(DATABASE_NAME,
						Context.MODE_PRIVATE, null);
				ContentValues fila = new ContentValues();
				fila.put("country_name", "PERU");
				fila.put("capital_city", "LIMA");
				db.insert(DATABASE_TABLE, null, fila);
				db.close();

				Toast.makeText(
						getApplicationContext(),
						"Se agrego un nuevo pais " + fila.get("country_name")
								+ " con capital: " + fila.get("capital_city"),
						Toast.LENGTH_SHORT).show();

			}
		});

		// actualizar
		btnActualizar = (Button) findViewById(R.id.actualizarFila);
		btnActualizar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				SQLiteDatabase db = openOrCreateDatabase(DATABASE_NAME,
						Context.MODE_PRIVATE, null);
				ContentValues fila = new ContentValues();
				fila.put("country_name", "PERU");
				fila.put("capital_city", "D.C. ");
				
				String where = "country_name=?";
				String []   whereArgs   =  new String []{"PERU"} ;
				db.update(DATABASE_TABLE, fila, where, whereArgs);
				
				db.close();

				
				
				Toast.makeText(
						getApplicationContext(),
						"La capital se ha actualizado",
						Toast.LENGTH_SHORT).show();

			}
		});
		//total filas
		btnTotalFilas = (Button) findViewById(R.id.totalFilas);
		btnTotalFilas.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				SQLiteDatabase db = openOrCreateDatabase(DATABASE_NAME,
						Context.MODE_PRIVATE, null);
				String [] resultados = new String[]{"_id"};
				Cursor todasFilas = db.query(DATABASE_TABLE, resultados, null, null, null, null, null,null);
				Integer total = todasFilas.getCount();
				
				
				
				db.close();

				
				
				Toast.makeText(
						getApplicationContext(),
						"Total de Filas en Tabla : " +total,
						Toast.LENGTH_SHORT).show();

			}
		});
		
		//obtener total de registros
		btnRecuperarTodos = (Button) findViewById(R.id.todasFilas);
		btnRecuperarTodos.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				SQLiteDatabase db = openOrCreateDatabase(DATABASE_NAME,
						Context.MODE_PRIVATE, null);
				String [] resultados = new String[]{"_id","country_name","capital_city"};
				
				String respuesta = "Todas las capitales ";
				
				
				Cursor todasFilas = db.query(DATABASE_TABLE, resultados, null, null, null, null, null,null);
				
				
				Integer cindex = todasFilas.getColumnIndex("capital_city");
				
				if (todasFilas.moveToFirst()){
					
					do {
						respuesta +=todasFilas.getString(cindex)+",";
						
					}while(todasFilas.moveToNext());
					
				}
				
				
				db.close();

				
				
				Toast.makeText(
						getApplicationContext(),
						respuesta,
						Toast.LENGTH_LONG).show();

			}
		});
		
		//boton borrar
		btnBorrar = (Button) findViewById(R.id.borrarFilas);
		btnBorrar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				SQLiteDatabase db = openOrCreateDatabase(DATABASE_NAME,
						Context.MODE_PRIVATE, null);
				
				
				String where = "country_name=?";
				String []   whereArgs   =  new String []{"PERU"} ;
				db.delete(DATABASE_TABLE, where, whereArgs);
				
				db.close();

				
				
				Toast.makeText(
						getApplicationContext(),
						"Las filas han sido borradas",
						Toast.LENGTH_SHORT).show();

			}
		});
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.db, menu);
		return true;
	}

}
