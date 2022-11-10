package com.crio.jukebox.entities;

import java.util.List;


public class Artist {
    private final String artistName;

    public Artist(String artistName){
        this.artistName = artistName;
    }

    @Override
    public String toString(){
        return "Artist{" +"artistName='" + artistName + '\'' +'}';
    }
}
