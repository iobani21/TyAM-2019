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

        // el método getActivity regresa una referencia a la actividad que hospeda al framento
        FragmentActivity activity = getActivity ();
        if (activity == null) return;

        String [] planetsArray = activity.getResources ().getStringArray (R.array.planets);
        List<String> planets = Arrays.asList (planetsArray);

        RecyclerView recyclerView = activity.findViewById (R.id.myList);
        if (recyclerView ==  null) return;

        recyclerView.setLayoutManager (new LinearLayoutManager(activity, RecyclerView.VERTICAL, false));

        // se crea un objeto MyAdapter al que se pasan los parámetros necesarios
        // context: la actividad que hospeda al fragmento
        // List<Planets>: la lista con la información al mostrar
        // OnPlanetSelected: el objeto listener mediante el cuál se invocará al manejador del evento planetSelected para los elementos de la lista
        recyclerView.setAdapter (new MyAdapter (activity, planets, this.listener));
    }

    /**
     *  Asigna al manejador del elemento seleccionad en la lista
     * @param listener Objeto que implementa la interfaz OnPlanetSelected
     */
    public void setOnPlanetSelectedListener (OnPlanetSelected listener) {
        this.listener = listener;
    }
}

/**
 * Adapter para iterar por la lista de planetas. La referencia a la función de cada método está explicada en la lamina
 * que puede encotrarse en el contenido del curso.
 *
 *
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
        // se asigna la información en cada fila de la lista y se establece el manejador del evento OnClickListener,
        // de modo que al seleccionarse se invoque al método planetSelected en el objeto que manejará dicho evento
        holder.setData (planets.get (position));
        holder.itemView.setOnClickListener (view -> listener.planetSelected (position));
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
