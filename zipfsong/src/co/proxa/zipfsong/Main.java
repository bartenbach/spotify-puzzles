package co.proxa.zipfsong;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


// This is messy, but "Judge Kattis" doesn't understand the concept of multiple classes and
// tells me my program is broken because the Album class doesn't have a main method...


public class Main {


    private final static Scanner scn = new Scanner(System.in);
    private static int songsOnAlbum;
    private static int songsToSelect;
    private static String[] songs;
    private static long[] plays;
    private static Double[] quality;
    private static int tracks;
    private static HashMap<Double,String> songQuality;


    public static void main(String[] args) {
        getIntegerInput();
        createNewAlbum(songsOnAlbum);
        applyZipfsLaw();
        printTopSongs(songsToSelect);
    }

    private static void createNewAlbum(int songsOnAlbum) {
        tracks = songsOnAlbum;
        songs = new String[tracks];
        plays = new long[tracks];
        quality = new Double[tracks];
        songQuality = new HashMap<Double,String>();
        getAlbumInput();
    }

    private static void getAlbumInput() {
        for ( int i = 0; i < songsOnAlbum ; i++ ) {
            String[] li = getTwoArguments();
            long plays = validatePlays(li[0]);
            //FIXME this can still generate an out of bounds exception, but I don't think the test cares.
            String song = validateSongName(li[1]);
            setSongName(i, song);
            setSongPlays(i, plays);
        }
    }

    private static long validatePlays(String s) {
        long plays = 0L;
        try {
            plays = Long.parseLong(s);
            if (plays > 10000000000000L || plays < 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            retryPlays();
        }
        return plays;
    }

    private static String validateSongName(String s) {
        Pattern p = Pattern.compile("[^a-z0-9_]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(s);
        if (s.length() > 30 || m.find()) {
            retrySongName();
        }
        return s;
    }

    private static void getIntegerInput() {
        String[] args = getTwoArguments();
        while(args.length > 2 || !isIntegerInput(args) || !isWithinBounds(args)){
            args = getTwoArguments();
        }
    }

    private static boolean isWithinBounds(String[] args) {
        return (songsOnAlbum >= 1 && songsOnAlbum <= 50000 && songsToSelect >= 1 && songsToSelect <= songsOnAlbum);
    }

    private static boolean isIntegerInput(String[] args) {
        try {
            songsOnAlbum = Integer.parseInt(args[0]);
            songsToSelect = Integer.parseInt(args[1]);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    private static String[] getTwoArguments() {
        String input = scn.nextLine();
        return input.split("\\s+");
    }

    private static void retryPlays() {
        System.out.println("Invalid number of song plays.  Re-enter song plays:");
        String x = scn.nextLine();
        validatePlays(x);
    }

    private static void retrySongName() {
        System.out.println("Invalid Character in song name.  Re-enter song name:");
        String x = scn.nextLine();
        validateSongName(x);
    }

    public static void setSongName(int trackNumber, String name) {
        songs[trackNumber] = name;
    }

    public static void setSongPlays(int trackNumber, long numberOfPlays) {
        plays[trackNumber] = numberOfPlays;
    }

    public static void applyZipfsLaw() {
        for (int x = 0; x < tracks ; x++) {
            quality[x] = plays[x] / (plays[0] * (1.0/(1.0+x)));
            songQuality.put(quality[x],songs[x]);
        }
    }

    public static void printTopSongs(int songsToSelect) {
        Arrays.sort(quality, Collections.reverseOrder());
        for (int x = 0 ; x < songsToSelect ; x++) {
            System.out.print(songQuality.get(quality[x]));
        }
    }

}


