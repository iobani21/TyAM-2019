package mx.uv.fiee.iinf.toolbarmenuexample;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    TextView tvContent;

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        Toolbar toolbar = findViewById (R.id.toolbar);
        setSupportActionBar (toolbar);

        tvContent = findViewById (R.id.tvContent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater ().inflate (R.menu.main, menu);
        return super.onCreateOptionsMenu (menu);
    }

    @Override
    public boolean onOptionsItemSelected (@NonNull MenuItem item) {
        switch (item.getItemId ()) {
            case R.id.item1:
                Snackbar.make (tvContent, "Favorites clicked!", Snackbar.LENGTH_LONG).show ();
                break;
            case R.id.item2:
                Snackbar.make (tvContent, "Location clicked!", Snackbar.LENGTH_LONG).show ();
                break;
            case R.id.item3:
                Snackbar.make (tvContent, "Phone call clicked!", Snackbar.LENGTH_LONG).show ();
                break;
            case R.id.item4:
                Snackbar.make (tvContent, "Calendar clicked!", Snackbar.LENGTH_LONG).show ();
                break;
        }

        return super.onOptionsItemSelected (item);
    }
}
