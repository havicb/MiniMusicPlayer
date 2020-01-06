package com.login;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;

class StartPlaylist extends Thread {
    private Iterator<Song> songs;
    private StartSong playingSong;

    public StartPlaylist(Iterator it, Song firstSong) {
        songs = (Iterator<Song>) it;
        playingSong = new StartSong(firstSong);
    }

    @Override
    public void run() {
        Scanner unos = new Scanner(System.in);
        playingSong.start();
        while (true) {
            System.out.println("0-Prekid 1-Nova pjesma p-Pauziraj");
            String unesen = unos.nextLine();
            if (!this.songs.hasNext() && unesen.equalsIgnoreCase("1"))
                continue;
            if (unesen.equalsIgnoreCase("0"))
                System.exit(0);
            else if (unesen.equalsIgnoreCase("1")) {
                playingSong.stopPlayer();
                playingSong = new StartSong(songs.next());
                playingSong.start();
            }
        }
    }

    private boolean haveMoreSongs() {
        return this.songs.hasNext();
    }

}
