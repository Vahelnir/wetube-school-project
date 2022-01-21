<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="includes/head.jsp" %>
<%@ include file="includes/navigation-bar.jsp" %>

<h2 class="text-white ms-2 my-4">Les dernieres vidéos en ligne</h2>
<div class="container-fluid" style="background-color: #202020">
    <div class="row">
        <c:forEach items="${videos}" var="video">
            <div class="col-12 col-lg-3 col-md-4">
                <div class="card" style="background-color: #181818; margin-top: 30px">
                    <a href="play">
                        <div class="text-center" style="margin-top: 10px">
                            <img class="card-img-top img-fluid" src="${video.thumbnail}" alt=""/>
                        </div>
                    </a>
                    <div class="card-body">
                        <a href="${pageContext.request.contextPath}/video?p=${video.id}">
                            <h5 class="card-title">${video.title}</h5>
                        </a>
                        <p>Uploaded by <a
                            href="${pageContext.request.contextPath}/user?id=${video.author.id}">${video.author.username}</a>
                        </p>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <c:if test="${maxPage != 1}">
        <nav aria-label="Page navigation example">
            <ul class="pagination">
                <li class="page-item ${page == 1 ? 'disabled' : ''}">
                    <a class="page-link" href="?page=${page - 1}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <c:forEach var="i" begin="1" end="${maxPage}">
                    <li class="page-item ${i == page ? 'active' : ''}">
                        <a class="page-link" href="?page=${i}">${i}</a>
                    </li>
                </c:forEach>
                <li class="page-item ${page == maxPage ? 'disabled' : ''}">
                    <a class="page-link" href="?page=${page + 1}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </ul>
        </nav>
    </c:if>
</div>

<%@ include file="includes/footer.jsp" %>

<%@ include file="includes/foot.jsp" %>