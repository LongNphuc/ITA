package controllerr;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Role;

@WebServlet(name = "IndexController", urlPatterns = {" "})
public class IndexController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getSession().getAttribute("user") == null) {
        request.getSession().setAttribute("roleTeacher", Role.ROLE_TEACHER);
        request.getSession().setAttribute("roleStudent", Role.ROLE_STUDENT);
        request.getSession().setAttribute("roleAdmin", Role.ROLE_ADMIN);
        request.getSession().setAttribute("loginGoogleURL", constant.Constant.GOOGLE_URL_LOGIN);
        request.getRequestDispatcher("langdingPage.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
