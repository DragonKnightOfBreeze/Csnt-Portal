import {Component} from "@angular/core";
import {QueryParams} from "../../../../../domain/vo/QueryParams";
import {Page} from "../../../../../domain/interface/Page";
import {UserService} from "../../../../../service/api/user.service";
import {ActivatedRoute, NavigationEnd, Router} from "@angular/router";
import {ModalController, PopoverController} from "@ionic/angular";
import {TeacherTeamQueryVo} from "../../../../../domain/vo/TeacherTeamQueryVo";
import {TeacherTeam} from "../../../../../domain/entity/TeacherTeam";
import {TeacherTeamService} from "../../../../../service/api/tearcher-team.service";
import {TeacherTeamLevelPopoverPage} from "../teacher-team-level-popover/teacher-team-level-popover.page";
import {TeacherTeamSearchModalPage} from "../teacher-team-search-modal/teacher-team-search-modal.page";
import {filter} from "rxjs/operators";

@Component({
  selector: "app-teacher-team-list",
  templateUrl: "./teacher-team-list.page.html",
  styleUrls: ["./teacher-team-list.page.scss"],
})
export class TeacherTeamListPage {
  queryParams: QueryParams<TeacherTeamQueryVo>;

  currentPage: Page<TeacherTeam>;

  constructor(private service: TeacherTeamService,
              public userService: UserService,
              private route: ActivatedRoute,
              private router: Router,
              private popoverController: PopoverController,
              private modalController: ModalController) {
  }


  ionViewWillEnter() {
    this.getQueryParams();
    this.show();
    //更新查询参数后，也会更新当前显示数据
    this.router.events.pipe(filter(e => e instanceof NavigationEnd)).subscribe(() => {
      this.getQueryParams();
      this.show();
    });
  }

  private getQueryParams() {
    this.route.queryParamMap.subscribe(queryParamMap => {
      this.queryParams = {
        type: queryParamMap.get("type") || "all",
        field: JSON.parse(queryParamMap.get("field")) || new TeacherTeamQueryVo(),
        page: +queryParamMap.get("page") || 1,
        size: +queryParamMap.get("size") || 10
      }
    });
  }

  private show() {
    const {type, field, page, size} = this.queryParams;
    switch (type) {
      case "name":
        this.service.searchByName(field.name, page, size).subscribe(teacherTeamPage => {
          this.currentPage = teacherTeamPage;
        });
        break;
      case "professionLevel":
        this.service.searchByProfessionLevel(field.levelSet, page, size).subscribe(teacherTeamPage => {
          this.currentPage = teacherTeamPage;
        });
        break;
      case "advance":
        this.service.advanceSearch(field, page, size).subscribe(teacherTeamPage => {
          this.currentPage = teacherTeamPage;
        });
        break;
      default:
        this.service.list(page, size).subscribe(teacherTeamPage => {
          this.currentPage = teacherTeamPage;
        });
        break;
    }
  }

  delete(id: number) {
    this.currentPage.content.filter(e => e.id !== id);
    this.service.delete(id).subscribe();
  }

  search(event) {
    this.queryParams = new QueryParams();
    this.queryParams.type = "name";
    this.queryParams.field.name = event.target.value;
    this.show();
  }

  goPreviousPage() {
    if (!this.currentPage.first) {
      this.queryParams.page -= 1;
      this.show();
    }
  }

  goNextPage() {
    if (!this.currentPage.last) {
      this.queryParams.page += 1;
      this.show();
    }
  }

  async presentLevelPopover() {
    const popover = await this.popoverController.create({
      component: TeacherTeamLevelPopoverPage,
      translucent: true
    });
    await popover.present();
  }

  async presentSearchModal() {
    const modal = await this.modalController.create({
      component: TeacherTeamSearchModalPage
    });
    await modal.present();
  }
}
