package jimenez.enger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Index", urlPatterns = "/")
public class Index extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Object logged = null;

        if (session != null) {
            logged = session.getAttribute("logged");
        }

        if (logged == null || (boolean) logged == false) {
            request.getRequestDispatcher("login.jsp")
                    .forward(request, response);
        } else {
            request.getRequestDispatcher("index.jsp")
                    .forward(request, response);
        }
    }
}
