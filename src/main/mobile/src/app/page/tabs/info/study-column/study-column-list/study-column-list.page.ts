import {Component} from "@angular/core";
import {QueryParams} from "../../../../../domain/vo/QueryParams";
import {Page} from "../../../../../domain/interface/Page";
import {StudyColumn} from "../../../../../domain/entity/StudyColumn";
import {UserService} from "../../../../../service/api/user.service";
import {ActivatedRoute} from "@angular/router";
import {StudyColumnService} from "../../../../../service/api/study-reform.service";

@Component({
  selector: "app-study-column-list",
  templateUrl: "./study-column-list.page.html",
  styleUrls: ["./study-column-list.page.scss"],
})
export class StudyColumnListPage {
  queryParams: QueryParams<string>;

  currentPage: Page<StudyColumn>;

  constructor(private service: StudyColumnService,
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
    const {page, size} = this.queryParams;
    this.service.list(page, size).subscribe(columnPage => {
      this.currentPage = columnPage;
    });
  }

  private searchByTitle() {
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
