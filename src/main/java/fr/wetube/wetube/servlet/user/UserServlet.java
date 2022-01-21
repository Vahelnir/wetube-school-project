package fr.wetube.wetube.servlet.user;

import fr.wetube.wetube.database.model.User;
import fr.wetube.wetube.database.model.Video;
import fr.wetube.wetube.database.repository.UserRepository;
import fr.wetube.wetube.database.repository.VideoRepository;
import fr.wetube.wetube.util.RequestParameters;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@WebServlet(name = "user", urlPatterns = "/user")
public class UserServlet extends HttpServlet {
    @Inject
    private RequestParameters parameters;

    @Inject
    private UserRepository userRepository;

    @Inject
    private VideoRepository videoRepository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int videosPerPage = 8;
        int page = parameters.getInt("page").orElse(1);
        String id = parameters.getString("id").orElse("");

        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException exception) {
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }

        Optional<User> userOptional = userRepository.findById(uuid);
        if (userOptional.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }

        User user = userOptional.get();
        long videoCount = videoRepository.getUserVideoCount(user);
        int maxPage = (int) Math.ceil((float) videoCount / videosPerPage);

        if (page > maxPage) {
            page = maxPage;
        }

        if (page <= 0) {
            page = 1;
        }

        List<Video> videos = videoRepository.getRecentVideos(videosPerPage, (page - 1) * videosPerPage);

        request.setAttribute("user", user);
        request.setAttribute("page", page);
        request.setAttribute("maxPage", maxPage);
        request.setAttribute("videos", videos);
        request.getRequestDispatcher("/WEB-INF/user/index.jsp").forward(request, response);
    }
}
