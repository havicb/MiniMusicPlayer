package com.login;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.lang.System;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class BelminMusicPlayer {
    private List<Song> playlist;
    private Player player;

    public BelminMusicPlayer() {
        playlist = new LinkedList<>();
        player = null;
    }


    public boolean addTrack(File name) {
        boolean added = true;
        try {
            playlist.add(new Song(name));
            System.out.println(name.getName() + " succesfully added to playlist..");
        }catch(FileNotFoundException eo) {
            System.out.println(eo.toString());
            added = false;
        }
    return added;
    }

    public boolean playSong(String wantedSong) {
        Scanner scanner = new Scanner(System.in);
    FileInputStream location = songExistsInPlaylist(wantedSong);
        if(location == null) {
            System.out.println("There is no song called " + wantedSong + " in our playlist..");
        return false;
        }
            try {
                player = new Player(location);
                System.out.println("Playing.." + wantedSong);
                player.play();
            } catch (JavaLayerException ex) {
                System.out.println(ex.toString());
                return false;
            }

    return true;
    }

    public void startPlaylistFromBeggining() {
        if (playlist.isEmpty()) {
            System.out.println("Playlist is empty..");
            return;
        }
        Iterator<Song> it = playlist.iterator();
        StartPlaylist pokreniPjesme = new StartPlaylist(it, it.next());
        pokreniPjesme.start();
    }

    public boolean startPlayListRandom() {
        Scanner unos = new Scanner(System.in);
        if(playlist.isEmpty()) {
            System.out.println("Playlist is empty");
            return false;
        }
        StartRandomizedPlayList pokreniPjesme = new StartRandomizedPlayList(this.playlist);
        pokreniPjesme.start();
        return true;
    }

    private FileInputStream songExistsInPlaylist(String wanted) {
        for (Song current : playlist)
            if(wanted.equals(current.getSongName()))
                return current.getLocation();
    return null;
    }

    public void seePlayList() {
        for(Song current : playlist)
            System.out.println(current.getSongName());
    }


}
