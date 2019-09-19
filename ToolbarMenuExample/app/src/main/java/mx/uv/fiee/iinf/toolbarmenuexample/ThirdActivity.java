package mx.uv.fiee.iinf.toolbarmenuexample;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_third);

        Button ok = findViewById (R.id.btnOK);
        Button cancel = findViewById (R.id.btnCancel);

        ok.setOnClickListener (v -> {

            EditText name = findViewById (R.id.edtName);
            EditText phone = findViewById (R.id.edtPhone);
            EditText email = findViewById (R.id.edtEmail);

            Intent intent = new Intent ();
            intent.putExtra ("name", name.getText().toString ());
            intent.putExtra ("phone", phone.getText().toString ());
            intent.putExtra ("email", email.getText().toString ());

            setIntent (intent);
            setResult (RESULT_OK);

            finish ();

        });

        cancel.setOnClickListener (v -> {
            setResult (RESULT_CANCELED);
            finish ();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater ().inflate (R.menu.main, menu);
        return super.onCreateOptionsMenu (menu);
    }
}
