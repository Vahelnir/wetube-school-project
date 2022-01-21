package fr.wetube.wetube.util;

import org.jetbrains.annotations.Nullable;

import javax.enterprise.inject.Vetoed;
import java.util.*;

@Vetoed
public class RequestParameters {

    private Map<String, String[]> parameters;

    public RequestParameters() {
        this(new HashMap<>());
    }

    public RequestParameters(Map<String, String[]> parameters) {
        this.parameters = parameters;
    }

    public Optional<Integer> getInt(String key) {
        String[] params = parameters.get(key);
        if (params != null && params.length > 0 && !params[0].isBlank()) {
            try {
                return Optional.of(Integer.parseInt(params[0]));
            } catch (NumberFormatException e) {
                // do nothing
            }
        }
        return Optional.empty();
    }

    public Optional<String> getString(String key) {
        String[] params = parameters.get(key);
        if (params != null && params.length > 0 && !params[0].isBlank()) {
            return Optional.of(params[0]);
        }
        return Optional.empty();
    }

    public Optional<List<String>> getStringList(String key) {
        String[] params = parameters.get(key);
        if (params != null && params.length > 0) {
            return Optional.of(Arrays.asList(params));
        }
        return Optional.empty();
    }

    @Nullable
    public String[] getRaw(String key) {
        return this.parameters.get(key);
    }

    public Object getObject(String key) {
        String[] value = getRaw(key);
        if (value != null && value.length == 1) {
            return value[0];
        }
        return value;
    }

    public Map<String, String[]> getParametersMap() {
        return this.parameters;
    }

    public void setParameters(Map<String, String[]> parameters) {
        this.parameters = parameters;
    }
}
