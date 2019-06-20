import {Component} from "@angular/core";
import {UserService} from "../../../../../service/api/user.service";
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {DynamicService} from "../../../../../service/api/dynamic.service";
import {Dynamic} from "../../../../../domain/entity/Dynamic";

@Component({
  selector: "app-dynamic-detail",
  templateUrl: "./dynamic-detail.page.html",
  styleUrls: ["./dynamic-detail.page.scss"],
})
export class DynamicDetailPage {
  id: number;

  dynamic: Dynamic;

  constructor(private service: DynamicService,
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
    this.service.get(this.id).subscribe(dynamic => {
      this.dynamic = dynamic;
    });
  }

  delete() {
    this.location.back();
    this.service.delete(this.dynamic.id).subscribe();
  }

  isSponsorUser() {
    return this.userService.hasLogin() && this.dynamic.sponsorUser
      && this.userService.getCurrentUser().username == this.dynamic.sponsorUser.username;
  }
}
