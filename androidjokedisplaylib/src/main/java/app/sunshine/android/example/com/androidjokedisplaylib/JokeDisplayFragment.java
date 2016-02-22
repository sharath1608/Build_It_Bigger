package app.sunshine.android.example.com.androidjokedisplaylib;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;


/**
 * A simple {@link Fragment} subclass.
 */
public class JokeDisplayFragment extends Fragment {

    private ImageView gifImageView;
    private TextView jokeTextView;

    private ProgressBar progBar;

    public JokeDisplayFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_main_lib, container, false);
        gifImageView = (ImageView) root.findViewById(R.id.gifView);
        jokeTextView = (TextView) root.findViewById(R.id.jokeText);
        Typeface customFont = Typeface.createFromAsset(getActivity().getAssets(), "ComicRelief.ttf");
        jokeTextView.setTypeface(customFont);
        progBar = (ProgressBar) root.findViewById(R.id.prog_bar);
        progBar.setVisibility(View.VISIBLE);
        String jokeLongString = getActivity().getIntent().getStringExtra("myjoke");
        jokeTextView.setText(jokeLongString);
        if (jokeLongString==null || jokeLongString.equals(getString(R.string.fetch_error_user))){
            Toast.makeText(getActivity(), getString(R.string.fetch_error_toast), Toast.LENGTH_LONG).show();
            progBar.setVisibility(View.GONE);
            return root;
        }
        Ion.with(getActivity())
                .load(Utils.returnRandomString(getActivity()))
                .intoImageView(gifImageView)
                .setCallback(new FutureCallback<ImageView>() {
                    @Override
                    public void onCompleted(Exception e, ImageView result) {
                        progBar.setVisibility(View.GONE);
                    }
                });

        return root;
    }
}
