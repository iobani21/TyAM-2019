package mx.uv.fiee.iinf.tyam;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Locale;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        defineLanguage ();
        defineTheme ();
        setContentView (R.layout.activity_about);

        Toolbar toolbar = findViewById (R.id.toolbar);
        setSupportActionBar (toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled (true);
            getSupportActionBar().setDisplayHomeAsUpEnabled (true);
        }

    }

    void defineTheme () {
        SharedPreferences preferences = getSharedPreferences ("PKAT", MODE_PRIVATE);
        if (preferences.contains ("THEME")) {
            int theme = preferences.getInt ("THEME", 0);
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

    void defineLanguage () {
        SharedPreferences preferences = getSharedPreferences ("PKAT", MODE_PRIVATE);
        if (preferences.contains ("LANGUAGE")) {
            String language =  preferences.getString ("LANGUAGE", "");
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId () == android.R.id.home) {
            finish ();
        }
        return super.onOptionsItemSelected(item);
    }
}
