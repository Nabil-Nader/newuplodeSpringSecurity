package com.example.Exception;

 import org.springframework.http.HttpHeaders;
 import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
 import org.springframework.validation.FieldError;
 import org.springframework.web.bind.MethodArgumentNotValidException;
 import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

 import java.util.Date;
 import java.util.HashMap;
 import java.util.Map;

// apply for all controller
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler  {


    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object>  handleAllExceptions(Exception ex,WebRequest req){

      ExceptionResponse exceptionResponse =   new ExceptionResponse(new Date () , ex.getMessage(), req.getDescription(false));

          return new ResponseEntity<>(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object>  handleUSerNotFoundException(UserNotFoundException ex,WebRequest req){

        ExceptionResponse exceptionResponse =   new ExceptionResponse(new Date(), ex.getMessage(), req.getDescription(false));

        return new ResponseEntity<>(exceptionResponse,HttpStatus.NOT_FOUND);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) ->{

            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
    }




}
