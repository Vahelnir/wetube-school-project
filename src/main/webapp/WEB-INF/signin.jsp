<%@ include file="includes/head.jsp" %>
<%@ include file="includes/navigation-bar.jsp" %>

<div class="wrapper fadeInDown">
    <div id="formContent">
        <!-- Tabs Titles -->

        <!-- Icon -->
        <div class="fadeIn first">
            <img src="${pageContext.request.contextPath}/img/logo1.png" id="icon" alt="User Icon"/>
        </div>

        <!-- Login Form -->
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
                <input
                    type="${field.type}"
                    id="${field.name}"
                    class="fadeIn second"
                    name="${field.name}"
                    value="${param[field.name]}"
                    placeholder="${field.placeholder}"
                />
            </c:forEach>
            <input type="submit" class="fadeIn fourth" value="Se connecter">
        </form>

        <!-- Remind Passowrd -->
        <div id="formFooter">
            <a class="underlineHover" href="#">Mot de passe oublié ?</a>
        </div>
    </div>
</div>

<%@ include file="includes/foot.jsp" %>

