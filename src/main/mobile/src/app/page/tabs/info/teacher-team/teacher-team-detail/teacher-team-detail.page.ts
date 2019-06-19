import {Component} from "@angular/core";
import {UserService} from "../../../../../service/api/user.service";
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {TeacherTeam} from "../../../../../domain/entity/TeacherTeam";
import {TeacherTeamService} from "../../../../../service/api/tearcher-team.service";

@Component({
  selector: "app-teacher-team-detail",
  templateUrl: "./teacher-team-detail.page.html",
  styleUrls: ["./teacher-team-detail.page.scss"],
})
export class TeacherTeamDetailPage {
  id: number;

  teacherTeam: TeacherTeam;

  constructor(private service: TeacherTeamService,
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
    this.service.get(this.id).subscribe(teacherTeam => {
      this.teacherTeam = teacherTeam;
    });
  }

  delete() {
    this.location.back();
    this.service.delete(this.teacherTeam.id).subscribe();
  }
}
