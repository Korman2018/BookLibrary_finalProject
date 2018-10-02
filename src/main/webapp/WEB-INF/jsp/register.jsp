<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
    <script src="../js/checkPasswords.js"></script>
    <link href="css/register.css" rel="stylesheet">
    <title>Страница регистрации</title>
</head>

<body>

    <c:if test="${not empty message}">
            <script>
                alert('${message}');
                document.location.href = "../register";
            </script>
        </c:if>

    <div class="register-box login-box">
        <img src="images/avatar.png" class="avatar">
        <h1>Регистрация</h1>

        <form name="registerForm" action="../register" method="POST">
            <p>Фамилия</p>
            <input type="text" name="person.surname" placeholder="введите фамилию(RU)" required pattern="[А-Я]{1}[а-я]+">
            <p>Имя</p>
            <input type="text" name="person.firstName" placeholder="введите имя(RU)" required pattern="[А-Я]{1}[а-я]+">
            <p>Отчество</p>
            <input type="text" name="person.patronymic" placeholder="введите отчество (RU)" pattern="[А-Я]?[а-я]{0,}">
            <p>Логин</p>
            <input type="text" name="login" placeholder="введите логин (ENG,3-10 симв)" required pattern="[\w-]{3,10}$">
            <p>Пароль</p>
            <input type="password" id="password" name="password" placeholder="введите пароль(от 3 симв)" minlength="3">
            <p>Пароль повторно</p>
            <input type="password" id="password2" name="passwordAgain" placeholder="введите пароль повторно" minlength="3">
            <br>
            <input type="submit" name="submit" value="register" onclick="javascript:return validateForm();">
            Назад к странице логина?
            <br>
            <br>
            <a href="../login">Логин</a>
        </form>
    </div>


</body>

</html>