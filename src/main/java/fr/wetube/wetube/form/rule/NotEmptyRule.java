package fr.wetube.wetube.form.rule;

import fr.wetube.wetube.form.FormError;
import fr.wetube.wetube.form.Rule;
import fr.wetube.wetube.form.error.NotEmptyError;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class NotEmptyRule implements Rule {
    @Override
    public Optional<FormError> validate(Object value) {
        if (objectIsEmpty(value)) {
            return Optional.of(new NotEmptyError("Ne peut pas être vide."));
        }

        return Optional.empty();
    }

    private boolean objectIsEmpty(Object value) {
        return value == null
            || (value instanceof String && ((String) value).isBlank())
            || (value instanceof List && ((List) value).isEmpty())
            || (value instanceof Map && ((Map) value).isEmpty());
    }
}
