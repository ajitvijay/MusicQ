package com.cs48.MusicQ;

import java.util.List;

/**
 * Created by ajitvijayakumar on 6/4/18.
 */

public final class QRoomStatic {

//    public static QRoom qRoom;

    private static List<Integer> members;
    private static String name;
    private static String code;
    private static boolean playing;
    private static Song currentSong;
    private static QList playlist;
    private static int QLeader;

    protected QRoomStatic() {
    }

    public static QRoom getInstance(){
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

    public static List<Integer> getMembers() {
        return members;
    }

    public static void setMembers(List<Integer> members) {
        QRoomStatic.members = members;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        QRoomStatic.name = name;
    }

    public static String getCode() {
        return code;
    }

    public static void setCode(String code) {
        QRoomStatic.code = code;
    }

    public static boolean isPlaying() {
        return playing;
    }

    public static void setPlaying(boolean playing) {
        QRoomStatic.playing = playing;
    }

    public static Song getCurrentSong() {
        return currentSong;
    }

    public static void setCurrentSong(Song currentSong) {
        QRoomStatic.currentSong = currentSong;
    }

    public static QList getPlaylist() {
        return playlist;
    }

    public static void setPlaylist(QList playlist) {
        QRoomStatic.playlist = playlist;
    }

    public static int getQLeader() {
        return QLeader;
    }

    public static void setQLeader(int QLeader) {
        QRoomStatic.QLeader = QLeader;
    }
}
