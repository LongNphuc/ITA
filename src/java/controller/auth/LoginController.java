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
import java.util.ArrayList;
import model.Role;
import model.User;
import utils.Mail;

@WebServlet(name = "LoginController", urlPatterns = {"/LoginGoogleController"})
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getParameter("signin")!= null) {
            request.getRequestDispatcher("auth/sign-in.jsp").forward(request, response);
        }
        if (request.getParameter("loginnormal") == null) {
            String code = request.getParameter("code");
            String accessToken = Helper.getToken(code);

            UserGoogleDTO userInfo = Helper.getUserInfo(accessToken);
            System.out.println(userInfo);
            User user = new UserDAO().getUserByEmailOrMobile(UserDAO.LOGIN_EMAIL, userInfo.getEmail());

            if (user == null) {
                String verifyEmailCaptcha = Helper.getRandomNumberString();
                Mail.send(userInfo.getEmail(), "Verify Email", "Your verify code: " + verifyEmailCaptcha);
                request.setAttribute("verifyCode", verifyEmailCaptcha);
                request.getSession().setAttribute("userInfo", userInfo);
                request.getRequestDispatcher("auth/otp-verifyEmail.jsp").forward(request, response);
            } else {
                if (user.getOauthId() == null) {
                    new UserDAO().updateGoogleUser(user.getUserId(), user.getOauthId(), user.getFullName(), user.getImage());
                }
                request.getSession().setAttribute("user", user);
                request.getRequestDispatcher("projectDashboard.jsp").forward(request, response);
            }
        } else {
            String account = request.getParameter("signin1");
            String password = request.getParameter("signin2");   
            User userCheck = new User();
            try{
            userCheck = new UserDAO().getUserLogin((account.contains("@") ?
                    UserDAO.LOGIN_EMAIL : UserDAO.LOGIN_MOBILE), account,Helper.encryptPassword(password));
            }catch(Exception e) {
                e.printStackTrace();
            }
            if (userCheck != null) {
                boolean check = (userCheck.getRole().getRoleId() == Role.ROLE_STUDENT);
                System.out.println(check);
                for (User u : new UserDAO().getUserList(check)) {
                    if( userCheck.getUserId() == u.getUserId()){
                        request.getSession().setAttribute("user", u);
                        request.getRequestDispatcher("projectDashboard.jsp").forward(request, response);
                    }
                }
            } else {
                request.setAttribute("mess", "Don't have " + (account.contains("@") ?
                        UserDAO.LOGIN_EMAIL : UserDAO.LOGIN_MOBILE) + " exist.Or password not Correct. Again!");
                request.getRequestDispatcher("auth/sign-in.jsp").forward(request, response);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
