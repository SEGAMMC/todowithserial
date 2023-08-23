package servlets;

import logic.Autorisation;
import logic.ManageTask;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "deletearchiv", value = "/task/deletearchiv")
public class delete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String completeTask = request.getParameter("idtask");
        int idForComplete = Integer.parseInt(completeTask);
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
                mt.deleteTaskisArchiv(idForComplete);
            }
        }



        response.sendRedirect("/");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String completeTask = request.getParameter("completetask");
//        int idForComplete = Integer.parseInt(completeTask);
//
//
//        Cookie[] cookies = request.getCookies();
//        String cookieName = "auto-user";
//        Cookie currentCookie = null;
//        for (Cookie c : cookies) {
//            if (cookieName.equals(c.getName())) {
//                currentCookie = c;
//            }
//        }
//        String[] cookieArray = currentCookie.getValue().split("&&");
//        String nickname = cookieArray[0];
//        String cookie = cookieArray[1];
//
//        Autorisation autorisation = new Autorisation();
//        if (currentCookie != null) {
//            if (autorisation.checkCookie(nickname, cookie)) {
//                ManageTask mt = new ManageTask(nickname);
//                mt.deleteTaskisArchiv(idForComplete);
//            }
//        }
//        doGet(request, response);
    }
}
