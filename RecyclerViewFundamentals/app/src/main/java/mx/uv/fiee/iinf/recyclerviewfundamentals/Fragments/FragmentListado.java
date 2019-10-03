package mx.uv.fiee.iinf.recyclerviewfundamentals.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

import mx.uv.fiee.iinf.recyclerviewfundamentals.Interfaces.OnPlanetSelected;
import mx.uv.fiee.iinf.recyclerviewfundamentals.R;

public class FragmentListado extends Fragment {
    private OnPlanetSelected listener;

    @Nullable
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate (R.layout.fragment_listado, container, false);
    }

    @Override
    public void onActivityCreated (@Nullable Bundle savedInstanceState) {
        super.onActivityCreated (savedInstanceState);

        FragmentActivity activity = getActivity ();
        if (activity == null) return;

        String [] planetsArray = activity.getResources ().getStringArray (R.array.planets);
        List<String> planets = Arrays.asList (planetsArray);

        RecyclerView recyclerView = activity.findViewById (R.id.myList);
        if (recyclerView ==  null) return;

        recyclerView.setLayoutManager (new LinearLayoutManager(activity, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter (new MyAdapter (activity, planets, this.listener));
    }

    public void setOnPlanetSelectedListener (OnPlanetSelected listener) {
        this.listener = listener;
    }
}

/**
 * Adapter para iterar por la lista de planetas
 */
class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private List<String> planets;
    private OnPlanetSelected listener;

    MyAdapter (Context context, List<String> planets, OnPlanetSelected listener) {
        this.context = context;
        this.planets = planets;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from (context);
        View view = inflater.inflate (android.R.layout.simple_list_item_1, parent, false);
        return new MyViewHolder (view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final String planet = planets.get (position);
        holder.setData (planet);

        holder.itemView.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.planetSelected (position);
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
