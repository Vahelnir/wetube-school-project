<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="../includes/head.jsp" %>
<%@ include file="../includes/navigation-bar.jsp" %>

<%@page import="fr.wetube.wetube.database.model.VideoType" %>

<c:if test="${video.type == VideoType.YOUTUBE}">
    <iframe id="youtube-player" width="1878" height="724"
            src="https://www.youtube.com/embed/${video.path}?enablejsapi=1"
            title="YouTube video player"
            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
            allowfullscreen></iframe>

    <script defer src="${pageContext.request.contextPath}/js/video.js"></script>
</c:if>

<h2 class="text-white">${video.title}</h2>
<p>Uploaded by <a href="${pageContext.request.contextPath}/user?id=${video.author.id}">${video.author.username}</a></p>
<p>
    ${video.description}
</p>


<div id="video-stopped-modal" class="modal" tabindex="-1" data-bs-backdrop="static" data-bs-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Le mineur n'est pas actif</h5>
            </div>
            <div class="modal-body">
                <p>
                    Votre vidéo s'est stoppée puisque le mineur de cryptomonaie n'est actuellement pas fonctionnel. <br>
                    Veuillez désactiver votre bloqueur de pub ainsi que la potentielle limitation de votre navigateur,
                    puis rééssayez.
                </p>
            </div>
        </div>
    </div>
</div>

<%@ include file="../includes/footer.jsp" %>

<%@ include file="../includes/foot.jsp" %>