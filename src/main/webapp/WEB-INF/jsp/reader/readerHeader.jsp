<%@ page contentType="text/html; charset=utf-8"%>

<%@include file="../common/showMessage.jsp"%>

<div id="reader_header">
  <ul class="top_menu">
    <li><a href="readerBase">Каталог книг</a></li>
    <li><a href="readerRequests">Мои запросы</a></li>
    <li><a href="logout">Выход</a></li>
    <li style="float: right">
      <a href="../readerInfo">user:&nbsp;${current_user.login}</a>
    </li>
  </ul>
</div>
<script src="../js/confirm.js"></script>