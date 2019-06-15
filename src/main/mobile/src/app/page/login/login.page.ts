import {Component, OnInit} from '@angular/core';
import {UserLoginVo} from "../../domain/vo/UserLoginVo";
import {UserService} from "../../service/api/user.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {
  returnUrl: String;

  userLoginVo = new UserLoginVo();

  constructor(public service: UserService,
              private route: ActivatedRoute,
              private router: Router) {
  }


  ngOnInit() {
    this.returnUrl = this.route.snapshot.queryParamMap.get("returnUrl");
  }

  login() {
    //如果登录成功，无论用户角色如何，都跳转到之前访问的页面
    this.service.login(this.userLoginVo).subscribe(() => {
      this.router.navigate([this.returnUrl]);
    });
  }
}
