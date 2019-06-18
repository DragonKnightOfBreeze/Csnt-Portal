import {Component} from "@angular/core";
import {QueryParams} from "../../../../../domain/vo/QueryParams";
import {Page} from "../../../../../domain/interface/Page";
import {UserService} from "../../../../../service/api/user.service";
import {ActivatedRoute} from "@angular/router";
import {ModalController, PopoverController} from "@ionic/angular";
import {TeacherTeamQueryVo} from "../../../../../domain/vo/TeacherTeamQueryVo";
import {TeacherTeam} from "../../../../../domain/entity/TeacherTeam";
import {TeacherTeamService} from "../../../../../service/api/tearcher-team.service";
import {TeacherTeamLevelPopoverPage} from "../teacher-team-level-popover/teacher-team-level-popover.page";
import {TeacherTeamSearchModalPage} from "../teacher-team-search-modal/teacher-team-search-modal.page";

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
              private popoverController: PopoverController,
              private modalController: ModalController) {
  }


  ngOnInit() {
    this.queryParams = {
      type: this.route.snapshot.queryParamMap.get("type") || "all",
      field: JSON.parse(this.route.snapshot.queryParamMap.get("field")) || new TeacherTeamQueryVo(),
      page: +this.route.snapshot.queryParamMap.get("page") || 1,
      size: +this.route.snapshot.queryParamMap.get("size") || 10
    };
    this.show();
  }

  delete(id: number) {
    this.currentPage.content.filter(e => e.id !== id);
    this.service.delete(id).subscribe();
  }

  search(event) {
    this.queryParams.field.name = event.target.value;
    this.searchByName();
  }

  private show() {
    switch (this.queryParams.type) {
      case "name":
        this.searchByName();
        break;
      case "professionLevel":
        this.searchByProfessionLevel();
        break;
      case "advance":
        this.advanceSearch();
        break;
      default:
        this.list();
        break;
    }
  }

  private list() {
    const {page, size} = this.queryParams;
    this.service.list(page, size).subscribe(dynamicPage => {
      this.currentPage = dynamicPage;
    });
  }

  private searchByName() {
    const {field, page, size} = this.queryParams;
    this.service.searchByName(field.name, page, size).subscribe(dynamicPage => {
      this.currentPage = dynamicPage;
    });
  }

  private searchByProfessionLevel() {
    const {field, page, size} = this.queryParams;
    this.service.searchByProfessionLevel(field.levelSet, page, size).subscribe(dynamicPage => {
      this.currentPage = dynamicPage;
    });
  }

  advanceSearch() {
    const {field, page, size} = this.queryParams;
    this.service.advanceSearch(field, page, size).subscribe(teacherTeamPage => {
      this.currentPage = teacherTeamPage;
    });
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
    return await popover.present();
  }

  async presentSearchModal() {
    const modal = await this.modalController.create({
      component: TeacherTeamSearchModalPage
    });
    return await modal.present();
  }
}
