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
        SharedPreferences preferences = getSharedPreferences (Utils.SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        if (preferences.contains (Utils.SHARED_PREFERENCES_THEME_KEY)) {
            int theme = preferences.getInt (Utils.SHARED_PREFERENCES_THEME_KEY, 0);
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
        SharedPreferences preferences = getSharedPreferences (Utils.SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        if (preferences.contains (Utils.SHARED_PREFERENCES_LANGUAGE_KEY)) {
            String language =  preferences.getString (Utils.SHARED_PREFERENCES_LANGUAGE_KEY, "");
            Locale baseLocale;

            switch (language) {
                case Utils.LOCALE_LANGUAGE_FRENCH:
                    baseLocale = new Locale (Utils.LOCALE_LANGUAGE_FRENCH);
                    break;
                case Utils.LOCALE_LANGUAGE_ENGLISH:
                    baseLocale = new Locale (Utils.LOCALE_LANGUAGE_ENGLISH);
                    break;
                case Utils.LOCALE_LANGUAGE_GERMAN:
                    baseLocale = new Locale (Utils.LOCALE_LANGUAGE_GERMAN);
                    break;
                default:
                    baseLocale = new Locale (Utils.LOCALE_LANGUAGE_SPANISH);
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
    public boolean onOptionsItemSelected (@NonNull MenuItem item) {
        if (item.getItemId () == android.R.id.home) {
            finish ();
        }
        return super.onOptionsItemSelected(item);
    }
}
