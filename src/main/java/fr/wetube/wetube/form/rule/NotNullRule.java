package fr.wetube.wetube.form.rule;

import fr.wetube.wetube.form.FormError;
import fr.wetube.wetube.form.Rule;
import fr.wetube.wetube.form.error.NullError;

import java.util.Optional;

public class NotNullRule implements Rule {
    @Override
    public Optional<FormError> validate(Object value) {
        if (value != null) {
            return Optional.of(new NullError("Ne doit pas être null."));
        }
        return Optional.empty();
    }
}
