package aiss.GitHubMiner.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GitMinerNotRunningException.class)
    @ResponseStatus(value = HttpStatus.FAILED_DEPENDENCY)
    public @ResponseBody ErrorMessage gitMinerNotRunningExecptionHandler(
            GitMinerNotRunningException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.FAILED_DEPENDENCY.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return message;
    }

    @ExceptionHandler(ProjectNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @ResponseBody ErrorMessage projectNotFoundExceptionHandler(
            ProjectNotFoundException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return message;
    }

    @ExceptionHandler(AuthorizationException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public @ResponseBody ErrorMessage authorizationExceptionHandler(
            AuthorizationException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.UNAUTHORIZED.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return message;
    }
}
