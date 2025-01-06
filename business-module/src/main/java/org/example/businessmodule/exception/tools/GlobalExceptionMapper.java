package org.example.businessmodule.exception.tools;


import jakarta.json.bind.JsonbException;
import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.example.businessmodule.exception.*;
import org.example.businessmodule.filter.UriInfoFilter;

import java.util.ArrayList;
import java.util.List;


@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Throwable> {
    private static final String UNPROCESSABLE_ENTITY = "Ошибка валидации данных";
    private static final String BAD_REQUEST = "Некорректные данные";
    private static final String INTERNAL_SERVER_ERROR = "Внутренняя ошибка сервера";
    private static final String NOT_FOUND = "Объект не найден";
    private static final String SERVICE_UNAVAILABLE = "Сервис временно не доступен";


    @Override
    public Response toResponse(Throwable exception) {
        if (exception instanceof CustomNotFound) {
            return eventNotFoundHandler(exception);
        } else if (exception instanceof MultipleNotFound) {
            return multipleNotFoundHandler(exception);
        } else if (exception instanceof InvalidParameter) {
            return invalidParameterHandler(exception);
        } else if (exception instanceof TooLateToDelete) {
            return tooLateToDeleteHandler(exception);
        } else if (exception instanceof IncorrectParameter) {
            return incorrectTypeHandler(exception);
        } else if (exception instanceof TicketServiceNotAvailable) {
            return ticketServiceNotAvailableHandler(exception);
        } else if (exception instanceof ProcessingException) {
            return processingExceptionHandler(exception);
        } else if (exception instanceof AlreadyVIPException) {
            return alreadyVipHandler(exception);
        } else {
            return exceptionHandler(exception);
        }

    }


    public Response eventNotFoundHandler(Throwable exception) {
        CustomNotFound customNotFound = (CustomNotFound) exception;
        CustomErrorResponse errorResponse = new CustomErrorResponse(NOT_FOUND, customNotFound.getMessage(), getFullURL());
        return Response.status(Response.Status.NOT_FOUND)
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }


    public Response ticketServiceNotAvailableHandler(Throwable exception) {
        TicketServiceNotAvailable ticketServiceNotAvailable = (TicketServiceNotAvailable) exception;
        CustomErrorResponse errorResponse = new CustomErrorResponse(SERVICE_UNAVAILABLE, ticketServiceNotAvailable.toString(), getFullURL());
        return Response.status(Response.Status.SERVICE_UNAVAILABLE)
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    public Response invalidParameterHandler(Throwable exception) {
        InvalidParameter invalidParameter = (InvalidParameter) exception;
        ErrorResponseArray errorResponse = new ErrorResponseArray(UNPROCESSABLE_ENTITY, invalidParameter.getMessages(), getFullURL());
        return Response.status(422)
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }


    public Response tooLateToDeleteHandler(Throwable exception) {
        TooLateToDelete tooLateToDelete = (TooLateToDelete) exception;
        CustomErrorResponse errorResponse = new CustomErrorResponse(UNPROCESSABLE_ENTITY, tooLateToDelete.getMessage(), getFullURL());
        return Response.status(422)
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    public Response alreadyVipHandler(Throwable exception) {
        TooLateToDelete tooLateToDelete = (TooLateToDelete) exception;
        CustomErrorResponse errorResponse = new CustomErrorResponse(UNPROCESSABLE_ENTITY, tooLateToDelete.getMessage(), getFullURL());
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    public Response incorrectTypeHandler(Throwable exception) {
        IncorrectParameter incorrectParameter = (IncorrectParameter) exception;
        ErrorResponseArray errorResponse = new ErrorResponseArray(BAD_REQUEST, incorrectParameter.getMessages(), getFullURL());
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    public Response multipleNotFoundHandler(Throwable exception) {
        MultipleNotFound multipleNotFound = (MultipleNotFound) exception;
        ErrorResponseArray errorResponse = new ErrorResponseArray(BAD_REQUEST, multipleNotFound.getMessages(), getFullURL());
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    public Response processingExceptionHandler(Throwable exception) {
        ProcessingException q = (ProcessingException) exception;
        if (q.getCause() instanceof JsonbException) {
            JsonbException qq = (JsonbException) q.getCause();
            String field = qq.getMessage().split("'")[1].split("'")[0];
            List<String> mes = new ArrayList<>();
            mes.add("Не получилось десериализовать значение этого поля: " + field);
            ErrorResponseArray errorResponse = new ErrorResponseArray(BAD_REQUEST, mes, getFullURL());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(errorResponse)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
        return exceptionHandler(exception);
    }


    public Response exceptionHandler(Throwable exception) {
        Exception exception1 = (Exception) exception;
        CustomErrorResponse errorResponse = new CustomErrorResponse(INTERNAL_SERVER_ERROR, exception1.toString(), getFullURL());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();

    }


    public String getFullURL() {
        UriInfo uriInfo = UriInfoFilter.getUriInfo();
        if (uriInfo != null) {
            return uriInfo.getRequestUri().toString();
        }
        return "Unknown URL";
    }

}
