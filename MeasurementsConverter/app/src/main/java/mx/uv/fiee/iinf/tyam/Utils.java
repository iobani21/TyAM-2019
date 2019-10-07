package mx.uv.fiee.iinf.tyam;

import java.util.Locale;

class Utils {
    static final String SHARED_PREFERENCES_NAME = "PKAT";
    static final String SHARED_PREFERENCES_LANGUAGE_KEY = "LANGUAGE";
    static final String SHARED_PREFERENCES_DECIMALS_KEY = "DECIMALS";
    static final String SHARED_PREFERENCES_THEME_KEY = "THEME";

    static final String LOCALE_LANGUAGE_FRENCH = "fr";
    static final String LOCALE_LANGUAGE_SPANISH = "sp";
    static final String LOCALE_LANGUAGE_GERMAN = "de";
    static final String LOCALE_LANGUAGE_ENGLISH = "en";

    static int REQUEST_CODE_SETTINGS = 1001;

    static Locale localeSelected = null;
    static int decimalsSeletected = -1;
    static int themeSelected = -1;
}
