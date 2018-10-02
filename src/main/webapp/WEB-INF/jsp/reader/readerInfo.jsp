<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<head>
    <link href="../css/tables.css" rel="stylesheet">
    <script src="../js/checkPasswords.js"></script>
    <title>Персональная информация</title>
</head>

<body id="reader_info_body">

    <c:set var="redirect" value="../readerInfo" />
    <%@include file="../reader/readerHeader.jsp"%>

    <div class="details">
        <h1> Персональная информация</h1>
        <br>
        <form name="reader_info_form" action="../readerInfo" method="post">
            <input class="id_user" value="${current_user.id}" type="number" name="id">
            <h3>Фамилия</h3>
            <input value="${current_user.person.surname}" type="text" name="person.surname" required pattern="[А-Я]{1}[а-я]+">
            <h3>Имя</h3>
            <input value="${current_user.person.firstName}" type="text" name="person.firstName" required pattern="[А-Я]{1}[а-я]+">
            <h3>Отчество</h3>
            <input value="${current_user.person.patronymic}" type="text" name="person.patronymic" pattern="[А-Я]?[а-я]{0,}">
            <h3>Логин</h3>
            <input value="${current_user.login}" type="text" name="login" required pattern="[\w-]{3,10}$">
            <h3>Пароль</h3>
            <input type="password" id="password" name="password" placeholder="новый пароль" minlength="3">
            <h3>Пароль повторно</h3>
            <input type="password" id="password2" name="passwordAgain" placeholder="новый пароль" minlength="3">
            <br>
            <input type="submit" name="submit" value="сохранить изменения" onclick="javascript:return validateForm();"
                class="form_buttons">
        </form>
    </div>

</html>