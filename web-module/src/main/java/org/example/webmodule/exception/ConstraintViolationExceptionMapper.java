package org.example.webmodule.exception;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.example.businessmodule.exception.tools.ErrorResponseArray;
import org.example.businessmodule.filter.UriInfoFilter;
import org.example.businessmodule.validation.annotation.CustomNotNull;

import java.util.List;
import java.util.stream.Collectors;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
    private static final String UNPROCESSABLE_ENTITY = "Ошибка валидации данных";
    private static final String BAD_REQUEST = "Некорректные данные";

    @Override
    public Response toResponse(ConstraintViolationException exception) {

        List<ConstraintViolation<?>> violations = exception.getConstraintViolations().stream().collect(Collectors.toList());

        List<String> customNotNullErrors = violations.stream()
                .filter(this::isCustomNotNullViolation)
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.toList());

        List<String> otherErrors = violations.stream()
                .filter(violation -> !isCustomNotNullViolation(violation))
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.toList());

        ErrorResponseArray errorResponse;

        if(!customNotNullErrors.isEmpty()) {
            errorResponse = new ErrorResponseArray(BAD_REQUEST, customNotNullErrors, getFullURL());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(errorResponse)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } else {
            errorResponse = new ErrorResponseArray(UNPROCESSABLE_ENTITY, otherErrors, getFullURL());
            return Response.status(422)
                    .entity(errorResponse)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

    }

    private boolean isCustomNotNullViolation(ConstraintViolation<?> violation) {
        return violation.getConstraintDescriptor().getAnnotation().annotationType().equals(CustomNotNull.class);
    }

    public String getFullURL() {
        UriInfo uriInfo = UriInfoFilter.getUriInfo();
        if (uriInfo != null) {
            return uriInfo.getRequestUri().toString();
        }
        return "Unknown URL";
    }
}
