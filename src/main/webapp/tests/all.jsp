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
                                    <th>Название</th>
                                    <th>Предмет</th>
                                    <th>Дата прохождения</th>
                                    <th>Дедлайн</th>
                                    <th>Результат</th>
                                    <th>Количество попыток</th>
                                    <th>Действие</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>Треугольники</td>
                                    <td>Математика</td>
                                    <td>2002-15-65</td>
                                    <td>2002-15-65</td>
                                    <td>25/100</td>
                                    <td>3/3</td>
                                    <td><button class="btn btn-primary" type="button" style="background-color: #00adb5;padding: 3px 7px;margin-right: 16px;"><i class="fa fa-eye"></i></button><button class="btn btn-primary" type="button" style="background-color: #00adb5;padding: 3px 7px;margin-right: 16px;"><i class="fa fa-edit"></i></button></td>
                                </tr>
                                <tr>
                                    <td>Трапеции</td>
                                    <td>Математика</td>
                                    <td>2002-15-65</td>
                                    <td>2002-15-65</td>
                                    <td>25/100</td>
                                    <td>3/3</td>
                                    <td><button class="btn btn-primary" type="button" style="background-color: #00adb5;padding: 3px 7px;margin-right: 16px;"><i class="fa fa-eye"></i></button><button class="btn btn-primary" type="button" style="background-color: #00adb5;padding: 3px 7px;margin-right: 16px;"><i class="fa fa-edit"></i></button></td>
                                </tr>
                                <tr>
                                    <td>Рок-н-Ролл</td>
                                    <td>Музыка</td>
                                    <td>2002-15-65</td>
                                    <td>2002-15-65</td>
                                    <td>25/100</td>
                                    <td>3/3</td>
                                    <td><button class="btn btn-primary" type="button" style="background-color: #00adb5;padding: 3px 7px;margin-right: 16px;"><i class="fa fa-eye"></i></button><button class="btn btn-primary" type="button" style="background-color: #00adb5;padding: 3px 7px;margin-right: 16px;"><i class="fa fa-edit"></i></button></td>
                                </tr>
                                <tr>
                                    <td>Рок-н-Ролл</td>
                                    <td>Музыка</td>
                                    <td>2002-15-65</td>
                                    <td>2002-15-65</td>
                                    <td>25/100</td>
                                    <td>3/3</td>
                                    <td><button class="btn btn-primary" type="button" style="background-color: #00adb5;padding: 3px 7px;margin-right: 16px;"><i class="fa fa-eye"></i></button><button class="btn btn-primary" type="button" style="background-color: #00adb5;padding: 3px 7px;margin-right: 16px;"><i class="fa fa-edit"></i></button></td>
                                </tr>
                                <tr>
                                    <td>Рок-н-Ролл</td>
                                    <td>Музыка</td>
                                    <td>2002-15-65</td>
                                    <td>2002-15-65</td>
                                    <td>25/100</td>
                                    <td>3/3</td>
                                    <td><button class="btn btn-primary" type="button" style="background-color: #00adb5;padding: 3px 7px;margin-right: 16px;"><i class="fa fa-eye"></i></button><button class="btn btn-primary" type="button" style="background-color: #00adb5;padding: 3px 7px;margin-right: 16px;"><i class="fa fa-edit"></i></button></td>
                                </tr>
                                <tr>
                                    <td>Рок-н-Ролл</td>
                                    <td>Музыка</td>
                                    <td>2002-15-65</td>
                                    <td>2002-15-65</td>
                                    <td>25/100</td>
                                    <td>3/3</td>
                                    <td><button class="btn btn-primary" type="button" style="background-color: #00adb5;padding: 3px 7px;margin-right: 16px;"><i class="fa fa-eye"></i></button><button class="btn btn-primary" type="button" style="background-color: #00adb5;padding: 3px 7px;margin-right: 16px;"><i class="fa fa-edit"></i></button></td>
                                </tr>
                                <tr>
                                    <td>Рок-н-Ролл</td>
                                    <td>Музыка</td>
                                    <td>2002-15-65</td>
                                    <td>2002-15-65</td>
                                    <td>25/100</td>
                                    <td>3/3</td>
                                    <td><button class="btn btn-primary" type="button" style="background-color: #00adb5;padding: 3px 7px;margin-right: 16px;"><i class="fa fa-eye"></i></button><button class="btn btn-primary" type="button" style="background-color: #00adb5;padding: 3px 7px;margin-right: 16px;"><i class="fa fa-edit"></i></button></td>
                                </tr>
                                <tr>
                                    <td>Рок-н-Ролл</td>
                                    <td>Музыка</td>
                                    <td>2002-15-65</td>
                                    <td>2002-15-65</td>
                                    <td>25/100</td>
                                    <td>3/3</td>
                                    <td><button class="btn btn-primary" type="button" style="background-color: #00adb5;padding: 3px 7px;margin-right: 16px;"><i class="fa fa-eye"></i></button><button class="btn btn-primary" type="button" style="background-color: #00adb5;padding: 3px 7px;margin-right: 16px;"><i class="fa fa-edit"></i></button></td>
                                </tr>
                                <tr>
                                    <td>Рок-н-Ролл</td>
                                    <td>Музыка</td>
                                    <td>2002-15-65</td>
                                    <td>2002-15-65</td>
                                    <td>25/100</td>
                                    <td>3/3</td>
                                    <td><button class="btn btn-primary" type="button" style="background-color: #00adb5;padding: 3px 7px;margin-right: 16px;"><i class="fa fa-eye"></i></button><button class="btn btn-primary" type="button" style="background-color: #00adb5;padding: 3px 7px;margin-right: 16px;"><i class="fa fa-edit"></i></button></td>
                                </tr>
                                <tr>
                                    <td>Рок-н-Ролл</td>
                                    <td>Музыка</td>
                                    <td>2002-15-65</td>
                                    <td>2002-15-65</td>
                                    <td>25/100</td>
                                    <td>3/3</td>
                                    <td><button class="btn btn-primary" type="button" style="background-color: #00adb5;padding: 3px 7px;margin-right: 16px;"><i class="fa fa-eye"></i></button><button class="btn btn-primary" type="button" style="background-color: #00adb5;padding: 3px 7px;margin-right: 16px;"><i class="fa fa-edit"></i></button></td>
                                </tr>
                                <tr>
                                    <td>Евангелион</td>
                                    <td>Анимелогия</td>
                                    <td>2002-15-65</td>
                                    <td>2002-15-65</td>
                                    <td>100500/100</td>
                                    <td>3/3</td>
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
                    </nav><a class="btn btn-primary" href="edit.html" style="background-color: #f4476b;">Добавить тест</a></div>
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