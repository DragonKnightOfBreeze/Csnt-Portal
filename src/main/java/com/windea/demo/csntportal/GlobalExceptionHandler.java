package com.windea.demo.csntportal;

import com.windea.commons.base.exception.NotImplementedException;
import com.windea.demo.csntportal.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@ExceptionHandler(NotImplementedException.class)
	ResponseEntity<?> handleNotImplementedException(NotImplementedException e) {
		e.printStackTrace();
		return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
	}

	@ExceptionHandler(NotFoundException.class)
	ResponseEntity<?> handleNotFoundException(NotFoundException e) {
		e.printStackTrace();
		return ResponseEntity.notFound().build();
	}

	@ExceptionHandler(UserNotMatchedException.class)
	ResponseEntity<?> handleUserNotMatchedException(UserNotMatchedException e) {
		e.printStackTrace();
		return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	}

	@ExceptionHandler(ValidationException.class)
	ResponseEntity<?> handleValidationException(ValidationException e) {
		e.printStackTrace();
		return ResponseEntity.badRequest().body(e.getBindingResult());
	}

	//可以通过该方法处理其他未明确的异常，但是优先级高于`@ResponseStatus`，高于@PreAuthorize。
	//@ExceptionHandler(Exception.class)
	//ResponseEntity<?> handleOtherException(Exception e) {
	//	e.printStackTrace();
	//	return ResponseEntity.badRequest().body(e);
	//}
}
