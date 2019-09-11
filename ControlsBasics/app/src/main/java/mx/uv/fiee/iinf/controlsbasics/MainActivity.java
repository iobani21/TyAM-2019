package mx.uv.fiee.iinf.controlsbasics;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class MainActivity extends Activity {
    String TAG = "PKAT";

    // variables persistentes
    int spinnerSelectedIndex = -1;
//    String name = "MYNAME";
//    String phone = "MYPHONE";
//    String email = "MYEMAIL";
    int day;
    int month;
    int year;

    @Override
    protected void onSaveInstanceState (@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d (TAG, "ON SAVE INSTANCE STATE");
//        outState.putInt ("SPSelectedIndex", spinnerSelectedIndex);
//        outState.putString ("NAME", name);
//        outState.putString ("PHONE", phone);
//        outState.putString ("EMAIL", email);

        outState.putInt ("DAY", day);
        outState.putInt ("MONTH", month);
        outState.putInt ("YEAR", year);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState (savedInstanceState);
        //Log.d (TAG, "ON RESTORE INSTANCE STATE");
        Log.d (TAG, "ONRESTOREINSTANCESTATE: CONTENT'S BUNDLE " + savedInstanceState.toString ());
    }

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        Log.d (TAG, "CREATE");

        if (savedInstanceState != null && !savedInstanceState.isEmpty ()) {
            Log.d (TAG, "ONCREATE: INSTANCE STATE IS NOT NULL, CONTENT IS " + savedInstanceState.toString ());

            day = (int) savedInstanceState.get ("DAY");
            month = (int) savedInstanceState.get ("MONTH");
            year = (int) savedInstanceState.get ("YEAR");
        } else {

            // se inicializan las variables persistentes
//        name = "MYNAME";
//        phone = "MYPHONE";
//        email = "MYEMAIL";
//
            Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
            day = calendar.get(Calendar.DAY_OF_MONTH);
            month = calendar.get(Calendar.MONTH);
            year = calendar.get(Calendar.YEAR);
        }

        /***
         * fuente de datos (modelo)
         */
        List<String> planetas = new ArrayList<>();
        planetas.add ("Mercurio");
        planetas.add ("Venus");
        planetas.add ("Tierra");
        planetas.add ("Marte");
        planetas.add ("Jupiter");
        planetas.add ("Saturno");
        planetas.add ("Urano");
        planetas.add ("Neptuno");
        planetas.add ("Pluton");

        List<String> colores = new ArrayList<>();
        colores.add ("Rojo");
        colores.add ("Verde");
        colores.add ("Azul");

        /***
         * el adaptador (controller) es el encargado de recorrer la fuente de datos y pasarlo a la vista,
         * igualmente cuando el usuario toca un elemento del spinner, el adapter recibe el evento y
         * es capaz de obtener el elemento seleccionado
         *
         * En este ejemplo utilizamdo arrayadapter ya que la fuente de datos es una lista. Los parámetros
         * que se pasan al constructor son:
         *
         * Context: La actividad donde se visualiza el spinner
         * Resource: el identificador del recurso que define como se visualizará la información
         * Objects: el array con la fuente de datos.
         */
        ArrayAdapter<String> adapter = new ArrayAdapter<> (this,
                android.R.layout.simple_spinner_dropdown_item, planetas);

        /*** vista */
        Spinner spinner = findViewById (R.id.mySpiner); // cargamos el spinner desde el archivo de vista
        spinner.setPrompt ("Selecciona..."); // esta propiedad se establece cuando deseamos mostrar el contenido como diálogo
        spinner.setAdapter (adapter); // establecemos el adaptador

        /***
         * Para capturar el toque de un elemento se debe definir un manejador para la interfaz OnItemSelectedListener
         */
        spinner.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener () {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                /***
                 * El elemento seleccionado se obtiene directamente desde el adaptador mediante getSelectedItem
                 */
                Toast.makeText (getBaseContext (), adapterView.getSelectedItem ().toString (), Toast.LENGTH_SHORT).show ();
            }

            @Override
            public void onNothingSelected (AdapterView<?> adapterView) {}
        });


        /***
         * interacción con los botones
         */
        Button myButtonSave = findViewById (R.id.myButtonSave);
        myButtonSave.setOnClickListener (view -> {
            Toast.makeText (this, "Saved!", Toast.LENGTH_SHORT).show ();
        });


        Button myButtonCancel = findViewById (R.id.myButtonCancel);
        myButtonCancel.setOnClickListener (view -> {
            Toast.makeText (this, "Canceled!", Toast.LENGTH_SHORT).show ();
        });
    }

    @Override
    protected void onStart () {
        super.onStart();
        Log.d (TAG, "ON START");
    }

    @Override
    protected void onResume () {
        super.onResume ();
        Log.d (TAG, "ON RESUME");
    }

    @Override
    protected void onPause () {
        super.onPause ();
        Log.d (TAG, "ON PAUSE");
    }

    @Override
    protected void onStop () {
        super.onStop ();
        Log.d (TAG, "ON STOP");
    }

    @Override
    protected void onDestroy () {
        super.onDestroy ();
        Log.d (TAG, "OK DESTROY");

    }

    @Override
    protected void onRestart () {
        super.onRestart ();
        Log.d (TAG, "ON RESTART");
    }
}
