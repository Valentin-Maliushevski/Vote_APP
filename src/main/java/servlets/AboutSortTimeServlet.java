package servlets;

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

@WebServlet (name = "AboutSortTimeServlet", urlPatterns = "/sort/about")
public class AboutSortTimeServlet extends HttpServlet {

  private IVoteService voteService;

  public AboutSortTimeServlet() {
    this.voteService = VoteService.getVoteInstance();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    req.setCharacterEncoding("UTF-8");
    resp.setContentType("text/html; charset=UTF-8");
    PrintWriter writer = resp.getWriter();

    List<String> sortAbout = voteService.getAboutByTime();

    writer.write("SORT ABOUT</br>");

    for (String choice : sortAbout) {
      writer.write(choice + "</br>");
    }
  }
}
