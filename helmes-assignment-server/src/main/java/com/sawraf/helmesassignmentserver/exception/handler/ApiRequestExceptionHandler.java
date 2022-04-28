package com.sawraf.helmesassignmentserver.exception.handler;


import com.sawraf.helmesassignmentserver.exception.ApplicationException;
import com.sawraf.helmesassignmentserver.exception.message.MessageResolver;
import com.sawraf.helmesassignmentserver.exception.response.ErrorResponse;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.sawraf.helmesassignmentserver.exception.message.MessageCode.CANNOT_COMMIT_TRANSACTION;
import static com.sawraf.helmesassignmentserver.exception.message.MessageCode.ERROR_VALIDATION;


@RestControllerAdvice
public class ApiRequestExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageResolver messageResolver;

    @Value("${helmes.assignment.error.print.stacktrace:false}")
    private boolean printStackTrace;

    public ApiRequestExceptionHandler(MessageResolver messageResolver) {
        this.messageResolver = messageResolver;
    }

    @ExceptionHandler({ApplicationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ErrorResponse> handleApplicationException(ApplicationException exception) {
        final String message = messageResolver.getMessage(exception);
        final ErrorResponse errorResponse = buildErrorResponse(exception, message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    private ErrorResponse buildErrorResponse(Exception exception,
                                             String message) {
        final ErrorResponse errorResponse = new ErrorResponse(message);
        if (printStackTrace) {
            errorResponse.setStackTrace(ExceptionUtils.getStackTrace(exception));
        }
        return errorResponse;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        final String message = messageResolver.getMessage(ERROR_VALIDATION);
        final ErrorResponse errorResponse = buildErrorResponse(exception, message);
        exception.getBindingResult().getFieldErrors()
                .forEach(fieldError -> errorResponse.addValidationError(fieldError.getField(),
                        fieldError.getDefaultMessage()));

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorResponse);
    }

    @ExceptionHandler({TransactionSystemException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ErrorResponse> handleTransactionSystemException(TransactionSystemException exception) {
        final String message = messageResolver.getMessage(CANNOT_COMMIT_TRANSACTION);
        final ErrorResponse errorResponse = buildErrorResponse(exception, message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
