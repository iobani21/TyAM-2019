package mx.uv.fiee.iinf.tyam;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        defineLanguage ();
        defineTheme ();
        setContentView (R.layout.activity_settings);

        Toolbar toolbar = findViewById (R.id.toolbar);
        setSupportActionBar (toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled (true);
            getSupportActionBar().setDisplayHomeAsUpEnabled (true);
        }

        Spinner spLanguages = findViewById (R.id.spLanguage);
        setupLanguageSpinner (spLanguages);
        spLanguages.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener () {
            @Override
            public void onItemSelected (AdapterView<?> adapterView, View view, int i, long l) {
                if (Utils.localeSelected == null && i == 0) return;

                switch (i) {
                    case 0:
                        Utils.localeSelected = new Locale ("sp");
                        break;
                    case 1:
                        Utils.localeSelected = new Locale ("en");
                        break;
                    case 2:
                        Utils.localeSelected = new Locale ("fr");
                        break;
                    case 3:
                        Utils.localeSelected = new Locale ("de");
                        break;
                }

                if (Utils.localeSelected != null &&
                        Utils.localeSelected.getLanguage ().equals (Locale.getDefault().getLanguage ())) return;

                Resources resources = getResources ();
                DisplayMetrics metrics = resources.getDisplayMetrics ();
                Configuration configuration = resources.getConfiguration ();
                configuration.setLocale (Utils.localeSelected);

                if (BuildConfig.VERSION_CODE < 25) {
                    resources.updateConfiguration (configuration, metrics);
                }
                else {
                    getBaseContext ().createConfigurationContext (configuration);
                }

                showMessageInfo ();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });


        Spinner spDecimals = findViewById (R.id.spDecimals);
        setupDecimalsSpinner (spDecimals);
        spDecimals.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener () {
            @Override
            public void onItemSelected (AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) return;
                if (i == Utils.decimalsSeletected) return;

                Utils.decimalsSeletected = Integer.parseInt (adapterView.getSelectedItem ().toString ());

                showMessageInfo ();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        Spinner spThemes = findViewById (R.id.spThemes);
        setupThemesSpinner (spThemes);
        spThemes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener () {
            @Override
            public void onItemSelected (AdapterView<?> adapterView, View view, int i, long l) {
                if (Utils.themeSelected == -1 && i == 0) return;
                if (Utils.themeSelected == i) return;

                Utils.themeSelected = (int) adapterView.getSelectedItemId ();

                showMessageInfo ();
                recreate ();
            }

            @Override
            public void onNothingSelected (AdapterView<?> adapterView) {}
        });
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate (R.menu.menu_settings, menu);
        return super.onCreateOptionsMenu (menu);
    }

    @Override
    public boolean onOptionsItemSelected (@NonNull MenuItem item) {
        switch (item.getItemId ()) {
            case android.R.id.home:
                setResult (RESULT_CANCELED);
                finish ();
                break;
            case R.id.mnuDone:
                saveSelections ();
                setResult (RESULT_OK);
                finish ();
                break;
        }

        return super.onOptionsItemSelected (item);
    }

    void saveSelections () {
        SharedPreferences preferences = getSharedPreferences (Utils.SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit ();
        editor.putString (Utils.SHARED_PREFERENCES_LANGUAGE_KEY, Utils.localeSelected.getLanguage ());
        editor.putInt (Utils.SHARED_PREFERENCES_DECIMALS_KEY, Utils.decimalsSeletected);
        editor.putInt (Utils.SHARED_PREFERENCES_THEME_KEY, Utils.themeSelected);
        editor.apply ();
    }

    void defineTheme () {
        if (Utils.themeSelected == -1) {
            SharedPreferences preferences = getSharedPreferences(Utils.SHARED_PREFERENCES_NAME, MODE_PRIVATE);
            if (preferences.contains("THEME")) {
                Utils.themeSelected = preferences.getInt("THEME", 0);
            }
        }

        switch (Utils.themeSelected) {
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

    void defineLanguage () {
        Configuration configuration   = getBaseContext().getResources().getConfiguration  ();
        DisplayMetrics displayMetrics = getBaseContext().getResources().getDisplayMetrics ();

        if (Utils.localeSelected != null) {
            Locale.setDefault (Utils.localeSelected);
            configuration.locale = Utils.localeSelected;
        } else {
            SharedPreferences preferences = getSharedPreferences ("PKAT", MODE_PRIVATE);
            if (preferences.contains ("LANGUAGE")) {
                String language = preferences.getString("LANGUAGE", "");
                Locale baseLocale;

                switch (language) {
                    case "fr":
                        baseLocale = new Locale("fr");
                        break;
                    case "en":
                        baseLocale = new Locale("en");
                        break;
                    case "de":
                        baseLocale = new Locale("de");
                        break;
                    default:
                        baseLocale = new Locale("sp");
                        break;
                }

                Locale.setDefault (baseLocale);
                configuration.locale = baseLocale;
                Utils.localeSelected = baseLocale;
            }
        }

        getBaseContext().getResources().updateConfiguration (configuration, displayMetrics);
    }

    void setupThemesSpinner (Spinner spThemes) {
        String [] themes = getResources().getStringArray (R.array.themes);
        ArrayAdapter<String> themAdapter = new ArrayAdapter<> (this, android.R.layout.simple_spinner_dropdown_item, themes);
        spThemes.setAdapter (themAdapter);

        SharedPreferences preferences = getSharedPreferences (Utils.SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        if (preferences.contains (Utils.SHARED_PREFERENCES_THEME_KEY)) {
            int foo = preferences.getInt (Utils.SHARED_PREFERENCES_THEME_KEY, 0);
            spThemes.setSelection (foo, true);
        }
    }

    void setupDecimalsSpinner (Spinner spDecimals) {
        String [] decimals = getResources().getStringArray (R.array.decimals);
        ArrayAdapter<String> decAdapter = new ArrayAdapter<> (this, android.R.layout.simple_spinner_dropdown_item, decimals);
        spDecimals.setAdapter (decAdapter);

        SharedPreferences preferences = getSharedPreferences ( Utils.SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        if (preferences.contains (Utils.SHARED_PREFERENCES_DECIMALS_KEY)) {
            int foo = preferences.getInt (Utils.SHARED_PREFERENCES_DECIMALS_KEY, 0);
            spDecimals.setSelection ((int) foo, true);
        }
    }

    void setupLanguageSpinner (Spinner spLanguages) {
        String [] languages = getResources().getStringArray (R.array.languages);
        ArrayAdapter<String> lanAdapter = new ArrayAdapter<> (this, android.R.layout.simple_spinner_dropdown_item, languages);
        spLanguages.setAdapter (lanAdapter);

        SharedPreferences preferences = getSharedPreferences ( Utils.SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        if (preferences.contains (Utils.SHARED_PREFERENCES_LANGUAGE_KEY)) {
            String lang = preferences.getString (Utils.SHARED_PREFERENCES_LANGUAGE_KEY, "");

            int foo = 0;
            switch (lang) {
                case "en":
                    foo = 1;
                    break;
                case "fr":
                    foo = 2;
                    break;
                case "de":
                    foo = 3;
                    break;
            }

            spLanguages.setSelection (foo, true);
        }
    }

    void showMessageInfo () {
        String info = getResources().getString (R.string.dialogInfo);
        Toast.makeText (getBaseContext (), info, Toast.LENGTH_SHORT).show ();
    }
}

//    AlertDialog dialog = new AlertDialog.Builder (SettingsActivity.this).create ();
//                dialog.setTitle (R.string.dialogInfo);
//                        dialog.setMessage (getResources().getString (R.string.dialogMessage));
//                        dialog.setButton (AlertDialog.BUTTON_NEUTRAL, "OK", (dialogInterface, i1) -> recreate ());
//                        dialog.show ();