<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.util.Dictionary" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.ifmo.epampractice.entity.Tests" %>
<%@ page import="java.util.List" %>
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
                            <li class="nav-item" role="presentation"><a class="nav-link" href="../groups/all.html" style="color: #ffffff;"><i class="fa fa-users" style="margin-right: 5px;color: #ffffff;"></i>Пользователи</a></li>
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
            <div class="row">
                <div class="col">
                    <h3 style="color: #505e6c;font-family: 'Source Sans Pro', sans-serif;">Мои тесты</h3>
                </div>
            </div>
            <div class="row">
                <div class="col" style="font-family: 'Source Sans Pro', sans-serif;color: #505e6c;">
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Код теста</th>
                                    <th>Название</th>
                                    <th>Предмет</th>
                                    <th>Количество групп</th>
                                    <th>Действие</th>
                                </tr>
                            </thead>
                            <tbody>

                             <%  Map<Integer, String> subjectDict = (Map<Integer, String>) request.getAttribute("subjectDict");
                                 Map<Integer, Integer> groupsCountDict = (Map<Integer, Integer>) request.getAttribute("groupsCountDict");
                                 for (Tests test: (List<Tests>) request.getAttribute("testsList")) {
                                     int testId = test.getId();
                                     int groupsCount = 0;
                                     if (groupsCountDict.containsKey(testId))  {
                                         groupsCount = groupsCountDict.get(testId);
                                     }
                                     String testTitle = test.getTitle();
                                     String subjectTitle = subjectDict.get(test.getSubjectId());

                             %>
                                <tr>
                                    <td><%= testId %></td>
                                    <td><%= testTitle %></td>
                                    <td><%= subjectTitle %></td>
                                    <td><%= groupsCount  %></td>
                                    <td>
                                        <a class="btn text-white border-white" style="background-color: #00adb5;" href="${pageContext.request.contextPath}/edit_test?id=<%= testId %>">Изменить тест</a>
                                     </td>
                                </tr>
                            <% } %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <a class="btn btn-primary" href="${pageContext.request.contextPath}/edit_test" style="background-color: #f4476b;">Добавить тест</a>
                </div>
            </div>
        </div>
    </div>
    <div class="features-boxed">
        <div class="container">
            <div class="intro">
                <h2 class="text-center" style="padding-top: 0;">Тесты по дисциплинам</h2>
            </div>
            <div class="row justify-content-center features" style="padding-top: 0;">
                <div class="col-sm-6 col-md-5 col-lg-4 item">
                    <div class="box"><i class="fa fa-subscript icon" style="color: #00adb5;"></i>
                        <h3 class="name">Математика</h3>
                        <p class="description">Aenean tortor est, vulputate quis leo in, vehicula rhoncus lacus. Praesent aliquam in tellus eu.</p><a class="learn-more" href="#" style="color: #f4476b;">Learn more »</a></div>
                </div>
                <div class="col-sm-6 col-md-5 col-lg-4 item">
                    <div class="box"><i class="fa fa-code icon" style="color: #00adb5;"></i>
                        <h3 class="name">Программирование</h3>
                        <p class="description">Aenean tortor est, vulputate quis leo in, vehicula rhoncus lacus. Praesent aliquam in tellus eu.</p><a class="learn-more" href="#" style="color: #f4476b;">Learn more »</a></div>
                </div>
                <div class="col-sm-6 col-md-5 col-lg-4 item">
                    <div class="box"><i class="fa fa-headphones icon" style="color: #00adb5;"></i>
                        <h3 class="name">Музыка</h3>
                        <p class="description">Aenean tortor est, vulputate quis leo in, vehicula rhoncus lacus. Praesent aliquam in tellus eu.</p><a class="learn-more" href="#">Learn more »</a></div>
                </div>
                <div class="col-sm-6 col-md-5 col-lg-4 item">
                    <div class="box"><i class="fa fa-file-text-o icon" style="color: #00adb5;"></i>
                        <h3 class="name">Журналистика</h3>
                        <p class="description">Aenean tortor est, vulputate quis leo in, vehicula rhoncus lacus. Praesent aliquam in tellus eu.</p><a class="learn-more" href="#" style="color: #f4476b;">Learn more »</a></div>
                </div>
                <div class="col-sm-6 col-md-5 col-lg-4 item">
                    <div class="box"><i class="fa fa-leaf icon" style="color: #00adb5;"></i>
                        <h3 class="name">Экология</h3>
                        <p class="description">Aenean tortor est, vulputate quis leo in, vehicula rhoncus lacus. Praesent aliquam in tellus eu.</p><a class="learn-more" href="#" style="color: #f4476b;">Learn more »</a></div>
                </div>
                <div class="col-sm-6 col-md-5 col-lg-4 item">
                    <div class="box"><i class="fa fa-underline icon" style="color: #00adb5;"></i>
                        <h3 class="name">Русский язык</h3>
                        <p class="description">Aenean tortor est, vulputate quis leo in, vehicula rhoncus lacus. Praesent aliquam in tellus eu.</p><a class="learn-more" href="#" style="color: #f4476b;">Learn more »</a></div>
                </div>
            </div>
        </div>
    </div>
    <script src="../assets/js/jquery.min.js"></script>
    <script src="../assets/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>