<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>STSS - Пользователи</title>
    <%@include file="../blocks/head.jsp"%>
</head>

<body style="height: 100%;">
    <div class="register-photo" style="font-family: 'Source Sans Pro', sans-serif;height: 100%;padding-top: 26vh;background-color: #f1f7fc;">
        <div class="form-container">
            <div class="image-holder" style="font-family: 'Source Sans Pro', sans-serif;background-image: url(&quot;../assets/img/green-chameleon-s9CC2SKySJM-unsplash.jpg&quot;);"></div>
            <form method="post">
                <h2 class="text-center" style="width: 300px;"><strong>Добро пожаловать!</strong></h2>
                <div class="form-group"><input class="form-control" type="email" name="email" placeholder="Email"></div>
                <div class="form-group"><input class="form-control" type="password" name="password" placeholder="Пароль"></div>
                <div class="form-group"><button class="btn btn-primary btn-block" type="submit">Вход</button></div><a class="already" href="signin.html">Нет аккаунта? Создать!</a></form>
        </div>
    </div>
    <script src="../assets/js/jquery.min.js"></script>
    <script src="../assets/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>