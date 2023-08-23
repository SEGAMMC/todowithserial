package servlets;

import logic.Autorisation;
import logic.ManageUser;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "autorisation", value = "/autorisation")
public class autorisation extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
//        requestDispatcher.forward(request, response);

        response.sendRedirect("/");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nickname = request.getParameter("nicknamelogin");
        String pass = request.getParameter("passlogin");

        ManageUser mu = new ManageUser(new User(nickname, pass));
        if (!mu.checkAutorisation()) System.out.println("Servlet autorisation: User not Found!");

        Autorisation auto = new Autorisation();
        String newCookie = auto.getNewSession(nickname, pass);
        Cookie cookie = new Cookie("auto-user", newCookie);
        response.addCookie(cookie);

        doGet(request, response);
    }
}
