<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<head>
	<link href="../css/tables.css" rel="stylesheet">
	<title>Информация о книге</title>
</head>

<body id="admin_book_info_body">

	<c:set var="redirect" value="../admin/adminBookInfo" />
	<%@include file="../admin/adminHeader.jsp"%>

	<div class="details">
		<h1> Информация о книге </h1>
		<br>
		<form action="/adminBook/save" method="post">
			<input class="id_book" type="number" name="id" value="${book.id}">
			<p>Название книги</p>
			<input type="text" name="title" value="${book.title}" placeholder="введите название">
			<p>Автор</p>
			<select size="1" id="выберите автора" name="authorId">
				<c:forEach var="it" items="${authors}">
					<option value="${it.id}" <c:if test="${it.id == book.author.id}">selected</c:if>>
						${it.person}</option>
				</c:forEach>
			</select>
			<p>Жанр</p>
			<select size="1" id="выберите жанр" name="genreId">
				<c:forEach var="itg" items="${genres}">
					<option value="${itg.id}" <c:if test="${itg.id == book.genre.id}">selected</c:if>>
						${itg.description}</option>
				</c:forEach>
			</select>
			<br>
			<br>
			<input type="submit" name="submit" value="сохранить изменения" onclick="return conf();" class="form_buttons">
		</form>
	</div>

</body>

</html>