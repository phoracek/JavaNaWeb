<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="m" %>

<m:Base titulek="Zápisky">   
    <form action="<c:url value='/pridat' />" method="POST" id="pridat-zapisek">
        <label for="nadpis">Nadpis</label>
        <input type="text" name="nadpis" />
        <br>
        <textarea name="obsah" cols="40" rows="5"></textarea>
        <br>
        <input value="Přidat" type="submit" />
    </form>

    <c:if test="${param.upozorneni}">
        <span>Musíte vyplnit obě pole.</span>
    </c:if>

    <c:choose>
        <c:when test="${not empty zapisky}">
            <c:forEach var="zapisek" items="${zapisky}">
                <div class="zapisek">

                    <div class="nadpis"><c:out value="${zapisek.nadpis}"/></div>

                    <div class="tlacitka">
                        <form method="GET" action="<c:url value='/upravit'/>">
                            <input type="hidden" value="${zapisek.id}" name="id" />
                            <input type="submit" value="Upravit"/>
                        </form>                               
                        <form method="POST" action="<c:url value='/smazat'/>">
                            <input type="hidden" value="${zapisek.id}" name="id" />
                            <input type="submit" value="Smazat"/>
                        </form>                               
                    </div>

                    <div class="obsah"><c:out value="${zapisek.obsah}"/></div>

                </div>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <span>Dosud nebyl přidán žádný zápisek.</span>
        </c:otherwise>
    </c:choose>
           
</m:Base>