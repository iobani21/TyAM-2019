package mx.uv.fiee.iinf.tyam.sharedpreferencesdemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/***
 * Ejemplo de implementación de SharedPreferences, el archivo generado puede ser consultado con:
 *
 *
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String MY_PREFERENCES = "myPref";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";

    SharedPreferences sharedPreferences;
    TextView name;
    TextView email;

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        Button btnSave = findViewById (R.id.btnSave);
        btnSave.setOnClickListener (this);

        Button btnRetrieve = findViewById (R.id.btnRetrieve);
        btnRetrieve.setOnClickListener (this);

        Button btnClear = findViewById (R.id.btnClear);
        btnClear.setOnClickListener (this);

        name = findViewById (R.id.etName);
        email = findViewById (R.id.etEmail);

        sharedPreferences = getSharedPreferences (MY_PREFERENCES, Context.MODE_PRIVATE);
        if (sharedPreferences.contains (KEY_NAME)) {
            name.setText (sharedPreferences.getString (KEY_NAME, ""));
        }

        if (sharedPreferences.contains (KEY_EMAIL)) {
            email.setText (sharedPreferences.getString (KEY_EMAIL, ""));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId ()) {
            case R.id.btnSave:
                save ();
                break;
            case R.id.btnRetrieve:
                retrieve ();
                break;
            case R.id.btnClear:
                clear ();
                break;
        }
    }

    private void clear() {
        name.setText ("");
        email.setText ("");
    }

    private void retrieve() {
        if (sharedPreferences.contains (KEY_NAME)) {
            name.setText (sharedPreferences.getString (KEY_NAME, ""));
        }

        if (sharedPreferences.contains (KEY_EMAIL)) {
            email.setText (sharedPreferences.getString (KEY_EMAIL, ""));
        }
    }

    private void save() {
        String n = name.getText().toString ();
        String e = email.getText().toString ();

        SharedPreferences.Editor editor = sharedPreferences.edit ();
        editor.putString (KEY_NAME, n);
        editor.putString (KEY_EMAIL, e);
        editor.apply ();
    }
}
