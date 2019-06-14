import {Component, OnInit} from "@angular/core";
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {UserService} from "../../../../service/api/user.service";
import {DevelopmentColumnService} from "../../../../service/api/development-column.service";
import {Dynamic} from "../../../../domain/entity/DevelopmentColumn";

@Component({
  selector: "app-development-column-detail",
  templateUrl: "./development-column-detail.page.html",
  styleUrls: ["./development-column-detail.page.scss"],
})
export class DevelopmentColumnDetailPage implements OnInit {
  column: Dynamic;

  id: number;

  constructor(private service: DevelopmentColumnService,
              private userService: UserService,
              private route: ActivatedRoute,
              private location: Location) {
  }


  ngOnInit() {
    this.id = +this.route.snapshot.paramMap.get("id");
    this.get();
  }

  private get() {
    this.service.get(this.id).subscribe(column => {
      this.column = column;
    });
  }

  delete() {
    this.location.back();
    this.service.delete(this.column.id).subscribe();
  }

  update() {
    this.service.update(this.column).subscribe(updatedColumn => {
      this.column = updatedColumn;
    });
  }
}
