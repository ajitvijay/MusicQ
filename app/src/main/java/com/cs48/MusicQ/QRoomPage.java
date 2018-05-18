package com.cs48.MusicQ;

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

import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.SpotifyAppRemote;

import com.spotify.protocol.client.Subscription;
import com.spotify.protocol.types.PlayerContext;
import com.spotify.protocol.types.PlayerState;
import com.spotify.protocol.types.Track;

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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qroom_page);

        ListView listView = (ListView) findViewById(R.id.songlist);
        String[] items = {"coordinate-Travis Scott", "Connect The Dots-Meek Mill", "Ran It Up-Rich " +
                "the Kid"};
        arrayList = new ArrayList<>(Arrays.asList(items));
        adapter = new ArrayAdapter<String>(this, R.layout.song_layout, R.id.txtitem,arrayList);
        listView.setAdapter(adapter);
        SongInput = (EditText) findViewById(R.id.songinput);
        Button btnAddSong = (Button) findViewById(R.id.btn_add_song);
        btnAddSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newItem = SongInput.getText().toString();
                if(newItem != null) {
                    arrayList.add(new String(newItem));
                }
                adapter.notifyDataSetChanged();


            }
        });

        Intent intent = getIntent();
        final QRoom qRoom1 = (QRoom) intent.getSerializableExtra("qroom");


        //text = (TextView) findViewById(R.id.room);
        //text.setText(qRoom1.toString());

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


}

