<%@tag description="Základní šablona stránky" pageEncoding="UTF-8"%>
<%@ attribute name="titulek" rtexprvalue="true" required="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="internacionalizace.text" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${titulek}</title>
        <link rel='stylesheet' href='<c:url value="/css/style.css"/>' />
    </head>
    <body>
        <h1 style="display: inline"><fmt:message key="base.header" /></h1>
        <form action="" style="display: inline">
            <c:forEach items="${param}" var="parameter">
                <c:if test="${parameter.key != 'language'}">
                    <input type="hidden" name="${parameter.key}" value="${parameter.value}" />
                </c:if>              
            </c:forEach>    
            <select name="language" onchange="submit()">
                <option value="en" ${language == 'en' ? 'selected' : ''}>
                    English
                </option>
                <option value="cs" ${language == 'cs' ? 'selected' : ''}>
                    Česky
                </option>
            </select>
        </form>

        <jsp:doBody/>
   </body>
</html>