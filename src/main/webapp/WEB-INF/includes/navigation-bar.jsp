<nav class="navbar navbar-expand-lg bg-dark navbar-dark">
    <a class="navbar-brand text-success" href="#">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarContent">
            <span class="navbar-toggler-icon"></span>
        </button>
        <a href="${pageContext.request.contextPath}">
            <img src="${pageContext.request.contextPath}/img/logo1.png" alt="Accueil" width="100" height="40"/>
        </a>
    </a>
    <div id="navbar_left" class="collapse navbar-collapse">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link text-light" href="${pageContext.request.contextPath}/home">Accueil</a>
            </li>
        </ul>
    </div>
    <%--    <div class="mx-auto" id="search">--%>
    <%--        <form class="d-flex" method="get" action="${pageContext.request.contextPath}/search">--%>
    <%--            <div class="input-group">--%>
    <%--                <input type="search" class="form-control" placeholder="Recherche..."--%>
    <%--                       aria-label="rechercher" aria-describedby="button-addon2" name="search">--%>
    <%--                <button class="btn btn-outline-secondary" type="button" id="button-addon2">Rechercher</button>--%>
    <%--            </div>--%>
    <%--        </form>--%>
    <%--    </div>--%>
    <div id="navbar_right" class="collapse navbar-collapse">
        <ul class="navbar-nav ms-auto">
            <c:if test="${empty sessionScope.user}">
                <li class="nav-item">
                    <a class="nav-link text-light"
                       href="${pageContext.request.contextPath}/signup">Inscription</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-light"
                       href="${pageContext.request.contextPath}/signin">Connexion</a>
                </li>
            </c:if>
            <c:if test="${not empty sessionScope.user}">
                <li class="nav-item text-white dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">
                            ${sessionScope.user.username}
                    </a>
                    <div class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/video/upload">
                            Ajouter une vidéo
                        </a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Déconnexion</a>
                    </div>
                </li>
            </c:if>
        </ul>
    </div>
</nav>