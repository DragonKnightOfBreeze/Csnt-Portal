import {Component} from "@angular/core";
import {User} from "../../../../../../frontend/src/app/domain/entity/User";
import {UserService} from "../../../service/api/user.service";
import {ValidationService} from "../../../service/api/validation.service";
import {MenuController} from "@ionic/angular";

@Component({
  selector: "app-account",
  templateUrl: "./account.page.html",
  styleUrls: ["./account.page.scss"],
})
export class AccountPage {
  username: string;

  user = new User();

  constructor(public service: UserService,
              public validationService: ValidationService,
              public menuController: MenuController) {
  }


  ngOnInit() {
    console.log(this.service.hasLogin());
    this.getData();
    this.show();
  }

  private getData() {
    this.username = this.service.getCurrentUser().username;
  }

  private show() {
    this.service.getAccountInfo(this.username).subscribe(user => {
      this.user = user;
    });
  }

  updateAccountInfo() {
    this.service.updateAccountInfo(this.user).subscribe(updatedUser => {
      this.user = updatedUser;
    });
  }

  async openAccountMenu() {
    await this.menuController.enable(true, "account-menu").then(() => {
      this.menuController.open("account-menu");
    });
  }
}
