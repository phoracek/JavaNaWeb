<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="m" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="internacionalizace.text" />

<fmt:message key="base.title" var="titulek" />
<m:Base titulek='${titulek}'>   
    <form action="<c:url value='/pridat' />" method="POST" id="pridat-zapisek">
        <label for="nadpis"><fmt:message key="notes.header" /></label>
        <input type="text" name="nadpis" />
        <br>
        <textarea name="obsah" cols="40" rows="5"></textarea>
        <br>
        <input value='<fmt:message key="notes.addnew" />' type="submit" />
    </form>

    <c:if test="${param.upozorneni}">
        <span><fmt:message key="notes.warning.fillboth" /></span>
    </c:if>

    <c:choose>
        <c:when test="${not empty zapisky}">
            <c:forEach var="zapisek" items="${zapisky}">
                <div class="zapisek">

                    <div class="nadpis"><c:out value="${zapisek.nadpis}"/></div>

                    <div class="tlacitka">
                        <form method="GET" action="<c:url value='/upravit'/>">
                            <input type="hidden" value="${zapisek.id}" name="id" />
                            <input type="submit" value='<fmt:message key="notes.edit" />' />
                        </form>                               
                        <form method="POST" action="<c:url value='/smazat'/>">
                            <input type="hidden" value="${zapisek.id}" name="id" />
                            <input type="submit" value='<fmt:message key="notes.delete" />' />
                        </form>                               
                    </div>

                    <div class="obsah"><c:out value="${zapisek.obsah}"/></div>

                </div>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <span><fmt:message key="notes.warning.none" /></span>
        </c:otherwise>
    </c:choose>
           
</m:Base>