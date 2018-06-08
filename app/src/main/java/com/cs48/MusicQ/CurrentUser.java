package com.cs48.MusicQ;

import java.util.ArrayList;
import java.util.List;

public final class CurrentUser{
    private static int id = 1;
    private static String firstName = "Kaushik";
    private static String lastName = "Mahorker";
    private static String username = "kaushik";
    private static String password = "";
    private static List<QList> playlists = new ArrayList<>();
    private static QRoom currentRoom = new QRoom();
    private static boolean inRoom = false;
    private static boolean spotifyMember = false;

    protected CurrentUser(){
        
    }

    public static User getInstance() {
        User user = new User(id, firstName, lastName, username, password, playlists, currentRoom, inRoom, spotifyMember);
        return user;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        CurrentUser.id = id;
    }

    public static String getFirstName() {
        return firstName;
    }

    public static void setFirstName(String firstName) {
        CurrentUser.firstName = firstName;
    }

    public static String getLastName() {
        return lastName;
    }

    public static void setLastName(String lastName) {
        CurrentUser.lastName = lastName;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        CurrentUser.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        CurrentUser.password = password;
    }

    public static List<QList> getPlaylists() {
        return playlists;
    }

    public static void setPlaylists(List<QList> playlists) {
        CurrentUser.playlists = playlists;
    }

    public static QRoom getCurrentRoom() {
        return currentRoom;
    }

    public static void setCurrentRoom(QRoom currentRoom) {
        CurrentUser.currentRoom = currentRoom;
    }

    public static boolean isInRoom() {
        return inRoom;
    }

    public static void setInRoom(boolean inRoom) {
        CurrentUser.inRoom = inRoom;
    }

    public static boolean isSpotifyMember() {
        return spotifyMember;
    }

    public static void setSpotifyMember(boolean spotifyMember) {
        CurrentUser.spotifyMember = spotifyMember;
    }
}
