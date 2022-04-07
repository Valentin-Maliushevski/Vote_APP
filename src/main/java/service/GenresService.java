package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenresService {
  private List<String> genres = new ArrayList<>(Arrays.asList("Частушки", "Рэп", "Рок", "Хип-Хоп",
      "Классика", "Джаз", "Блюз", "..."));

  public List<String> getGenres() {
    return genres;
  }

  public void add(String genre) {
    genres.add(genre);
  }

  public boolean isExist(int index){
    int size = genres.size();
    return index >= 0 && index < size;
  }
  private static class GenresServiceHolder {
    private static final GenresService GENRES_INSTANCE = new GenresService();
  }

  public static GenresService getGenresInstance() {
    return GenresServiceHolder.GENRES_INSTANCE;
  }

  private GenresService() {
  }
}
