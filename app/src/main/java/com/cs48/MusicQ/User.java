package com.cs48.MusicQ;

/**
 * Created by ajitvijayakumar on 5/17/18.
 */

import java.util.List;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private List<QList> playlists;
    private QRoom currentRoom;
    private boolean inRoom;
    private boolean spotifyMember;


    public User(){}
    public User (int id, String firstName, String lastName, String username, String password, List<QList> playlists, QRoom currentRoom, boolean inRoom, boolean spotifyMember) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.playlists = playlists;
        this.currentRoom = currentRoom;
        this.inRoom = inRoom;
        this.spotifyMember = spotifyMember;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<QList> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<QList> playlists) {
        this.playlists = playlists;
    }

    public QRoom getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(QRoom currentRoom) {
        this.currentRoom = currentRoom;
    }

    public boolean isInRoom() {
        return inRoom;
    }

    public void setInRoom(boolean inRoom) {
        this.inRoom = inRoom;
    }

    public boolean isSpotifyMember() {
        return spotifyMember;
    }

    public void setSpotifyMember(boolean spotifyMember) {
        this.spotifyMember = spotifyMember;
    }
}
