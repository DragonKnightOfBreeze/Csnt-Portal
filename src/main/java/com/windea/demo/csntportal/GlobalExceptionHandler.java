package com.windea.demo.csntportal;


import com.windea.demo.csntportal.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 全局异常处理器。
 */
@ControllerAdvice(basePackages = "com.windea.demo.csntportal.api")
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(FatalException.class)
	ResponseEntity<?> handleFatalException(Exception e) {
		return ResponseEntity.badRequest().build();
	}

	@ExceptionHandler(ResultEmptyException.class)
	ResponseEntity<?> handleResultEmptyException(Exception e) {
		return ResponseEntity.noContent().build();
	}

	@ExceptionHandler(ResultNotFoundException.class)
	ResponseEntity<?> handleResultNotFoundException(Exception e) {
		return ResponseEntity.notFound().build();
	}

	@ExceptionHandler(UserDuplicateException.class)
	ResponseEntity<?> handleUserDuplicateException(Exception e, BindingResult bindingResult) {
		bindingResult.reject("user.duplicate");
		return ResponseEntity.badRequest().body(bindingResult);
	}

	@ExceptionHandler(UserNotAcceptedException.class)
	ResponseEntity<?> handleUserNotAcceptedException(Exception e) {
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
	}

	@ExceptionHandler(ValidateException.class)
	ResponseEntity<?> handleValidateException(Exception e, BindingResult bindingResult) {
		return ResponseEntity.badRequest().body(bindingResult);
	}


	/**
	 * 处理验证异常。
	 */
	@ExceptionHandler(AuthenticationException.class)
	ResponseEntity<?> handleAuthException(Exception e) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}


	/**
	 * 处理其他异常（未测试）。
	 */
	@ExceptionHandler(Exception.class)
	ResponseEntity<?> handleOtherException(Exception e) {
		return ResponseEntity.badRequest().body("Fatal Exception!");
	}
}
