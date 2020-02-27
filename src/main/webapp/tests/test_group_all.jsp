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
                            <option value="noGroup" selected="">Тест без группы</option>
                                <optgroup label="Биология">
                                    <option value="">Эукариоты</option>
                                    <option value="13">Прокариоты</option>
                                    <option value="14">Хорда</option>
                            </optgroup>
                    </select>
                    </form>
                </div>
                <div class="col">
                    <form><select class="form-control"><option value="" selected="">Выберите группу</option><optgroup label="Группы K"><option value="12" selected="">K6844</option><option value="13">K6847</option><option value="14">K8484</option></optgroup></select></form>
                </div>
            </div>
            <div class="row">
                <div class="col" style="font-family: 'Source Sans Pro', sans-serif;color: #505e6c;">
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Тест</th>
                                    <th>Группа</th>
                                    <th>Обязательный</th>
                                    <th>Дедлайн</th>
                                    <th>Попытки</th>
                                    <th>Ограничение времени</th>
                                    <th>Действие</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>ID 1 (Пирамиды)</td>
                                    <td>ID 2 (K5489)</td>
                                    <td>Да</td>
                                    <td>2002-15-65</td>
                                    <td>3</td>
                                    <td>60 минут</td>
                                    <td><button class="btn btn-primary" type="button" style="background-color: #00adb5;padding: 3px 7px;margin-right: 16px;"><i class="fa fa-eye"></i></button><button class="btn btn-primary" type="button" style="background-color: #00adb5;padding: 3px 7px;margin-right: 16px;"><i class="fa fa-edit"></i></button></td>
                                </tr>
                                <tr>
                                    <td>ID 1 (Что-то там еще)</td>
                                    <td>ID 2 (Л5684)</td>
                                    <td>Да</td>
                                    <td>2002-15-65</td>
                                    <td>3</td>
                                    <td>60 минут</td>
                                    <td><button class="btn btn-primary" type="button" style="background-color: #00adb5;padding: 3px 7px;margin-right: 16px;"><i class="fa fa-eye"></i></button><button class="btn btn-primary" type="button" style="background-color: #00adb5;padding: 3px 7px;margin-right: 16px;"><i class="fa fa-edit"></i></button></td>
                                </tr>
                                <tr>
                                    <td>ID 1 (Пирамиды)</td>
                                    <td>ID 2 (K5489)</td>
                                    <td>Да</td>
                                    <td>2002-15-65</td>
                                    <td>3</td>
                                    <td>60 минут</td>
                                    <td><button class="btn btn-primary" type="button" style="background-color: #00adb5;padding: 3px 7px;margin-right: 16px;"><i class="fa fa-eye"></i></button><button class="btn btn-primary" type="button" style="background-color: #00adb5;padding: 3px 7px;margin-right: 16px;"><i class="fa fa-edit"></i></button></td>
                                </tr>
                                <tr>
                                    <td>ID 1 (Пирамиды)</td>
                                    <td>ID 2 (K5489)</td>
                                    <td>Да</td>
                                    <td>2002-15-65</td>
                                    <td>3</td>
                                    <td>60 минут</td>
                                    <td><button class="btn btn-primary" type="button" style="background-color: #00adb5;padding: 3px 7px;margin-right: 16px;"><i class="fa fa-eye"></i></button><button class="btn btn-primary" type="button" style="background-color: #00adb5;padding: 3px 7px;margin-right: 16px;"><i class="fa fa-edit"></i></button></td>
                                </tr>
                                <tr>
                                    <td>ID 1 (Пирамиды)</td>
                                    <td>ID 2 (K5489)</td>
                                    <td>Да</td>
                                    <td>2002-15-65</td>
                                    <td>3</td>
                                    <td>60 минут</td>
                                    <td><button class="btn btn-primary" type="button" style="background-color: #00adb5;padding: 3px 7px;margin-right: 16px;"><i class="fa fa-eye"></i></button><button class="btn btn-primary" type="button" style="background-color: #00adb5;padding: 3px 7px;margin-right: 16px;"><i class="fa fa-edit"></i></button></td>
                                </tr>
                                <tr>
                                    <td>ID 1 (Пирамиды)</td>
                                    <td>ID 2 (K5489)</td>
                                    <td>Да</td>
                                    <td>2002-15-65</td>
                                    <td>3</td>
                                    <td>60 минут</td>
                                    <td><button class="btn btn-primary" type="button" style="background-color: #00adb5;padding: 3px 7px;margin-right: 16px;"><i class="fa fa-eye"></i></button><button class="btn btn-primary" type="button" style="background-color: #00adb5;padding: 3px 7px;margin-right: 16px;"><i class="fa fa-edit"></i></button></td>
                                </tr>
                                <tr>
                                    <td>ID 1 (Пирамиды)</td>
                                    <td>ID 2 (K5489)</td>
                                    <td>Да</td>
                                    <td>2002-15-65</td>
                                    <td>3</td>
                                    <td>60 минут</td>
                                    <td><button class="btn btn-primary" type="button" style="background-color: #00adb5;padding: 3px 7px;margin-right: 16px;"><i class="fa fa-eye"></i></button><button class="btn btn-primary" type="button" style="background-color: #00adb5;padding: 3px 7px;margin-right: 16px;"><i class="fa fa-edit"></i></button></td>
                                </tr>
                                <tr>
                                    <td>ID 1 (Пирамиды)</td>
                                    <td>ID 2 (K5489)</td>
                                    <td>Да</td>
                                    <td>2002-15-65</td>
                                    <td>3</td>
                                    <td>60 минут</td>
                                    <td><button class="btn btn-primary" type="button" style="background-color: #00adb5;padding: 3px 7px;margin-right: 16px;"><i class="fa fa-eye"></i></button><button class="btn btn-primary" type="button" style="background-color: #00adb5;padding: 3px 7px;margin-right: 16px;"><i class="fa fa-edit"></i></button></td>
                                </tr>
                                <tr>
                                    <td>ID 1 (Пирамиды)</td>
                                    <td>ID 2 (K5489)</td>
                                    <td>Да</td>
                                    <td>2002-15-65</td>
                                    <td>3</td>
                                    <td>60 минут</td>
                                    <td><button class="btn btn-primary" type="button" style="background-color: #00adb5;padding: 3px 7px;margin-right: 16px;"><i class="fa fa-eye"></i></button><button class="btn btn-primary" type="button" style="background-color: #00adb5;padding: 3px 7px;margin-right: 16px;"><i class="fa fa-edit"></i></button></td>
                                </tr>
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
                    </nav><a class="btn btn-primary" href="test_group_edit.html" style="background-color: #f4476b;">Добавить тест</a>
                    <a class="btn btn-primary" href="test_group_edit.html" style="background-color: #f4476b; margin-left: 16px">Добавить соответствие тест - группа</a></div>
            </div>
        </div>
    </div>
    <div class="features-boxed"></div>
    <script src="../assets/js/jquery.min.js"></script>
    <script src="../assets/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>