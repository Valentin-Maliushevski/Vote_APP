package core.dto;

import java.util.Arrays;

public class Vote {

  private int artist;
  private int[] genres;
  private String about;

  public Vote(int artist, int[] genres, String about) {
    this.artist = artist;
    this.genres = genres;
    this.about = about;
  }

  public int getArtist() {
    return artist;
  }

  public int[] getGenres() {
    return genres;
  }

  public String getAbout() {
    return about;
  }

  @Override
  public String toString() {
    return "Pool{" +
        "artist=" + artist +
        ", genres=" + Arrays.toString(genres) +
        ", about='" + about + '\'' +
        '}';
  }
}
