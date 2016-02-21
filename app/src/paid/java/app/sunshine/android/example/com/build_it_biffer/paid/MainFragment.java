package app.sunshine.android.example.com.build_it_biffer.paid;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.jokeapp.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import java.io.IOException;
import app.sunshine.android.example.com.androidjokedisplaylib.JokeDisplayActivity;
import app.sunshine.android.example.com.build_it_biffer.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private String jokeString;
    private TextView mTestTv;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            new EndpointTask().execute();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View root = inflater.inflate(R.layout.fragment_main, container, false);
            final Button jokeButton = (Button) root.findViewById(R.id.joke_button);
            mTestTv = (TextView) root.findViewById(R.id.test_result_text);
            jokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent jokeIntent = new Intent(getActivity(), JokeDisplayActivity.class);
                jokeIntent.putExtra(getString(R.string.joke_key), jokeString);
                startActivity(jokeIntent);
            }
        });
        return root;
    }



    // Async task to communicate with GCE development server and receive the joke as a string.
    public class EndpointTask extends AsyncTask<Void,Void,String> {
        private MyApi myApiService = null;

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            jokeString = s;
            mTestTv.setText(jokeString);
        }

        @Override
        protected String doInBackground(Void... pairs) {

            if (myApiService == null) {
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                        .setRootUrl(getString(R.string.root_url))
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                                request.setDisableGZipContent(true);
                            }
                        });

                myApiService = builder.build();
            }
            try {
                return myApiService.getRandomJoke().execute().getData();
            } catch (IOException e) {
                e.printStackTrace();
                return getString(R.string.fetch_error_user);
            }
        }
    }

}
