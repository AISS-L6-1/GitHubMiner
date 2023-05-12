package aiss.GitHubMiner.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ProjectNotFoundException extends Exception {

    public ProjectNotFoundException() {
        super();
    }

    public ProjectNotFoundException(final String message) {
        super(message);
    }

}
