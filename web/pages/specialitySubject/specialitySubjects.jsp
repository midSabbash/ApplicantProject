<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
    <%@include file="../include/style.jsp" %>
</head>
<body>
<%@include file="../include/template.jsp" %>
<div class="container">
    <fieldset>
        <legend>SpecialitySubject</legend>
        <c:choose>
        <c:when test="${specialitySubject.size() == 0}">
            <p><c:out value="No specialitySubject yet"></c:out></p>
        </c:when>
        <c:otherwise>
        <table>
            <tr>
                <th>ID</th>
                <th>Profession ID</th>
                <th>Subject ID</th>
                <th>Actions</th>
            </tr>
            <c:forEach items="${specialitySubjects}" var="specialitySubject">
                <tr>
                    <td>
                        <c:out value="${specialitySubject.getId()}"/>
                    </td>
                    <td>
                        <c:out value="${specialitySubject.getProfession().getId()}"/>
                    </td>
                    <td>
                        <c:out value="${specialitySubject.getSubject().getId()}"/>
                    </td>
                    <td>
                        <a title="Edit ${specialitySubject.getId()} ${profession.getId()} ${subject.getId()} "
                           href="controller?command=editSpecSubject&id=${specialitySubject.getId()}">
                            <i class="fa fa-pencil"></i>
                        </a>
                        <a title="Delete ${specialitySubject.getId()} ${profession.getId()} ${subject.getId()} "
                           href="controller?command=deleteSpecSubject&id=${specialitySubject.getId()}">
                            <i class="fa fa-trash-o"></i>
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </fieldset>
    </c:otherwise>
    </c:choose>
    <div class="add_button">
        <i class="fa fa-plus-circle"></i>
        <a href="controller?command=addSpecSubject">Add new SpecialitySubject</a>
    </div>
</div>
</body>
</html>