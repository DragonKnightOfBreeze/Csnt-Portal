import {Component, OnInit} from '@angular/core';
import {DevelopmentColumn} from "../../domain/entity/DevelopmentColumn";
import {DevelopmentColumnService} from "../../service/api/development-column.service";
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {UserService} from "../../service/api/user.service";
import {JwtUserResponse} from "../../domain/entity/JwtUserResponse";

@Component({
  selector: 'app-development-column-detail',
  templateUrl: './development-column-detail.component.html',
  styleUrls: ['./development-column-detail.component.scss']
})
export class DevelopmentColumnDetailComponent implements OnInit {
  currentUser: JwtUserResponse;

  /**当前数据对象。*/
  column: DevelopmentColumn;

  /**是否通过后台表单参数验证。*/
  isValidForUpdate = true;


  constructor(private userService: UserService,
              private service: DevelopmentColumnService,
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
    let id = +this.route.snapshot.queryParamMap.get("id");
    this.service.get(id).subscribe(column => this.column = column);
  }

  /**
   * 删除数据，传入数据id。如果操作成功，则弹出提示框。
   * 可能抛出：403 权限错误
   */
  delete() {
    window.alert("删除成功！");
    this.goBack();
    this.service.delete(this.column.id).subscribe();
  }

  /**
   * 更新数据，传入表单模型。
   * 只有管理员可以调用。
   * 可能抛出：400 参数错误，403 权限错误
   */
  update() {
    this.service.update(this.column).subscribe(updatedColumn => {
      this.column = updatedColumn;
      this.isValidForUpdate = true;
    }, () => this.isValidForUpdate = false);
  }

  /**
   * 返回到上一页。
   */
  goBack() {
    this.location.back();
  }
}
