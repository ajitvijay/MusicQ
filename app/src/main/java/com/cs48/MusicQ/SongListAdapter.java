package com.cs48.MusicQ;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SongListAdapter extends BaseAdapter {

    private final Activity context;
    private final List<Song> songsList;

    public SongListAdapter(Activity context, List<Song> data){

        this.context = context;
        this.songsList = data;
    }


    @Override
    public int getCount() {
        return songsList.size();
    }

    @Override
    public Object getItem(int i) {
        return songsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Song currSong = songsList.get(i);
        LayoutInflater inflater = context.getLayoutInflater();
        View row = inflater.inflate(R.layout.song_layout, viewGroup, false);
        TextView songName, score;
        ImageView upArrow, downArrow;
        songName = (TextView) row.findViewById(R.id.songNameTextView);
        score = (TextView) row.findViewById(R.id.scoreTextView);
        upArrow = (ImageView) row.findViewById(R.id.upArrow);
        downArrow = (ImageView) row.findViewById(R.id.downArrow);
        songName.setText(currSong.getTitle());
        score.setText(currSong.getUpvotes() - currSong.getDownvotes());

        return row;
    }
}
