package org.ometa.instagrammer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ometa.instagrammer.adapters.InstagramPhotosAdapter;
import org.ometa.instagrammer.models.Photo;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    public static final String CLIENT_ID= "709b61ee280746bb8c8e70a6b2aed3a4";

    private ArrayList<Photo> photos;
    private InstagramPhotosAdapter aPhotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

        photos = new ArrayList<>();
        aPhotos = new InstagramPhotosAdapter(this, photos);

        ListView lvPhotos = (ListView) findViewById(R.id.lvPhotos);
        lvPhotos.setAdapter(aPhotos);

        fetchPopularPhotos();

        // create adaptor linknig it to source

//        We should be using the following concepts:
//        ● RelativeLayout to position multiple views
//        ● Sending network requests to fetch popular photos
//        ● Decoding network response into data models
//        ● Custom adapter to populate images into list
//        ● Image loader to display remote images

//        User Stories:
//
//        The following user stories must be completed:
//
//        User can scroll through current popular photos from Instagram
//        For each photo displayed, user can see the following details:
//        Graphic, Caption, Username
//                (Optional) relative timestamp, like count, user profile image
//        The following advanced user stories are optional:
//
//        Advanced: Add pull-to-refresh for popular stream with SwipeRefreshLayout
//        Advanced: Show latest comment for each photo (bonus: show last 2 comments)
//        Advanced: Display each photo with the same style and proportions as the real Instagram (see screens below)
//        Advanced: Display each user profile image using a RoundedImageView
//        Advanced: Display a nice default placeholder graphic for each image during loading (read more about Picasso)
//        Advanced: Improve the user interface through styling and coloring
//                Bonus: Allow user to view all comments for an image within a separate activity or dialog fragment
//        Bonus: Allow video posts to be played in full-screen using the VideoView

    }

    public void fetchPopularPhotos() {
        String url = "https://api.instagram.com/v1/media/popular?client_id=" + CLIENT_ID;

        AsyncHttpClient client = new AsyncHttpClient();

        client.get(url, null, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray photosJSON = null;
                try {
                    photosJSON = response.getJSONArray("data");
                    for (int i = 0; i < photosJSON.length(); i++) {
                        JSONObject photoJSON = photosJSON.getJSONObject(i);
                        photos.add(new Photo(photoJSON));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                aPhotos.notifyDataSetChanged();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                //super.onFailure(statusCode, headers, responseString, throwable);
                Log.i("DEBUG", responseString);
                Log.i("HEADERS", headers.toString());
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
