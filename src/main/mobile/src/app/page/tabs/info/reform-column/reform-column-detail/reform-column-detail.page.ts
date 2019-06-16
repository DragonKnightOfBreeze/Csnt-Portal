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
    this.id = +this.route.snapshot.paramMap.get("id");
    this.show();
  }

  delete() {
    this.location.back();
    this.service.delete(this.column.id).subscribe();
  }

  private get() {
    this.service.get(this.id).subscribe(column => {
      this.column = column;
    });
  }

  private show() {
    this.get();
  }
}
