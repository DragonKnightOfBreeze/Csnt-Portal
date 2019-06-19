import {Component} from "@angular/core";
import {ReformColumn} from "../../../../../domain/entity/ReformColumn";
import {UserService} from "../../../../../service/api/user.service";
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {ReformColumnService} from "../../../../../service/api/reform-column.service";

@Component({
  selector: "app-reform-column-detail",
  templateUrl: "./reform-column-detail.page.html",
  styleUrls: ["./reform-column-detail.page.scss"],
})
export class ReformColumnDetailPage {
  id: number;

  column: ReformColumn;

  constructor(private service: ReformColumnService,
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
    })
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
