package fr.wetube.wetube;

import org.jetbrains.annotations.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    @Inject
    private EntityManager entityManager;

    public void doGet(HttpServletRequest request, @NotNull HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

//        Video video = new Video("Vidéo de test", "Je suis une vidéo de test lol blabla xd ptdr lorem ipsum", "arz4VAE_x0E", "youtube");

//        entityManager.getTransaction().begin();
//        entityManager.persist(video);
//        entityManager.getTransaction().commit();
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
//        out.println("<h1>" + bcryptPasswordEncoder.encode(video.getTitle()) + "</h1>");
        out.println("</body></html>");
    }

}