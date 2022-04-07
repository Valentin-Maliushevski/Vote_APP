package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import core.dto.Vote;
import service.VoteService;

@WebServlet(name = "VoteServlet", urlPatterns = "/vote")
public class VoteServlet extends HttpServlet {

  private VoteService service;

  public VoteServlet() { this.service = VoteService.getVoteInstance(); }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    req.setCharacterEncoding("UTF-8");

    int artist = Integer.parseInt(req.getParameter("artist")) - 1;
    int[] genres = Arrays.stream(req.getParameterValues("genre"))
        .mapToInt(s -> Integer.parseInt(s) -1)
        .toArray();
    String about = req.getParameter("about");

    Vote vote = new Vote(artist, genres, about);

    service.saveVote(vote);

    resp.setContentType("text/html; charset=UTF-8");
    PrintWriter writer = resp.getWriter();

    writer.write(vote.toString());
  }
}
