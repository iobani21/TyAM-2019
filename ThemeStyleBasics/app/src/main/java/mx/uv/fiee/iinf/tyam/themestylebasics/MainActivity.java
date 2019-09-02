package mx.uv.fiee.iinf.tyam.themestylebasics;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        Utils.onActivityCreateSetTheme (this);
        setContentView (R.layout.activity_main);

        Spinner spinner = findViewById (R.id.spThemes);
        spinner.setSelection (MyApplication.position);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (MyApplication.position != i) {
                    Utils.chageTheme (MainActivity.this, i);
                }

                MyApplication.position = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
    }
}


class Utils  {
    private static final int THEME_MATERIAL_LIGHT = 0;
    private static final int TYAM_CUSTOM_THEME = 1;
    private static int selectedTheme;


    public static void chageTheme (Activity activity, int theme) {
        selectedTheme = theme;

        activity.finish ();
        activity.startActivity (new Intent (activity, activity.getClass ()));
        activity.overridePendingTransition (android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public static void onActivityCreateSetTheme (Activity activity) {
        switch (selectedTheme) {
            default:
            case THEME_MATERIAL_LIGHT:
                activity.setTheme (R.style.AppTheme);
                break;
            case TYAM_CUSTOM_THEME:
                activity.setTheme (R.style.Theme_TYAM_CUSTOM_THEME);
                break;
        }
    }
}