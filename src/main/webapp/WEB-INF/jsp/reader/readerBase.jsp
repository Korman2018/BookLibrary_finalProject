<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<head>
  <link href="../css/tables.css" rel="stylesheet">
  <title>Страница читателя</title>
</head>

<body class="base_tables">

  <c:set var="redirect" value="../readerBase" />
  <%@include file="../reader/readerHeader.jsp"%>

  <div id="reader_base_body">

    <div id="reader_base_header" class="page_headers">
      <form action="../readerBase" method="post">
        <table>
          <tr>
            <td>
              <input type="text" name="search_string" placeholder="Поиск..">
            </td>
            <td>
              <input type="radio" name="search" value="title"><span> по названию</span>
              <input type="radio" name="search" value="author"><span>по автору</span>
              <input type="radio" name="search" value="genre"><span>по жанру</span>
              <input type="radio" name="search" value="all"><span>все книги</span>
            </td>
            <td>
              <input type="submit" name="search_button" value="Найти" class="form_buttons">
            </td>
          </tr>
        </table>
      </form>
    </div>

    <div>
      <table class="hvr" id="reader_tbl">
        <tr>
          <th class="id_book">id</th>
          <th>Название книги</th>
          <th>Автор</th>
          <th>Жанр</th>
          <th>Запрос книги</th>
        </tr>
        <c:forEach var="it" items="${books}">
          <form action="../readerBase/req" method="post">
            <tr>
              <td class="hdn">${it.id}</td>
              <td>${it.title}</td>
              <td>${it.author}</td>
              <td>${it.genre}</td>
              <td class="hdn">
                <input class="hdn" type="number" name="id" value="${it.id}">
              </td>
              <td>
                <input type="submit" name="req" value="Запросить" onclick="return conf();" class="form_buttons">
              </td>
            </tr>
          </form>
        </c:forEach>
      </table>
    </div>

  </div>

</body>

</html>