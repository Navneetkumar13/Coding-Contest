package com.crio.jukebox.entities;

import java.util.List;


public class Playlist extends BaseEntity{
    private final String playlistName;
    private final String playlistId;
    private List <Song> songsInPlaylist;
    private String creator;

    // public Playlist(User user){
    //     this(user.id,user.playlistName,user.creator,user.songsInPlaylist);
    // }

    public Playlist(String playlistId, String playlistName,List<Song> songsInPlaylist){
        this.playlistId = playlistId;
        this.playlistName = playlistName;
        this.songsInPlaylist = songsInPlaylist;
    }
    public void addSong(Song song){
        songsInPlaylist.add(song);
    }

    public String getPlaylistId(){
        return playlistId;
    }

    public String getPlaylistName(){
        return playlistName;
    }

    public List<Song> getSongsInPlaylist(){
        return songsInPlaylist;
    }

    // @Override
    // public String toString(){
    //     return "Current Song Playing { Song: "+songName+" Album: "+album+" Artist: "+artist+"}";
    // }
}