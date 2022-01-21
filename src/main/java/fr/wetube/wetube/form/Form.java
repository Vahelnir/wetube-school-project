package fr.wetube.wetube.form;

import fr.wetube.wetube.util.RequestParameters;

import java.util.ArrayList;
import java.util.List;

public class Form {

    private final List<Field> fields = new ArrayList<>();

    public Form() {}

    public Field addField(String name, String inputType, Rule[] rules) {
        Field field = new Field(name, inputType, rules);
        this.fields.add(field);
        return field;
    }

    public boolean validate(RequestParameters parameters) {
        boolean areFieldsValid = true;
        for (Field field : fields) {
            boolean isFieldValid = field.validate(parameters.getObject(field.getName()));
            if (!isFieldValid) {
                areFieldsValid = false;
            }
        }

        return areFieldsValid;
    }

    public List<Field> getFields() {
        return fields;
    }
}
