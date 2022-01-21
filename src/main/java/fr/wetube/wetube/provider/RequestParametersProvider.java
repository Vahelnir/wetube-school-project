package fr.wetube.wetube.provider;

import fr.wetube.wetube.util.RequestParameters;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class RequestParametersProvider {

    @Produces
    @RequestScoped
    public RequestParameters produceRequestParameter(HttpServletRequest request) {
        return new RequestParameters(new HashMap<>(request.getParameterMap()));
    }
}
