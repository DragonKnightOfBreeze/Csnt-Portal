import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {RouterModule, Routes} from "@angular/router";

import {IonicModule} from "@ionic/angular";

import {TabsPage} from "./tabs.page";
import {LoginGuard} from "../../service/guard/login-guard.service";
import {SharedModule} from "../../shared.module";
import {HomePageModule} from "./home/home.module";
import {InfoPageModule} from "./info/info.module";
import {AccountPageModule} from "./account/account.module";

const routes: Routes = [{
  path: '',
  component: TabsPage
}, {
  path: "home",
  loadChildren: "./home/home.module#HomePageModule"
}, {
  path: "info",
  loadChildren: "./info/info.module#InfoPageModule"
}, {
  path: "account",
  loadChildren: "./account/account.module#AccountPageModule",
  canLoad: [LoginGuard]
}];

@NgModule({
  declarations: [
    TabsPage
  ],
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    RouterModule.forChild(routes),
    SharedModule,
    HomePageModule,
    InfoPageModule,
    AccountPageModule
  ]
})
export class TabsPageModule {
}
