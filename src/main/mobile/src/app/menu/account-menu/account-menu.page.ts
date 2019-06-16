import {Component} from '@angular/core';
import {UserService} from "../../service/api/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-account-menu',
  templateUrl: './account-menu.page.html',
  styleUrls: ['./account-menu.page.scss'],
})
export class AccountMenuPage {
  constructor(public service: UserService,
              private router: Router) {
  }


  private logout() {
    this.service.logout();
    this.router.navigate([""]);
  }
}
