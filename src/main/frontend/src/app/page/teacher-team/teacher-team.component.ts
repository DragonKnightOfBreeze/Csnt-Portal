import {Component, OnInit} from '@angular/core';
import {Page} from "../../domain/interface/Page";
import {TeacherTeam} from "../../domain/entity/TeacherTeam";
import {TeacherTeamService} from "../../service/api/tearcher-team.service";
import {TeacherTeamSearchVo} from "../../domain/vo/TeacherTeamSearchVo";
import {JwtUserResponse} from "../../domain/entity/JwtUserResponse";
import {UserService} from "../../service/api/user.service";
import {SearchParams} from "../../domain/vo/SearchParams";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-teacher-team',
  templateUrl: './teacher-team.component.html',
  styleUrls: ['./teacher-team.component.scss']
})
export class TeacherTeamComponent implements OnInit {
  currentUser: JwtUserResponse;

  /** 当前数据的页面对象，注意数据数组存储在content属性中。 */
  teacherTeamPage: Page<TeacherTeam>;

  /**增加数据表单的模型对象。*/
  newTeacherTeam = new TeacherTeam();

  /**是否通过后台表单参数验证。*/
  isValidForCreate = true;

  /**查询参数的封装对象。*/
  searchParams = new SearchParams<TeacherTeamSearchVo>();

  /**是否通过后台表单参数验证。*/
  isValidForSearch = true;


  constructor(private userService: UserService,
              private service: TeacherTeamService,
              private route: ActivatedRoute) {
  }


  ngOnInit() {
    this.currentUser = this.userService.currentUserSubject.value;
    this.show();
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
    }, () => this.isValidForCreate = false);
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
   * 根据不同的查询类型和可能的分页参数，列出数据。
   */
  private show() {
    this.searchParams.type = this.route.snapshot.queryParamMap.get("type") || "All";
    this.searchParams.field = JSON.parse(this.route.snapshot.queryParamMap.get("field")) || new TeacherTeamSearchVo();
    this.searchParams.page = +this.route.snapshot.queryParamMap.get("page") || 1;
    this.searchParams.size = +this.route.snapshot.queryParamMap.get("size") || 10;

    if (this.searchParams.type === "ByName") {
      this.searchByName();
    } else if (this.searchParams.type === "ByProfessionLevel") {
      this.searchByProfessionLevel();
    } else if (this.searchParams.type === "ByTeacherCount") {
      this.searchByTeacherCount();
    } else if (this.searchParams.type === "Advance") {
      this.advanceSearch();
    } else {
      this.list();
    }
  }

  /**
   * 列出所有数据，在组件初始化时调用。
   */
  list() {
    this.searchParams.type = "All";
    this.service.list(this.searchParams.page, this.searchParams.size).subscribe(teacherTeamPage => {
      this.teacherTeamPage = teacherTeamPage;
    });
  }

  /**
   * 根据参数查询数据，调用后会刷新当前显示的数据。
   */
  searchByName() {
    this.searchParams.type = "ByName";
    const name = this.searchParams.field.name;
    this.service.searchByName(name, this.searchParams.page, this.searchParams.size).subscribe(teacherTeamPage => {
      this.teacherTeamPage = teacherTeamPage;
    });
  }

  /**
   * 根据参数查询数据，调用后会刷新当前显示的数据。
   */
  searchByProfessionLevel() {
    this.searchParams.type = "ByProfessionLevel";
    const levelSet = this.searchParams.field.levelSet;
    this.service.searchByProfessionLevel(levelSet, this.searchParams.page, this.searchParams.size).subscribe(teacherTeamPage => {
      this.teacherTeamPage = teacherTeamPage;
    });
  }

  /**
   * 根据参数查询数据，调用后会刷新当前显示的数据。
   */
  searchByTeacherCount() {
    this.searchParams.type = "ByTeacherCount";
    const min = this.searchParams.field.min;
    const max = this.searchParams.field.max;
    this.service.searchByTeacherCount(min, max, this.searchParams.page, this.searchParams.size).subscribe(teacherTeamPage => {
      this.teacherTeamPage = teacherTeamPage;
    });
  }

  /**
   * 高级查询，传入表单模型数据，调用后会刷新当前显示的数据。
   * 可能抛出：400 参数错误
   */
  advanceSearch() {
    this.searchParams.type = "Advance";
    const searchVo = this.searchParams.field;
    this.service.advanceSearch(searchVo, this.searchParams.page, this.searchParams.size).subscribe(teacherTeamPage => {
      this.teacherTeamPage = teacherTeamPage;
      this.isValidForSearch = true;
    }, () => this.isValidForSearch = false);
  }
}
