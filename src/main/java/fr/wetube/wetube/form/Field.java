package fr.wetube.wetube.form;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Field {

    private List<Rule> rules = new ArrayList<>();
    private List<FormError> errors = new ArrayList<>();

    private String type;
    private String name;
    private String placeholder = "";

    public Field(String name, String type, List<Rule> rules) {
        this.type = type;
        this.name = name;
        this.rules = rules;
    }

    public Field(String name, String type, Rule[] rules) {
        this(name, type, new ArrayList<>(Arrays.asList(rules)));
    }

    public boolean validate(Object value) {
        List<FormError> errors = getRules().stream()
            .map(rule -> rule.validate(value))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toList());
        setErrors(errors);
        return errors.size() == 0;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    public List<FormError> getErrors() {
        return errors;
    }

    public void setErrors(List<FormError> errors) {
        this.errors = errors;
    }

    public boolean hasErrors() {
        return this.errors.size() > 0;
    }

    public String getType() {
        return type;
    }

    public Field setType(String type) {
        this.type = type;
        return this;
    }

    public String getName() {
        return name;
    }

    public Field setName(String name) {
        this.name = name;
        return this;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public Field setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        return this;
    }
}
