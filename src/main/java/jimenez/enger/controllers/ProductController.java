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
    this.productDb.Create(productModel);
  }

  protected void doPut(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    if (request.getAttribute("Id") != null) {
      this.productModel.Id = Integer.parseInt(request.getParameter("Id"));
    }
    getBody(request);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
  }

  private void getBody(HttpServletRequest request) {
    this.productModel.Name = request.getParameter("Name");
    this.productModel.Description = request.getParameter("Description");
    this.productModel.Price = Double.parseDouble(request.getParameter("Price"));
    this.productModel.Quantity = Integer.parseInt(request.getParameter("Quantity"));
    this.productModel.Supplier = request.getParameter("Supplier");
    this.productModel.Status = Integer.parseInt(request.getParameter("Status"));
  }
}
