import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {RouterModule, Routes} from "@angular/router";

import {IonicModule} from "@ionic/angular";

import {AccountPage} from "./account.page";
import {AccountMenuPage} from "./account-menu/account-menu.page";
import {SharedModule} from "../../../shared.module";

const routes: Routes = [{
  path: "",
  component: AccountPage
}];

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    RouterModule.forChild(routes),
    SharedModule
  ],
  declarations: [
    AccountPage,
    AccountMenuPage
  ],
  //这是必要声明的
  entryComponents: [AccountMenuPage]
})
export class AccountPageModule {
}
