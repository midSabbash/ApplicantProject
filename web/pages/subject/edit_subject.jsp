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

        <form method="post" action="controller?command=saveSubject">
            <c:choose>
                <c:when test="${subject ne null}">
                    <span>Subject name</span>
                    <input type="text" name="subject_name"
                           value="${subject.getSubjectName()}"/><br/>
                    <input type="hidden" name="subject_id" value="${subject.getId()}"/><br/>
                </c:when>
                <c:otherwise>
                    <span>Subject</span>
                    <input type="text" name="subject_name"/><br/>
                </c:otherwise>
            </c:choose>
            <input type="submit" value="Save"/>
        </form>
    </fieldset>
</div>
</body>
</html>
