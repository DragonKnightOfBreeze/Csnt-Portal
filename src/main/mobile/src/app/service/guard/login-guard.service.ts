import {Injectable} from "@angular/core";
import {CanLoad, Route, Router, UrlSegment} from "@angular/router";
import {UserService} from "../api/user.service";

/**
 * 要求用户已登录的守卫。
 */
@Injectable({providedIn: "root"})
export class LoginGuard implements CanLoad {
  constructor(private router: Router,
              private userService: UserService) {
  }


  canLoad(route: Route, segments: UrlSegment[]): boolean {
    if (this.userService.hasLogin) {
      return true;
    }
    console.log("未验证！先请登录。");
    this.router.navigate(["/login"], {queryParams: {returnUrl: segments}});
    return false;
  }
}
