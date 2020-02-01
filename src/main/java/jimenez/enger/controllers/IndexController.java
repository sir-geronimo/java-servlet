package jimenez.enger.controllers;

import jimenez.enger.services.ProductDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Index", urlPatterns = "/")
public class IndexController extends HttpServlet {
  ProductDao productDb = new ProductDao();

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    if (AccessController.isLogged(request)) {
      request.setAttribute("productsAmount", productDb.GetAll().size());
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
