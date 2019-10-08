package mx.uv.fiee.iinf.tyam.memegrafia;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_details);

//        FrameLayout frameLayout = new FrameLayout (getBaseContext ());
//        frameLayout.setId (View.generateViewId ());
//        LayoutParams layoutParams = new LayoutParams (ViewGroup.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
//        frameLayout.setLayoutParams (layoutParams);
//
//        setContentView (frameLayout);

        if (getIntent () != null) {
            int index = getIntent().getIntExtra(MainActivity.KEY, 0);

            getSupportFragmentManager ()
                    .beginTransaction ()
                    .replace (R.id.detailsContainer, MemeDetailsFragment.newInstance (index))
                    .setTransition (FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit ();
        }
    }
}
