import {Component, OnInit} from '@angular/core';
import {Page} from "../../domain/interface/Page";
import {TeacherTeam} from "../../domain/entity/TeacherTeam";
import {TeacherTeamService} from "../../service/api/tearcher-team.service";
import {ProfessionLevel} from "../../enums/ProfessionLevel";
import {TeacherTeamSearchVo} from "../../domain/vo/TeacherTeamSearchVo";

@Component({
  selector: 'app-teacher-team',
  templateUrl: './teacher-team.component.html',
  styleUrls: ['./teacher-team.component.scss']
})
export class TeacherTeamComponent implements OnInit {
  /** 当前数据的页面对象，注意数据数组存储在content属性中。 */
  teacherTeamPage: Page<TeacherTeam>;

  /**增加数据表单的模型对象。*/
  newTeacherTeam = new TeacherTeam();

  /**是否通过后台表单参数验证。*/
  isValidForCreate = true;

  /**查询表单的模型对象。*/
  searchVo = new TeacherTeamSearchVo();

  /**是否通过后台表单参数验证。*/
  isValidForSearch = true;


  constructor(private service: TeacherTeamService) {
  }


  ngOnInit() {
    this.list();
  }

  /**
   * 增加数据，传入表单模型数据。
   * 可能抛出：400 参数错误
   */
  create() {
    this.service.create(this.newTeacherTeam).subscribe(teacherTeam => {
      this.teacherTeamPage.content.push(teacherTeam);
      this.teacherTeamPage.content.slice(0, 10);
      this.isValidForCreate = true;
    },()=>this.isValidForCreate = false);
  }

  /**
   * 删除数据，传入数据id。如果操作成功，则弹出提示框。
   * 可能抛出：403 权限错误
   */
  delete(id: number) {
    window.alert("删除成功！");
    this.teacherTeamPage.content.filter(e => e.id !== id);
    this.service.delete(id).subscribe();
  }

  /**
   * 列出所有数据，在组件初始化时调用。
   */
  list(page = 1, size = 10) {
    this.service.list(page, size).subscribe(teacherTeamPage => {
      this.teacherTeamPage = teacherTeamPage;
    });
  }

  /**
   * 根据参数查询数据，调用后会刷新当前显示的数据。
   */
  searchByTitle(name: string, page = 1, size = 10) {
    this.service.searchByName(name, page, size).subscribe(teacherTeamPage => {
      this.teacherTeamPage = teacherTeamPage;
    });
  }

  /**
   * 根据参数查询数据，调用后会刷新当前显示的数据。
   */
  searchByProfessionLevel(levelSet: ProfessionLevel[], page = 1, size = 10) {
    this.service.searchByProfessionLevel(levelSet, page, size).subscribe(teacherTeamPage => {
      this.teacherTeamPage = teacherTeamPage;
    });
  }

  /**
   * 根据参数查询数据，调用后会刷新当前显示的数据。
   */
  searchByTeacherCount(min:number,max:number, page = 1, size = 10) {
    this.service.searchByTeacherCount(min,max, page, size).subscribe(teacherTeamPage => {
      this.teacherTeamPage = teacherTeamPage;
    });
  }

  /**
   * 高级查询，传入表单模型数据，调用后会刷新当前显示的数据。
   * 可能抛出：400 参数错误
   */
  advanceSearch(page = 1, size = 10) {
    this.service.advanceSearch(this.searchVo, page, size).subscribe(teacherTeamPage => {
      this.teacherTeamPage = teacherTeamPage;
      this.isValidForSearch = true;
    },()=>this.isValidForSearch = false);
  }
}
