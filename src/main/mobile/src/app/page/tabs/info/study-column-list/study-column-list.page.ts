import {Component, OnInit} from "@angular/core";
import {SearchParams} from "../../../../domain/vo/SearchParams";
import {Page} from "../../../../domain/interface/Page";
import {StudyColumn} from "../../../../domain/entity/StudyColumn";
import {UserService} from "../../../../service/api/user.service";
import {ActivatedRoute} from "@angular/router";
import {StudyColumnService} from "../../../../service/api/study-reform.service";

@Component({
  selector: "app-study-column-list",
  templateUrl: "./study-column-list.page.html",
  styleUrls: ["./study-column-list.page.scss"],
})
export class StudyColumnListPage implements OnInit {
  searchParams: SearchParams<string>;

  currentPage: Page<StudyColumn>;
  newColumn = new StudyColumn();

  constructor(private service: StudyColumnService,
              public userService: UserService,
              private route: ActivatedRoute) {
  }


  ngOnInit() {
    this.searchParams = {
      type: this.route.snapshot.queryParamMap.get("type") || "All",
      field: this.route.snapshot.queryParamMap.get("field") || "",
      page: +this.route.snapshot.queryParamMap.get("page") || 1,
      size: +this.route.snapshot.queryParamMap.get("size") || 10
    };
    this.show();
  }

  create() {
    this.service.create(this.newColumn).subscribe(column => {
      this.currentPage.content.push(column);
      this.currentPage.content.slice(0, 10);
    });
  }

  delete(id: number) {
    this.currentPage.content.filter(e => e.id !== id);
    this.service.delete(id).subscribe();
  }

  private show() {
    if (this.searchParams.type == "ByTitle") {
      this.searchByTitle();
    } else {
      this.list();
    }
  }

  private list() {
    this.searchParams.type = "All";
    this.service.list(this.searchParams.page, this.searchParams.size).subscribe(columnPage => {
      this.currentPage = columnPage;
    });
  }

  search(event) {
    this.searchParams.field = event.target.value;
    this.searchByTitle();
  }

  private searchByTitle() {
    this.searchParams.type = "ByTitle";
    this.service.searchByTitle(this.searchParams.field, this.searchParams.page, this.searchParams.size).subscribe(columnPage => {
      this.currentPage = columnPage;
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
}
