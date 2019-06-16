import {Component} from "@angular/core";
import {User} from "../../../../../../frontend/src/app/domain/entity/User";
import {UserService} from "../../../service/api/user.service";

@Component({
  selector: "app-account",
  templateUrl: "./account.page.html",
  styleUrls: ["./account.page.scss"],
})
export class AccountPage {
  username: string;

  user: User;

  constructor(public service: UserService) {
  }


  ngOnInit() {
    this.username = this.service.currentUser.username;
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
