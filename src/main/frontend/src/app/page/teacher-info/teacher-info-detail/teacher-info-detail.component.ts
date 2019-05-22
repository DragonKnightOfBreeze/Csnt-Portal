import {Component, OnInit} from '@angular/core';
import {TeacherInfo} from "../../../domain/entity/TeacherInfo";
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {TeacherInfoService} from "../../../service/api/teacher-info.service";
import {UserService} from "../../../service/api/user.service";
import {JwtUserResponse} from "../../../domain/entity/JwtUserResponse";
import {Profession} from "../../../enums/Profession";
import {Gender} from "../../../enums/Gender";

@Component({
  selector: 'app-teacher-info-detail',
  templateUrl: './teacher-info-detail.component.html',
  styleUrls: ['./teacher-info-detail.component.scss']
})
export class TeacherInfoDetailComponent implements OnInit {
  currentUser: JwtUserResponse;

  /**当前数据对象。*/
  teacherInfo: TeacherInfo;

  /**是否通过后台表单参数验证。*/
  isValidForUpdate = true;

  /**增加数据表单的模型对象。*/
  newTeacherInfo = new TeacherInfo();

  /**是否通过后台表单参数验证。*/
  isValidForCreate = true;

  Gender = Gender;
  Profession = Profession;


  constructor(private userService: UserService,
              private service: TeacherInfoService,
              private route: ActivatedRoute,
              private location: Location) {
  }


  ngOnInit() {
    this.currentUser = this.userService.currentUserSubject.value;
    this.get();
  }

  /**
   * 增加数据，传入表单模型数据。
   * 可能抛出：400 参数错误
   */
  create() {
    this.service.create(this.newTeacherInfo).subscribe(() => {
      this.isValidForCreate = true;
    }, () => this.isValidForCreate = false);
  }

  /**
   * 删除数据，传入数据id。如果操作成功，则弹出提示框。
   * 可能抛出：403 权限错误
   */
  delete() {
    window.alert("删除成功！");
    this.goBack();
    this.service.delete(this.teacherInfo.id).subscribe();
  }

  /**
   * 更新数据，传入表单模型。
   * 只有管理员可以调用。
   * 可能抛出：400 参数错误，403 权限错误
   */
  update() {
    this.service.update(this.teacherInfo).subscribe(updatedTeacherInfo => {
      this.teacherInfo = updatedTeacherInfo;
      this.isValidForUpdate = true;
    }, () => this.isValidForUpdate = false);
  }

  /**
   * 得到当前数据。
   * 可能抛出：404 未找到
   */
  get() {
    //从路由地址中得到路由参数
    let id = +this.route.snapshot.paramMap.get("id");
    this.service.get(id).subscribe(teacherInfo => this.teacherInfo = teacherInfo);
  }

  /**
   * 返回到上一页。
   */
  goBack() {
    this.location.back();
  }
}
