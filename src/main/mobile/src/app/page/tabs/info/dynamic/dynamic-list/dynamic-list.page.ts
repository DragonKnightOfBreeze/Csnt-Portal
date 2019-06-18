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


  ngOnInit() {
    this.router.events.pipe(filter(e => e instanceof NavigationEnd)).subscribe(() => {
      this.getQueryParams();
      this.show();
    });
  }

  private getQueryParams() {
    this.queryParams = {
      type: this.route.snapshot.queryParamMap.get("type") || "all",
      field: JSON.parse(this.route.snapshot.queryParamMap.get("field")) || new DynamicQueryVo(),
      page: +this.route.snapshot.queryParamMap.get("page") || 1,
      size: +this.route.snapshot.queryParamMap.get("size") || 10
    };
  }

  private show() {
    switch (this.queryParams.type) {
      case "subject":
        this.searchBySubject();
        break;
      case "category":
        this.searchByCategory();
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

  private searchBySubject() {
    const {field, page, size} = this.queryParams;
    this.service.searchBySubject(field.subject, page, size).subscribe(dynamicPage => {
      this.currentPage = dynamicPage;
    });
  }

  private searchByCategory() {
    const {field, page, size} = this.queryParams;
    this.service.searchByCategory(field.categorySet, page, size).subscribe(dynamicPage => {
      this.currentPage = dynamicPage;
    });
  }

  private advanceSearch() {
    const {field, page, size} = this.queryParams;
    this.service.advanceSearch(field, page, size).subscribe(dynamicPage => {
      this.currentPage = dynamicPage;
    });
  }

  delete(id: number) {
    this.currentPage.content.filter(e => e.id !== id);
    this.service.delete(id).subscribe();
  }

  search(event) {
    this.queryParams.field.subject = event.target.value;
    this.searchBySubject();
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
    return this.userService.hasLogin && dynamic.sponsorUser
      && this.userService.currentUser.username == dynamic.sponsorUser.username;
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

  async presentCreateModal() {
    const modal = await this.modalController.create({
      component: DynamicCreateModalPage
    });
    return await modal.present();
  }
}
