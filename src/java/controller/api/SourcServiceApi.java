package controller.api;

import com.google.gson.Gson;
import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.User;

@WebServlet(name = "SourcServiceApi", urlPatterns = {"/sourcServiceApi"})
public class SourcServiceApi extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        switch (request.getParameter("key")) {
            case "listMobileExist":
                ArrayList<String> listMobile = new ArrayList<>();
                for (User user : new UserDAO().getUserList(false)) {
                    listMobile.add(user.getMobile());
                }
                response.setContentType("text/html;charset=UTF-8");
                try ( PrintWriter out = response.getWriter()) {
                    String mobileJson = new Gson().toJson(listMobile);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    out.print(mobileJson);
                    out.flush();
                }
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
