import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {Introduce} from "../../../domain/entity/Introduce";
import {IntroduceService} from "../../../service/api/introduce.service";
import {UserService} from "../../../service/api/user.service";
import {JwtUserResponse} from "../../../domain/entity/JwtUserResponse";

@Component({
  selector: 'app-introduce-detail',
  templateUrl: './introduce-detail.component.html',
  styleUrls: ['./introduce-detail.component.scss']
})
export class IntroduceDetailComponent implements OnInit {
  currentUser: JwtUserResponse;

  /**当前数据对象。*/
  introduce: Introduce;

  /**是否通过后台表单参数验证。*/
  isValidForUpdate = true;


  constructor(private userService:UserService,
      private service: IntroduceService,
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
    this.service.get(id).subscribe(introduce => this.introduce = introduce);
  }

  /**
   * 删除数据，传入数据id。如果操作成功，则弹出提示框。
   * 可能抛出：403 权限错误
   */
  delete() {
    window.alert("删除成功！");
    this.goBack();
    this.service.delete(this.introduce.id).subscribe();
  }

  /**
   * 更新数据，传入表单模型。
   * 只有管理员可以调用。
   * 可能抛出：400 参数错误，403 权限错误
   */
  update() {
    this.service.update(this.introduce).subscribe(updatedIntroduce => {
      this.introduce = updatedIntroduce;
      this.isValidForUpdate = true;
    },()=>this.isValidForUpdate = false);
  }

  /**
   * 返回到上一页。
   */
  goBack() {
    this.location.back();
  }
}
