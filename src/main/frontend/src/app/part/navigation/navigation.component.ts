import {Component, OnInit} from "@angular/core";
import {JwtUserResponse} from "../../domain/entity/JwtUserResponse";
import {UserService} from "../../service/api/user.service";

/**
 * 项目的导航组件。
 */
@Component({
  selector: "app-navigation",
  templateUrl: "./navigation.component.html",
  styleUrls: ["./navigation.component.scss"]
})
export class NavigationComponent implements OnInit {
  /**当前用户对象，可从中得到用户名、角色等基本信息。*/
  currentUser: JwtUserResponse;


  constructor(private userService: UserService) {
  }


  ngOnInit(): void {
    this.currentUser = this.userService.currentUserSubject.value;
  }
}
