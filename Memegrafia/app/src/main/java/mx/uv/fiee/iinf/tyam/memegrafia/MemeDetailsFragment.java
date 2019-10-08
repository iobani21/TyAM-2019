package mx.uv.fiee.iinf.tyam.memegrafia;

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

public class MemeDetailsFragment extends Fragment {

    public static MemeDetailsFragment newInstance (int index) {
        MemeDetailsFragment fragment = new MemeDetailsFragment ();

        Bundle b = new Bundle ();
        b.putInt (MainActivity.KEY, index);
        fragment.setArguments (b);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate (R.layout.fragment_meme_details, container, false);
    }

    @Override
    public void onActivityCreated (@Nullable Bundle savedInstanceState) {
        super.onActivityCreated (savedInstanceState);

        Bundle b = getArguments ();
        if (b == null) return;
        int index = b.getInt (MainActivity.KEY);

        FragmentActivity activity = getActivity ();
        if (activity == null) return;

        TextView tvTitle        = activity.findViewById (R.id.tvName);
        ImageView memeImage     = activity.findViewById (R.id.meme_image);
        TextView tvDescription  = activity.findViewById (R.id.tvDescription);
        TextView tvUrl          = activity.findViewById (R.id.tvUrl);

        String name             = activity.getResources ().getStringArray (R.array.names) [index];
        String description      = activity.getResources ().getStringArray (R.array.descriptions) [index];
        String url              = activity.getResources ().getStringArray (R.array.urls) [index];
        TypedArray typedArray   = activity.getResources ().obtainTypedArray (R.array.images);
        Drawable meme           = typedArray.getDrawable (index);

        tvTitle.setText (name);
        tvDescription.setText (description);
        tvUrl.setText (url);
        memeImage.setImageDrawable (meme);
        typedArray.recycle ();
    }
}
