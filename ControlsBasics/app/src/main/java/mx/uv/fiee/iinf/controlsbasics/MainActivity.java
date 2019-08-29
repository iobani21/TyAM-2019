package mx.uv.fiee.iinf.controlsbasics;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button myButtonSave = findViewById (R.id.myButtonSave);
        myButtonSave.setOnClickListener (view -> {
            Toast.makeText (this, "Saved!", Toast.LENGTH_SHORT).show ();
        });


        Button myButtonCancel = findViewById (R.id.myButtonCancel);
        myButtonCancel.setOnClickListener (view -> {
            Toast.makeText (this, "Canceled!", Toast.LENGTH_SHORT).show ();
        });
    }

}
