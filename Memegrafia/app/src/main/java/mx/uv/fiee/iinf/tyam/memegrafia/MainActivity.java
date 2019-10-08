package mx.uv.fiee.iinf.tyam.memegrafia;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements OnMemeTouchedListener {
    public static final String KEY = "PKAT";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView (R.layout.activity_main);

        getSupportFragmentManager ()
                .beginTransaction ()
                .replace (R.id.rootContainer, new MemesListFragment ())
                .setTransition (FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit ();
    }

    @Override
    public void onMemeTouched (int index) {
        //Snackbar.make (findViewById (R.id.rootContainer), name, Snackbar.LENGTH_LONG).show ();
        Intent intent = new Intent (getBaseContext (), DetailsActivity.class);
        intent.putExtra (KEY, index);
        startActivity (intent);
    }
}
