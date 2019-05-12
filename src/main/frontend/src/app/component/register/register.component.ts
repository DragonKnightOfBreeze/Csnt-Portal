import {Component} from "@angular/core";
import {User} from "../../domain/entity/User";
import {UserService} from "../../service/api/user.service";
import {Router} from "@angular/router";

/**
 * 项目的注册组件。
 */
@Component({
  selector: "app-register",
  templateUrl: "./register.component.html",
  styleUrls: ["./register.component.sass"]
})
export class RegisterComponent {
  valid = true;
  user = new User();

  constructor(private userService: UserService,
              private router: Router) {
  }

  /**
   * 提交表单数据。
   */
  onSubmit() {
    this.userService.register(this.user).subscribe(user => {
      //如果注册成功，则跳转到登录页，否则记为输入不合法
      if (user) {
        this.router.navigateByUrl("/login");
      } else {
        this.valid = false;
      }
    })
  }
}
