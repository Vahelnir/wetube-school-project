package fr.wetube.wetube.servlet;

import fr.wetube.wetube.database.model.Video;
import fr.wetube.wetube.database.repository.VideoRepository;
import fr.wetube.wetube.util.RequestParameters;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@WebServlet(name = "video", urlPatterns = "/video")
public class VideoServlet extends HttpServlet {
    @Inject
    private RequestParameters parameters;

    @Inject
    private VideoRepository videoRepository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Optional<String> idOptional = parameters.getString("p");
        if (idOptional.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }

        UUID uuid;
        try {
            uuid = UUID.fromString(idOptional.get());
        } catch (IllegalArgumentException exception) {
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }

        Optional<Video> videoOptional = videoRepository.findById(uuid);
        if (videoOptional.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }

        request.setAttribute("video", videoOptional.get());
        request.getRequestDispatcher("/WEB-INF/video/video.jsp").forward(request, response);
    }
}
