package servlets;

import logic.Autorisation;
import logic.ManageTask;
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

@WebServlet(name = "addtask", value = "/addtask")
public class addtask extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String titleAdd = request.getParameter("titleadd");
        String descriptionAdd = request.getParameter("descriptionadd");

        Cookie[] cookies = request.getCookies();
        String cookieName = "auto-user";
        Cookie currentCookie = null;
        for (Cookie c : cookies) {
            if (cookieName.equals(c.getName())) {
                currentCookie = c;
            }
        }
        String[] cookieArray = currentCookie.getValue().split("&&");
        String nickname = cookieArray[0];
        String cookie = cookieArray[1];

        Autorisation autorisation = new Autorisation();
        if (currentCookie != null) {
            if (autorisation.checkCookie(nickname, cookie)) {
                ManageTask mt = new ManageTask(nickname);
                mt.addTask(titleAdd, descriptionAdd);
            }
        }
        doGet(request, response);
    }
}
