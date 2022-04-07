package service;
import core.dto.SavedVote;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import core.dto.ChoiceWithCounter;
import core.dto.Vote;


public class VoteService implements IVoteService {

  private static class VoteServiceHolder {
    private static final VoteService VOTE_SERVICE = new VoteService();
  }
  public static VoteService getVoteInstance() {
    return VoteServiceHolder.VOTE_SERVICE;
  }

  private ArtistsService artistsService;
  private GenresService genresService;
  private List<SavedVote> votes = new ArrayList<>();

  public VoteService() {
    this.artistsService = ArtistsService.getArtistsInstance();
    this.genresService = GenresService.getGenresInstance();
  }
  @Override
  public void check(Vote vote){

    if(!this.artistsService.isExist(vote.getArtist())) {
      throw new IllegalArgumentException("такого артиста не существует");
    }

    if(vote.getGenres().length < 3 || vote.getGenres().length > genresService.getGenres().size()){
      throw new IllegalArgumentException("слишком много или мало жаноров!");
    }

    for(int genre : vote.getGenres()) {
      if(!this.genresService.isExist(genre)) {
        throw new IllegalArgumentException("такого жанра не существует");
      }
    }
  }
  @Override
  public void saveVote(Vote vote){
    check(vote);
    SavedVote savedVote = new SavedVote(LocalDateTime.now(), vote);
    this.votes.add(savedVote);
    this.votes.sort(SavedVote::compareTo);
  }

  public List<String> getAboutByTime(Comparator<SavedVote>... comparators) {
    Map<LocalDateTime, String> data = new HashMap<>();

    for(SavedVote pool : this.votes) {
      LocalDateTime time = pool.getTime();
      String about = pool.getVote().getAbout();
      data.put(time, about);
    }
    Comparator<Map.Entry<LocalDateTime, String>> selectedComparator = (o1, o2) -> {
      return o1.getKey().compareTo(o2.getKey());
    };

    List<Map.Entry<LocalDateTime, String>> entries = data.entrySet().stream()
        .sorted(selectedComparator.reversed())
        .collect(Collectors.toList());

    List<String> result = new ArrayList<>();

    for (Entry<LocalDateTime, String> entry : entries) {
      result.add(entry.getValue());
    }
    return result;
  }

  @Override
  public List<ChoiceWithCounter<String>> getArtistTop(
      Comparator<ChoiceWithCounter>... comparator) {
    Map<Integer, Integer> data = new HashMap<>();

    for(SavedVote pool : this.votes) {
      int artist = pool.getVote().getArtist();
      Integer count = data.getOrDefault(artist, 0);
      data.put(artist, count + 1);
    }

    Comparator<Map.Entry<Integer, Integer>> selectedComparator = (o1, o2) -> {
      return o1.getValue() - o2.getValue();
    };

    List<Map.Entry<Integer, Integer>> entries = data.entrySet().stream()
        .sorted(selectedComparator.reversed())
        .collect(Collectors.toList());

    List<ChoiceWithCounter<String>> result = new ArrayList<>();

    for (Entry<Integer, Integer> entry : entries) {
      result.add(new ChoiceWithCounter<>(this.artistsService.getArtists().get(entry.getKey()), entry.getValue()));
    }

    return result;
  }

  @Override
  public List<ChoiceWithCounter<String>> getGenreTop(
      Comparator<ChoiceWithCounter>... comparator) {
    Map<Integer, Integer> data = new HashMap<>();

    for(SavedVote pool : this.votes) {
      int[] genres = pool.getVote().getGenres();

      for(int genre : genres) {
        Integer count = data.getOrDefault(genre, 0);
        data.put(genre, count + 1);
      }
    }

    Comparator<Map.Entry<Integer, Integer>> selectedComparator = (o1, o2) -> {
      return o1.getValue() - o2.getValue();
    };

    List<Map.Entry<Integer, Integer>> entries = data.entrySet().stream()
        .sorted(selectedComparator.reversed())
        .collect(Collectors.toList());

    List<ChoiceWithCounter<String>> result = new ArrayList<>();

    for (Entry<Integer, Integer> entry : entries) {
      result.add(new ChoiceWithCounter<>(this.genresService.getGenres().get(entry.getKey()), entry.getValue()));
    }

    return result;
  }

}
