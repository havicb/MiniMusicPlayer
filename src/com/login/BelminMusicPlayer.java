package com.login;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.beans.Encoder;
import javax.sound.sampled.AudioSystem;
import java.lang.System;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;

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
                setUpPlayer(location);
                System.out.println("Playing.." + wantedSong);
                player.play();
            } catch (JavaLayerException ex) {
                System.out.println(ex.toString());
                return false;
            }

    return true;
    }

    public boolean startPlaylist() {
        if(playlist.isEmpty()) {
            System.out.println("Playlist is empty..");
            return false;
        }
        Iterator<Song> songIT = playlist.iterator();
        while(songIT.hasNext()) {
            Song current = songIT.next();
        try {
            setUpPlayer(current.getLocation());
            System.out.println("Now playing.." + current.getSongName());
            player.play();
        }catch(JavaLayerException ex) {
            System.out.println(ex.toString());
            return false;
        }
        }
    return true;
    }

    private void setUpPlayer(FileInputStream location) throws JavaLayerException {
        player = new Player(location);
    }

    private static void getDurationWithMp3Spi(File file) throws UnsupportedAudioFileException, IOException { // NE RADI SA MP3 FILE-OVIMA
        AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(file);
        if (fileFormat != null) {
            Map<?, ?> properties = ((AudioFileFormat) fileFormat).properties();
            String key = "duration";
            Long microseconds = (Long) properties.get(key);
            int mili = (int) (microseconds / 1000);
            int sec = (mili / 1000) % 60;
            int min = (mili / 1000) / 60;
            System.out.println("time = " + min + ":" + sec);
        } else {
            System.out.println("Usao u else..");
            throw new UnsupportedAudioFileException();
        }
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
