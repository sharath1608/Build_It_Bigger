package app.sunshine.android.example.com.build_it_biffer.free;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.jokeapp.backend.myApi.MyApi;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
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
    private InterstitialAd mInterstitialAd;
    private Intent mJokeIntent;
    private static String deviceId;

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
            AdView adView = (AdView) root.findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder()
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    .build();
            adView.loadAd(adRequest);
            mInterstitialAd = new InterstitialAd(getActivity());
            mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
            requestInterstitialAd();

            jokeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }

                mJokeIntent = new Intent(getActivity(), JokeDisplayActivity.class);
                mJokeIntent.putExtra(getString(R.string.joke_key), jokeString);
                mInterstitialAd.setAdListener(new AdListener() {

                    @Override
                    public void onAdClosed() {
                        startActivity(mJokeIntent);
                    }
                });
            }
        });
        return root;
    }

    public void requestInterstitialAd(){
        // Uncomment the below line if using an emulator

        //deviceId = Settings.Secure.getString(getActivity().getContentResolver(), Settings.Secure.ANDROID_ID);
                AdRequest inAdequest = new AdRequest.Builder()
                        .addTestDevice(getString(R.string.dev_id))
                        .build();

        mInterstitialAd.loadAd(inAdequest);
    }

    // Async task to communicate with GCE development server and receive the joke as a string.
    class EndpointTask extends AsyncTask<Void,Void,String> {

        private MyApi myApiService = null;

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            jokeString = s;
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
