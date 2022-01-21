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

@WebServlet(name = "signup", value = "/signup")
public class SignUpServlet extends HttpServlet {
    @Inject
    private RequestParameters parameters;

    @Inject
    private UserRepository userRepository;

    Field usernameField;
    Field emailField;
    Field passwordField;
    Field passwordConfirmationField;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User loggedUser = (User) request.getSession().getAttribute("user");
        if (loggedUser != null) {
            response.sendRedirect(request.getContextPath() + "/signin");
            return;
        }

        Form form = createForm();
        request.setAttribute("form", form);
        request.getRequestDispatcher("/WEB-INF/signup.jsp").forward(request, response);
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
            request.getRequestDispatcher("/WEB-INF/signup.jsp").forward(request, response);
            return;
        }

        // there is no need to check if they are present since they've been validated above
        String username = parameters.getString("username").get();
        String email = parameters.getString("email").get();
        String password = parameters.getString("password").get();
        String passwordConfirmation = parameters.getString("password_confirm").get();

        Optional<User> userFromEmail = userRepository.findByEmail(email);
        if (userFromEmail.isPresent()) {
            emailField.getErrors().add(new FormError("Email is already used."));
            request.getRequestDispatcher("/WEB-INF/signup.jsp").forward(request, response);
            return;
        }

        if (!password.equals(passwordConfirmation)) {
            passwordConfirmationField.getErrors().add(new FormError("Passwords not matching"));
            request.getRequestDispatcher("/WEB-INF/signup.jsp").forward(request, response);
            return;
        }

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        User user = new User(email, passwordEncoder.encode(password));
        user.setUsername(username);

        userRepository.save(user);
        response.sendRedirect(request.getContextPath() + "/signin");
    }

    private Form createForm() {
        Form form = new Form();
        usernameField = form
            .addField("username", "text", new Rule[]{ new NotEmptyRule(), new LengthRule().min(6) })
            .setPlaceholder("Nom de compte");
        emailField = form
            .addField("email", "text", new Rule[]{ new NotEmptyRule(), new SimpleEmailRule() })
            .setPlaceholder("Email");
        passwordField = form
            .addField("password", "password", new Rule[]{ new NotEmptyRule(), new LengthRule().min(10) })
            .setPlaceholder("Mot de passe");
        passwordConfirmationField = form
            .addField("password_confirm", "password", new Rule[]{ new NotEmptyRule(), new LengthRule().min(10) })
            .setPlaceholder("Confirmation du mot de passe");
        return form;
    }
}
