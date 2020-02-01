package jimenez.enger.controllers;

import jimenez.enger.models.ProductModel;
import jimenez.enger.services.ProductDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Product", urlPatterns = "/Product")
public class ProductController extends HttpServlet {
  ProductDao productDb = new ProductDao();
  ProductModel productModel = new ProductModel();

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws IOException {
    if (request.getParameter("Action") != null) {
      String action = request.getParameter("Action");

      switch (action) {
        case "create":
          getBody(request);
          productDb.Create(productModel);
          break;

        case "update":
          if (request.getParameter("Id") != null) {
            int id = Integer.parseInt(request.getParameter("Id"));
            getBody(request);
            productDb.Update(id, productModel);
          }
          break;

        case "delete":
          if (request.getParameter("Id") != null) {
            int id = Integer.parseInt(request.getParameter("Id"));
            productDb.Delete(id);
          }
          break;
      }
    }

    response.sendRedirect("Product");
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    if (AccessController.isLogged(request)) {
      if (request.getParameter("Action") != null) {
        String action = request.getParameter("Action");

        switch (action) {
          case "create":
            request
              .getRequestDispatcher("new_product.jsp")
              .forward(request, response);
            break;

          case "edit":
            if (request.getParameter("Id") != null) {
              int id = Integer.parseInt(request.getParameter("Id"));
              request.setAttribute("product", productDb.Get(id));
              request
                .getRequestDispatcher("edit_product.jsp")
                .forward(request, response);
            }
            break;
        }
      } else {
        request.setAttribute("products", productDb.GetAll());
        request
          .getRequestDispatcher("view_products.jsp")
          .forward(request, response);
      }
    } else {
      response.sendRedirect("Access");
    }
  }

  private void getBody(HttpServletRequest request) {
    productModel.Name = request.getParameter("Name");
    productModel.Description = request.getParameter("Description");
    productModel.Price = Double.parseDouble(request.getParameter("Price"));
    productModel.Quantity = Integer.parseInt(request.getParameter("Quantity"));
    productModel.Supplier = request.getParameter("Supplier");
    productModel.Status = request.getParameter("Status");
  }
}
