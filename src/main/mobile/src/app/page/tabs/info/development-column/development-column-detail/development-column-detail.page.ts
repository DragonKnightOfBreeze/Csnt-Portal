import {Component} from "@angular/core";
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {UserService} from "../../../../../service/api/user.service";
import {DevelopmentColumnService} from "../../../../../service/api/development-column.service";
import {DevelopmentColumn} from "../../../../../domain/entity/DevelopmentColumn";

@Component({
  selector: "app-development-column-detail",
  templateUrl: "./development-column-detail.page.html",
  styleUrls: ["./development-column-detail.page.scss"],
})
export class DevelopmentColumnDetailPage {
  id: number;

  column: DevelopmentColumn;

  constructor(private service: DevelopmentColumnService,
              private userService: UserService,
              private route: ActivatedRoute,
              private location: Location) {
  }


  ngOnInit() {
    this.getParams();
    this.show();
  }

  private getParams() {
    this.route.paramMap.subscribe(paramMap => {
      this.id = +paramMap.get("id");
    });
  }

  private show() {
    this.service.get(this.id).subscribe(column => {
      this.column = column;
    });
  }

  delete() {
    this.location.back();
    this.service.delete(this.column.id).subscribe();
  }
}
