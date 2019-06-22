import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, CanActivate, CanActivateChild, Router, RouterStateSnapshot} from "@angular/router";
import {UserService} from "../api/user.service";

/**
 * 要求用户已登录的守卫。
 */
@Injectable({providedIn: "root"})
export class LoginGuard implements CanActivate, CanActivateChild {
  constructor(private router: Router,
              private userService: UserService) {
  }


  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    if (this.userService.hasLogin()) {
      return true;
    }
    console.warn("未验证！请先登录。");
    this.router.navigate(["login"], {queryParams: {returnUrl: state.url || ""}});
    return false;
  }

  canActivateChild(childRoute: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    return this.canActivate(childRoute, state);
  }
}
