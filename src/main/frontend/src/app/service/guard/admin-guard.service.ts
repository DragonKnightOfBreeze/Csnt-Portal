import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, CanActivate, CanActivateChild, Router, RouterStateSnapshot} from "@angular/router";
import {UserService} from "../api/user.service";
import {Role} from "../../enums/Role";

/**
 * 管理员权限的守卫的服务。要求用户角色为ADMIN。
 */
@Injectable({providedIn: "root"})
export class AdminGuard implements CanActivate, CanActivateChild {
  constructor(private router: Router,
              private userService: UserService) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const currentUser = this.userService.currentUserSubject.value;
    //如果当前用户角色为ADMIN，则通过守卫，否则转到首页或403
    if (currentUser && currentUser.role == Role.Admin) {
      return true;
    }
    console.log("权限错误！");
    this.router.navigateByUrl("/error/403");
    return false;
  }

  canActivateChild(childRoute: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    return this.canActivate(childRoute, state);
  }
}
