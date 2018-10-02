<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<head>
  <link href="../css/tables.css" rel="stylesheet">
  <title>Запросы читателя</title>
</head>

<body id="reader_requests_body">

  <c:set var="redirect" value="../readerRequests" />
  <%@include file="../reader/readerHeader.jsp"%>

  <div>

    <div id="reader_requests_header" class="page_headers">
      <form class="add_margins" action="../readerRequests" method="post">
        <input class="hdn" type="number" name="reader_id" value="1">
        <input type="radio" type="number" name="req" value="requested"><span>запрошенные книги</span>
        <input type="radio" type="number" name="req" value="processed"><span>обработанные книги</span>
        <input type="radio" type="number" name="req" value="issued"><span>выданные книги</span>
        <input type="radio" type="number" name="req" value="all"><span>все запросы</span>
        <input type="submit" name="search" value="Найти" class="form_buttons">
      </form>
      <br>
    </div>

    <div>
      <table class="hvr" id="reader_tbl">
        <tr title="">
          <th>Название книги</th>
          <th>Текущий статус</th>
        </tr>
        <c:forEach var="it" items="${requests}">
          <tr>
            <td>${it.book.title}</td>
            <td>${it.status.description}</td>
          </tr>
        </c:forEach>
      </table>
    </div>

  </div>

</body>

</html>