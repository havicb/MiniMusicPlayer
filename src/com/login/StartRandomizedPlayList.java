package com.login;

import javazoom.jl.player.Player;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

class StartRandomizedPlayList extends Thread {
    private List<Song> songs;
    private StartSong playingSong;

    public StartRandomizedPlayList(List<Song> songs) {
        this.songs = songs;
        int randSong = this.generateNumber();
        playingSong = new StartSong(songs.get(randSong));
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        playingSong.start();
        while(true) {
            int randSong = (int) Math.random() * songs.size();
                System.out.println("0-Prekid 1-Nova pjesma p-Pauziraj");
                String unesen = scanner.nextLine();
                if (unesen.equalsIgnoreCase("0"))
                    System.exit(0);
                else if (unesen.equalsIgnoreCase("1")) {
                    playingSong.stopPlayer();
                    playingSong = new StartSong(songs.get(this.generateNumber()));
                    playingSong.start();
                }
            }
        }

    private int generateNumber() {
       Random random = new Random();
       return random.nextInt(songs.size());
    }
}
