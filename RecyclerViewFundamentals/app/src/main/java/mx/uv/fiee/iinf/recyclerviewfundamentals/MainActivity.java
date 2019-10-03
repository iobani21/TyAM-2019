package mx.uv.fiee.iinf.recyclerviewfundamentals;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import mx.uv.fiee.iinf.recyclerviewfundamentals.Fragments.DetallesFragment;
import mx.uv.fiee.iinf.recyclerviewfundamentals.Fragments.FragmentListado;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        FragmentListado f = new  FragmentListado ();
        f.setOnPlanetSelectedListener (position -> {
            FrameLayout layout = findViewById (R.id.contentDetalles);

            if (layout != null) {
                getSupportFragmentManager ()
                        .beginTransaction ()
                        .replace (R.id.contentDetalles, new DetallesFragment (position))
                        .setTransition (FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit ();
            } else {
                Intent intent = new Intent (this, DetallesActivity.class);
                intent.putExtra ("POSITION", position);
                startActivity (intent);
            }
        });

        getSupportFragmentManager ()
                .beginTransaction ()
                .add (R.id.container, f)
                .setTransition (FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit ();
    }
}



