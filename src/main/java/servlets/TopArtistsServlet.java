package servlets;

import core.dto.ChoiceWithCounter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.IVoteService;
import service.VoteService;


@WebServlet(name = "TopArtistsServlet", urlPatterns = "/top/artists")
public class TopArtistsServlet extends HttpServlet {

  private IVoteService voteService;

  public TopArtistsServlet() {
    this.voteService = VoteService.getVoteInstance();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    req.setCharacterEncoding("UTF-8");
    resp.setContentType("text/html; charset=UTF-8");
    PrintWriter writer = resp.getWriter();

    List<ChoiceWithCounter<String>> artistTop = voteService.getArtistTop();

    writer.write("TOP ARTISTS</br>");

    for (ChoiceWithCounter<String> choice : artistTop) {
      writer.write(choice.getChoice() + ": " + choice.getCountVote() + "</br>");
    }
  }
}
