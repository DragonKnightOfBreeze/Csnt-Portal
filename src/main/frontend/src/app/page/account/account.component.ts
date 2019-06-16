import {Component, OnInit} from '@angular/core';
import {User} from "../../domain/entity/User";
import {UserService} from "../../service/api/user.service";
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {Gender} from "../../enums/Gender";
import {Role} from "../../enums/Role";
import {Profession} from "../../enums/Profession";

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.scss']
})
export class AccountComponent implements OnInit {
  /**当前数据对象。包含更多的用户信息。*/
  user: User;

  /**是否通过后台表单参数验证。*/
  isValidForUpdate = true;

  Gender = Gender;
  Role = Role;
  Profession = Profession;


  constructor(private service: UserService,
              private route: ActivatedRoute,
              private location: Location) {
  }

  //NOTE
  //通过subscribe()的第2个参数error: err=>void处理错误。err.status即为http状态码。
  //也可以在前端全局错误拦截器ErrorInterceptor中处理错误。

  ngOnInit() {
    this.getAccountInfo();
  }

  /**
   * 更新数据，传入表单模型。
   * 只有管理员可以调用。
   * 可能抛出：400 参数错误，403 用户不匹配
   */
  updateAccountInfo() {
    this.service.updateAccountInfo(this.user).subscribe(updatedUser => {
      this.user = updatedUser;
      this.isValidForUpdate = true;
    }, () => this.isValidForUpdate = false);
  }

  /**
   * 得到当前数据。
   * 可能抛出：404 未找到
   */
  getAccountInfo() {
    //从路由地址中得到路由参数
    let username = this.route.snapshot.paramMap.get("username");
    this.service.getAccountInfo(username).subscribe(user => {
      this.user = user;
    });
  }

  /**
   * 得到动态列表。
   */
  getDynamicList(id: number) {
    return this.service.getDynamicList(id);
  }

  /**
   * 返回到上一页。
   */
  goBack() {
    this.location.back();
  }
}
