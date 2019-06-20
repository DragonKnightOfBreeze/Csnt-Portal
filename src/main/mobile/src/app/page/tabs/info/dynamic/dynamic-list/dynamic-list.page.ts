import {Component} from "@angular/core";
import {QueryParams} from "../../../../../domain/vo/QueryParams";
import {Page} from "../../../../../domain/interface/Page";
import {Dynamic} from "../../../../../domain/entity/Dynamic";
import {UserService} from "../../../../../service/api/user.service";
import {ActivatedRoute, NavigationEnd, Router} from "@angular/router";
import {DynamicService} from "../../../../../service/api/dynamic.service";
import {DynamicQueryVo} from "../../../../../../../../frontend/src/app/domain/vo/DynamicQueryVo";
import {ModalController, PopoverController} from "@ionic/angular";
import {DynamicCategoryPopoverPage} from "../dynamic-category-popover/dynamic-category-popover.page";
import {DynamicSearchModalPage} from "../dynamic-search-modal/dynamic-search-modal.page";
import {DynamicCreateModalPage} from "../dynamic-create-modal/dynamic-create-modal.page";
import {filter} from "rxjs/operators";

@Component({
  selector: "app-dynamic-list",
  templateUrl: "./dynamic-list.page.html",
  styleUrls: ["./dynamic-list.page.scss"],
})
export class DynamicListPage {
  queryParams: QueryParams<DynamicQueryVo>;

  currentPage: Page<Dynamic>;

  constructor(private service: DynamicService,
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
        field: JSON.parse(queryParamMap.get("field")) || new DynamicQueryVo(),
        page: +queryParamMap.get("page") || 1,
        size: +queryParamMap.get("size") || 10
      }
    });
  }

  private show() {
    const {type, field, page, size} = this.queryParams;
    switch (type) {
      case "subject":
        this.service.searchBySubject(field.subject, page, size).subscribe(dynamicPage => {
          this.currentPage = dynamicPage;
        });
        break;
      case "category":
        this.service.searchByCategory(field.categorySet, page, size).subscribe(dynamicPage => {
          this.currentPage = dynamicPage;
        });
        break;
      case "advance":
        this.service.advanceSearch(field, page, size).subscribe(dynamicPage => {
          this.currentPage = dynamicPage;
        });
        break;
      default:
        this.service.list(page, size).subscribe(dynamicPage => {
          this.currentPage = dynamicPage;
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
    this.queryParams.type = "subject";
    this.queryParams.field.subject = event.target.value;
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

  isSponsorUser(dynamic: Dynamic) {
    return this.userService.hasLogin() && dynamic.sponsorUser
      && this.userService.getCurrentUser().username == dynamic.sponsorUser.username;
  }

  async presentCategoryPopover() {
    const popover = await this.popoverController.create({
      component: DynamicCategoryPopoverPage,
      translucent: true
    });
    await popover.present();
  }

  async presentSearchModal() {
    const modal = await this.modalController.create({
      component: DynamicSearchModalPage
    });
    await modal.present();
  }

  async presentCreateModal() {
    const modal = await this.modalController.create({
      component: DynamicCreateModalPage
    });
    await modal.present();
  }
}
