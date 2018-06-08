package com.cs48.MusicQ;

import java.io.Serializable;

/**
 * Created by ajitvijayakumar on 5/17/18.
 */

public class Song implements Serializable, Comparable<Song>, QMemberCompositeInterface {
    private String title;
    private String artist;
    private int length;
    private boolean playing;
    private int upvotes;
    private int downvotes;
    private String spotifyURI;
    private String imageUrl;
    private int addedUser;
    private static final long serialVersionUID = 3L;

    //Make call for image when needing it in the actual Activity

    public Song(){
        title = "";
        artist = "";
        length = 0;
        playing = false;
        upvotes = 0;
        downvotes = 0;
        spotifyURI = "";
    }

    public Song(String title, String artist, int length, boolean playing, int upvotes, int downvotes, String spotifyURI) {
        this.title = title;
        this.artist = artist;
        this.length = length;
        this.playing = playing;
        this.upvotes = upvotes;
        this.downvotes = downvotes;
        this.spotifyURI = spotifyURI;
        this.imageUrl = "";
    }

    public Song(String title, String artist, int length, boolean playing, int upvotes, int downvotes, String spotifyURI, String imageUrl) {
        this.title = title;
        this.artist = artist;
        this.length = length;
        this.playing = playing;
        this.upvotes = upvotes;
        this.downvotes = downvotes;
        this.spotifyURI = spotifyURI;
        this.imageUrl = imageUrl;
    }

    @Override
    public int compareTo(Song temp) {
        int voteScore = ((Song) temp).getScore();
        return voteScore - this.getScore();
    }
    
    public int getScore() { return upvotes - downvotes; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public int getDownvotes() {
        return downvotes;
    }

    public void setDownvotes(int downvotes) {
        this.downvotes = downvotes;
    }

    public String getSpotifyURI() {
        return spotifyURI;
    }

    public void setSpotifyURI(String spotifyURI) {
        this.spotifyURI = spotifyURI;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }



    @Override
    public void addNewMember(int id) {
        addedUser = id;
    }
}
