<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <head>
        <title>STSS</title>
        <%@include file="blocks/head.jsp" %>
    </head>
</head>
<body>
<div>
    <div class="header-blue" style="padding-bottom: 70px;">
        <nav class="navbar navbar-light navbar-expand-md navigation-clean-search">
            <div class="container-fluid"><a class="navbar-brand" href="index2.html"
                                            style="font-size: 30px;line-height: 30px;">STSS</a>
                <button data-toggle="collapse" class="navbar-toggler text-white border-white" data-target="#navcol-1">
                    <span class="sr-only">Toggle navigation</span><i class="fa fa-align-justify"></i></button>
                <div
                        class="collapse navbar-collapse" id="navcol-1">
                    <ul class="nav navbar-nav">
                        <li class="nav-item" role="presentation"><a class="nav-link" href="#" style="color: white;">О
                            нас</a></li>
                        <li class="nav-item" role="presentation"></li>
                    </ul>
                    <span class="ml-auto navbar-text" style="margin-right: 16px;color: #ffffff;"><a
                            style="color: white;" href="auth/login.html">Войти</a></span><a
                        class="btn btn-light action-button" role="button" href="auth/signin.html"
                        style="background-color: #f4476b;color: #ffffff;">Регистрация</a></div>
            </div>
        </nav>
        <div class="container hero">
            <div class="row">
                <div class="col-12 col-lg-6 col-xl-5 offset-xl-1">
                    <h1 style="font-weight: bold;font-family: 'Source Sans Pro', sans-serif;">Simple Testing System for
                        Students</h1>
                    <p style="font-weight: 400;">Новая система тестирования призвана сделать этот процесс максимально
                        удобным и наиболее эффективным!<br></p>
                    <button class="btn btn-light btn-lg action-button" type="button" style="background-color: #f4476b;">
                        Заэнджоиться!
                    </button>
                </div>
                <div
                        class="col-md-5 col-lg-5 offset-lg-1 offset-xl-0 d-none d-lg-block phone-holder"><img
                        class="device" src="assets/img/test%20(1).png" style="width: 26vw;height: 26vw;"></div>
            </div>
        </div>
    </div>
</div>
<div class="features-boxed">
    <div class="container">
        <div class="intro">
            <h2 class="text-center">Features </h2>
            <p class="text-center">Nunc luctus in metus eget fringilla. Aliquam sed justo ligula. Vestibulum nibh erat,
                pellentesque ut laoreet vitae.</p>
        </div>
        <div class="row justify-content-center features">
            <div class="col-sm-6 col-md-5 col-lg-4 item">
                <div class="box"><i class="fa fa-map-marker icon"></i>
                    <h3 class="name">Works everywhere</h3>
                    <p class="description">Aenean tortor est, vulputate quis leo in, vehicula rhoncus lacus. Praesent
                        aliquam in tellus eu.</p><a class="learn-more" href="#">Learn more »</a></div>
            </div>
            <div class="col-sm-6 col-md-5 col-lg-4 item">
                <div class="box"><i class="fa fa-clock-o icon"></i>
                    <h3 class="name">Always available</h3>
                    <p class="description">Aenean tortor est, vulputate quis leo in, vehicula rhoncus lacus. Praesent
                        aliquam in tellus eu.</p><a class="learn-more" href="#">Learn more »</a></div>
            </div>
            <div class="col-sm-6 col-md-5 col-lg-4 item">
                <div class="box"><i class="fa fa-list-alt icon"></i>
                    <h3 class="name">Customizable </h3>
                    <p class="description">Aenean tortor est, vulputate quis leo in, vehicula rhoncus lacus. Praesent
                        aliquam in tellus eu.</p><a class="learn-more" href="#">Learn more »</a></div>
            </div>
        </div>
    </div>
</div>
<div class="team-grid">
    <div class="container">
        <div class="intro">
            <h2 class="text-center" style="margin-bottom: 0;">Разработчики</h2>
        </div>
        <div class="row people">
            <div class="col-md-4 col-lg-3 item">
                <div class="box" style="background-image:url(assets/img/1.jpg)">
                    <div class="cover">
                        <h3 class="name">Ben Johnson</h3>
                        <p class="title">Musician</p>
                        <div class="social"><a href="#"><i class="fa fa-facebook-official"></i></a><a href="#"><i
                                class="fa fa-twitter"></i></a><a href="#"><i class="fa fa-instagram"></i></a></div>
                    </div>
                </div>
            </div>
            <div class="col-md-4 col-lg-3 item">
                <div class="box" style="background-image:url(assets/img/2.jpg)">
                    <div class="cover">
                        <h3 class="name">Emily Clark</h3>
                        <p class="title">Artist </p>
                        <div class="social"><a href="#"><i class="fa fa-github"></i></a><a href="#"><i
                                class="fa fa-twitter"></i></a><a href="#"><i class="fa fa-instagram"></i></a></div>
                    </div>
                </div>
            </div>
            <div class="col-md-4 col-lg-3 item">
                <div class="box" style="background-image:url(assets/img/3.jpg)">
                    <div class="cover">
                        <h3 class="name">Carl Kent</h3>
                        <p class="title">Stylist </p>
                        <div class="social"><a href="#"><i class="fa fa-facebook-official"></i></a><a href="#"><i
                                class="fa fa-twitter"></i></a><a href="#"><i class="fa fa-instagram"></i></a></div>
                    </div>
                </div>
            </div>
            <div class="col-md-4 col-lg-3 item">
                <div class="box" style="background-image:url(assets/img/4.jpg)">
                    <div class="cover">
                        <h3 class="name">Felicia Adams</h3>
                        <p class="title">Model </p>
                        <div class="social"><a href="#"><i class="fa fa-facebook-official"></i></a><a href="#"><i
                                class="fa fa-twitter"></i></a><a href="#"><i class="fa fa-instagram"></i></a></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="blocks/footer.jsp" %>
</body>
</html>
