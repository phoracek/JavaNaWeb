<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="m" %>

<m:Base titulek="Zápisky">
    <form action="<c:url value='/ulozitupravy' />" method="POST">
        <input type="hidden" name="index" value="${param.index}" />
        <label for="nadpis">Nadpis</label>
        <input type="text" name="nadpis" value="<c:out value='${zapisek.nadpis}' />" />
        <br>
        <textarea name="obsah" cols="40" rows="5"><c:out value='${zapisek.obsah}' /></textarea>
        <br>
        <input value="Upravit" type="submit" />
    </form>

    <c:if test="${param.upozorneni}">
        <span>Musíte vyplnit obě pole.</span>
    </c:if>
</m:Base>