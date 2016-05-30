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

        <form method="post" action="controller?command=saveApplicantResult">
            <c:choose>
                <c:when test="${applicantResult ne null}">
                    <span>Applicant ID</span>
                    <input type="text" name="APPLICANT_ID"
                           value="${applicantResult.getApplicantId()}"/><br/>
                    <span>Subject ID</span>
                    <input type="text" name="SUBJECT_ID"
                           value="${applicantResult.getSubjectId()}"/><br/>
                    <span>Mark</span>
                    <input type="text" name="MARK"
                           value="${applicantResult.getMark()}"/><br/>
                    <input type="hidden" name="APPLICANT_RESULT_ID" value="${applicantResult.getId()}"/><br/>
                </c:when>
                <c:otherwise>
                    <span>Applicant ID</span>
                    <input type="text" name="APPLICANT_ID"/><br/>
                    <span>Subject ID</span>
                    <input type="text" name="SUBJECT_ID"/><br/>
                    <span>Mark</span>
                    <input type="text" name="MARK"/>
                </c:otherwise>
            </c:choose>
            <input type="submit" value="Save"/>
        </form>
    </fieldset>
</div>
</body>
</html>