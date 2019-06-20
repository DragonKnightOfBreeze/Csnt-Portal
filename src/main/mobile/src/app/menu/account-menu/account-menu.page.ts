import {Component} from '@angular/core';
import {UserService} from "../../service/api/user.service";
import {Router} from "@angular/router";
import {MenuController} from "@ionic/angular";

@Component({
  selector: 'app-account-menu',
  templateUrl: './account-menu.page.html',
  styleUrls: ['./account-menu.page.scss'],
})
export class AccountMenuPage {
  constructor(public service: UserService,
              private router: Router,
              private menuController: MenuController) {
  }


  private logout() {
    this.service.logout();
    this.router.navigate([""]);
    this.disableMenu();
  }

  private async disableMenu() {
    await this.menuController.close();
    await this.menuController.enable(true, "info-menu");
  }
}
