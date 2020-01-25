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
  ExcelFile excelFile = new ExcelFile();

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    if (Access.isLogged(request)) {
      this.excelFile.Write();

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
