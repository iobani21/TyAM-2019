package mx.uv.fiee.iinf.tyam;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Animation moveView;
    private EditText tvOrigin;
    private EditText tvDestiny;
    private int previousSelected = -1;

    private enum ConversionsAvailable {
        TEMPERATURE,
        LONGITUDE,
        VOLUME,
        ACCELERATION,
        WEIGHT,
        AREA
    }

    private int decimals;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        defineTheme ();
        defineLanguage ();
        defineDecimals ();
        setContentView (R.layout.activity_main);

        Toolbar toolbar = findViewById (R.id.toolbar);
        setSupportActionBar (toolbar);

        //
        // Fuente de datos para el spinner
        //
        String [] temperatureArray = getResources().getStringArray (R.array.temperature);
        final LinkedList<String> tempatureData = new LinkedList<> ();
        Collections.addAll (tempatureData, temperatureArray);

        String [] longitudeArray = getResources().getStringArray (R.array.longitude);
        final LinkedList<String> longitudeData = new LinkedList<> ();
        Collections.addAll (longitudeData, longitudeArray);

        String [] volumeArray = getResources().getStringArray (R.array.volume);
        final LinkedList<String> volumeData = new LinkedList<> ();
        Collections.addAll (volumeData, volumeArray);

        String [] accelerationArray = getResources().getStringArray (R.array.acceleration);
        final LinkedList<String> accelerationData = new LinkedList<> ();
        Collections.addAll (accelerationData, accelerationArray);

        String [] weightArray = getResources().getStringArray (R.array.weight);
        final LinkedList<String> weightData = new LinkedList<> ();
        Collections.addAll (weightData, weightArray);

        String [] areaArray = getResources().getStringArray (R.array.area);
        final LinkedList<String> areaData = new LinkedList<> ();
        Collections.addAll (areaData, areaArray);

        //
        //Creamos el adaptador utiliando una lista vacía pero que permita añadir y quitar elementos
        //
        ArrayAdapter<String> operationsAdapter = new ArrayAdapter<> (this, android.R.layout.simple_spinner_dropdown_item, new LinkedList<>());

        //
        // Obtenemos la referencia al spinner y establecemos el adaptador
        //
        Spinner spOperation = findViewById (R.id.spOperation);
        spOperation.setAdapter (operationsAdapter);

        //
        // Los RadioGroup se encargan de monitorear cuando el usuario selecciona algún tipo de conversión
        // cuando el usuario selecciona alguna de ellas, el contenido de la fuente de datos para dicha conversión
        //se agrega al adaptador ligado al spinner
        //
        RadioGroup rgConversiones1 = findViewById (R.id.rgConversiones1);
        RadioGroup rgConversiones2 = findViewById (R.id.rgConversiones2);

        rgConversiones1.setOnCheckedChangeListener ((radioGroup, selectedIndex) -> {
            //
            //Validación necesaria para permitir seleccionar únicamente un radiobutton a la vez en ambos radiogroup
            //
            if (selectedIndex == previousSelected) return;
            if (selectedIndex > -1 && rgConversiones2.getCheckedRadioButtonId () != -1) rgConversiones2.clearCheck ();

            operationsAdapter.clear (); // eliminar la lista previa
            operationsAdapter.add (getResources().getString (R.string.selecction)); // añade el element default cuyo índice será 0

            switch (selectedIndex) {
                case R.id.rbTemperature:
                    operationsAdapter.addAll (tempatureData);
                    break;
                case R.id.rbLongitude:
                    operationsAdapter.addAll (longitudeData);
                    break;
                case R.id.rbVolume:
                    operationsAdapter.addAll (volumeData);
                    break;
            }

            // mantiene la referencia al elemento seleccionado que evita la interacción cíclica al seleccionar elementos
            // en el segundo radiogroup y viceversa
            if (selectedIndex > -1) previousSelected = selectedIndex;
        });

        rgConversiones2.setOnCheckedChangeListener (((radioGroup, selectedIndex) -> {
            if (selectedIndex == previousSelected) return;
            if (selectedIndex > -1 && rgConversiones1.getCheckedRadioButtonId () != -1) rgConversiones1.clearCheck ();

            operationsAdapter.clear ();
            operationsAdapter.add (getResources().getString (R.string.selecction));

            switch (selectedIndex) {
                case R.id.rbAcceleration:
                    operationsAdapter.addAll (accelerationData);
                    break;
                case R.id.rbArea:
                    operationsAdapter.addAll (areaData);
                    break;
                case R.id.rbWeight:
                    operationsAdapter.addAll (weightData);
                    break;
            }

            if (selectedIndex > -1) previousSelected = selectedIndex;
        }));


        //
        // Para realializar el cálculo del valor hacemos uso de las colecciones, fuente de datos del spinner,
        // para discriminar el tipo de operación deseada
        //
        Button btnConvert = findViewById (R.id.btnConvert);
        btnConvert.setOnClickListener (v -> {
            tvOrigin = findViewById (R.id.tvOrigin); // obtenemos la referencia al edittext origen
            if (tvOrigin.getText().toString().equals ("")) return; // si está vacío no se realiza nada

            double value = Double.parseDouble (tvOrigin.getText ().toString ()); // se realiza la conversión de string a double

            // se obtiene el id del radiobutton seleccionado por el usuario
            int convertSelected = rgConversiones1.getCheckedRadioButtonId () == -1 ? rgConversiones2.getCheckedRadioButtonId () : rgConversiones1.getCheckedRadioButtonId ();

            // a partir del id del radiobutton seleccionado se obtiene el texto que servirá para ubicar la operación dentro de las colecciones
            //String itemSelected = ((RadioButton) findViewById (convertSelected)).getText ().toString ();
            String itemSelected = spOperation.getSelectedItem ().toString ();

            // dependiendo el radiobutton seleccionado se invoca al método calcular
            switch (convertSelected) {
                case R.id.rbTemperature:
                    calculate (ConversionsAvailable.TEMPERATURE, tempatureData, itemSelected, value);
                    break;
                case R.id.rbLongitude:
                    calculate (ConversionsAvailable.LONGITUDE, longitudeData, itemSelected, value);
                    break;
                case R.id.rbVolume:
                    calculate (ConversionsAvailable.VOLUME, volumeData, itemSelected, value);
                    break;
                case R.id.rbAcceleration:
                    calculate (ConversionsAvailable.ACCELERATION, accelerationData, itemSelected, value);
                    break;
                case R.id.rbWeight:
                    calculate (ConversionsAvailable.WEIGHT, weightData, itemSelected, value);
                    break;
                case R.id.rbArea:
                    calculate (ConversionsAvailable.AREA, areaData, itemSelected, value);
                    break;
            }
        });

        //
        // resetear los campos
        //
        Button btnReset = findViewById (R.id.btnReset);
        btnReset.setOnClickListener (v -> {
            tvDestiny.setText ("");
            rgConversiones1.clearCheck ();
            rgConversiones2.clearCheck ();
            operationsAdapter.clear ();
        });

        moveView = AnimationUtils.loadAnimation (getBaseContext (), R.anim.move);
        moveView.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                tvDestiny.setTextColor (Color.BLACK);
            }

            @Override
            public void onAnimationRepeat (Animation animation) {}
        });
        tvDestiny = findViewById (R.id.tvDestination);
    }

    /**
     * Realiza el cálculo del valor ingresado por el usuario mediante el tipo de conversión seleccionado por el usuario
     * @param conversion Tipo de conversión seleccionada
     * @param dataSource Colección que almacena las diferentes operaciones para el tipo de conversión deseada
     * @param opSelected Cadena de texto con la operación específica
     * @param value Valor ingresado por el usuario
     */
    void calculate (ConversionsAvailable conversion, LinkedList<String> dataSource, String opSelected, double value) {
        tvOrigin.startAnimation (moveView);
        tvDestiny.setTextColor (Color.WHITE);

        int index = dataSource.indexOf (opSelected);
        String format = "%." + this.decimals + "f";
        double foo = 0.0;

        switch (conversion) {
            case TEMPERATURE:
                switch (index) {
                    case 0:
                        foo = (value * 1.8) + 32;
                        break;
                    case 1:
                        foo = (value - 32) / 1.8;
                        break;
                }
                break;
            case LONGITUDE:
                switch (index) {
                    case 0:
                        foo = value * 1.0904;
                        break;
                    case 1:
                        foo = value / 1.0904;
                        break;
                    case 2:
                        foo = value / 1.0609;
                        break;
                    case 3:
                        foo = value * 1.0609;
                        break;
                }
                break;
            case VOLUME:
                switch (index) {
                    case 0:
                        foo = value * 3.785;
                        break;
                    case 1:
                        foo = value / 3.875;
                        break;
                }
                break;
            case ACCELERATION:
                switch (index) {
                    case 0:
                        foo = value * 1.0609;
                        break;
                    case 1:
                        foo = value / 1.0609;
                        break;
                }
                break;
            case WEIGHT:
                switch (index) {
                    case 0:
                        foo = value * 2.205;
                        break;
                    case 1:
                        foo = value / 2.205;
                        break;
                    case 2:
                        foo = value * 28.35;
                        break;
                    case 3:
                        foo = value / 28.35;
                        break;
                }
                break;
            case AREA:
                switch (index) {
                    case 0:
                        foo = value * 10000;
                        break;
                    case 1:
                        foo = value / 10000;
                        break;
                }
                break;
        }

        tvDestiny.setText (String.format (Locale.getDefault (), format, foo));
    }


    /**
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate (R.menu.main, menu);
        return true;
    }

    /**
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected (@NonNull MenuItem item) {
        switch (item.getItemId ()) {
            case R.id.mnuSettings:
                Intent intentSettings = new Intent (this, SettingsActivity.class);
                startActivityForResult (intentSettings, Utils.REQUEST_CODE_SETTINGS);
                break;
            case R.id.mnuAbout:
                Intent aboutIntent = new Intent (this, AboutActivity.class);
                startActivity (aboutIntent);
                break;
        }

        return super.onOptionsItemSelected (item);
    }

    void defineLanguage () {
        SharedPreferences preferences = getSharedPreferences (Utils.SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        if (preferences.contains (Utils.SHARED_PREFERENCES_LANGUAGE_KEY)) {
            String language =  preferences.getString (Utils.SHARED_PREFERENCES_LANGUAGE_KEY, "");
            Locale baseLocale;

            switch (language) {
                case "fr":
                    baseLocale = new Locale ("fr");
                    break;
                case "en":
                    baseLocale = new Locale ("en");
                    break;
                case "de":
                    baseLocale = new Locale ("de");
                    break;
                default:
                    baseLocale = new Locale ("sp");
                    break;
            }

            Locale.setDefault(baseLocale);

            Configuration configuration = getBaseContext().getResources().getConfiguration ();
            DisplayMetrics displayMetrics = getBaseContext().getResources().getDisplayMetrics ();
            configuration.locale = baseLocale;

            getBaseContext().getResources().updateConfiguration (configuration, displayMetrics);
        }
    }

    void defineTheme () {
        SharedPreferences preferences = getSharedPreferences (Utils.SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        if (preferences.contains (Utils.SHARED_PREFERENCES_THEME_KEY)) {
            int theme = preferences.getInt (Utils.SHARED_PREFERENCES_THEME_KEY, -1);
            switch (theme) {
                case 1:
                    setTheme (R.style.GREENS_THEME);
                    break;
                case 2:
                    setTheme (R.style.BLUES_THEME);
                    break;
                default:
                    setTheme (R.style.AppTheme);
                    break;
            }
        }
    }

    void defineDecimals () {
        SharedPreferences preferences = getSharedPreferences (Utils.SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        this.decimals = preferences.getInt (Utils.SHARED_PREFERENCES_DECIMALS_KEY, 0);
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == Utils.REQUEST_CODE_SETTINGS) {
            if (resultCode == RESULT_OK) {
                recreate ();
            }
        }

        Utils.decimalsSeletected = -1;
        Utils.themeSelected = -1;
        Utils.localeSelected = null;

        super.onActivityResult(requestCode, resultCode, data);
    }
}
