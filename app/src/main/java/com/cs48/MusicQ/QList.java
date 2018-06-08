package com.cs48.MusicQ;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ajitvijayakumar on 5/17/18.
 */

public class QList implements Serializable, QMemberCompositeInterface{
    private String name;
    private List<Song> songs;
    private List<Integer> members;
    private static final long serialVersionUID = 2L;

    public QList(){
        name = "";
        songs = new ArrayList<>();
    }

    public QList(String name, List<Song> songs) {
        this.name = name;
        this.songs = songs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public List<Integer> getMembers() {
        return members;
    }

    public void setMembers(List<Integer> members) {
        this.members = members;
    }


    @Override
    public void addNewMember(int id) {
        members.add(id);
    }
}
