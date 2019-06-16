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
    this.service.get(this.id).subscribe(introduce => {
      this.introduce = introduce;
    });
  }
}
