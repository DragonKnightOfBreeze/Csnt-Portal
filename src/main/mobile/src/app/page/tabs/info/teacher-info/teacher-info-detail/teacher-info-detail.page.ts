import {Component} from "@angular/core";
import {UserService} from "../../../../../service/api/user.service";
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {TeacherInfo} from "../../../../../domain/entity/TeacherInfo";
import {TeacherInfoService} from "../../../../../service/api/teacher-info.service";

@Component({
  selector: "app-teacher-info-detail",
  templateUrl: "./teacher-info-detail.page.html",
  styleUrls: ["./teacher-info-detail.page.scss"],
})
export class TeacherInfoDetailPage {
  id: number;

  teacherInfo: TeacherInfo;

  constructor(private service: TeacherInfoService,
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
    this.service.get(this.id).subscribe(teacherInfo => {
      this.teacherInfo = teacherInfo;
    });
  }

  delete() {
    this.location.back();
    this.service.delete(this.id).subscribe();
  }
}
