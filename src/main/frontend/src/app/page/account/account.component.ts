import {Component, OnInit} from '@angular/core';
import {User} from "../../domain/entity/User";
import {UserService} from "../../service/api/user.service";

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.scss']
})
export class AccountComponent implements OnInit {
  user: User;


  constructor(private service: UserService) {
  }

  //NOTE
  //通过subscribe()的第2个参数error: err=>void处理错误。err.status即为http状态码。
  //也可以在前端全局错误拦截器ErrorInterceptor中处理错误。

  ngOnInit() {
    this.get();
  }

  /**
   * 得到当前数据。
   * 可能抛出：404 未找到
   */
  get() {
    //TODO
    let username = "Windea";
    this.service.getAccountInfo(username).subscribe(user => this.user = user);
  }

  /**
   * 更新数据，传入表单模型。如果操作成功，则弹出提示框。
   * 只有管理员可以调用。
   * 可能抛出：400 参数错误，403 用户不匹配
   */
  update(updatedUser: User) {
    this.service.updateAccountInfo(updatedUser).subscribe(() => window.alert("更新成功！"));
  }
}
