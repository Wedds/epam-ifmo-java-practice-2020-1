<%@ page import="com.ifmo.epampractice.entity.Groups" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="com.ifmo.epampractice.entity.Users" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.ifmo.epampractice.entity.Tests" %>
<%@ page import="com.ifmo.epampractice.service.SubjectsService" %>
<%@ page import="com.ifmo.epampractice.service.UsersService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Groups group = (Groups) request.getAttribute("group"); %>
<html>
<head>
    <title>STSS - Группа <%= group.getName() %></title>
    <%@include file="../blocks/head.jsp"%>
</head>

<body style="color: #eef4f7;background-color: #eef4f7;">
<div>
    <div class="header-blue" style="padding-bottom: 0;">
        <nav class="navbar navbar-light navbar-expand-md navigation-clean-search">
            <div class="container-fluid"><a class="navbar-brand" href="../index2.html" style="font-size: 30px;line-height: 30px;">STSS</a><button data-toggle="collapse" class="navbar-toggler text-white border-white" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><i class="fa fa-align-justify"></i></button>
                <div
                        class="collapse navbar-collapse" id="navcol-1">
                    <ul class="nav navbar-nav">
                        <li class="nav-item" role="presentation"><a class="nav-link" href="../users/all.jsp" style="color: #ffffff;"><i class="fa fa-users" style="margin-right: 5px;color: #ffffff;"></i>Пользователи</a></li>
                        <li class="nav-item" role="presentation"><a class="nav-link" href="../tests/all.html" style="color: #ffffff;"><i class="fa fa-check-square-o" style="margin-right: 5px;color: #ffffff;"></i>Тестирование</a></li>
                        <li class="nav-item" role="presentation"><a class="nav-link" href="../subjects/all.html" style="color: #ffffff;"><i class="fa fa-graduation-cap" style="margin-right: 5px;color: #ffffff;"></i>Дисциплины</a></li>
                        <li class="nav-item" role="presentation"><a class="nav-link active" href="all.html" style="color: #ffffff;"><i class="fa fa-child" style="margin-right: 5px;color: #ffffff;"></i>Группы</a></li>
                        <li class="nav-item" role="presentation"></li>
                    </ul>
                    <form class="form-inline mr-auto" target="_self">
                        <div class="form-group"><label for="search-field"></label></div>
                    </form><span class="navbar-text"><i class="fa fa-user-o" style="color: #ffffff;"></i> <a class="login" href="../profile/profile.html" style="margin-right: 16px;color: #ffffff;">Профиль</a></span></div>
            </div>
        </nav>
    </div>
</div>
<div>
    <div class="container shadow-lg p-3 mb-5 rounded" style="margin-top: 40px;background-color: #ffffff;">
        <div class="row">
            <div class="col">
                <h3 style="color: #505e6c;font-family: 'Source Sans Pro', sans-serif;">Группа <%= group.getName() %></h3>
            </div>
        </div>
        <div class="row">
            <div class="col col-2" style="font-family: 'Source Sans Pro', sans-serif;color: #505e6c;">
                <div>
                    <%= "Создана: " + group.getCreatedAt().getDayOfMonth() + "/" + group.getCreatedAt().getMonthValue() + "/"
                            + group.getCreatedAt().getYear() + " в " + group.getCreatedAt().getHour() + ":"
                            + group.getCreatedAt().getMinute()%>
                </div>
                <div>
                    <a class="btn btn-primary border-white" href="edit.jsp" style="background-color: #f4476b;">Изменить группу</a>
                </div>
            </div>
            <div class="col" style="font-family: 'Source Sans Pro', sans-serif;color: #505e6c;">
                <div class="row">
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                            <tr>
                                <th></th>
                                <th>Имя</th>
                                <th>Фамилия</th>
                                <th>Отчество</th>
                                <th>Дата рождения</th>
                                <th>Статус</th>
                            </tr>
                            </thead>
                            <tbody>
                            <% List<Users> users = (List<Users>) group.getUsersList();
                                for (Users user: users) {
                                    /* Avatar */
                                    final String firstName = user.getFirstName();
                                    final String lastName = user.getLastName();
                                    final String middleName = user.getMiddleName();
                                    final Date birthday = user.getBirthDate();
                                    final String workTitle = user.getWorkTitle();
                            %>
                            <tr>
                                <td><%= "*Avatar*" %></td>
                                <td><%= firstName %></td>
                                <td><%= lastName %></td>
                                <td><%= middleName %></td>
                                <td><%= birthday.toString() %></td>
                                <td><%= workTitle %></td>
                            </tr>
                            <% } %>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="row">
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                            <tr>
                                <th>Название</th>
                                <th>Дисциплина</th>
                                <th>Крайний срок</th>
                                <th>Обязателен</th>
                                <th>ФИО автора</th>
                            </tr>
                            </thead>
                            <tbody>
                            <% List<Tests> tests = (List<Tests>) group.getTestsList();
                                for (Tests test: tests) {
                                    /* Avatar */
                                    final String title = test.getTitle();
                                    final String subject = SubjectsService.getInstance().getById(test.getSubjectId()).getName();
                                    final LocalDateTime deadline = test.getDeadline();
                                    final boolean isNecessary = test.getIsNecessary();
                                    final String author = UsersService.getInstance().getById(test.getCreatorId()).getLastName() + " "
                                            + UsersService.getInstance().getById(test.getCreatorId()).getFirstName() + " "
                                            + UsersService.getInstance().getById(test.getCreatorId()).getMiddleName();
                            %>
                            <tr>
                                <td><%= title %></td>
                                <td><%= subject %></td>
                                <td><%= group.getCreatedAt().getDayOfMonth() + "/" + group.getCreatedAt().getMonthValue() + "/"
                                        + group.getCreatedAt().getYear() + " в " + group.getCreatedAt().getHour() + ":"
                                        + group.getCreatedAt().getMinute() %></td>
                                <td><%=  (isNecessary) ? "Да" : "Нет" %></td>
                                <td><%= author %></td>
                            </tr>
                            <% } %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="../assets/js/jquery.min.js"></script>
<script src="../assets/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>