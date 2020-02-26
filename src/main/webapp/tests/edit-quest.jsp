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
            <form>
                <div class="form-row">
                    <div class="col">
                        <h3 style="color: #505e6c;font-family: 'Source Sans Pro', sans-serif;">Новый вопрос</h3><label style="color: #505e6c;">Ссылка на изображение</label><input class="form-control" type="email"><label style="color: #505e6c;">Описание</label><textarea class="form-control"></textarea><label style="color: #505e6c;">Тест-контейнер</label>
                        <select
                            class="form-control">
                            <option value="12" selected="">Тест 1</option>
                            <option value="13">Тест 2</option>
                            <option value="14">Тест 3</option>
                            </select>
                    </div>
                    <div class="col">
                        <h3 style="color: #505e6c;font-family: 'Source Sans Pro', sans-serif;">Ответы</h3>
                        <ul class="list-group" style="color: #00adb5;margin-bottom: 20px;">
                            <li class="list-group-item" style="background-color: #505e6c;"><span style="color: #ffffff;">Ответ 1</span></li>
                            <li class="list-group-item" style="background-color: #505e6c;"><span style="color: #ffffff;">Ответ 2</span></li>
                            <li class="list-group-item" style="background-color: #505e6c;"><span style="color: #ffffff;">Ответ 3</span></li>
                        </ul>
                        <div class="form-row">
                            <div class="col"><select class="form-control"><option value="">Ответ 1</option><option value="">Ответ 2</option><option value="">Ответ 3</option><option value="">Ответ 4</option></select></div>
                            <div class="col-1"><button class="btn btn-primary" type="button" style="background-color: #00adb5;"><i class="fa fa-plus"></i></button></div>
                        </div>
                    </div>
                </div>
                <div class="form-row">
                    <div class="col"><button class="btn btn-primary" type="button" style="background-color: #f4476b;margin-top: 40px;">Добавить (изменить) тест</button><a class="btn btn-primary" href="edit-answ.html" style="margin-top: 40px;margin-left: 16px;background-color: #f4476b;">Добавить ответ</a></div>
                </div>
            </form>
        </div>
    </div>
    <script src="../assets/js/jquery.min.js"></script>
    <script src="../assets/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>