package service;

import core.dto.ChoiceWithCounter;
import core.dto.SavedVote;
import java.util.Comparator;
import java.util.List;
import core.dto.Vote;


public interface IVoteService {
  void check(Vote vote);
  void saveVote(Vote vote);
  List<String> getAboutByTime(Comparator<SavedVote>... comparators);
  List<ChoiceWithCounter<String>> getArtistTop(Comparator<ChoiceWithCounter>... comparator);
  List<ChoiceWithCounter<String>> getGenreTop(Comparator<ChoiceWithCounter>... comparator);

}
