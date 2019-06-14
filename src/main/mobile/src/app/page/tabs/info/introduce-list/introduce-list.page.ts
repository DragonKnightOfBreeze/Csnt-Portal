import {Component, OnInit} from "@angular/core";
import {UserService} from "../../../../service/api/user.service";
import {ActivatedRoute} from "@angular/router";
import {Introduce} from "../../../../domain/entity/Introduce";
import {IntroduceService} from "../../../../service/api/introduce.service";

@Component({
  selector: "app-introduce-list",
  templateUrl: "./introduce-list.page.html",
  styleUrls: ["./introduce-list.page.scss"],
})
export class IntroduceListPage implements OnInit {
  currentList: Introduce[];
  newIntroduce = new Introduce();

  constructor(private service: IntroduceService,
              public userService: UserService,
              private route: ActivatedRoute) {
  }


  ngOnInit() {
    this.list();
  }

  create() {
    this.service.create(this.newIntroduce).subscribe(column => {
      this.currentList.push(column);
      this.currentList.slice(0, 10);
    });
  }

  delete(id: number) {
    this.currentList.filter(e => e.id !== id);
    this.service.delete(id).subscribe();
  }

  private list() {
    this.service.list().subscribe(introduceList => {
      this.currentList = introduceList;
    });
  }
}
