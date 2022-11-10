package com.crio.jukebox.entities;

public class Song extends BaseEntity{

    private final String songName;
    private final Artist artist;
    private final Album album;
    public Song(String songName,Artist artist,Album album){
        this.songName = songName;
        this.artist = artist;
        this.album = album;
    }

    public void playSong(){
        System.out.println("Playing the song"+songName);
    }

    public String getSongName(){
        return songName;
    }

    public Artist getArtist(){
        return artist;
    }

    public Album getAlbum(){
        return album;
    }
    
    @Override
    public String toString(){
        return "Current Song Playing { Song: "+songName+" Album: "+album+" Artist: "+artist+"}";
    }
}
