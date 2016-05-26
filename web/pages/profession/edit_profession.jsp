<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><c:out value="${title}"/></title>
    <%@include file="../include/style.jsp" %>
</head>
<body>
<%@include file="../include/template.jsp" %>
<div class="container">
    <fieldset>
        <legend><c:out value="${title}"/></legend>

        <form method="post" action="controller?command=saveProfession">
            <c:choose>
                <c:when test="${profession ne null}">
                    <span>Profession name</span>
                    <input type="text" name="profession_name"
                           value="${profession.getProfessionName()}"/><br/>

                </c:when>
                <c:otherwise>
                    <span>Profession</span>
                    <input type="text" name="profession_id"/><br/>

                </c:otherwise>
            </c:choose>
            <input type="submit" value="Save"/>
        </form>
    </fieldset>
</div>
</body>
</html>
