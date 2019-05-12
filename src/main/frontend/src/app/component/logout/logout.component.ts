import {Component, OnInit} from "@angular/core";
import {UserService} from "../../service/api/user.service";
import {Router} from "@angular/router";

/**
 * 项目的登出组件。
 */
@Component({
  selector: "app-logout",
  templateUrl: "./logout.component.html",
  styleUrls: ["./logout.component.sass"]
})
export class LogoutComponent implements OnInit {
  constructor(private userService: UserService,
              private router: Router) {
  }


  //在初始化时就调用服务中的相关方法
  ngOnInit() {
    this.userService.logout();
    this.router.navigateByUrl("/");
  }
}
