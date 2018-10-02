<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<head>
	<link href="../css/tables.css" rel="stylesheet">
	<title>Список жанров</title>
</head>

<body id="admin_genres_body">

	<c:set var="redirect" value="../adminGenres" />
	<%@include file="../admin/adminHeader.jsp"%>

	<div>
		<div id="admin_genres_header" class="page_headers">
			<form name="add_genre" action="../adminGenres/add" method="POST">
				<table id="add_genre">
					<tr>
						<td>
							<input type="text" name="description" placeholder="введите название жанра" required>
						</td>
						<td>
							<input type="submit" name="add_genre" value="добавить жанр" onclick="return conf();" class="form_buttons">
						</td>
					</tr>
				</table>
			</form>
		</div>

		<div>
			<table class="hvr" id="genres_tbl" title="список жанров">
				<tr>
					<th id="genre_id">id жанра</th>
					<th>название жанра</th>
					<th>сохранить</th>
					<th>удалить</th>
				</tr>
				<c:forEach var="it" items="${genres}">
					<tr>
						<form id="save_genre" class="genre_request" action="../adminGenres/save" method="POST">
							<td class="genre_id">${it.id}</td>
							<td><input type="text" name="description" value="${it.description}"></td>
							<td class="hdn">
								<input class="hdn" type="number" name="id" value="${it.id}">
							</td>
							<td>
								<input type="submit" name="button" value="сохранить изменения" onclick="return conf();" class="form_buttons">
							</td>
						</form>
						<td>
							<form class="genre_request" action="../adminGenres/del" method="POST">
								<input class="hdn" type="number" name="id" value="${it.id}">
								<input type="submit" name="del_genre" value="удалить" onclick="return conf();" class="form_buttons">
							</form>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>

	</div>

</body>

</html>