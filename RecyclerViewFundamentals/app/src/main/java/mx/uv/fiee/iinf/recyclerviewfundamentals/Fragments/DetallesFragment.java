package mx.uv.fiee.iinf.recyclerviewfundamentals.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import mx.uv.fiee.iinf.recyclerviewfundamentals.R;

public class DetallesFragment extends Fragment {
    private int position;

    public DetallesFragment () {
        super ();
    }

    /**
     * Constructor no estándard que nos sirve para recibir el parámetro entero emdiante el cuál
     * se obtendrá la información almacenada en los arrays.
     *
     * La documentación desaconseja modificar los constructores ya que son utilizados para persitir
     * la información de los controles ante los cambios de estado. Se recomienda en su lugar usar el patrón de diseño factory.
     *
     * @param position indice del elemento seleccionado en la lista de planetas.
     */
    public DetallesFragment (int position) {
        super ();
        this.position = position;
    }

    @Nullable
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate (R.layout.fragment_detalles, container, false);
    }

    @Override
    public void onActivityCreated (@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FragmentActivity activity = getActivity ();
        if (activity == null) return;

        // la información mostrada depende de los arrays almacenados en los recursos
        // y se obtiene mediante el parámetros position recibido en el constructor
        String [] detallesArray = getResources ().getStringArray (R.array.detalles);
        String [] planetsArray = getResources ().getStringArray (R.array.planets);

        TextView tvDetalles = activity.findViewById (R.id.tvDetalle);
        if (tvDetalles != null) tvDetalles.setText (detallesArray [position]);

        TextView tvTitle = activity.findViewById (R.id.tvTitle);
        if (tvTitle != null) tvTitle.setText (planetsArray [position]);
    }
}
