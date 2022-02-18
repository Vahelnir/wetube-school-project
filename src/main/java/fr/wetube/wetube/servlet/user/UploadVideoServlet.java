package fr.wetube.wetube.servlet.user;

import fr.wetube.wetube.database.model.User;
import fr.wetube.wetube.database.model.Video;
import fr.wetube.wetube.database.model.VideoType;
import fr.wetube.wetube.database.repository.VideoRepository;
import fr.wetube.wetube.form.Form;
import fr.wetube.wetube.form.Rule;
import fr.wetube.wetube.form.rule.NotEmptyRule;
import fr.wetube.wetube.util.RequestParameters;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "uploadVideo", urlPatterns = "/video/upload")
public class UploadVideoServlet extends HttpServlet {
    @Inject
    private RequestParameters parameters;

    @Inject
    private VideoRepository videoRepository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User loggedUser = (User) request.getSession().getAttribute("user");
        if (loggedUser == null) {
            response.sendRedirect(request.getContextPath() + "/signin");
            return;
        }

        Form form = createForm();
        request.setAttribute("form", form);
        request.getRequestDispatcher("/WEB-INF/video/upload.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User loggedUser = (User) request.getSession().getAttribute("user");
        if (loggedUser == null) {
            response.sendRedirect(request.getContextPath() + "/signin");
            return;
        }

        Form form = createForm();
        boolean isValid = form.validate(parameters);
        request.setAttribute("form", form);
        if (!isValid) {
            request.getRequestDispatcher("/WEB-INF/video/upload.jsp").forward(request, response);
            return;
        }

        // there is no need to check if they are present since they've been validated above
        String title = parameters.getString("title").get();
        String path = parameters.getString("path").get();
        String thumbnail = parameters.getString("thumbnail").get();

        Video video = new Video();
        video.setTitle(title);
        video.setPath(path);
        video.setThumbnail(thumbnail);
        video.setAuthor(loggedUser);
        // TODO: handle a real file upload & file streaming
        video.setType(VideoType.YOUTUBE);

        videoRepository.save(video);
        response.sendRedirect(request.getContextPath() + "/video?p=" + video.getId());
    }

    private Form createForm() {
        Form form = new Form();
        form.addField("title", "text", new Rule[]{ new NotEmptyRule() })
            .setPlaceholder("Titre");
        form.addField("description", "textarea", new Rule[]{})
            .setPlaceholder("Description");
        form.addField("path", "text", new Rule[]{ new NotEmptyRule() })
            .setPlaceholder("Url");
        form.addField("thumbnail", "text", new Rule[]{ new NotEmptyRule() })
            .setPlaceholder("Miniature");
        return form;
    }
}
