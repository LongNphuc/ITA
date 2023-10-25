package controller.auth;

import com.google.gson.Gson;
import dao.UserDAO;
import dto.UserGoogleDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.Helper;

@WebServlet(name = "RegisterController", urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("fillInfor") != null) {
            request.setAttribute("flag", (request.getParameter("flag") == null));
            request.setAttribute("mobile", request.getParameter("mobile"));
            request.getRequestDispatcher("auth/fillInforUser.jsp").forward(request, response);
        }else {
            request.getRequestDispatcher("auth/sign-up.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserGoogleDTO userInfo = new UserGoogleDTO();
        if (request.getParameter("flag").equalsIgnoreCase("true")) {
            userInfo = (UserGoogleDTO) request.getSession().getAttribute("userInfo");
        }
        try {
            boolean check = new UserDAO().addUser(userInfo,
                    Integer.parseInt(request.getParameter("role")),
                    request.getParameter("mobile"), Helper.encryptPassword(request.getParameter("password")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("projectDashboard.jsp").forward(request, response);
    }

}
