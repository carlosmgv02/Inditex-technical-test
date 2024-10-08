package org.example.inditex.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(getErrorAttributes("Ruta no encontrada: " + ex.getRequestURL()));
    }

    @ExceptionHandler(value = {PriceNotFoundException.class})
    protected ResponseEntity<Object> handlePriceNotFound(PriceNotFoundException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getErrorAttributes(ex));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        if (ex.getName().equals("date")) {
            return ResponseEntity.badRequest()
                    .body(getErrorAttributes("Formato de fecha incorrecto. Usa yyyy-MM-dd HH:mm:ss"));
        }
        return ResponseEntity.badRequest().body(getErrorAttributes("Error en el formato del parámetro"));
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(getErrorAttributes(ex));
    }

    private Map<String, String> getErrorAttributes(Exception ex) {
        return getErrorAttributes(ex.getMessage());
    }

    private Map<String, String> getErrorAttributes(String message) {
        return Map.of("message", message);
    }
}