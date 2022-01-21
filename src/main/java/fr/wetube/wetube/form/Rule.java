package fr.wetube.wetube.form;

import java.util.Optional;

public interface Rule {
    Optional<FormError> validate(Object value);
}
