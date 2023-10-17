package controller.auth;

import dao.UserDAO;
import java.io.IOException;

import dto.UserGoogleDTO;
import utils.Helper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Role;
import model.User;
import utils.Mail;

@WebServlet(name = "LoginGoogleController", urlPatterns = {"/LoginGoogleController"})
public class LoginGoogleController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code = request.getParameter("code");
        String accessToken = Helper.getToken(code);

        UserGoogleDTO userInfo = Helper.getUserInfo(accessToken);
        System.out.println(userInfo);
        User user = new UserDAO().getUser(UserDAO.LOGIN_EMAIL, userInfo.getEmail());
        request.getSession().setAttribute("roleTeacher", Role.ROLE_TEACHER);
        request.getSession().setAttribute("roleStudent", Role.ROLE_STUDENT);
        request.getSession().setAttribute("roleAdmin", Role.ROLE_ADMIN);
        
        if (user == null) {
            String verifyEmailCaptcha = Helper.getRandomNumberString();
            Mail.send(userInfo.getEmail(), "Verify Email", "Your verify code: " + verifyEmailCaptcha);
            request.setAttribute("verifyCode", verifyEmailCaptcha);
            request.getSession().setAttribute("userInfo", userInfo);
            request.getRequestDispatcher("otp-verifyEmail.jsp").forward(request, response);
        } else {
             if(user.getOauthId() == null) {
                 new UserDAO().updateGoogleUser(user.getUserId(), user.getOauthId(), user.getFullName(), user.getImage());
             }request.getSession().setAttribute("user", user);
            request.getRequestDispatcher("projectDashboard.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
