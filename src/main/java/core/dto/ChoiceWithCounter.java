package core.dto;

public class ChoiceWithCounter<T> implements Comparable<ChoiceWithCounter<?>>{

  private T choice;
  private int countVote;

  public ChoiceWithCounter(T choice, int countVote) {
    this.choice = choice;
    this.countVote = countVote;
  }

  public T getChoice() {
    return choice;
  }

  public int getCountVote() {
    return countVote;
  }

  @Override
  public String toString() {
    return "ChoiceWithCounter{" +
        "choice = " + choice +
        ", countVote = " + countVote +
        '}';
  }

  @Override
  public int compareTo(ChoiceWithCounter<?> o) {
    return this.countVote - o.getCountVote();
  }
}
