<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<head>
  <link href="../css/tables.css" rel="stylesheet">
  <script src="../js/jquery-3.3.1.min.js"></script>
  <script src="../js/getRowId.js"></script>
  <title>Страница библиотекаря</title>
</head>

<body id="admin_base_body">

  <div>

    <c:set var="redirect" value="../adminBase" />
    <%@include file="../admin/adminHeader.jsp"%>

    <div id="admin_base_header" class="page_headers">
      <div id="add_book_div">
        <form name="add_book" action="../adminBase/add" method="post">
          <table id="add_book">
            <tr>
              <td>
                <span>Название</span>
                <input type="text" name="title" placeholder="введите название(длина от 2 симв)" required>
              </td>
              <td>
                <span>Автор</span>
                <select size="1" name="authorId" placeholder="выберите автора">
                  <c:forEach var="it" items="${authors}">
                    <option value="${it.id}">${it.person}</option>
                  </c:forEach>
                </select>
              </td>
              <td>
                <span>Жанр&nbsp;&nbsp;</span>
                <select size="1" name="genreId" placeholder="выберите жанр">
                  <c:forEach var="itg" items="${genres}">
                    <option value="${itg.id}">${itg.description}</option>
                  </c:forEach>
                </select>
              </td>
              <td>
                <input type="submit" name="add_book" value="добавить книгу" onclick="return conf();" class="form_buttons">
              </td>
            </tr>
          </table>
        </form>
      </div>
      <hr>
      <div>
        <form id="admin_base_search" action="/adminBase" method="post">
          <table>
            <tr>
              <td> <input type="text" name="search_string" placeholder="Поиск.."></td>
              <td>
                <input type="radio" name="search" value="title"> <span>по названию</span>
                <input type="radio" name="search" value="author"> <span>по автору</span>
                <input type="radio" name="search" value="genre"><span>по жанру</span>
                <input type="radio" name="search" value="all"> <span> все книги</span>
              </td>
              <td><input type="submit" name="search_button" value="Найти" class="form_buttons"></td>
            </tr>
          </table>
        </form>
      </div>
      <br>
    </div>

    <div>
      <table class="hvr" id="admin_tbl" title="Просмотр подробной информации">
        <thead>
          <tr class="books_header">
            <th>id</th>
            <th>Название книги</th>
            <th>Автор</th>
            <th>Жанр</th>
            <th>Удалить</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="it" items="${books}">
            <form action="../adminBase/del" method="POST">
              <tr>
                <td>${it.id}</td>
                <td>${it.title}</td>
                <td>${it.author}</td>
                <td>${it.genre}</td>
                <td style="display:none">
                  <input style="display:none" type="number" name="del_id" value="${it.id}">
                </td>
                <td>
                  <input type="submit" name="del_book" value="удалить" onclick="return conf();" class="form_buttons">
                </td>
              </tr>
            </form>
          </c:forEach>
        </tbody>
      </table>
    </div>

  </div>

</body>

</html>