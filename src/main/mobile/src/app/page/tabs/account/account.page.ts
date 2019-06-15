import {Component, OnInit} from "@angular/core";
import {User} from "../../../../../../frontend/src/app/domain/entity/User";
import {UserService} from "../../../service/api/user.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: "app-account",
  templateUrl: "./account.page.html",
  styleUrls: ["./account.page.scss"],
})
export class AccountPage implements OnInit {
  username: string;

  user: User;

  constructor(public service: UserService,
              private route: ActivatedRoute) {
  }


  ngOnInit() {
    this.username = this.route.snapshot.paramMap.get("username");
    this.show();
  }

  updateAccountInfo() {
    this.service.updateAccountInfo(this.user).subscribe(updatedUser => {
      this.user = updatedUser;
    });
  }

  private show() {
    this.getAccountInfo();
  }

  private getAccountInfo() {
    this.service.getAccountInfo(this.username).subscribe(user => {
      this.user = user;
    });
  }
}
