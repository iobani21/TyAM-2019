package mx.uv.fiee.iinf.activitypassdata;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        imageView = findViewById (R.id.imageView1);

        Button button = findViewById (R.id.takePhoto);
        button.setOnClickListener (v -> {
            if (!getPackageManager().hasSystemFeature (PackageManager.FEATURE_CAMERA_ANY)) {
                Toast.makeText (getBaseContext (), "Camera not found!", Toast.LENGTH_LONG).show ();
            }

            int permission = ContextCompat.checkSelfPermission (this, Manifest.permission.CAMERA);
            if (permission == PackageManager.PERMISSION_GRANTED) {
                launchCamera ();
            } else {
                ActivityCompat.requestPermissions (this, new String [] {Manifest.permission.CAMERA}, 1002);
            }
        });

    }

    private void launchCamera () {
        Intent intent = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult (intent, 1001);
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1001) {
            if (resultCode == RESULT_OK) {
                Bundle extras;

                if (data != null) {
                    extras = data.getExtras ();

                    if (extras != null) {
                        Bitmap bitmap = (Bitmap) extras.get ("data");
                        imageView.setImageBitmap (bitmap);
                    }
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult (int requestCode, @NonNull String [] permissions, @NonNull int [] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1002) {
            if (grantResults.length > 0 && grantResults [0] == PackageManager.PERMISSION_GRANTED) {
                launchCamera ();
            }
        }
    }
}
