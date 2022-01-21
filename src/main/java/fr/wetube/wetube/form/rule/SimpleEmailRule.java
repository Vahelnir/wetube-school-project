package fr.wetube.wetube.form.rule;

import fr.wetube.wetube.form.FormError;
import fr.wetube.wetube.form.Rule;
import fr.wetube.wetube.form.error.NotValidEmailError;

import java.util.Optional;

public class SimpleEmailRule implements Rule {
    private final String regex = "^(.+)@(\\S+)$";

    @Override
    public Optional<FormError> validate(Object value) {
        if (value instanceof String && ((String) value).matches(regex)) {
            return Optional.empty();
        }
        return Optional.of(new NotValidEmailError("Doit être une adresse email valide."));
    }
}
