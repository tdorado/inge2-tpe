<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Message Sender</title>
</head>
<body>
<h1>Write message</h1>
<c:url var="send_message_url" value="/sendMessage"/>
<form:form action="${send_message_url}" method="post" modelAttribute="message">
    <form:label path="textMessage">Message: </form:label> 
    <form:input type="text" path="textMessage" autofocus="autofocus"/>
    <input type="submit" value="Submit"/>
</form:form>
<c:if test="${sentMessageSuccess}">
    <div>Successfully sent message ${sentMessage.textMessage}</div>
</c:if>
</body>
</html>