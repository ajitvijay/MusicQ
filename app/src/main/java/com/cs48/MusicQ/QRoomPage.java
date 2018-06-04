package com.cs48.MusicQ;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextClock;
import android.widget.TextView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.widget.Button;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;


import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.SpotifyAppRemote;

import com.spotify.protocol.client.Subscription;
import com.spotify.protocol.types.PlayerContext;
import com.spotify.protocol.types.PlayerState;
import com.spotify.protocol.types.Track;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;


public class QRoomPage extends AppCompatActivity {
    TextView text;

    private static final String CLIENT_ID = "194766eebd3c4e0d8422817e3673efef";
    private static final String REDIRECT_URI = "https://spotify.com";
    private SpotifyAppRemote mSpotifyAppRemote;

    private ArrayList<String> arrayList;
    private ArrayAdapter<String> adapter;
    private EditText SongInput;
    private RequestQueue myRequestQueue;

    Button btn1;
    Button btn2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qroom_page);

        findViewsById();
        setListenerForViews();

        ListView listView = (ListView) findViewById(R.id.songlist);
        String[] items = {"coordinate-Travis Scott", "Connect The Dots-Meek Mill", "Ran It Up-Rich " +
                "the Kid"};
        arrayList = new ArrayList<>(Arrays.asList(items));
        adapter = new ArrayAdapter<String>(this, R.layout.song_layout, R.id.txtitem,arrayList);
        listView.setAdapter(adapter);
        SongInput = (EditText) findViewById(R.id.songinput);
        myRequestQueue = Volley.newRequestQueue(this);
        myRequestQueue.start();

        Intent intent = getIntent();
        final QRoom qRoom1 = (QRoom) intent.getSerializableExtra("qroom");



        ConnectionParams connectionParams =
                new ConnectionParams.Builder(CLIENT_ID)
                        .setRedirectUri(REDIRECT_URI)
                        .showAuthView(true)
                        .build();

        SpotifyAppRemote.CONNECTOR.connect(this, connectionParams,
                new Connector.ConnectionListener() {

                    public void onConnected(SpotifyAppRemote spotifyAppRemote) {
                        mSpotifyAppRemote = spotifyAppRemote;
                        // Interact with App Remote here
                        Log.d("onConnected", "hey");

                        // Play a playlist
                        //SpotifyAppRemote.getPlayerApi().play("spotify:user:nehagubbi:playlist:32TZiwvQNWf9AEJyHH2Ezj");
                        mSpotifyAppRemote.getPlayerApi().play(qRoom1.getExistingPlaylistURI());
                        // Subscribe to PlayerState
                        mSpotifyAppRemote.getPlayerApi()
                                .subscribeToPlayerState().setEventCallback(new Subscription.EventCallback<PlayerState>() {

                            public void onEvent(PlayerState playerState) {
                                final Track track = playerState.track;
                                if (track != null) {
                                    Log.d("MainActivity", track.name + " by " + track.artist.name);
                                }
                            }
                        });
                    }

                    public void onFailure(Throwable throwable) {
                        // Handle errors here
                        Log.e("MyActivity", throwable.getMessage(), throwable);
                    }
                });
    }

    protected void onDestroy() {
        super.onDestroy();
        SpotifyAppRemote.CONNECTOR.disconnect(mSpotifyAppRemote);
    }

    private void findViewsById() {

        try {
            /* Get a reference for each button */
            btn1 = (Button) findViewById(R.id.btn_add_song);
            btn2 = (Button) findViewById(R.id.searchsong);

        } catch (NullPointerException exc) {
            exc.printStackTrace();
        }

    }
    private void setListenerForViews() {

        btn1.setOnClickListener(myListener);
        btn2.setOnClickListener(myListener);

    }
    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String urllink = "https://api.spotify.com/v1/search?q=";
            String urllink2 =  "&type=track&market=US&limit=1&offset=1";

            if (view == btn1) {
                // add song
                String inputer = SongInput.getText().toString();
                String[] splitStr = inputer.split("\\s+");
                for(int i =0; i < splitStr.length; i++){
                    if(i!= splitStr.length-1) {
                     urllink = urllink + splitStr[i] + "%20";
                    }
                    else {
                        urllink = urllink + splitStr[i];
                    }
                }
                String postreturn = urllink + urllink2;
                JSONObject request = passUrl(postreturn);
                if(request!= null){
                    try {
                        JSONArray jarray =  request.getJSONArray("items");
                        JSONArray artistarray = jarray.getJSONArray(1); //artists
                        String songname = jarray.getString(11); //song name
                        String songcode = jarray.getString(16); // URI code
                        String artistname = artistarray.getString(4);
                        int duration = jarray.getInt(4);
                        AlertDialog.Builder builder = new AlertDialog.Builder(QRoomPage.this);
                        builder.setTitle(songname );
                        builder.setNegativeButton(songcode, null);
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            }
            if (view == btn2) {
                // search song

            }
        }
    };


    private JSONObject passUrl(String url){
        final JSONObject[] responseOut = new JSONObject[1];
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (response.has("error")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(QRoomPage.this);
                    builder.setTitle("Error Found");
                    builder.setNegativeButton("Ok", null);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    Log.d("error", "ERROR IN VOLLEY REQ");
                    responseOut[0] = null;
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(QRoomPage.this);
                    builder.setTitle("Request Sucessfull!");
                    builder.setNegativeButton("Ok", null);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    responseOut[0] = response;
                    Log.d("success", response.toString());
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", "Bearer BQCWT0w-3A9YND22mNTDJLkrW4uH8k6cloYXalQBaPB4qG-9wvT0TxF_fgb2wAYvB_0ucmNZwEMzVAZGikg9tfW8bJedRUbcicTV8QvywMM3PXZOgmY6OnMID15m007N4PAm23r75jKjNg");
                return headers;
            }
        };

        myRequestQueue.add(request);

        return responseOut[0];
    }

}

