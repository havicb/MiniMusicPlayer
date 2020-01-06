package com.login;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

class StartSong extends Thread {
    private Player player;
    private Song currentlyPlaying;

    public StartSong(Song wanted) {
        currentlyPlaying = wanted;
    }

    @Override
    public void run() {
            try {
                player = new Player(currentlyPlaying.getLocation());
                System.out.println("Now playing.." + currentlyPlaying.getSongName());
                player.play();
            } catch (JavaLayerException ex) {}
        }

    public void stopPlayer() {
        player.close();
    }

    public int getPosition() {
        return player.getPosition();
    }

}


