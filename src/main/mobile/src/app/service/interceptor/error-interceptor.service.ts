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
      console.log(err);
      this.router.navigate(["error"]);

      //根据错误状态码，分别跳转到不同的错误页面
      switch (err.status) {
        //400 Bad request 参数验证错误
        case 400:
          window.alert("参数错误！");
          break;
        //401	Unauthorized 未验证，需要登录
        case 401:
          this.userService.logout();
          this.router.navigate(["login"]);
          break;
        //403 Forbidden 权限错误
        case 403:
          this.router.navigate(["error/403"]);
          break;
        //404 Not Found 页面未找到
        case 404:
          this.router.navigate(["error/404"]);
          break;
        default:
          this.router.navigate(["error"]);
          break;
      }

      const error = err.error || err.statusText;
      return throwError(error);
    }))
  }
}
