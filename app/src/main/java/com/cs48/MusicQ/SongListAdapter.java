package com.cs48.MusicQ;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
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
        score.setText(currSong.getScore() + "");
        upArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                songsList.get(i).setUpvotes(songsList.get(i).getUpvotes() + 1);
                Collections.sort(songsList);
                SongListAdapter.super.notifyDataSetChanged();
            }
        });

        downArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                songsList.get(i).setDownvotes(songsList.get(i).getDownvotes() + 1);
                Collections.sort(songsList);
                SongListAdapter.super.notifyDataSetChanged();
            }
        });

        return row;
    }

    public List<Song> getSongsList() {
        return songsList;
    }
}
