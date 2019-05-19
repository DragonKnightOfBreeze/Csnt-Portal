import {Component, OnInit} from "@angular/core";
import {User} from "../../domain/entity/User";
import {UserService} from "../../service/api/user.service";
import {Router} from "@angular/router";
import {Role} from "../../enums/Role";
import {Gender} from "../../enums/Gender";
import {Profession} from "../../enums/Profession";

/**
 * 项目的注册组件。
 */
@Component({
  selector: "app-register",
  templateUrl: "./register.component.html",
  styleUrls: ["./register.component.scss"]
})
export class RegisterComponent implements OnInit {
  /**用户注册表单的模型对象。*/
  user = new User();

  /**是否通过后台表单参数验证。*/
  isValid = true;

  Gender = Gender;
  Role = Role;
  Profession = Profession;


  constructor(private userService: UserService,
              private router: Router) {
  }

  ngOnInit(): void {
  }

  register() {
    this.userService.register(this.user).subscribe(() => {
      //如果注册成功，则跳转到登录页
      this.router.navigateByUrl("/login");
      this.isValid = true;
    }, () => this.isValid = false)
  }
}
