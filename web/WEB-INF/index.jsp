<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Zápisky</title>
    </head>
    <body>
        <h1>Zápisky</h1>
        <form method="POST" action="">
            <input type="text" name="zapisek" />
            <input type="submit" name="odeslat" value="Zapsat" />
        </form>
        <ul>
            <c:forEach var="zapisek" items="${zapisky}">
                <li><c:out value="${zapisek}" /></li> 
            </c:forEach>
        </ul>
    </body>
</html> 