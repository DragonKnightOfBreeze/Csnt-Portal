import {Injectable} from "@angular/core";
import {CanLoad, Route, Router, UrlSegment} from "@angular/router";
import {UserService} from "../api/user.service";

/**
 * 要求用户为管理员的守卫。
 */
@Injectable({providedIn: "root"})
export class AdminGuard implements CanLoad {
  constructor(private router: Router,
              private userService: UserService) {
  }


  canLoad(route: Route, segments: UrlSegment[]): boolean {
    //如果当前用户角色为ADMIN，则通过，否则转到权限错误页面
    if (this.userService.isAdmin) {
      return true;
    }
    console.log("权限错误！请以管理员身份登录。");
    this.router.navigate(["error/403"]);
    return false;
  }
}
