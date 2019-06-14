import {Component, OnInit} from "@angular/core";
import {User} from "../../../../../../frontend/src/app/domain/entity/User";
import {Gender} from "../../../../../../frontend/src/app/enums/Gender";
import {Role} from "../../../../../../frontend/src/app/enums/Role";
import {Profession} from "../../../../../../frontend/src/app/enums/Profession";
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

  Gender = Gender;
  Role = Role;
  Profession = Profession;

  constructor(public service: UserService,
              private route: ActivatedRoute) {
  }


  ngOnInit() {
    this.username = this.route.snapshot.paramMap.get("username");
    this.getAccountInfo();
  }

  private getAccountInfo() {
    this.service.getAccountInfo(this.username).subscribe(user => {
      this.user = user;
    });
  }

  updateAccountInfo() {
    this.service.updateAccountInfo(this.user).subscribe(updatedUser => {
      this.user = updatedUser;
    });
  }
}
