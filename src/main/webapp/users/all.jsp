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
                            <li class="nav-item" role="presentation"><a class="nav-link active" href="all.jsp" style="color: #ffffff;"><i class="fa fa-users" style="margin-right: 5px;color: #ffffff;"></i>Пользователи</a></li>
                            <li class="nav-item" role="presentation"><a class="nav-link" href="../tests/all.html" style="color: #ffffff;"><i class="fa fa-check-square-o" style="margin-right: 5px;color: #ffffff;"></i>Тестирование</a></li>
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
                    <h3 style="color: #505e6c;font-family: 'Source Sans Pro', sans-serif;">Пользователи</h3>
                </div>
            </div>
            <div class="row">
                <div class="col" style="font-family: 'Source Sans Pro', sans-serif;color: #505e6c;">
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Email</th>
                                    <th>ФИО</th>
                                    <th>Роль</th>
                                    <th>Группа</th>
                                    <th>Действие</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>test@test.com</td>
                                    <td>Иванов Иван Иванович</td>
                                    <td>Администратор</td>
                                    <td><button class="btn btn-primary" type="button" style="background-color: #00adb5;padding: 3px 7px;margin-right: 16px;">&nbsp;ID: 25&nbsp;<i class="fa fa-mail-forward"></i></button></td>
                                    <td><button class="btn btn-primary" type="button" style="background-color: #00adb5;padding: 3px 7px;margin-right: 16px;"><i class="fa fa-eye"></i></button><button class="btn btn-primary" type="button" style="background-color: #00adb5;padding: 3px 7px;margin-right: 16px;"><i class="fa fa-edit"></i></button></td>
                                </tr>
                                <tr>
                                    <td>test@test.com</td>
                                    <td>Иванов Иван Иванович</td>
                                    <td>Администратор</td>
                                    <td><button class="btn btn-primary" type="button" style="background-color: #00adb5;padding: 3px 7px;margin-right: 16px;">&nbsp;ID: 25&nbsp;<i class="fa fa-mail-forward"></i></button></td>
                                    <td><button class="btn btn-primary" type="button" style="background-color: #00adb5;padding: 3px 7px;margin-right: 16px;"><i class="fa fa-eye"></i></button><button class="btn btn-primary" type="button" style="background-color: #00adb5;padding: 3px 7px;margin-right: 16px;"><i class="fa fa-edit"></i></button></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col"><a class="btn btn-primary" href="edit.jsp" style="background-color: #f4476b;">Добавить пользователя</a></div>
            </div>
        </div>
    </div>
    <script src="../assets/js/jquery.min.js"></script>
    <script src="../assets/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>