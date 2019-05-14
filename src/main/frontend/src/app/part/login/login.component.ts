import {Component, OnInit} from "@angular/core";
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
export class LoginComponent implements OnInit{
  /**用户登录表单的模型对象。*/
  userLoginVo = new UserLoginVo();

  /**是否通过后台表单参数验证。*/
  isValid = true;


  constructor(private userService: UserService,
              private router: Router) {
  }


  ngOnInit(): void {
  }

  login() {
    this.userService.login(this.userLoginVo).subscribe(() => {
      //如果登录成功，无论用户角色如何，都跳转到首页
      this.router.navigateByUrl("/");
      this.isValid = true;
    }, () => this.isValid = false);
  }

}
