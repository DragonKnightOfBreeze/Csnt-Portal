import {Component, OnInit} from '@angular/core';
import {TeacherTeam} from "../../domain/entity/TeacherTeam";
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {TeacherTeamService} from "../../service/api/tearcher-team.service";

@Component({
  selector: 'app-teacher-team-detail',
  templateUrl: './teacher-team-detail.component.html',
  styleUrls: ['./teacher-team-detail.component.scss']
})
export class TeacherTeamDetailComponent implements OnInit {
  /**当前数据对象。*/
  teacherTeam: TeacherTeam;

  /**是否通过后台表单参数验证。*/
  isValidForCreate = true;


  constructor(private service: TeacherTeamService,
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
  get() {
    //从路由地址中得到路由参数
    let id = +this.route.snapshot.paramMap.get("id");
    this.service.get(id).subscribe(teacherTeam => this.teacherTeam = teacherTeam);
  }

  /**
   * 删除数据，传入数据id。如果操作成功，则弹出提示框。
   * 可能抛出：403 权限错误
   */
  delete() {
    window.alert("删除成功！");
    this.goBack();
    this.service.delete(this.teacherTeam.id).subscribe();
  }

  /**
   * 更新数据，传入表单模型。
   * 只有管理员可以调用。
   * 可能抛出：400 参数错误，403 权限错误
   */
  update() {
    this.service.update(this.teacherTeam).subscribe(updatedTeacherTeam => {
      this.teacherTeam = updatedTeacherTeam;
      this.isValidForCreate = true;
    },()=>this.isValidForCreate = false);
  }

  /**
   * 返回到上一页。
   */
  goBack() {
    this.location.back();
  }
}
