package com.windea.demo.csntportal;


import com.windea.demo.csntportal.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 全局异常处理器。
 */
@RestController
@ControllerAdvice(basePackages = "com.windea.demo.csntportal.api")
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(FatalException.class)
	ResponseEntity<?> handleFatalException(FatalException e) {
		e.printStackTrace();
		return ResponseEntity.badRequest().build();
	}

	@ExceptionHandler(ResultEmptyException.class)
	ResponseEntity<?> handleResultEmptyException(ResultEmptyException e) {
		e.printStackTrace();
		return ResponseEntity.noContent().build();
	}

	@ExceptionHandler(ResultNotFoundException.class)
	ResponseEntity<?> handleResultNotFoundException(ResultNotFoundException e) {
		e.printStackTrace();
		return ResponseEntity.notFound().build();
	}

	@ExceptionHandler(UserNotAcceptableException.class)
	ResponseEntity<?> handleUserNotAcceptableException(UserNotAcceptableException e) {
		e.printStackTrace();
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
	}

	@ExceptionHandler(ValidationException.class)
	ResponseEntity<?> handleValidationException(ValidationException e) {
		e.printStackTrace();
		return ResponseEntity.badRequest().body(e.getBindingResult());
	}


	@ExceptionHandler(AuthenticationException.class)
	ResponseEntity<?> handleAuthException(AuthenticationException e) {
		e.printStackTrace();
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

	//NOTE 可以通过该方法处理其他未明确的异常，但是优先级高于`@ResponseStatus`。
	@ExceptionHandler(Exception.class)
	ResponseEntity<?> handleOtherException(Exception e) {
		e.printStackTrace();
		return ResponseEntity.badRequest().body(e);
	}
}
