<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="m" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="internacionalizace.text" />

<fmt:message key="base.title" var="titulek" />
<m:Base titulek='${titulek}'>  
    <form method="POST" action="j_security_check" >
        <input type="hidden" name="id" value="${zapisek.id}" />
        <label for="jmeno"><fmt:message key="login.username" /></label><br>
        <input type="text" name="j_username" id="jmeno" />
        <br>
        <label for="heslo"><fmt:message key="login.password" /></label><br>
        <input type="password" name="j_password" id="heslo" />
        <br>
        <input value='<fmt:message key="login.submit" />' type="submit" />
    </form>

    <c:if test="${param.upozorneni}">
        <span><fmt:message key="login.invalid" /></span>
    </c:if>
</m:Base>