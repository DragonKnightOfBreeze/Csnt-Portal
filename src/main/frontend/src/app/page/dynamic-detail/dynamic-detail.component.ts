import {Component, OnInit} from '@angular/core';
import {Dynamic} from "../../domain/entity/Dynamic";
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {DynamicService} from "../../service/api/dynamic.service";
import {UserService} from "../../service/api/user.service";
import {JwtUserResponse} from "../../domain/entity/JwtUserResponse";
import {DynamicCategory} from "../../enums/DynamicCategory";

@Component({
  selector: 'app-dynamic-detail',
  templateUrl: './dynamic-detail.component.html',
  styleUrls: ['./dynamic-detail.component.scss']
})
export class DynamicDetailComponent implements OnInit {
  currentUser: JwtUserResponse;

  /**当前数据对象。*/
  dynamic: Dynamic;

  DynamicCategory = DynamicCategory;


  constructor(private userService: UserService,
              private service: DynamicService,
              private route: ActivatedRoute,
              private location: Location) {
  }


  ngOnInit() {
    this.currentUser = this.userService.currentUserSubject.value;
    this.get();
  }

  /**
   * 得到当前数据。
   * 可能抛出：404 未找到
   */
  get() {
    //从路由地址中得到路由参数
    let id = +this.route.snapshot.paramMap.get("id");
    this.service.get(id).subscribe(dynamic => this.dynamic = dynamic);
  }

  /**
   * 删除数据，传入数据id。如果操作成功，则弹出提示框。
   * 只有管理员、发起用户可调用。
   * 可能抛出：403 权限错误
   */
  delete() {
    window.alert("删除成功！");
    this.goBack();
    this.service.delete(this.dynamic.id).subscribe();
  }

  /**
   * 返回到上一页。
   */
  goBack() {
    this.location.back();
  }
}
