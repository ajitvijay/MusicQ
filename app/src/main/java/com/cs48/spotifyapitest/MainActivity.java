package com.cs48.spotifyapitest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;

import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.SpotifyAppRemote;

import com.spotify.protocol.client.Subscription;
import com.spotify.protocol.types.PlayerContext;
import com.spotify.protocol.types.PlayerState;
import com.spotify.protocol.types.Track;

public class MainActivity extends AppCompatActivity {

    private static final String CLIENT_ID = "194766eebd3c4e0d8422817e3673efef";
    private static final String REDIRECT_URI = "https://spotify.com";
    private SpotifyAppRemote mSpotifyAppRemote;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                        mSpotifyAppRemote.getPlayerApi().play("spotify:user:nehagubbi:playlist:32TZiwvQNWf9AEJyHH2Ezj");

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