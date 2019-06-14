import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {UserService} from "../api/user.service";
import {Observable} from "rxjs";
import {Injectable} from "@angular/core";

/**
 * 前端Jwt拦截器。
 */
@Injectable({providedIn: "root"})
export class JwtInterceptor implements HttpInterceptor {
  constructor(private userService: UserService) {
  }


  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    //如果可行，添加jwt令牌到验证头中
    const currentUser = this.userService.currentUserSubject.value;
    //没有必要指定内容类型为json
    if (currentUser && currentUser.token) {
      request = request.clone({
        setHeaders: {
          Authorization: `${currentUser.type}${currentUser.token}`
        }
      });
    }
    return next.handle(request);
  }
}
