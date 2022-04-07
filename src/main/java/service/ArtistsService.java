package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArtistsService {
  private List<String> artists = new ArrayList<>(Arrays.asList("Круг", "Evanessence", "Eminem", "Бах"));

  public List<String> getArtists() {
    return artists;
  }

  public void add(String artist) {
    artists.add(artist);
  }

  public boolean isExist(int index){
    int size = artists.size();
    return index >= 0 && index < size;
  }

  private static class ArtistsServiceHolder {
    private static final ArtistsService ARTISTS_INSTANCE = new ArtistsService();
  }
  public static ArtistsService getArtistsInstance() {
    return ArtistsServiceHolder.ARTISTS_INSTANCE;
  }
  private ArtistsService() {
  }
}
