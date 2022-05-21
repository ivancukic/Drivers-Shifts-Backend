package drive.time.jwt.config;

import javax.persistence.NonUniqueResultException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import drive.time.jwt.exception.ApiException;
import drive.time.jwt.exception.DriverExceptions;
import drive.time.jwt.exception.ShiftException;

@ControllerAdvice
public class RestControllerAdvice extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(NonUniqueResultException.class)
	public ResponseEntity<Object> handleWrongInputDriver(NonUniqueResultException nonUniqueResultException) {
		
		return new ResponseEntity<Object>("Two same drivers are on first and second shift!", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = {DriverExceptions.class})
	public ResponseEntity<Object> handleWrongInputDriver(DriverExceptions exception) {
		
		HttpStatus request = HttpStatus.BAD_REQUEST;
		
		ApiException apiException = new ApiException(exception.getMessage(), exception, request);
		
		return new ResponseEntity<Object>(apiException, request);
	}
	
	@ExceptionHandler(value = {ShiftException.class})
	public ResponseEntity<Object> handleSameShift(ShiftException exception) {
		
		HttpStatus request = HttpStatus.BAD_REQUEST;
		
		ApiException apiException = new ApiException(exception.getMessage(), exception, request);
		
		return new ResponseEntity<Object>(apiException, request);
	}
	
	 

}
