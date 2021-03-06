import {Component, OnInit} from "@angular/core";
import {UserService} from "../../service/api/user.service";
import {Router} from "@angular/router";

/**
 * 项目的登出组件。
 */
@Component({
  selector: "app-logout",
  templateUrl: "./logout.component.html",
  styleUrls: ["./logout.component.scss"]
})
export class LogoutComponent implements OnInit {
  constructor(private userService: UserService) {
  }


  ngOnInit() {
    this.logout();
  }

  private logout() {
    this.userService.logout();
  }
}
