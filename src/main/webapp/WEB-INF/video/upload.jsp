<%@ include file="../includes/head.jsp" %>
<%@ include file="../includes/navigation-bar.jsp" %>

<div class="wrapper fadeInDown">
    <div id="formContent">
        <form method="post">
            <c:forEach items="${form.fields}" var="field">
                <c:if test="${field.hasErrors()}">
                    <div class="alert alert-danger" role="alert">
                        <ul>
                            <c:forEach var="error" items="${field.errors}">
                                <li>${error.message}</li>
                            </c:forEach>
                        </ul>
                    </div>
                </c:if>
                <c:choose>
                    <c:when test="${not field.type.equals('textarea')}">
                        <input
                            type="${field.type}"
                            id="${field.name}"
                            class="fadeIn second"
                            name="${field.name}"
                            value="${param[field.name]}"
                            placeholder="${field.placeholder}"
                        />
                    </c:when>
                    <c:otherwise>
                        <textarea
                            name="${field.name}"
                            id="${field.name}"
                            class="fadeIn second form-control custom-input"
                            placeholder="${field.placeholder}"
                        >${param[field.name]}</textarea>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <input type="submit" class="fadeIn fourth" value="Ajouter une vidéo">
        </form>
    </div>
</div>

<%@ include file="../includes/foot.jsp" %>

