import {Component} from "@angular/core";
import {UserLoginVo} from "../../domain/vo/UserLoginVo";
import {UserService} from "../../service/api/user.service";
import {Router} from "@angular/router";

/**
 * 项目的登录组件。
 */
@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.scss"]
})
export class LoginComponent {
  valid = true;
  userLoginVo = new UserLoginVo();


  constructor(private userService: UserService,
              private router: Router) {
  }


  /**
   * 提交表单数据。
   */
  onSubmit() {
    this.userService.login(this.userLoginVo).subscribe(user => {
      //如果用户存在则跳转到首页，无论用户角色如何，否则记为输入不合法
      if (user) {
        this.router.navigateByUrl("/");
      } else {
        this.valid = false;
      }
    });
  }
}
