import {Injectable} from "@angular/core";
import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {UserService} from "../api/user.service";
import {Router} from "@angular/router";
import {Observable, throwError} from "rxjs";
import {catchError, tap} from "rxjs/operators";
import {ObjectError} from "../../domain/interface/ObjectError";
import {ValidationService} from "../api/validation.service";

/**
 * 错误拦截器。
 */
@Injectable({providedIn: "root"})
export class ErrorInterceptor implements HttpInterceptor {
  constructor(private userService: UserService,
              private validationService: ValidationService,
              private router: Router) {
  }


  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    //err: HttpErrorResponse
    //err.error 具有error, message, path, status, timestamp等属性的对象，或者badRequest中的request body对象
    //参数验证错误时，err.error的类型最好为java exception，但是带有一个ObjectError[]类型的属性。
    return next.handle(request).pipe(
      tap(() => {
        this.validationService.setValidationErrors([]);
      }),
      catchError((err: HttpErrorResponse) => {
        console.error(`${err.status} ${err.error.message}`);
        console.log(request);
        console.log(err);

        if (err.status != 400) {
          this.validationService.setValidationErrors([]);
        }

        //根据错误状态码，分别跳转到不同的错误页面
        switch (err.status) {
          case 400:
            const validationErrors: ObjectError[] = err.error.validationErrors || [];
            this.validationService.setValidationErrors(validationErrors);
            break;
          case 401:
            this.userService.logout();
            this.router.navigate(["login"]);
            break;
          case 403:
            this.router.navigate(["error/403"]);
            break;
          case 404:
            this.router.navigate(["error/404"]);
            break;
          default:
            this.router.navigate(["error"]);
            break;
        }

        return throwError(err.error);
      }))
  }
}
