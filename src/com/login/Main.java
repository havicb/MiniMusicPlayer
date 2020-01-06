package com.login;


import com.login.BelminMusicPlayer;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static BelminMusicPlayer bmp = new BelminMusicPlayer();
    public static void main(String[] args) throws IOException, UnsupportedAudioFileException {
        bmp.addTrack(new File("Idi idi moja vilo.mp3"));
        bmp.addTrack(new File("Bezobrazno su zelene.mp3"));
        bmp.addTrack(new File("Da sutis.mp3"));
        bmp.addTrack(new File("Deset mladja.mp3"));
        bmp.addTrack(new File("Ptica bijela.mp3"));
        bmp.addTrack(new File("Prevarena.mp3"));
        bmp.addTrack(new File("Skote jos te volim.mp3"));
        bmp.startPlaylistFromBeggining();


    }

    private static void startProgram() {
        while(true) {
            switch(chooseButton()) {
                case 1:
                    bmp.seePlayList();
                    break;
                case 2:
                    startSong();
                    break;
                case 3:
                    bmp.startPlaylistFromBeggining();
                    break;
                case 4:
                    downloadSong();
                    break;
                case 5:
                    break;
            }
        }
    }

    private static void startSong() {
        String songName;
        System.out.println("Enter a song name: ");
            bmp.playSong(scanner.nextLine());
    }

    private static void downloadSong() {
        System.out.println("Downloading failed..");
    }

    private static void seeOptions() {
        System.out.println("1-See playlist\n2-Start song\n3-Start playlist\n4-Download song\n5-Exit");
    }

    private static int chooseButton() {
        seeOptions();
        int selected = scanner.nextInt();
        scanner.nextLine();
    return selected;
    }


}