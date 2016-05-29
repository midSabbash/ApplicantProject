<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

        <form method="post" action="controller?command=saveSpecSubject">
            <c:choose>
                <c:when test="${specialitySubject ne null}">
                    <span>Profession ID</span>
                    <input type="text" name="profession_id"
                           value="${specialitySubject.getProfessionId()}"/><br/>
                    <span>Subject ID</span>
                    <input type="text" name="subject_id" value="${specialitySubject.getSubjectId()}"/><br/>
                </c:when>
                <c:otherwise>
                    <span>Profession ID</span>
                    <input type="text" name="profession_id"/><br/>
                    <span>Subject ID</span>
                    <input type="text" name="subject_id"/><br/>
                </c:otherwise>
            </c:choose>
            <input type="submit" value="Save"/>
        </form>
    </fieldset>
</div>
</body>
</html>