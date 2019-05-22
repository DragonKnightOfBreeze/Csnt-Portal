import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {User} from "../../../domain/entity/User";
import {UserService} from "../../../service/api/user.service";

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.scss']
})
export class UserDetailComponent implements OnInit {
  /**当前数据对象。*/
  user: User;


  constructor(private service: UserService,
              private route: ActivatedRoute,
              private location: Location) {
  }


  ngOnInit() {
    this.get();
  }

  /**
   * 得到当前数据。
   * 可能抛出：404 未找到
   */
  private get() {
    //从路由地址中得到路由参数
    let id = +this.route.snapshot.paramMap.get("id");
    this.service.get(id).subscribe(
        user => this.user = user,
        undefined,
        () => this.getDynamicList(id)
    );
  }

  /**
   * 得到动态列表。
   */
  private getDynamicList(id: number) {
    this.service.getDynamicList(id).subscribe(dynamicList => this.user.dynamicList = dynamicList);
  }

  /**
   * 返回到上一页。
   */
  goBack() {
    this.location.back();
  }
}
