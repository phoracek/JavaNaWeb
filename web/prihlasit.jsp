<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="m" %>

<m:Base titulek="Zápisky">    
    <form method="POST" action="j_security_check" >
        <input type="hidden" name="id" value="${zapisek.id}" />
        <label for="jmeno">Přihlašovací jméno</label><br>
        <input type="text" name="j_username" id="jmeno" />
        <br>
        <label for="heslo">Heslo</label><br>
        <input type="password" name="j_password" id="heslo" />
        <br>
        <input value="Přihlásit" type="submit" />
    </form>

    <c:if test="${param.upozorneni}">
        <span>Zadané údaje nejsou platné.</span>
    </c:if>
</m:Base>