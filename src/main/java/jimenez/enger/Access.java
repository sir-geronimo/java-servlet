package jimenez.enger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "Access",urlPatterns = "/Access")
public class Access extends HttpServlet {
    private String[] _errors = { "" };

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Object logged = null;

        if (session != null) {
            logged = session.getAttribute("logged");
        }

        if (logged != null && (boolean) logged) {
            request.getRequestDispatcher("index.jsp")
                    .forward(request, response);
        }
        else {
            request.getRequestDispatcher("login.jsp")
                    .forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String user = "admin";
        String pswrd = "123";

        if (username.equals(user) && password.equals(pswrd)) {
            session.setAttribute("logged", true);
            response.sendRedirect("Index");
        }
        else {
            this._errors[0] = "Usuario o contraseña incorrecta";
            request.setAttribute("errors", this._errors);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
