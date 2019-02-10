package com.foxappsbd.sandwichrecipe;

/**
 * Created by Arafat on 18/06/2016.
 */
public class Album {
    private String name;
    private int numOfSongs;
    private int thumbnail;

    public Album() {
    }

    public Album(String name, int thumbnail) {
        this.name = name;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfSongs() {
        return numOfSongs;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}