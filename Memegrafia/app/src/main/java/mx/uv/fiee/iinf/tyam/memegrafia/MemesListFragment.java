package mx.uv.fiee.iinf.tyam.memegrafia;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MemesListFragment extends Fragment {
    private OnMemeTouchedListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach (context);

        if (context instanceof OnMemeTouchedListener) {
            listener = (OnMemeTouchedListener) context;
        } else {
            throw new ClassCastException ("Must implement OnMemeTouchedListener first!");
        }
    }

    @Nullable
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate (R.layout.fragment_memes_list, container, false);
    }

    @Override
    public void onActivityCreated (@Nullable Bundle savedInstanceState) {
        super.onActivityCreated (savedInstanceState);

        FragmentActivity activity = getActivity ();
        if (activity == null) return;

        String [] namesArray = activity.getResources ().getStringArray (R.array.names);
        TypedArray memesArray = activity.getResources ().obtainTypedArray (R.array.images);

        RecyclerView recyclerView = activity.findViewById (R.id.recview);
        recyclerView.setLayoutManager (new GridLayoutManager(getContext (), 2, GridLayoutManager.VERTICAL, false));
        recyclerView.setAdapter (new MemesListAdapter (getContext (), namesArray, memesArray, listener));
   }
}

class MemesListAdapter extends RecyclerView.Adapter<MemesViewHolder> {
    private Context context;
    private String [] names;
    private TypedArray memes;
    private OnMemeTouchedListener listener;

    MemesListAdapter (Context context, String [] names, TypedArray memes, OnMemeTouchedListener listener) {
        this.context = context;
        this.names = names;
        this.memes = memes;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MemesViewHolder onCreateViewHolder (@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from (context).inflate (R.layout.recycler_list_item, viewGroup, false);
        return new MemesViewHolder (view);
    }

    @Override
    public void onBindViewHolder (@NonNull MemesViewHolder memesViewHolder, int i) {
        memesViewHolder.bind (names [i], memes.getDrawable (i));

        memesViewHolder.itemView.setOnClickListener (view -> {
            if (listener != null) listener.onMemeTouched (i);
        });
    }

    @Override
    public int getItemCount () {
        return names.length;
    }
}

class MemesViewHolder extends RecyclerView.ViewHolder {
    private ImageView memeImage;
    private TextView name;

    MemesViewHolder (@NonNull View itemView) {
        super (itemView);

        memeImage = itemView.findViewById (R.id.meme_image);
        name = itemView.findViewById (R.id.name);
    }

    void bind (String text, Drawable drawable) {
        memeImage.setImageDrawable (drawable);
        name.setText (text);
    }

}
