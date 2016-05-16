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
<form method="POST" action="https://api.dropboxapi.com/1/oauth2/token">
    <input type="text" name="code" value="V4jasYnvCqoAAAAAAACMfm7GgJa9u-QSQXihN_frukQ">
    <input type="text" name="grant_type" value="authorization_code">
    <input type="text" name="client_secret" value="f0mbiavhm1gvlae">
    <input type="text" name="client_id" value="yupwnr7l9luikkb">
    <input type="text" name="redirect_uri" value="http://localhost:8080/api/dropbox/v1/">
    <input type="submit" value="send">

</form>
</body>
</html>
