import {Component} from "@angular/core";
import {ReformColumnService} from "../../../../../service/api/reform-column.service";
import {UserService} from "../../../../../service/api/user.service";
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {Introduce} from "../../../../../domain/entity/Introduce";

@Component({
  selector: "app-introduce-detail",
  templateUrl: "./introduce-detail.page.html",
  styleUrls: ["./introduce-detail.page.scss"],
})
export class IntroduceDetailPage {
  id: number;

  introduce: Introduce;

  constructor(private service: ReformColumnService,
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
    this.service.get(this.id).subscribe(introduce => {
      this.introduce = introduce;
    });
  }

  delete() {
    this.location.back();
    this.service.delete(this.id).subscribe();
  }
}
