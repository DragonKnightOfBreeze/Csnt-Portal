import {Component} from "@angular/core";
import {QueryParams} from "../../../../../domain/vo/QueryParams";
import {Page} from "../../../../../domain/interface/Page";
import {UserService} from "../../../../../service/api/user.service";
import {ActivatedRoute, NavigationEnd, Router} from "@angular/router";
import {ReformColumn} from "../../../../../domain/entity/ReformColumn";
import {ReformColumnService} from "../../../../../service/api/reform-column.service";
import {filter} from "rxjs/operators";

@Component({
  selector: "app-reform-column-list",
  templateUrl: "./reform-column-list.page.html",
  styleUrls: ["./reform-column-list.page.scss"],
})
export class ReformColumnListPage {
  queryParams: QueryParams<string>;

  currentPage: Page<ReformColumn>;

  constructor(private service: ReformColumnService,
              public userService: UserService,
              private route: ActivatedRoute,
              private router: Router) {
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
        field: JSON.parse(queryParamMap.get("field")) || "",
        page: +queryParamMap.get("page") || 1,
        size: +queryParamMap.get("size") || 10
      }
    });
  }

  private show() {
    const {type, field, page, size} = this.queryParams;
    if (type === "title") {
      this.service.searchByTitle(field, page, size).subscribe(columnPage => {
        this.currentPage = columnPage;
      });
    } else {
      this.service.list(page, size).subscribe(dynamicPage => {
        this.currentPage = dynamicPage;
      });
    }
  }

  delete(id: number) {
    this.currentPage.content.filter(e => e.id !== id);
    this.service.delete(id).subscribe();
  }

  search(event) {
    this.queryParams = new QueryParams();
    this.queryParams.type = "title";
    this.queryParams.field = event.target.value;
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
}
