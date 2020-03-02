<%@ page import="com.ifmo.epampractice.entity.Tests" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Collection" %>
<%@ page import="com.ifmo.epampractice.entity.Groups" %>
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
                    <h3 style="color: #505e6c;font-family: 'Source Sans Pro', sans-serif;">Все тесты</h3>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <form>
                        <select class="form-control">
                            <%
                                Map<Integer, String> subjectDict = (Map<Integer, String>) request.getAttribute("subjectDict");
                                List<Integer> subjectIdList = (List<Integer>) request.getAttribute("subjectIdList");
                                for (Integer subjectId:subjectIdList){
                                    String subjectTitle = subjectDict.get(subjectId);

                                    %>

                                <optgroup label="<%= subjectTitle %>">

                            <%
                                for (Tests test:(List<Tests>) request.getAttribute("testsList")){
                                    if (test.getSubjectId() == subjectId) {
                                        String testTitle = test.getTitle();
                                        int testId = test.getId();
                                        %>
                                    <option value="<%= testId %>"><%= testTitle %></option>
                                    <%
                                    }

                            %>

                            </optgroup>
                            <% }
                            }
                            %>
                    </select>
                    </form>
                </div>
                <div class="col">
                    <form>
                        <select class="form-control">
                            <option value="" selected="">Выберите группу</option>

                            <%
                                Map<Integer, String> groupsDict = (Map<Integer, String>) request.getAttribute("groupsDict");
                                for (int groupId:groupsDict.keySet()){
                            %>
                            <!--<optgroup label="Группы K"> -->
                                <option value="<%= groupId %>>"><%= groupsDict.get(groupId) %></option>
                            <!-- </optgroup> -->
                            <% } %>
                        </select>
                    </form>
                </div>
            </div>
            <div class="row">
                <div class="col" style="font-family: 'Source Sans Pro', sans-serif;color: #505e6c;">
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Тест (Id)</th>
                                    <th>Группа (Id)</th>
                                    <th>Обязательный</th>
                                    <th>Дедлайн</th>
                                    <th>Попытки</th>
                                    <th>Ограничение времени</th>
                                    <th>Действие</th>
                                </tr>
                            </thead>
                            <tbody>

                            <%
                                for (Tests test: (List<Tests>) request.getAttribute("testsForGroupsList")) {
                                int testId = test.getId();
                                String testTitle = test.getTitle();
                                int groupId = test.getGroupId();
                                String groupTitle = groupsDict.get(groupId);
                                String isNecessary = "Нет";
                                if (test.getIsNecessary()){
                                    isNecessary = "Да";
                                }
                                LocalDateTime deadline = test.getDeadline();
                                int maxAttempts = test.getMaxAttempts();
                                int timeLimit = test.getTimeLimit();


                            %>

                                <tr>
                                    <td><%= testTitle %> (<%=testId %>)</td>
                                    <td><%= groupTitle %> (<%=groupId %>)</td>
                                    <td><%= isNecessary %></td>
                                    <td><%= deadline %></td>
                                    <td><%= maxAttempts %></td>
                                    <td><%= timeLimit %> минут</td>
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
                    <nav>
                        <ul class="pagination">
                            <li class="page-item"><a class="page-link" href="#" aria-label="Previous"><span aria-hidden="true">«</span></a></li>
                            <li class="page-item"><a class="page-link" href="#">1</a></li>
                            <li class="page-item"><a class="page-link" href="#">2</a></li>
                            <li class="page-item"><a class="page-link" href="#">3</a></li>
                            <li class="page-item"><a class="page-link" href="#">4</a></li>
                            <li class="page-item"><a class="page-link" href="#">5</a></li>
                            <li class="page-item"><a class="page-link" href="#" aria-label="Next"><span aria-hidden="true">»</span></a></li>
                        </ul>
                    </nav><a class="btn btn-primary" href="${pageContext.request.contextPath}/edit_test" style="background-color: #f4476b;">Добавить тест</a>
                    <a class="btn btn-primary" href="test_group_edit.html" style="background-color: #f4476b; margin-left: 16px">Добавить соответствие тест - группа</a></div>
            </div>
        </div>
    </div>
    <div class="features-boxed"></div>
    <script src="../assets/js/jquery.min.js"></script>
    <script src="../assets/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>