import {Injectable} from "@angular/core";
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {UserService} from "../api/user.service";
import {Router} from "@angular/router";
import {Observable, throwError} from "rxjs";
import {catchError} from "rxjs/operators";

/**
 * 前端错误拦截器。
 */
@Injectable({providedIn: "root"})
export class ErrorInterceptor implements HttpInterceptor {
  constructor(private userService: UserService,
              private router: Router) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(request).pipe(catchError(err => {
      //根据错误状态码，分别跳转到不同的错误页面
      switch (err.status) {
        case 204:
          //204 No content
          //this.router.navigateByUrl("/error/204");
          break;
        case 400:
          //400 Bad request
          //一般为参数验证错误，不进行处理
          break;
        case 401:
          //401	Unauthorized
          this.userService.logout();
          this.router.navigateByUrl("/login");
          break;
        case 403:
          //403 Forbidden
          //this.router.navigateByUrl("/error/403");
          break;
        case 404:
          //404 Not Found
          //this.router.navigateByUrl("/error/404");
          break;
        case 500:
          //500	Internal Server Error
          //this.router.navigateByUrl("/error/500");
          break;
        default:
          break;
      }

      const error = err.error || err.statusText;
      return throwError(error);
    }))
  }
}
