import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from "@angular/router";
import {UserService} from "../api/user.service";

/**
 * 已登录的守卫的服务。要求用户已登录。
 */
@Injectable({providedIn: "root"})
export class LoginGuard implements CanActivate {
  constructor(private router: Router,
              private userService: UserService) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const currentUser = this.userService.currentUserSubject.value;
    //如果当前用户已登录，则通过验证，否则转到首页或401
    if (currentUser) {
      return true;
    }
    console.log("未验证！先请登录。");
    this.router.navigate(["/login"], {queryParams: {returnUrl: state.url}});
    return false;
  }
}
