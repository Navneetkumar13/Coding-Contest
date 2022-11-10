package com.crio.jukebox.entities;

import java.util.List;


public class User extends BaseEntity{
    private String userName;
    private List <Playlist> userPlaylist;

    public User(User user){
        // this(user.id,user.userName,user.userPlaylist);
        this.userName = userName;
        this.userPlaylist = userPlaylist;
    }

    public String getUser(){
        return userName;
    }

    public List<Playlist> getUserPlaylist(){
        return userPlaylist;
    }
}
