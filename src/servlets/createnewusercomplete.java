package servlets;

import logic.ManageUser;
import model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "createnewusercomplete", value = "/createnewusercomplete")
public class createnewusercomplete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nickname = request.getParameter("nickname");
        String pass = request.getParameter("pass");

        ManageUser mu = new ManageUser(new User(nickname, pass));
        mu.createNewUser();

        String str = "User with " + nickname + " register!";
        request.setAttribute("messageRegister", str);
        doGet(request, response);
    }
}
