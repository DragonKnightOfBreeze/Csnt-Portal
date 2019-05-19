import {Component, OnInit} from '@angular/core';
import {Page} from "../../domain/interface/Page";
import {User} from "../../domain/entity/User";
import {UserService} from "../../service/api/user.service";
import {ActivatedRoute} from "@angular/router";
import {SearchParams} from "../../domain/vo/SearchParams";
import {UserSearchVo} from "../../domain/vo/UserSearchVo";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {
  /** 当前数据的页面对象，注意数据数组存储在content属性中。 */
  currentPage: Page<User>;

  /**查询参数的封装对象。*/
  searchParams = new SearchParams<UserSearchVo>();


  constructor(private service: UserService,
              private route: ActivatedRoute) {
  }


  ngOnInit() {
    this.show();
  }

  /**
   * 根据不同的查询类型和可能的分页参数，列出数据。
   */
  private show() {
    this.searchParams.type = this.route.snapshot.queryParamMap.get("type") || "All";
    this.searchParams.field = JSON.parse(this.route.snapshot.queryParamMap.get("field")) || new UserSearchVo();
    this.searchParams.page = +this.route.snapshot.queryParamMap.get("page") || 1;
    this.searchParams.size = +this.route.snapshot.queryParamMap.get("size") || 10;

    if (this.searchParams.type === "ByNickname") {
      this.searchByNickname();
    } else {
      this.list();
    }
  }

  /**
   * 列出所有数据，在组件初始化时调用。
   */
  private list() {
    this.searchParams.type = "All";
    this.service.list(this.searchParams.page, this.searchParams.size).subscribe(userPage => {
      this.currentPage = userPage;
    });
  }

  /**
   * 根据参数查询数据，调用后会刷新当前显示的数据。
   */
  searchByNickname() {
    this.searchParams.type = "ByNickname";
    const nickname = this.searchParams.field.nickname;
    this.service.searchByNickname(nickname, this.searchParams.page, this.searchParams.size).subscribe(userPage => {
      this.currentPage = userPage;
    });
  }
}
