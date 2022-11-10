package com.crio.jukebox.entities;

import java.util.List;

public class Album extends BaseEntity{
    private final String albumName;
    private final Artist artist;
    private List<Song> songsInAlbum;

    public Album(String albumName,Artist artist,List<Song> songsInAlbum){
        this.albumName = albumName;
        this.artist = artist;
        this.songsInAlbum = songsInAlbum;
    }

    public String getAlbumName(){
        return albumName;
    }

    public List<Song> getSongsInAlbum(){
        return songsInAlbum;
    }
    public Artist getAlbumArtist(){
        return artist;
    }
}
