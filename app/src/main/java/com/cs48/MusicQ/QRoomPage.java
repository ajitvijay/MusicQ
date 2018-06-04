package com.cs48.MusicQ;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import android.util.Log;
import android.widget.Button;

import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.SpotifyAppRemote;

import com.spotify.protocol.client.Subscription;
import com.spotify.protocol.types.PlayerState;
import com.spotify.protocol.types.Track;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class QRoomPage extends AppCompatActivity {


    TextView text;

    private static final String CLIENT_ID = "194766eebd3c4e0d8422817e3673efef";
    private static final String REDIRECT_URI = "https://spotify.com";
    private SpotifyAppRemote mSpotifyAppRemote;
    private SongListAdapter songListAdapter;
    private ImageView pausePlay, skip;
    private TextView currentSongTextView;

//    private List<Song> songsList;
    private EditText songInput;
    private QRoom currQRoom;


    public class SongListAdapter extends BaseAdapter {

//        private final Activity context;
//        private final List<Song> songsList;

        public SongListAdapter(){

//            this.context = context;
//            this.songsList = data;
        }


        @Override
        public int getCount() {
            return currQRoom.getPlaylist().getSongs().size();
        }

        @Override
        public Object getItem(int i) {
            return currQRoom.getPlaylist().getSongs().get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            Song currSong = currQRoom.getPlaylist().getSongs().get(i);
            LayoutInflater inflater = QRoomPage.super.getLayoutInflater();
            View row = inflater.inflate(R.layout.song_layout, viewGroup, false);
            TextView songName, score;
            ImageView upArrow, downArrow;
            songName = (TextView) row.findViewById(R.id.songNameTextView);
            score = (TextView) row.findViewById(R.id.scoreTextView);
            upArrow = (ImageView) row.findViewById(R.id.upArrow);
            downArrow = (ImageView) row.findViewById(R.id.downArrow);
            songName.setText(currSong.getTitle());
            score.setText(currSong.getScore() + "");
            upArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    currQRoom.getPlaylist().getSongs().get(i).setUpvotes(currQRoom.getPlaylist().getSongs().get(i).getUpvotes() + 1);
                    Collections.sort(currQRoom.getPlaylist().getSongs());
                    SongListAdapter.super.notifyDataSetChanged();
                }
            });

            downArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    currQRoom.getPlaylist().getSongs().get(i).setDownvotes(currQRoom.getPlaylist().getSongs().get(i).getDownvotes() + 1);
                    Collections.sort(currQRoom.getPlaylist().getSongs());
                    SongListAdapter.super.notifyDataSetChanged();
                }
            });

            return row;
        }

        public List<Song> getSongsList() {
            return currQRoom.getPlaylist().getSongs();
        }
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currQRoom = (QRoom) getIntent().getSerializableExtra("qroom");
        //TODO: Check if current user is Leader, and change layout based on that
        //ONLY IF LEADER
        if(currQRoom.getQLeader() == CurrentUser.id) {
            setContentView(R.layout.combined_qroom_layout);
        }
        else {
            setContentView(R.layout.activity_qroom_page);
        }

        initSpotifyConnection();
        initCurrentSongView();
        initListView();

        songInput = (EditText) findViewById(R.id.songinput);
        Button btnAddSong = (Button) findViewById(R.id.btn_add_song);

        btnAddSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newItem = songInput.getText().toString();
                Song songTest = new Song("NewSong", "Unknown", 100, false, 0, 0, "aa");
                currQRoom.getPlaylist().getSongs().add(songTest);
                songListAdapter.notifyDataSetChanged();
//                if(newItem != null) {
//                    arrayList.add(new String(newItem));
//                }
//                adapter.notifyDataSetChanged();


            }
        });



        //text = (TextView) findViewById(R.id.room);
        //text.setText(qRoom1.toString());


    }

    protected void onDestroy() {
        super.onDestroy();
        SpotifyAppRemote.CONNECTOR.disconnect(mSpotifyAppRemote);
    }

    private List<Song> getDummyData(){
        List<Song> songs = new ArrayList<Song>();
        Song song1 = new Song("Big Shot", "Kendrick Lamar", 100, false, 0, 0, "spotify:track:5cXg9IQS34FzLVdHhp7hu7");
        Song song2 = new Song("Powerglide", "Rae Sremmurd", 100, false, 0, 0 , "spotify:track:2yUbCEiaolfSMluDo9RMmG");
        Song song3 = new Song("Nice for What", "Drake", 100, false, 0, 0, "spotify:track:1cTZMwcBJT0Ka3UJPXOeeN");
        Song song4 = new Song("XO TOUR Llif3", "Lil Uzi Vert", 100, false, 0, 0, "spotify:track:7GX5flRQZVHRAGd6B4TmDO");

        songs.add(song1);
        songs.add(song2);
        songs.add(song3);
        songs.add(song4);

        return songs;
    }

    private void initSpotifyConnection(){
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
                        mSpotifyAppRemote.getPlayerApi().play(currQRoom.getCurrentSong().getSpotifyURI());
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

    private void initCurrentSongView(){
        currentSongTextView = (TextView) findViewById(R.id.currentSongName);
        currentSongTextView.setText(currQRoom.getCurrentSong().getTitle());
        pausePlay = (ImageView) findViewById(R.id.pausePlayImage);
        if(currQRoom.isPlaying()) {
            pausePlay.setImageResource(android.R.drawable.ic_media_pause);
        }else{
            pausePlay.setImageResource(android.R.drawable.ic_media_play);
        }
        skip = (ImageView) findViewById(R.id.skipImage);
        pausePlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currQRoom.isPlaying()) {
                    mSpotifyAppRemote.getPlayerApi().pause();
                    pausePlay.setImageResource(android.R.drawable.ic_media_play);
                    currQRoom.setPlaying(false);
                }else{
                    mSpotifyAppRemote.getPlayerApi().resume();
                    pausePlay.setImageResource(android.R.drawable.ic_media_pause);
                    currQRoom.setPlaying(true);
                }

            }
        });
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currQRoom.getPlaylist().getSongs().size() > 1) {
//                    if(currentSongTextView.getText().equals("")) {
                        currQRoom.setCurrentSong(currQRoom.getPlaylist().getSongs().get(0));
                        currQRoom.getPlaylist().getSongs().remove(0);
//                    }

                }
                else{

                    currQRoom.setPlaying(false);
                    currQRoom.setCurrentSong(new Song());
                    pausePlay.setImageResource(android.R.drawable.ic_media_play);
                }
                songListAdapter.notifyDataSetChanged();
                updateCurrentSongView();
            }
        });
    }

    private void initListView(){
        ListView listView = (ListView) findViewById(R.id.songlist);
        currQRoom.getPlaylist().setSongs(getDummyData());
        songListAdapter = new SongListAdapter();
        listView.setAdapter(songListAdapter);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                songListAdapter.notifyDataSetChanged();
            }
        });
    }

    private void updateCurrentSongView(){
        if(currQRoom.getCurrentSong() == null){
            currentSongTextView.setText("");
        }
        else {
            currentSongTextView.setText(currQRoom.getCurrentSong().getTitle());
        }
    }


}

