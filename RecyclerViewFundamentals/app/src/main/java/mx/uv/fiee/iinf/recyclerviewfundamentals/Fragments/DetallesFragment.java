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

    public DetallesFragment (int position) {
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

        String [] detallesArray = getResources ().getStringArray (R.array.detalles);
        String [] planetsArray = getResources ().getStringArray (R.array.planets);

        TextView tvDetalles = activity.findViewById (R.id.tvDetalle);
        tvDetalles.setText (detallesArray [position]);

        TextView tvTitle = activity.findViewById (R.id.tvTitle);
        tvTitle.setText (planetsArray [position]);
    }
}
