import {Component} from '@angular/core';
import {User} from "../../domain/entity/User";
import {Gender} from "../../../../../frontend/src/app/enums/Gender";
import {Role} from "../../../../../frontend/src/app/enums/Role";
import {Profession} from "../../../../../frontend/src/app/enums/Profession";
import {UserService} from "../../service/api/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.page.html',
  styleUrls: ['./register.page.scss'],
})
export class RegisterPage {
  user = new User();

  Gender = Gender;
  Role = Role;
  Profession = Profession;

  constructor(public service: UserService,
              private router: Router) {
  }


  register() {
    //如果注册成功，则跳转到登录页
    this.service.register(this.user).subscribe(() => {
      this.router.navigate(["login"], {queryParams: {returnUrl: "/"}});
    });
  }
}
