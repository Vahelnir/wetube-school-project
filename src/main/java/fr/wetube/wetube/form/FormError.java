package fr.wetube.wetube.form;

public class FormError {
    private final String message;

    public FormError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
