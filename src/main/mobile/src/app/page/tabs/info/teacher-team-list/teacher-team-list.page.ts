import {Component, OnInit} from "@angular/core";
import {SearchParams} from "../../../../domain/vo/SearchParams";
import {Page} from "../../../../domain/interface/Page";
import {UserService} from "../../../../service/api/user.service";
import {ActivatedRoute} from "@angular/router";
import {ModalController, PopoverController} from "@ionic/angular";
import {TeacherTeamSearchVo} from "../../../../domain/vo/TeacherTeamSearchVo";
import {TeacherTeam} from "../../../../domain/entity/TeacherTeam";
import {ProfessionLevel} from "../../../../enum/ProfessionLevel";
import {TeacherTeamService} from "../../../../service/api/tearcher-team.service";
import {TeacherTeamLevelPopoverPage} from "../teacher-team-level-popover/teacher-team-level-popover.page";
import {TeacherTeamSearchModalPage} from "../teacher-team-search-modal/teacher-team-search-modal.page";

@Component({
  selector: "app-teacher-team-list",
  templateUrl: "./teacher-team-list.page.html",
  styleUrls: ["./teacher-team-list.page.scss"],
})
export class TeacherTeamListPage implements OnInit {
  searchParams: SearchParams<TeacherTeamSearchVo>;

  currentPage: Page<TeacherTeam>;

  ProfessionLevel = ProfessionLevel;

  constructor(private service: TeacherTeamService,
              public userService: UserService,
              private route: ActivatedRoute,
              private popoverController: PopoverController,
              private modalController: ModalController) {
  }


  ngOnInit() {
    this.searchParams = {
      type: this.route.snapshot.queryParamMap.get("type") || "All",
      field: JSON.parse(this.route.snapshot.queryParamMap.get("field")) || new TeacherTeamSearchVo(),
      page: +this.route.snapshot.queryParamMap.get("page") || 1,
      size: +this.route.snapshot.queryParamMap.get("size") || 10
    };
    this.show();
  }

  delete(id: number) {
    this.currentPage.content.filter(e => e.id !== id);
    this.service.delete(id).subscribe();
  }

  private show() {
    switch (this.searchParams.type) {
      case "BySubject":
        this.searchByName();
        break;
      case "ByCategory":
        this.searchByProfessionLevel();
        break;
      case "Advance":
        this.advanceSearch();
        break;
      default:
        this.list();
        break;
    }
  }

  private list() {
    this.searchParams.type = "All";
    this.service.list(this.searchParams.page, this.searchParams.size).subscribe(dynamicPage => {
      this.currentPage = dynamicPage;
    });
  }

  search(event) {
    this.searchParams.field = event.target.value;
    this.searchByName();
  }

  private searchByName() {
    this.searchParams.type = "name";
    const name = this.searchParams.field.name;
    this.service.searchByName(name, this.searchParams.page, this.searchParams.size).subscribe(dynamicPage => {
      this.currentPage = dynamicPage;
    });
  }

  private searchByProfessionLevel() {
    this.searchParams.type = "ByCategory";
    const levelSet = this.searchParams.field.levelSet;
    this.service.searchByProfessionLevel(levelSet, this.searchParams.page, this.searchParams.size).subscribe(dynamicPage => {
      this.currentPage = dynamicPage;
    });
  }

  advanceSearch() {
    this.searchParams.type = "Advance";
    const searchVo = this.searchParams.field;
    this.service.advanceSearch(searchVo, this.searchParams.page, this.searchParams.size).subscribe(teacherTeamPage => {
      this.currentPage = teacherTeamPage;
    });
  }

  goPreviousPage() {
    if (!this.currentPage.first) {
      this.searchParams.page -= 1;
      this.show();
    }
  }

  goNextPage() {
    if (!this.currentPage.last) {
      this.searchParams.page += 1;
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
