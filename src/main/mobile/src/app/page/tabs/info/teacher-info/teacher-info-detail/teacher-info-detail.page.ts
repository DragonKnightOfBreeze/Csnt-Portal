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
    this.id = +this.route.snapshot.paramMap.get("id");
    this.show();
  }

  delete() {
    this.location.back();
    this.service.delete(this.id).subscribe();
  }

  private show() {
    this.get();
  }

  private get() {
    this.service.get(this.id).subscribe(teacherInfo => {
      this.teacherInfo = teacherInfo;
    });
  }
}
