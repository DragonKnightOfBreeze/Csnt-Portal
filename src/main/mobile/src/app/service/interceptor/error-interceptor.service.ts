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
      console.log(err.status);
      console.log(err);

      this.router.navigate(["/error"]);

      // //根据错误状态码，分别跳转到不同的错误页面
      // switch (err.status) {
      //   case 400:
      //     //400 Bad request 一般为参数验证错误，接收错误信息或者设置布尔值
      //     window.alert("参数错误！");
      //     break;
      //   case 401:
      //     //401	Unauthorized 未验证，需要登录
      //     this.userService.logout();
      //     this.router.navigateByUrl("/login");
      //     break;
      //   case 403:
      //     //403 Forbidden 权限错误
      //     this.router.navigateByUrl("/error/403");
      //     break;
      //   case 404:
      //     //404 Not Found 页面未找到
      //     this.router.navigateByUrl("/error/404");
      //     break;
      //   case 500:
      //     //500	Internal Server Error 内部错误，不处理
      //     this.router.navigateByUrl("/error/500");
      //     break;
      //   case 501:
      //     //501	Not Implemented 功能未实现，不处理
      //     this.router.navigateByUrl("/error/501");
      //     break;
      //   default:
      //     break;
      // }

      const error = err.error || err.statusText;
      return throwError(error);
    }))
  }
}
