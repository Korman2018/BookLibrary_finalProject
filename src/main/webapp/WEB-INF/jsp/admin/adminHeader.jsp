<%@ page contentType="text/html; charset=utf-8"%>

<%@include file="../common/showMessage.jsp"%>

<div id="admin_header">
  <ul class="top_menu">
    <li><a href="../adminBase">Каталог книг</a></li>
    <li><a href="../adminRequests">Запросы книг</a></li>
    <li><a href="../adminAuthors">Авторы</a></li>
    <li><a href="../adminGenres">Жанры</a></li>
    <li><a href="../logout">Выход</a></li>
    <li style="float: right">
      <a href="../adminInfo">user:&nbsp;${current_user.login}</a>
    </li>
  </ul>
</div>

<script src="../js/confirm.js"></script>