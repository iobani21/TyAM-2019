package mx.uv.fiee.iinf.rssfeedsfetcher;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        TextView rssFeed = findViewById (R.id.rssfeed);

        String rss = downloadRSS ("https://www.nasa.gov/rss/dyn/lg_image_of_the_day.rss");

        if (rss != null) {
            rssFeed.setText(rss);
        }
    }

    private String downloadRSS (String uri) {
        String foo = null;

        try {
            URL url = new URL (uri);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection ();
            urlConnection.setConnectTimeout (5000);

            if (urlConnection.getResponseCode () != HttpURLConnection.HTTP_OK) return foo;

            InputStreamReader inputStreamReader = new InputStreamReader (urlConnection.getInputStream (), StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader (inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder ();
            String line;

            while ((line = bufferedReader.readLine ()) != null) {
                stringBuilder.append (line);
            }

            bufferedReader.close ();
            inputStreamReader.close ();

            urlConnection.disconnect ();
            foo = stringBuilder.toString ();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return foo;
    }

    private Bitmap downloadImage (String uri) {
        Bitmap foo = null;

        try {
            URL url = new URL (uri);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection ();

            if (urlConnection.getResponseCode() != HttpURLConnection.HTTP_OK) return foo;

            InputStream inputStream = urlConnection.getInputStream ();
            foo = BitmapFactory.decodeStream (inputStream);

//            BufferedInputStream bis = new BufferedInputStream (inputStream);
//            ByteArrayOutputStream data = new ByteArrayOutputStream ();
//            byte [] buffer = new byte [1024];
//            int bytesRead = 0;
//
//            while ((bytesRead = bis.read (buffer, 0, buffer.length)) > 0) {
//                data.write (buffer, 0, bytesRead);
//            }
//
//            foo = BitmapFactory.decodeByteArray (data.toByteArray (), 0, data.size ());
//            bis.close ();

            inputStream.close ();
            urlConnection.disconnect ();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return foo;
    }
}


