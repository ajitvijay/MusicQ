package com.cs48.MusicQ;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.util.Log;
import android.widget.Button;

import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.SpotifyAppRemote;

import com.spotify.protocol.client.Subscription;
import com.spotify.protocol.types.PlayerContext;
import com.spotify.protocol.types.PlayerState;
import com.spotify.protocol.types.Track;

public class MainActivity extends AppCompatActivity {
    Button mButton1;
    Button mButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get UI references
        findViewsById();

        // Set listener for the Views (like ImageView, TextView, Button, etc)
        setListenerForViews();
    }

    private void findViewsById() {

        try {
            /* Get a reference for each button */
            mButton1 = (Button) findViewById(R.id.button1);
            mButton2 = (Button) findViewById(R.id.button2);

        } catch (NullPointerException exc) {
            exc.printStackTrace();
        }

    }

    private void setListenerForViews() {

        mButton1.setOnClickListener(myListener);
        mButton2.setOnClickListener(myListener);

    }

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == mButton1) {

                Intent inten1 = new Intent(MainActivity.this, CreateRoomPage.class);
                startActivity(inten1);
            }
            if (view == mButton2) {
                Intent tip = new Intent(MainActivity.this, JoinRoomPage.class);
                startActivity(tip);
            }
        }
    };
}