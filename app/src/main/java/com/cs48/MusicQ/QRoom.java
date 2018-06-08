package com.cs48.MusicQ;

/**
 * Created by ajitvijayakumar on 5/17/18.
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QRoom implements Serializable, QMemberCompositeInterface {
    private List<Integer> members;
    private String name;
    private String code;
    private String existingPlaylistURI;
    private boolean playing;
    private Song currentSong;
    private QList playlist;
    private int QLeader;
    private firebase rootRef;
    private static final long serialVersionUID = 1L;

    public QRoom(List<Integer> members, String name, String code, boolean playing, Song currentSong, QList playlist, int QLeader) {
        this.members = members;
        this.name = name;
        this.code = code;
        this.playing = playing;
        this.currentSong = currentSong;
        this.playlist = playlist;
        this.QLeader = QLeader;
    }
    public QRoom() {
        rootRef = new Firebase("https:://fire-app52930.firebaseio.com/Users");
        Firebase childRef = RootRef.child(key);
        childRef.setMembers(members);
        childRef.setName(name);
        childRef.setCode(code);
        childRef.setPlaylist(playlist);
        childRef.setQLeader(QLeader);
    }

    public void setFireBase(){

    }

    public List<Integer> getMembers() {
        return members;
    }

    public void setMembers(List<Integer> members) {
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExistingPlaylistURI() {
        return existingPlaylistURI;
    }

    public void setExistingPlaylistURI(String playListName) {
        this.existingPlaylistURI = playListName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public Song getCurrentSong() {
        return currentSong;
    }

    public void setCurrentSong(Song currentSong) {
        this.currentSong = currentSong;
    }

    public QList getPlaylist() {
        return playlist;
    }

    public void setPlaylist(QList playlist) {
        this.playlist = playlist;
    }

    public int getQLeader() {
        return QLeader;
    }

    public void setQLeader(int QLeader) {
        this.QLeader = QLeader;
    }

    @Override
    public String toString() {
        return "Name: "+ name + " Code: " + code + "URI: " + existingPlaylistURI;
    }

    @Override
    public void addNewMember(int id) {
        if(!members.contains(id)){
            members.add(id);
        }
    }
}
