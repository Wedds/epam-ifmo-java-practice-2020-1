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
                            <li class="nav-item" role="presentation"><a class="nav-link" href="../tests/all.html" style="color: #ffffff;"><i class="fa fa-check-square-o" style="margin-right: 5px;color: #ffffff;"></i>Тестирование</a></li>
                            <li class="nav-item" role="presentation"><a class="nav-link" href="../subjects/all.html" style="color: #ffffff;"><i class="fa fa-graduation-cap" style="margin-right: 5px;color: #ffffff;"></i>Дисциплины</a></li>
                            <li class="nav-item" role="presentation"><a class="nav-link" href="../groups/all.html" style="color: #ffffff;"><i class="fa fa-child" style="margin-right: 5px;color: #ffffff;"></i>Группы</a></li>
                            <li class="nav-item" role="presentation"></li>
                        </ul>
                        <form class="form-inline mr-auto" target="_self">
                            <div class="form-group"><label for="search-field"></label></div>
                        </form><span class="navbar-text"><i class="fa fa-user-o" style="color: #ffffff;"></i> <a class="login" href="profile.html" style="margin-right: 16px;color: #ffffff;">Профиль</a></span></div>
        </div>
        </nav>
    </div>
    </div>
    <div>
        <div class="container" style="margin-top: 40px;font-family: 'Source Sans Pro', sans-serif;">
            <div class="row">
                <div class="col-md-4 col-lg-3 shadow-lg p-3 mb-5 bg-white rounded">
                    <div class="row">
                        <div class="col text-center"><img style="width: 60%;padding-bottom: 40px;" src="../assets/img/person.png"></div>
                    </div>
                    <div class="row">
                        <div class="col"><a class="btn btn-primary" href="../users/edit.jsp" style="background-color: #f4476b;padding: 3px 7px;"><i class="fa fa-edit"></i></a>
                            <h4 style="color: #505e6c;">Иванов Иван Иванович</h4>
                            <h5 style="color: #505e6c;">Возраст: 42</h5>
                            <h6 style="color: #505e6c;">Преподаватель физики</h6>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col"><span class="badge badge-primary bg-info" style="background-color: #00adb5;margin-right: 16px;font-size: 14px;">Роль в системе</span>
                            <p style="margin-left: 0;color: #505e6c;margin-bottom: 8px;">Преподаватель</p><span class="badge badge-primary bg-info" style="background-color: #00adb5;margin-right: 16px;font-size: 14px;">Группа</span>
                            <p style="margin-left: 0;color: #505e6c;">Какая-то группа</p>
                        </div>
                    </div>
                    <div class="row text-center"><button class="btn btn-primary btn-block text-center d-block" type="submit" style="width: 80%;margin-left: 10%;background-color: #f4476b;font-family: 'Source Sans Pro', sans-serif;">Выйти из системы</button></div>
                </div>
                <div class="col-md-8 col-lg-9 offset-md-0 offset-lg-0 shadow-lg p-3 mb-5 rounded" style="background-color: #ffffff;"></div>
            </div>
        </div>
    </div>
    <script src="../assets/js/jquery.min.js"></script>
    <script src="../assets/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>