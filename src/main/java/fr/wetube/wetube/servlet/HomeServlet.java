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
import java.util.List;

@WebServlet(name = "home", urlPatterns = { "", "/home" })
public class HomeServlet extends HttpServlet {

    @Inject
    private VideoRepository videoRepository;

    @Inject
    private RequestParameters parameters;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int videosPerPage = 8;
        int page = parameters.getInt("page").orElse(1);

        long videoCount = videoRepository.getVideoCount();
        int maxPage = (int) Math.ceil((float) videoCount / videosPerPage);

        if (page > maxPage) {
            page = maxPage;
        }

        if (page <= 0) {
            page = 1;
        }

        List<Video> videos = videoRepository.getRecentVideos(videosPerPage, (page - 1) * videosPerPage);

        request.setAttribute("page", page);
        request.setAttribute("maxPage", maxPage);
        request.setAttribute("videos", videos);
        request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
    }
}
