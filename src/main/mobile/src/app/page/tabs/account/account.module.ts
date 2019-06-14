import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {RouterModule, Routes} from "@angular/router";

import {IonicModule} from "@ionic/angular";

import {AccountPage} from "./account.page";
import {AccountMenuPage} from "./account-menu/account-menu.page";
import {AppModule} from "../../../app.module";

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
    AppModule
  ],
  declarations: [
    AccountPage,
    AccountMenuPage
  ]
})
export class AccountPageModule {
}
