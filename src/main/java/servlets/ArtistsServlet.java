package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.ArtistsService;


@WebServlet(name = "ArtistsServlet", urlPatterns = "/vote/artist")
public class ArtistsServlet extends HttpServlet {
  private ArtistsService service;

  public ArtistsServlet() {
    this.service = ArtistsService.getArtistsInstance();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
      req.setCharacterEncoding("UTF-8");

      resp.setContentType("text/html; charset=UTF-8");
      PrintWriter writer = resp.getWriter();

      List<String> artists = service.getArtists();

      int index = 1;
      for (String artist : artists) {
        writer.write("<p>" + index++ + " - " + artist + "</p></br");
      }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    service.add(req.getParameter("artist"));
  }
}
