package core.dto;

import java.time.LocalDateTime;

public class SavedVote implements Comparable<SavedVote>{
  private final LocalDateTime time;
  private final Vote vote;

  public SavedVote(LocalDateTime time, Vote vote) {
    this.time = time;
    this.vote = vote;
  }

  public LocalDateTime getTime() {
    return time;
  }

  public Vote getVote() {
    return vote;
  }

  @Override
  public int compareTo(SavedVote o) {
    return this.time.compareTo(o.getTime());
  }

  @Override
  public String toString() {
    return "SavedVote {" +
        "time = " + time +
        ", vote = " + vote +
        '}';
  }

}
