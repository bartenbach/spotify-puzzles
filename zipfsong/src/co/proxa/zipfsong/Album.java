package co.proxa.zipfsong;


public class Album {


    private String[] songs;
    private long[] plays;
    private int tracks;


    public Album(int tracks) {
        this.tracks = tracks;
        this.songs = new String[tracks];
        this.plays = new long[tracks];
    }

    public void setSongName(int trackNumber, String name) {
        this.songs[trackNumber] = name;
    }

    public void setSongPlays(int trackNumber, long numberOfPlays) {
        this.plays[trackNumber] = numberOfPlays;
    }

    public int getAlbumLength() {
        return this.songs.length;
    }

    public void applyZipfsLaw() {
        long[] zipfs = new long[this.tracks];
        for (int x = 0; x < tracks ; x++) {
            zipfs[x] = plays[x] / x+1;
            //TODO: test this most likely bogus, half-asleep logic.
        }
    }
}
