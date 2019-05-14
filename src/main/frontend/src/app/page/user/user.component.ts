import {Component, OnInit} from '@angular/core';
import {Page} from "../../domain/interface/Page";
import {User} from "../../domain/entity/User";
import {UserService} from "../../service/api/user.service";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {
  /** 当前数据的页面对象，注意数据数组存储在content属性中。 */
  userPage: Page<User>;


  constructor(private service: UserService) {
  }


  ngOnInit() {
    this.list();
  }

  /**
   * 列出所有数据，在组件初始化时调用。
   */
  private list(page = 1, size = 10) {
    this.service.list(page, size).subscribe(userPage => {
      this.userPage = userPage;
    });
  }

  /**
   * 根据参数查询数据，调用后会刷新当前显示的数据。
   */
  searchByNickname(nickname: string, page = 1, size = 10) {
    this.service.searchByNickname(nickname, page, size).subscribe(userPage => {
      this.userPage = userPage;
    });
  }
}
