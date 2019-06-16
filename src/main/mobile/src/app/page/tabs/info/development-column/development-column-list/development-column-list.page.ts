import {Component} from "@angular/core";
import {DevelopmentColumnService} from "../../../../../service/api/development-column.service";
import {UserService} from "../../../../../service/api/user.service";
import {ActivatedRoute} from "@angular/router";
import {DevelopmentColumn} from "../../../../../domain/entity/DevelopmentColumn";
import {Page} from "../../../../../domain/interface/Page";
import {QueryParams} from "../../../../../domain/vo/QueryParams";

@Component({
  selector: "app-development-column-list",
  templateUrl: "./development-column-list.page.html",
  styleUrls: ["./development-column-list.page.scss"],
})
export class DevelopmentColumnListPage {
  queryParams: QueryParams<string>;

  currentPage: Page<DevelopmentColumn>;

  constructor(private service: DevelopmentColumnService,
              public userService: UserService,
              private route: ActivatedRoute) {
  }


  ngOnInit() {
    this.queryParams = {
      type: this.route.snapshot.queryParamMap.get("type") || "all",
      field: this.route.snapshot.queryParamMap.get("field") || "",
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
    this.queryParams.field = event.target.value;
    this.searchByTitle();
  }

  private show() {
    if (this.queryParams.type == "title") {
      this.searchByTitle();
    } else {
      this.list();
    }
  }

  private list() {
    this.service.list(this.queryParams.page, this.queryParams.size).subscribe(columnPage => {
      this.currentPage = columnPage;
    });
  }

  private searchByTitle() {
    //这里可以使用解构声明
    const {field, page, size} = this.queryParams;
    this.service.searchByTitle(field, page, size).subscribe(columnPage => {
      this.currentPage = columnPage;
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
}
