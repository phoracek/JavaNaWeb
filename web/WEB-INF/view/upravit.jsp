<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="m" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="internacionalizace.text" />

<fmt:message key="base.title" var="titulek" />
<m:Base titulek='${titulek}'> 
    <form action="<c:url value='/ulozitupravy' />" method="POST">
        <input type="hidden" name="id" value="${zapisek.id}" />
        <label for="nadpis"><fmt:message key="notes.header" /></label>
        <input type="text" name="nadpis" value="<c:out value='${zapisek.nadpis}' />" />
        <br>
        <textarea name="obsah" cols="40" rows="5"><c:out value='${zapisek.obsah}' /></textarea>
        <br>
        <input value='<fmt:message key="notes.edit" />' type="submit" />
    </form>

    <c:if test="${param.upozorneni}">
        <span><fmt:message key="notes.warning.fillboth" /></span>
    </c:if>
</m:Base>