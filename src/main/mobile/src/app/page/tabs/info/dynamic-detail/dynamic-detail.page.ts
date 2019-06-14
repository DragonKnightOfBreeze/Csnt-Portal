import {Component, OnInit} from "@angular/core";
import {UserService} from "../../../../service/api/user.service";
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {DynamicService} from "../../../../service/api/dynamic.service";
import {DynamicCategory} from "../../../../../../../frontend/src/app/enums/DynamicCategory";
import {Dynamic} from "../../../../domain/entity/Dynamic";

@Component({
  selector: "app-dynamic-detail",
  templateUrl: "./dynamic-detail.page.html",
  styleUrls: ["./dynamic-detail.page.scss"],
})
export class DynamicDetailPage implements OnInit {
  id: number;

  dynamic: Dynamic;

  DynamicCategory = DynamicCategory;

  constructor(private service: DynamicService,
              private userService: UserService,
              private route: ActivatedRoute,
              private location: Location) {
  }


  ngOnInit() {
    this.id = +this.route.snapshot.paramMap.get("id");
    this.get();
  }

  private get() {
    this.service.get(this.id).subscribe(dynamic => {
      this.dynamic = dynamic;
    });
  }

  delete() {
    this.location.back();
    this.service.delete(this.dynamic.id).subscribe();
  }

  isSponsorUser() {
    return this.userService.currentUser.username == this.dynamic.sponsorUser.username;
  }
}
