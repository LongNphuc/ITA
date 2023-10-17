package controller.auth;

import dao.UserDAO;
import dto.UserGoogleDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="RegisterController", urlPatterns={"/register"})
public class RegisterController extends HttpServlet {
   
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.getRequestDispatcher("fillInforUser.jsp").forward(request, response);
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        UserGoogleDTO userInfo = (UserGoogleDTO) request.getSession().getAttribute("userInfo");
        boolean check = new UserDAO().addUser(userInfo,
                Integer.parseInt(request.getParameter("role")),
                request.getParameter("mobile"), request.getParameter("password"));
        request.getRequestDispatcher("projectDashboard.jsp").forward(request, response);
    }

}
