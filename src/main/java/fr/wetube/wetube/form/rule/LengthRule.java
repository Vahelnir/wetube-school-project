package fr.wetube.wetube.form.rule;

import fr.wetube.wetube.form.FormError;
import fr.wetube.wetube.form.Rule;
import fr.wetube.wetube.form.error.NotEmptyError;

import java.util.List;
import java.util.Optional;

public class LengthRule implements Rule {
    private int max;
    private int min;

    public LengthRule(int min, int max) {
        this.max = max;
        this.min = min;
    }

    public LengthRule() {
        this(0, 40);
    }

    @Override
    public Optional<FormError> validate(Object value) {
        if (value instanceof String && isInRange(((String) value).length(), getMin(), getMax())) {
            return Optional.of(
                new NotEmptyError("Doit avoir entre " + getMin() + " et " + getMax() + " caractères.")
            );
        }

        if (value instanceof List && isInRange(((List) value).size(), getMin(), getMax())) {
            return Optional.of(
                new NotEmptyError("Doit avoir entre " + getMin() + " et " + getMax() + " éléments.")
            );
        }

        return Optional.empty();
    }

    private boolean isInRange(int valueLength, int min, int max) {
        return valueLength < min || valueLength > max;
    }

    public int getMax() {
        return max;
    }

    public LengthRule max(int max) {
        this.max = max;
        return this;
    }

    public int getMin() {
        return min;
    }

    public LengthRule min(int min) {
        this.min = min;
        return this;
    }
}
