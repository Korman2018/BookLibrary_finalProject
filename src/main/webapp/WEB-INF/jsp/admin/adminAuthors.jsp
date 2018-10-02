<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<head>
	<link href="../css/tables.css" rel="stylesheet">
	<title>Список авторов</title>
</head>

<body id="admin_authors_body">

	<c:set var="redirect" value="../adminAuthors" />
	<%@include file="../admin/adminHeader.jsp"%>

	<div>

		<div id="admin_authors_header" class="page_headers">
			<form name="add_author" action="../adminAuthors/add" method="POST">
				<table>
					<tr>
						<td>
							<input type="text" name="firstName" placeholder="введите имя" required pattern="[А-Я]{1}[а-я]+">
						</td>
						<td>
							<input type="text" name="patronymic" placeholder="введите отчество">
						</td>
						<td>
							<input type="text" name="surname" placeholder="введите фамилию" required pattern="[А-Я]{1}[а-я]+">
						</td>
						<td>
							<input type="submit" name="add_author" value="добавить автора" onclick="return conf();" class="form_buttons">
						</td>
					</tr>
				</table>
			</form>
		</div>

		<div>
			<table class="hvr" id="authors_tbl">
				<tr title="">
					<th id="author_id">id автора</th>
					<th>Имя</th>
					<th>Отчество</th>
					<th>Фамилия</th>
					<th>сохранить</th>
					<th>удалить</th>
				</tr>
				<c:forEach var="it" items="${authors}">
					<tr>
						<form class="author_request" action="../adminAuthors/save" method="POST">
							<td class="author_id">${it.id}</td>
							<td><input type="text" name="firstName" value="${it.person.firstName}"></td>
							<td><input type="text" name="patronymic" value="${it.person.patronymic}"></td>
							<td><input type="text" name="surname" value="${it.person.surname}"></td>
							<td class="hdn">
								<input class="hdn" type="number" name="saveId" value="${it.id}">
							</td>
							<td>
								<input type="submit" name="save_author" value="сохранить изменения" onclick="return conf();" class="form_buttons">
							</td>
						</form>
						<td>
							<form class="author_request" action="../adminAuthors/del" method="POST">
								<input class="hdn" type="number" name="id" value="${it.id}">
								<input type="submit" name="del_author" value="удалить" onclick="return conf();" class="form_buttons">
							</form>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>

	</div>

</body>

</html>