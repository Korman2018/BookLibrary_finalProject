<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<head>
  <link href="../css/tables.css" rel="stylesheet">
  <title>Запросы(страница библиотекаря)</title>
</head>

<body id="admin_requests_body">

  <c:set var="redirect" value="../adminRequests" />
  <%@include file="../admin/adminHeader.jsp"%>

  <div>

    <div id="admin_requests_header" class="page_headers">
      <form class="add_margins" action="/adminRequests" method="post">
        <input type="radio" type="number" name="req" value="requested"><span> запрошенные книги</span>
        <input type="radio" type="number" name="req" value="processed"><span>обработанные книги</span>
        <input type="radio" type="number" name="req" value="issued"><span>выданные книги</span>
        <input type="radio" type="number" name="req" value="all"><span>все запросы</span>
        <input type="submit" name="search" value="Найти" class="form_buttons">
        <br>
      </form>
      <br>
    </div>

    <div>
      <table class="hvr" id="admin_tbl">
        <tr title="">
          <th id="request_id">id</th>
          <th>Читатель</th>
          <th>Название книги</th>
          <th>Библиотекарь</th>
          <th>Текущий статус</th>
          <th>Сохранить</th>
        </tr>
        <c:forEach var="it" items="${requests}">
          <form class="book_request" action="../adminRequests/save" method="POST">
            <tr>
              <td class="id_request">${it.id}</td>
              <td>${it.reader.person}</td>
              <td>${it.book.title}</td>
              <td>${it.librarian.person}</td>
              <td>
                <select size="1" class="statuses" name="status">
                  <c:forEach var="its" items="${statuses}">
                    <option value="${its.id}" <c:if test="${its.id == it.status.id}">selected</c:if>>
                      ${its.description}</option>
                  </c:forEach>
                </select>
              </td>
              <td class="hdn">
                <input class="hdn" type="number" name="saveId" value="${it.id}">
              </td>
              <td>
                <input type="submit" name="save" value="сохранить изменения" onclick="return conf();" class="form_buttons">
              </td>
            </tr>
          </form>
        </c:forEach>
      </table>
    </div>

  </div>

</body>

</html>