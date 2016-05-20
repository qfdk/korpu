<%--
  Created by IntelliJ IDEA.
  User: qfdk
  Date: 16/5/12
  Time: 21:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Logined</title>
</head>
<body>
<form method="POST" action="https://api.dropboxapi.com/2/users/get_space_usage?authorization">
    <label>
        <input type="text" name="access_token" value="V4jasYnvCqoAAAAAAACNN4c-ZH1WjYJFgmfhFgRefJ5w7SWUUwhwW9-z23BEUOih">
    </label>
    <input type="submit" value="send">

</form>
</body>
</html>
