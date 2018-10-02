<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<head>
    <link href="css/register.css" rel="stylesheet">
    <title>Страница авторизации</title>
</head>

<body>

    <c:if test="${not empty message}">
        <script>
            alert('${message}');
            document.location.href = "../login";
        </script>
    </c:if>

    <div class="login-box">
        <img src="images/avatar.png" class="avatar">
        <h1>Авторизация</h1>

        <form name="loginForm" action="../login" method="post">
            <p>Логин</p>
            <input type="text" name="login" placeholder="введите логин" required>
            <p>Пароль</p>
            <input type="password" name="password" placeholder="введите пароль" required>
            <br>
            <input type="submit" name="submit" value="login">
            <br>
            Новый пользователь?
            <br>
            <br>
            <a href="../register">Регистрация</a>
        </form>
    </div>

</body>

</html>