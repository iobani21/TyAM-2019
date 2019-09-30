package mx.uv.fiee.iinf.recyclerviewfundamentals;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
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

        RecyclerView recyclerView = findViewById (R.id.myList);
        recyclerView.setLayoutManager (new LinearLayoutManager (this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter (new MyAdapter (this, planets));
    }
}

class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<String> planets;

    MyAdapter (Context context, ArrayList<String> planets) {
        this.context = context;
        this.planets = planets;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from (context);
        View view = inflater.inflate (android.R.layout.simple_list_item_1, parent, false);
        return new MyViewHolder (view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final String planet = planets.get (position);
        holder.setData (planet);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText (context, planet, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return planets.size ();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView text1;

        MyViewHolder(@NonNull View itemView) {
            super (itemView);
            text1 = itemView.findViewById (android.R.id.text1);
        }

        void setData (String data) {
            text1.setText (data);
        }
    }
}



