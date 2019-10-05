package mx.uv.fiee.iinf.tyam;

import java.util.Locale;

class Utils {
    static String SHARED_PREFERENCES_NAME = "PKAT";
    static String SHARED_PREFERENCES_LANGUAGE_KEY = "LANGUAGE";
    static String SHARED_PREFERENCES_DECIMALS_KEY = "DECIMALS";
    static String SHARED_PREFERENCES_THEME_KEY = "THEME";

    static int REQUEST_CODE_SETTINGS = 1001;

    static Locale localeSelected = null;
    static int decimalsSeletected = -1;
    static int themeSelected = -1;
}
