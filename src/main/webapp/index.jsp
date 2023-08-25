<%@ page import="logic.ManageTask" %>
<%@ page import="model.Task" %>
<%@ page import="java.util.List" %>
<%@ page import="logic.Autorisation" %>
<%@ page import="java.util.Collections" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>TODO Task Manager</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css"/>
    <link rel="stylesheet" type="text/css" href="/css/w3.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"
          integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
</head>
<body class="w3-light-grey">
<%
    Cookie[] cookies = request.getCookies();
    String cookieName = "auto-user";
    Cookie currentCookie = null;
    for (Cookie c : cookies) {
        if (cookieName.equals(c.getName())) {
            currentCookie = c;
        }
    }
    Autorisation autorisation = new Autorisation();
    if (currentCookie != null && request.getAttribute("newuser") == "false") {
        String[] cookieArray = currentCookie.getValue().split("&&");
        String nickname = cookieArray[0];
        String cookie = cookieArray[1];

        if (autorisation.checkCookie(nickname, cookie)) {
            out.println("<h1>Welcome " + nickname + "</h1>\n");
        }
    }
    if (currentCookie == null && request.getAttribute("newuser") == null) {
        out.println(
                "<div class=\"w3-container w3-teal\">\n" +
                        "<h4>Form autorisation</h4>\n" +
                        "</div>\n" +
                        "<form class=\"w3-container\" method=\"post\" action=\"/autorisation\">\n" +
                        "<div class=\"p-3\">\n" +
                        "<div class=\"form-group row\">" +
                        "<label class=\"w3-text-teal\" for=\"nick\">Nickname</label>\n" +
                        "<input type=\"text\" name=\"nicknamelogin\" required minlength=\"2\" maxlength=\"128\" class=\"w3-input w3-border w3-light-grey\"\n" +
                        "id=\"nicknamelogin\"/>\n" +
                        "</div>\n" +
                        "<div class=\"form-group row\">\n" +
                        "<label class=\"w3-text-teal\" for=\"passwordlogin\">Password</label>\n" +
                        "<input type=\"text\" name=\"passlogin\" minlength=\"2\" maxlength=\"256\" class=\"w3-input w3-border w3-light-grey\" id=\"pass\"/>\n" +
                        "</div>\n" +
                        "<div class=\"text-center\" >\n" +
                        "<button type = \"submit\" class=\"w3-btn w3-blue-grey\" >Log in</button >\n" +
                        "<a href=\"/createuser\">Create New User</a>" +
                        "</div >\n" +
                        "</div >\n" +
                        "</form >\n"
        );
    }

    if (request.getAttribute("messageErrorAuto") != null) {
        out.println(
                "<div class=\"w3-container w3-red\">\n" +
                        "<h4>" + request.getAttribute("messageErrorAuto") + "</h4>\n" +
                        "</div>\n"
        );
    }
    if (request.getAttribute("newuser") == "true") {
        out.println(
                "<div class=\"w3-container w3-teal\">\n" +
                        "<h4>Form registration</h4>\n" +
                        "</div>\n" +
                        "<form class=\"w3-container\" method=\"post\" action=\"/createnewusercomplete\">\n" +
                        "<div class=\"p-3\">\n" +
                        "<div class=\"form-group row\">" +
                        "<label class=\"w3-text-teal\" for=\"nick\">Nickname</label>\n" +
                        "<input type=\"text\" name=\"nickname\" required minlength=\"2\" maxlength=\"128\" class=\"w3-input w3-border w3-light-grey\"\n" +
                        "id=\"nickname\"/>\n" +
                        "</div>\n" +
                        "<div class=\"form-group row\">\n" +
                        "<label class=\"w3-text-teal\" for=\"password\">Password</label>\n" +
                        "<input type=\"text\" name=\"pass\" minlength=\"2\" maxlength=\"256\" class=\"w3-input w3-border w3-light-grey\" id=\"pass\"/>\n" +
                        "</div>\n" +
                        "<div class=\"text-center\" >\n" +
                        "<button type = \"submit\" class=\"w3-btn w3-blue-grey\" >Create new User</button >\n" +
                        "</div >\n" +
                        "</div >\n" +
                        "</form >\n"
        );
    }

    if (currentCookie != null) {
        out.println("<div class=\"w3-container w3-teal\">\n" +
                "    <h3>Add Task</h3>\n" +
                "</div>\n" +
                " <div class=\"my-3\">\n" +
                "<form class=\"w3-container\" method=\"post\" action =\"addtask\">\n" +
                "<div class=\"p-3\">\n" +
                "<div class=\"form-group row\">\n" +
                "<label class=\"w3-text-teal\" for=\"title\">Title</label>\n" +
                "<input type=\"text\" name = \"titleadd\"\n" +
                "required minlength=\"2\" maxlength=\"128\" class=\"form-control\"\n" +
                "id=\"title\"/>\n" +
                "</div>\n" +
                "<div class=\"form-group row\">\n" +
                "<label class=\"w3-text-teal\" for=\"description\">Description</label>\n" +
                "<input type=\"text\" name = \"descriptionadd\" minlength=\"2\"\n" +
                "maxlength=\"256\" class=\"form-control\"\n" +
                "id=\"description\"/>\n" +
                "</div>\n" +

                "<div class=\"text-center\">\n" +
                "<button type=\"submit\" class=\"w3-btn w3-blue-grey\">\n" +
                "Add Task</button>\n" +
                "</div>\n" +
                "</div>\n" +
                "</form>\n" +
                "</div>\n");
    }
%>
<div>
    <h3 class="w3-container w3-indigo">Active Task</h3>
    <table>
        <thead class="thead-light">
        <tr>
            <th scope="col"></th>
            <th scope="col">Title</th>
            <th scope="col">Description</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (Cookie c : cookies) {
                if (cookieName.equals(c.getName())) {
                    currentCookie = c;
                }
            }

            if (currentCookie != null) {
                String[] cookieArray = currentCookie.getValue().split("&&");
                String nickname = cookieArray[0];
                ManageTask mt = new ManageTask(nickname);
                List<Task> listActivTask = mt.getListTasks().getActivTasks();
                Collections.reverse(listActivTask);
                if (listActivTask.size() > 0)
                    for (Task activeTask : listActivTask) {
                        out.println("<tr>" +
                                "<td><a href=\"/task/complete?idtask=" + activeTask.getId() + "\" title=\"Complete task\"\n" +
                                "class=\"fa-regular fa-pen-to-square icon-dark\"></a>\n</td>\n" +
                                "<td>" + activeTask.getTitle() + "</td>\n" +
                                "<td>" + activeTask.getDescription() + "</td>\n" +
                                "</tr>\n");
                    }
                else {
                    out.println("<tr>" +
                            "<td>-</td>\n" +
                            "<td>-</td>\n" +
                            "</tr>\n");
                }
            }
        %>
        </tbody>
    </table>
</div>

<div>
    <h4 class="w3-container w3-grey">Archiv Task</h4>
    <table class="w3-padding-16">
        <thead class="thead-light">
        <tr>
            <th scope="col"></th>
            <th scope="col">Title</th>
            <th scope="col">Description</th>
            <th scope="col">Delete</th>
        </tr>
        </thead>
        <tbody>
        <%
            if (currentCookie != null) {
                String[] cookieArray = currentCookie.getValue().split("&&");
                String nickname = cookieArray[0];
                ManageTask mt = new ManageTask(nickname);

                List<Task> listArchivTask = mt.getListTasks().getArchivTasks();
                Collections.reverse(listArchivTask);
                if (listArchivTask.size() > 0)
                    for (Task archiveTask : listArchivTask) {
                        out.println("<tr>" +
                                "<td><a href=\"/task/completearchiv?idtask=" + archiveTask.getId() + "\" title=\"UnComplete task\"\n" +
                                "class=\"fa-regular fa-pen-to-square icon-dark\"></a>\n</td>\n" +
                                "<td>" + archiveTask.getTitle() + "</td>\n" +
                                "<td>" + archiveTask.getDescription() + "</td>\n" +
                                "<td>\n <a href=\"/task/deletearchiv?idtask=" + archiveTask.getId() + "\" id=\"btn1Delete\"\n" +
                                "title=\"Delete this task\" class=\"fa-regular fa-trash-can icon-dark btn-delete\"></a>\n" +
                                "</td>" +
                                "</tr>\n");
                    }
                else {
                    out.println("<tr>" +
                            "<td>-</td>\n" +
                            "<td>-</td>\n" +
                            "<td>-</td>\n" +
                            "</tr>\n");
                }
            }
        %>
        </tbody>
    </table>
</div>
</body>
</html>