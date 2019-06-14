import {Component, OnInit} from "@angular/core";
import {SearchParams} from "../../../../domain/vo/SearchParams";
import {Page} from "../../../../domain/interface/Page";
import {UserService} from "../../../../service/api/user.service";
import {ActivatedRoute} from "@angular/router";
import {ReformColumn} from "../../../../domain/entity/ReformColumn";
import {ReformColumnService} from "../../../../service/api/reform-column.service";

@Component({
  selector: "app-reform-column-list",
  templateUrl: "./reform-column-list.page.html",
  styleUrls: ["./reform-column-list.page.scss"],
})
export class ReformColumnListPage implements OnInit {
  searchParams: SearchParams<string>;

  currentPage: Page<ReformColumn>;
  newColumn = new ReformColumn();

  constructor(private service: ReformColumnService,
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
