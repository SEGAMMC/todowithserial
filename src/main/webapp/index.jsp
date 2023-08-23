<%@ page import="logic.ManageTask" %>
<%@ page import="model.Task" %>
<%@ page import="java.util.List" %>
<%@ page import="logic.Autorisation" %>
<%@ page import="java.util.Collections" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>


<!DOCTYPE html>
<html>

<head>
    <title>TO DO Task Manager</title>

    <%--        <link rel="stylesheet" type="text/css" href="@{/css/style.css}"/>--%>
    <%--    <link rel="stylesheet" type="text/css" href="@{/css/w3.css}"/>--%>

    <%--    <link rel="stylesheet" type="text/css" href="@{/webjars/bootstrap/css/bootstrap.min.css}"/>--%>
    <link rel="stylesheet" type="text/css" href="/css/style.css"/>
    <link rel="stylesheet" type="text/css" href="/css/w3.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"
          integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>


    <!--  <script type="text/javascript" th:src="@{/webjars/jquery/jquery.min.js}"></script>-->
    <!--  <script type="text/javascript" th:src="@{/webjars/bootstrap/js/bootstrap.min.js}"></script>-->
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
    <table>
        <h3 class="w3-container w3-indigo">Active Task</h3>
        <thead class="thead-light">
        <tr>
            <th scope="col"></th>
            <th scope="col">Title</th>
            <th scope="col">Description</th>
            <%--            <th scope="col">Delete</th>--%>
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

        </tr>

        </tbody>
    </table>
</div>


<div>
    <table class="w3-padding-16">
        <h4 class="w3-container w3-grey">Archiv Task</h4>
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
                            "<td>-</td>" +
                            "</tr>\n");
                }


            }

        %>

        </tbody>
    </table>


</div>


<!--///////////////////////////////////-->


<!--  <div th:replace="fragments/header :: header"></div>-->

<!--  <div class="container-fluid" style="max-width: 900px; margin: 0 auto;">-->
<!--    <h2 class="text-center">Tutorials</h2>-->

<!--    <div th:if="${message != null}" class="alert alert-success alert-dismissible fade show text-center message"-->
<!--      role="alert">-->
<!--      [[${message}]]-->
<!--      <button type="button" class="close btn-sm" data-dismiss="alert" aria-label="Close">-->
<!--        <span aria-hidden="true">&times;</span>-->
<!--      </button>-->
<!--    </div>-->

<!--    <div class="my-3">-->
<!--      <form th:action="@{/tutorials}">-->
<!--        <div class="row d-flex">-->
<!--          <div class="col-md-6 mt-2">-->
<!--            <div class="search">-->
<!--              <i class="fa fa-search"></i>-->
<!--              <input id="keyword" type="search" name="keyword" th:value="${keyword}" required class="form-control"-->
<!--                placeholder="Enter keyword">-->
<!--              <button type="submit" class="btn btn-secondary">Search</button>-->
<!--            </div>-->
<!--          </div>-->
<!--          <div class="col-md-6 mt-2">-->
<!--            <button id="btnClear" class="btn btn-info">Clear</button>-->
<!--          </div>-->
<!--        </div>-->
<!--      </form>-->
<!--    </div>-->

<!--    <div>-->
<!--&lt;!&ndash;            th:if="${tutorials.size() > 0}">&ndash;&gt;-->
<!--      <table class="table table-hover table-responsive-xl">-->
<!--        <thead class="thead-light">-->
<!--          <tr>-->
<!--            <th scope="col">Id</th>-->
<!--            <th scope="col">Title</th>-->
<!--            <th scope="col">Description</th>-->
<!--&lt;!&ndash;            <th scope="col">Level</th>&ndash;&gt;-->
<!--&lt;!&ndash;            <th scope="col">Published</th>&ndash;&gt;-->
<!--            <th scope="col">Actions</th>-->
<!--          </tr>-->
<!--        </thead>-->
<!--        <tbody>-->
<!--          <tr>-->
<!--&lt;!&ndash;                  th:each="tutorial : ${tutorials}">&ndash;&gt;-->
<!--            <th scope="row">[[${tutorial.id}]]</th>-->
<!--            <td>[[${tutorial.title}]]</td>-->
<!--            <td>[[${tutorial.description}]]</td>-->
<!--&lt;!&ndash;            <td>[[${tutorial.level}]]</td>&ndash;&gt;-->
<!--            <td>-->
<!--&lt;!&ndash;              <a th:if="${tutorial.published == true}" class="fa-regular fa-square-check"&ndash;&gt;-->
<!--&lt;!&ndash;                th:href="@{'/tutorials/' + ${tutorial.id} + '/published/false'}" title="Disable this tutorial"></a>&ndash;&gt;-->
<!--&lt;!&ndash;              <a th:if="${tutorial.published == false}" class="fa-regular fa-square icon-dark"&ndash;&gt;-->
<!--&lt;!&ndash;                th:href="@{'/tutorials/' + ${tutorial.id} + '/published/true'}" title="Enable this tutorial"></a>&ndash;&gt;-->
<!--            </td>-->
<!--            <td>-->
<!--              <a th:href="@{'/tutorials/' + ${tutorial.id}}" title="Edit this tutorial"-->
<!--                class="fa-regular fa-pen-to-square icon-dark"></a>-->
<!--              &nbsp;-->
<!--              <a th:href="@{'/tutorials/delete/' + ${tutorial.id}}" th:tutorialTitle="${tutorial.title}" id="btnDelete"-->
<!--                title="Delete this tutorial" class="fa-regular fa-trash-can icon-dark btn-delete"></a>-->
<!--            </td>-->
<!--          </tr>-->
<!--        </tbody>-->
<!--      </table>-->
<!--    </div>-->

<!--    <div class="" th:unless="${tutorials.size() > 0}">-->
<!--      <span>No tutorials found!</span>-->
<!--    </div>-->
<!--  </div>-->

<!--  <div class="modal fade text-center" id="confirmModal">-->
<!--    <div class="modal-dialog">-->
<!--      <div class="modal-content">-->
<!--        <div class="modal-header">-->
<!--          <h5 class="modal-title">Delete Confirmation</h5>-->
<!--          <button type="button" class="close" data-dismiss="modal">-->
<!--            <span aria-hidden="true">&times;</span>-->
<!--          </button>-->
<!--        </div>-->

<!--        <div class="modal-body">-->
<!--          <span id="confirmText"></span>-->
<!--        </div>-->

<!--        <div class="modal-footer">-->
<!--          <a type="button" id="yesBtn" class="btn btn-danger">Yes</a>-->
<!--          <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>-->
<!--        </div>-->
<!--      </div>-->
<!--    </div>-->
<!--  </div>-->

<!--  <div th:replace="fragments/footer :: footer"></div>-->

<!--  <script type="text/javascript">-->
<!--    $(document).ready(function () {-->
<!--      $(".btn-delete").on("click", function (e) {-->
<!--        e.preventDefault();-->
<!--        link = $(this);-->

<!--        tutorialTitle = link.attr("tutorialTitle");-->
<!--        $("#yesBtn").attr("href", link.attr("href"));-->
<!--        $("#confirmText").html("Do you want to delete the Tutorial \<strong\>" + tutorialTitle + "\<\/strong\>?");-->
<!--        $("#confirmModal").modal();-->
<!--      });-->

<!--      $("#btnClear").on("click", function (e) {-->
<!--        e.preventDefault();-->
<!--        $("#keyword").text("");-->
<!--        window.location = "[[@{/tutorials}]]";-->
<!--      });-->
<!--    });-->
<!--  </script>-->

</body>

</html>