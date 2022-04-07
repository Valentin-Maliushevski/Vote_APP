package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.GenresService;


@WebServlet(name = "GenresServlet", urlPatterns = "/vote/genres")
public class GenresServlet extends HttpServlet {
  private GenresService service;

  public GenresServlet() {
    this.service = GenresService.getGenresInstance();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
      req.setCharacterEncoding("UTF-8");

      resp.setContentType("text/html; charset=UTF-8");
      PrintWriter writer = resp.getWriter();

      List<String> genres = service.getGenres();

      int index = 1;
      for (String genre : genres) {
        writer.write("<p>" + index++ + " - " + genre + "</p></br");
      }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    service.add(req.getParameter("genre"));
  }
}
