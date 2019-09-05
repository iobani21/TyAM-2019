package mx.uv.fiee.iinf.controlsbasics;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        ArrayAdapter<String> a = new ArrayAdapter<> (this,
                android.R.layout.simple_spinner_dropdown_item, planetas);

        Spinner spinner = findViewById (R.id.mySpiner);
        spinner.setPrompt ("Selecciona...");
        spinner.setAdapter (a);

        spinner.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText (getBaseContext(), planetas.get (i).toString (), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button myButtonSave = findViewById (R.id.myButtonSave);
        myButtonSave.setOnClickListener (view -> {
            Toast.makeText (this, "Saved!", Toast.LENGTH_SHORT).show ();
        });


        Button myButtonCancel = findViewById (R.id.myButtonCancel);
        myButtonCancel.setOnClickListener (view -> {
            Toast.makeText (this, "Canceled!", Toast.LENGTH_SHORT).show ();
        });
    }

}
