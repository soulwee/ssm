<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; utf-8" language="java" %>
<html>
<body>
<h2>Hello World!</h2>
<c:forEach var="list" items="${userList}">

  <span>ID:${list.userId}&nbsp;name:${list.userName}&nbsp;pwd:${list.password}&nbsp;</span><br/>
</c:forEach>
empno&nbsp;ename&nbsp;job&nbsp;<br/>
<c:forEach var="list" items="${empList}">

  <span>${list.empNo}&nbsp;${list.ename}&nbsp;${list.job}&nbsp;</span><br/>
</c:forEach>
</body>
</html>
