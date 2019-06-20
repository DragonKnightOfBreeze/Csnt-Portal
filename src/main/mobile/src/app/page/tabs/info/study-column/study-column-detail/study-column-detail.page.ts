import {Component} from "@angular/core";
import {UserService} from "../../../../../service/api/user.service";
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {StudyColumn} from "../../../../../domain/entity/StudyColumn";
import {StudyColumnService} from "../../../../../service/api/study-reform.service";

@Component({
  selector: "app-study-column-detail",
  templateUrl: "./study-column-detail.page.html",
  styleUrls: ["./study-column-detail.page.scss"],
})
export class StudyColumnDetailPage {
  id: number;

  column: StudyColumn;

  constructor(private service: StudyColumnService,
              private userService: UserService,
              private route: ActivatedRoute,
              private location: Location) {
  }


  ionViewWillEnter() {
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
    this.service.delete(this.id).subscribe();
  }
}
