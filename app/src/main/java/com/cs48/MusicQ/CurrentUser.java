package com.cs48.MusicQ;

import java.util.ArrayList;
import java.util.List;

public final class CurrentUser extends User{
    public static int id = 1;
    public static String firstName = "Kaushik";
    public String lastName = "Mahorker";
    public static String username = "kaushik";
//    public static String password;
    public static List<QList> playlists = new ArrayList<>();
    public static QRoom currentRoom = new QRoom();
    public static boolean inRoom = false;
    public static boolean spotifyMember = false;


}
