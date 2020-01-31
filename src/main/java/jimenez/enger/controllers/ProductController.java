package jimenez.enger.controllers;

import jimenez.enger.models.ProductModel;
import jimenez.enger.services.ProductDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Product", urlPatterns = "/Product")
public class ProductController extends HttpServlet {
  ProductDao productDb = new ProductDao();
  ProductModel productModel = new ProductModel();

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    getBody(request);
    productDb.Create(productModel);
  }

  protected void doPut(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    if (request.getAttribute("Id") != null) {
      productModel.Id = Integer.parseInt(request.getParameter("Id"));
    }
    getBody(request);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
  }

  private void getBody(HttpServletRequest request) {
    productModel.Name = request.getParameter("Name");
    productModel.Description = request.getParameter("Description");
    productModel.Price = Double.parseDouble(request.getParameter("Price"));
    productModel.Quantity = Integer.parseInt(request.getParameter("Quantity"));
    productModel.Supplier = request.getParameter("Supplier");
    productModel.Status = Integer.parseInt(request.getParameter("Status"));
  }
}
