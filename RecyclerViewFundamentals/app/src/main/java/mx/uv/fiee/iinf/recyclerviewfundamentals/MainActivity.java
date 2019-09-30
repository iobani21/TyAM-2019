package mx.uv.fiee.iinf.recyclerviewfundamentals;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        ArrayList<String> planets = new ArrayList<> ();
        planets.add ("Mercurio");
        planets.add ("Venus");
        planets.add ("Tierra");
        planets.add ("Marte");
        planets.add ("Jupiter");
        planets.add ("Saturno");
        planets.add ("Urano");
        planets.add ("Neptuno");

        ArrayAdapter<String> myAdapter =new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, planets);

        RecyclerView recyclerView = findViewById (R.id.myList);
        recyclerView.setAdapter (myAdapter);
    }
}


