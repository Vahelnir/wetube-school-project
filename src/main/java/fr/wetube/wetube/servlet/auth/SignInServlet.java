package fr.wetube.wetube.servlet.auth;

import fr.wetube.wetube.database.model.User;
import fr.wetube.wetube.database.repository.UserRepository;
import fr.wetube.wetube.form.Field;
import fr.wetube.wetube.form.Form;
import fr.wetube.wetube.form.FormError;
import fr.wetube.wetube.form.Rule;
import fr.wetube.wetube.form.rule.LengthRule;
import fr.wetube.wetube.form.rule.NotEmptyRule;
import fr.wetube.wetube.form.rule.SimpleEmailRule;
import fr.wetube.wetube.util.RequestParameters;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "signin", value = "/signin")
public class SignInServlet extends HttpServlet {
    @Inject
    private RequestParameters parameters;

    @Inject
    private UserRepository userRepository;

    private Field emailField;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User loggedUser = (User) request.getSession().getAttribute("user");
        if (loggedUser != null) {
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }

        Form form = createForm();
        request.setAttribute("form", form);
        request.getRequestDispatcher("/WEB-INF/signin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User loggedUser = (User) request.getSession().getAttribute("user");
        if (loggedUser != null) {
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }

        Form form = createForm();
        boolean isValid = form.validate(parameters);
        request.setAttribute("form", form);
        if (!isValid) {
            request.getRequestDispatcher("/WEB-INF/signin.jsp").forward(request, response);
            return;
        }

        // there is no need to check if they are present since they've been validated above
        String email = parameters.getString("email").get();
        String password = parameters.getString("password").get();

        Optional<User> userFromEmail = userRepository.findByEmail(email);
        if (userFromEmail.isEmpty()) {
            emailField.getErrors().add(new FormError("Wrong email or password."));
            request.getRequestDispatcher("/WEB-INF/signin.jsp").forward(request, response);
            return;
        }

        User user = userFromEmail.get();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            emailField.getErrors().add(new FormError("Wrong email or password."));
            request.getRequestDispatcher("/WEB-INF/signin.jsp").forward(request, response);
            return;
        }

        request.getSession().setAttribute("user", user);
        response.sendRedirect(request.getContextPath() + "/home");
    }

    private Form createForm() {
        Form form = new Form();
        emailField = form
            .addField("email", "text", new Rule[]{ new NotEmptyRule(), new SimpleEmailRule() })
            .setPlaceholder("Email");
        form.addField("password", "password", new Rule[]{ new NotEmptyRule(), new LengthRule().min(10) })
            .setPlaceholder("Mot de passe");
        return form;
    }
}
