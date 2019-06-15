import {Component, OnInit} from "@angular/core";
import {UserService} from "../../../../../service/api/user.service";
import {Introduce} from "../../../../../domain/entity/Introduce";
import {IntroduceService} from "../../../../../service/api/introduce.service";

@Component({
  selector: "app-introduce-list",
  templateUrl: "./introduce-list.page.html",
  styleUrls: ["./introduce-list.page.scss"],
})
export class IntroduceListPage implements OnInit {
  currentList: Introduce[];

  constructor(private service: IntroduceService,
              public userService: UserService) {
  }


  ngOnInit() {
    this.show();
  }

  delete(id: number) {
    this.currentList.filter(e => e.id !== id);
    this.service.delete(id).subscribe();
  }

  private show() {
    this.list();
  }

  private list() {
    this.service.list().subscribe(introduceList => {
      this.currentList = introduceList;
    });
  }
}
