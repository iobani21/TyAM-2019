package mx.uv.fiee.iinf.drawermaterialtabdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.ListFragment;

import java.util.LinkedList;

public class NumbersFragment extends ListFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate (R.layout.fragment_numbers, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FragmentActivity activity = getActivity ();
        if (activity == null) return;

        LinkedList<Integer> numbers = new LinkedList<> ();
        for (int i = 0; i < 50; i++) {
            numbers.add (i);
        }

        ArrayAdapter<Integer> adapter = new ArrayAdapter<> (activity, android.R.layout.simple_list_item_1, numbers);
        setListAdapter (adapter);
    }
}
