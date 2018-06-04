package com.cs48.MusicQ;

import java.util.List;

/**
 * Created by ajitvijayakumar on 6/4/18.
 */

public final class QRoomStatic {

//    public static QRoom qRoom;

    public static List<Integer> members;
    public static String name;
    public static String code;
    public static boolean playing;
    public static Song currentSong;
    public static QList playlist;
    public static int QLeader;


    public static QRoom generateQRoomObject(){
        QRoom qRoom = new QRoom(members, name, code, playing, currentSong, playlist, QLeader);
        return qRoom;
    }

    public static void populateFields(QRoom qRoom){
        members = qRoom.getMembers();
        name = qRoom.getName();
        code = qRoom.getCode();
        playing = qRoom.isPlaying();
        currentSong = qRoom.getCurrentSong();
        playlist = qRoom.getPlaylist();
        QLeader = qRoom.getQLeader();
    }
}
