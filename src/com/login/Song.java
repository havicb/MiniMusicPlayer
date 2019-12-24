package com.login;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Song {
    private String songName;
    private FileInputStream location;

    public Song(File location) throws FileNotFoundException {
        try {
            this.songName = location.getName();
            this.location = new FileInputStream(location);
        }catch(FileNotFoundException ex) {
            throw new FileNotFoundException("Can't find file..");
        }
    }

    public String getSongName() {
        String [] parts = songName.split("\\.");
    return parts[0];
    }

    public FileInputStream getLocation() {
        return location;
    }

}
