<%@ page import="com.ifmo.epampractice.entity.Tests" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.sun.net.httpserver.Authenticator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>STSS - Пользователи</title>
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
                            <li class="nav-item" role="presentation"><a class="nav-link active" href="all.html" style="color: #ffffff;"><i class="fa fa-check-square-o" style="margin-right: 5px;color: #ffffff;"></i>Тестирование</a></li>
                            <li class="nav-item" role="presentation"><a class="nav-link" href="../subjects/all.html" style="color: #ffffff;"><i class="fa fa-graduation-cap" style="margin-right: 5px;color: #ffffff;"></i>Дисциплины</a></li>
                            <li class="nav-item" role="presentation"><a class="nav-link" href="../groups/all.html" style="color: #ffffff;"><i class="fa fa-child" style="margin-right: 5px;color: #ffffff;"></i>Группы</a></li>
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
            <%  Tests test = (Tests) request.getAttribute("test");
                boolean isExist = Boolean.TRUE;
                if (test.getId()==0){
                    isExist = Boolean.FALSE;
                }
                String title = test.getTitle();
                String description = test.getDescription();
                int subjectId = test.getSubjectId();
                boolean isRandom = test.getIsRandom();
            %>
            <form method = "post" action="/edit_test?id=<%= test.getId() %>">
                <div class="form-row">
                    <div class="col">
                        <h3 style="color: #505e6c;font-family: 'Source Sans Pro', sans-serif;"><% if (isExist){%> Изменение теста <% } else { out.print("Создание теста");}%></h3>
                        <label style="color: #505e6c;">Название теста</label><input class="form-control" type="text" name="title" required <% if (isExist){%> value="<%= title %>" <% } %>>
                        <label style="color: #505e6c;">Описание</label><textarea name="description" class="form-control"><% if (isExist){ out.print(description); } %></textarea>
                        </div>
                    <div class="col">
                        <h3 style="color: #505e6c;font-family: 'Source Sans Pro', sans-serif;">&ensp;</h3>
                        <label style="color: #505e6c;">Дисциплина</label>
                        <select
                                class="form-control" name="subject">
                            <%
                            Map<Integer, String> subjectDict = (Map<Integer, String>) request.getAttribute("subjectDict");
                            for (int subjId:subjectDict.keySet()){
                            String subjectTitle = subjectDict.get(subjId);
                            %>
                            <option value="<%= subjId %>" <% if (isExist && subjectId==subjId){%> selected="" <% } %>><%= subjectTitle %></option>
                            <% } %>
                        </select>
                        <br><label style="color: #505e6c;">Порядок</label>
                        <select class="form-control" name="isRandom">
                            <option value="12" selected="">Случайный</option>
                            <option value="13" <% if (isExist && !isRandom){%> selected="" <% } %>>Как при создании</option>
                        </select>
                </div>


                </div>
                <div class="form-row">
                <% if ((Boolean) request.getAttribute("success")){%>
                <br><h4 style="color: #505e6c;font-family: 'Source Sans Pro', sans-serif;">Тест успешно добавлен/изменен</h4>
                <% } %>
                </div>
        <div class="form-row">
            <div class="col"><button type="submit" class="btn btn-primary" style="background-color: #f4476b;margin-top: 40px;">Подтвердить</button><a class="btn btn-primary" href="edit-quest.html" style="margin-top: 40px;margin-left: 16px;background-color: #f4476b;">Добавить вопрос</a></div>
        </div>
        </form>
    </div>
    </div>
    <script src="../assets/js/jquery.min.js"></script>
    <script src="../assets/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>