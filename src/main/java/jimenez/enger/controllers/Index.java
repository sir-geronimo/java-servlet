package jimenez.enger.controllers;

import jimenez.enger.helpers.ExcelFile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Index", urlPatterns = "/")
public class Index extends HttpServlet {

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    if (Access.isLogged(request)) {
      request
        .getRequestDispatcher("index.jsp")
        .forward(request, response);
    } else {
      request
        .getRequestDispatcher("login.jsp")
        .forward(request, response);
    }
  }
}
