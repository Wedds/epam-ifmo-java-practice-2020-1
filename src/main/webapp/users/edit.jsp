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
            <form>
                <div class="form-row">
                    <div class="col">
                        <h3 style="color: #505e6c;font-family: 'Source Sans Pro', sans-serif;">Пользователь</h3>
                    </div>
                </div>
                <div class="form-row">
                    <div class="col"><label style="color: #505e6c;">Email</label><input class="form-control" type="email"><label style="color: #505e6c;">Роль</label><select class="form-control" style="margin-bottom: 10px;"><option value="undefined">Студент</option><option value="12" selected="">Преподаватель</option><option value="13">Администратор</option><option value="">Модератор</option></select>
                        <label
                            style="color: #505e6c;">Пароль</label><input class="form-control" type="email"><label style="color: #505e6c;">Пароль (повторно)</label><input class="form-control" type="email"><label style="color: #505e6c;">Дата рождения</label><input class="form-control"
                                type="date"><label style="color: #505e6c;">Дата создания</label><input class="form-control" type="date"></div>
                    <div class="col"><label style="color: #505e6c;">Имя</label><input class="form-control" type="email"><label style="color: #505e6c;">Фамилия</label><input class="form-control" type="email"><label style="color: #505e6c;">Отчество</label><input class="form-control"
                            type="email"><label style="color: #505e6c;">Ссылка на аватар</label><input class="form-control" type="email"><label style="color: #505e6c;">Наименование должности</label><input class="form-control" type="email"><label style="color: #505e6c;">Группа</label>
                        <select
                            class="form-control" style="margin-bottom: 10px;">
                            <option value="undefined">К2151</option>
                            <option value="12" selected="">К8468</option>
                            </select>
                    </div>
                </div>
                <div class="form-row">
                    <div class="col"><button class="btn btn-primary border-white" type="button" style="background-color: #f4476b;">Добавить (изменить) дисциплину</button><button class="btn btn-primary border-white" type="button" style="background-color: #f4476b;margin-left: 20px;">Посмотреть в профиле</button></div>
                </div>
            </form>
        </div>
    </div>
    <script src="../assets/js/jquery.min.js"></script>
    <script src="../assets/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>