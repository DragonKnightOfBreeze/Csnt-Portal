import {Component, OnInit} from "@angular/core";
import {SearchParams} from "../../../../domain/vo/SearchParams";
import {Page} from "../../../../domain/interface/Page";
import {Dynamic} from "../../../../domain/entity/Dynamic";
import {UserService} from "../../../../service/api/user.service";
import {ActivatedRoute} from "@angular/router";
import {DynamicService} from "../../../../service/api/dynamic.service";
import {DynamicSearchVo} from "../../../../../../../frontend/src/app/domain/vo/DynamicSearchVo";
import {ModalController, PopoverController} from "@ionic/angular";
import {DynamicCategoryPopoverPage} from "../dynamic-category-popover/dynamic-category-popover.page";
import {DynamicSearchModalPage} from "../dynamic-search-modal/dynamic-search-modal.page";

@Component({
  selector: "app-dynamic-list",
  templateUrl: "./dynamic-list.page.html",
  styleUrls: ["./dynamic-list.page.scss"],
})
export class DynamicListPage implements OnInit {
  searchParams: SearchParams<DynamicSearchVo>;

  currentPage: Page<Dynamic>;
  newColumn = new Dynamic();

  constructor(private service: DynamicService,
              public userService: UserService,
              private route: ActivatedRoute,
              private popoverController: PopoverController,
              private modalController: ModalController) {
  }


  ngOnInit() {
    this.searchParams = {
      type: this.route.snapshot.queryParamMap.get("type") || "All",
      field: JSON.parse(this.route.snapshot.queryParamMap.get("field")) || new DynamicSearchVo(),
      page: +this.route.snapshot.queryParamMap.get("page") || 1,
      size: +this.route.snapshot.queryParamMap.get("size") || 10
    };
    this.show();
  }

  create() {
    this.service.create(this.newColumn).subscribe(dynamic => {
      this.currentPage.content.push(dynamic);
      this.currentPage.content.slice(0, 10);
    });
  }

  delete(id: number) {
    this.currentPage.content.filter(e => e.id !== id);
    this.service.delete(id).subscribe();
  }

  private show() {
    if (this.searchParams.type === "BySubject") {
      this.searchBySubject();
    } else if (this.searchParams.type === "ByCategory") {
      this.searchByCategory();
    } else if (this.searchParams.type === "BySponsorUsername") {
      this.searchBySponsorUsername();
    } else if (this.searchParams.type === "Advance") {
      this.advanceSearch();
    } else {
      this.list();
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
    this.searchBySubject();
  }

  private searchBySubject() {
    this.searchParams.type = "BySubject";
    const subject = this.searchParams.field.subject;
    this.service.searchBySubject(subject, this.searchParams.page, this.searchParams.size).subscribe(dynamicPage => {
      this.currentPage = dynamicPage;
    });
  }

  private searchBySponsorUsername() {
    this.searchParams.type = "BySponsorUsername";
    const sponsorUsername = this.searchParams.field.sponsorUsername;
    this.service.searchBySponsorUsername(sponsorUsername, this.searchParams.page, this.searchParams.size).subscribe(dynamicPage => {
      this.currentPage = dynamicPage;
    });
  }

  private searchByCategory() {
    this.searchParams.type = "ByCategory";
    const categorySet = this.searchParams.field.categorySet;
    this.service.searchByCategory(categorySet, this.searchParams.page, this.searchParams.size).subscribe(dynamicPage => {
      this.currentPage = dynamicPage;
    });
  }

  advanceSearch() {
    this.searchParams.type = "Advance";
    const searchVo = this.searchParams.field;
    this.service.advanceSearch(searchVo, this.searchParams.page, this.searchParams.size).subscribe(dynamicPage => {
      this.currentPage = dynamicPage;
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

  isSponsorUser(dynamic: Dynamic) {
    return this.userService.currentUser.username == dynamic.sponsorUser.username;
  }

  async presentCategoryPopover() {
    const popover = await this.popoverController.create({
      component: DynamicCategoryPopoverPage,
      translucent: true
    });
    return await popover.present();
  }

  async presentSearchModal() {
    const modal = await this.modalController.create({
      component: DynamicSearchModalPage
    });
    return await modal.present();
  }
}
