<%@ page contentType="text/html; charset=utf-8"%>

<c:if test="${not empty message}">
    <script>
        alert('${message}');
        document.location.href = "${redirect}";
    </script>
</c:if>