package mx.uv.fiee.iinf.toolbarmenuexample;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

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
                Intent intent = new Intent (getBaseContext (), SecondActivity.class);
                startActivity (intent);
                break;
            case R.id.item2:
                Snackbar.make (tvContent, "Location clicked!", Snackbar.LENGTH_LONG).show ();
                startActivity (getIntent ());
                break;
            case R.id.item3:
                Snackbar.make (tvContent, "Phone call clicked!", Snackbar.LENGTH_LONG).show ();
//                Intent intent1 = new Intent (Intent.ACTION_CALL);
//                intent1.setData (Uri.parse ("tel: 01800123000"));
//                startActivity (intent1);

                Intent intent3 = new Intent (this, ThirdActivity.class);
                startActivityForResult (intent3, 1001);
                break;
            case R.id.item4:
                Snackbar.make (tvContent, "Calendar clicked!", Snackbar.LENGTH_LONG).show ();
                break;
        }

        return super.onOptionsItemSelected (item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1001) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    String name = data.getStringExtra ("name");
                    Toast.makeText(this, name, Toast.LENGTH_LONG).show();
                }
            }
        }

    }
}
