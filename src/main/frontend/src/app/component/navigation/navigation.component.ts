import {Component, OnDestroy, OnInit} from "@angular/core";
import {Subscription} from "rxjs";
import {JwtResponseVo} from "../../domain/vo/JwtResponseVo";
import {UserService} from "../../service/api/user.service";

/**
 * 项目的导航组件。
 * TODO
 */
@Component({
  selector: "app-navigation",
  templateUrl: "./navigation.component.html",
  styleUrls: ["./navigation.component.sass"]
})
export class NavigationComponent implements OnInit, OnDestroy {
  /**当前用户对象，可从中得到用户名、角色等信息。*/
  currentUser: JwtResponseVo;
  currentUserSub: Subscription;


  constructor(private userService: UserService) {
  }


  ngOnInit(): void {
    this.currentUserSub = this.userService.currentUser$.subscribe(user => {
      this.currentUser = user;
    })
  }

  ngOnDestroy(): void {
    this.currentUserSub.unsubscribe();
  }
}
