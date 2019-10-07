package mx.uv.fiee.iinf.tyam;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

import java.util.Locale;

public class MyApplication extends Application {

    @Override
    public void onCreate () {
        super.onCreate ();

        SharedPreferences preferences = getSharedPreferences (Utils.SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        if (preferences.contains (Utils.SHARED_PREFERENCES_LANGUAGE_KEY)) {
            String language =  preferences.getString (Utils.SHARED_PREFERENCES_LANGUAGE_KEY, "");
            Locale baseLocale;

            switch (language) {
                case "fr":
                    baseLocale = new Locale (Utils.LOCALE_LANGUAGE_FRENCH);
                    break;
                case "en":
                    baseLocale = new Locale (Utils.LOCALE_LANGUAGE_ENGLISH);
                    break;
                case "de":
                    baseLocale = new Locale (Utils.LOCALE_LANGUAGE_GERMAN);
                    break;
                default:
                    baseLocale = new Locale (Utils.LOCALE_LANGUAGE_SPANISH);
                    break;
            }

            Locale.setDefault (baseLocale);

            Configuration configuration   = getBaseContext().getResources().getConfiguration  ();
            DisplayMetrics displayMetrics = getBaseContext().getResources().getDisplayMetrics ();
            configuration.locale = baseLocale;

            getBaseContext().getResources().updateConfiguration (configuration, displayMetrics);
        }

    }
}
