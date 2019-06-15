import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {RouterModule, Routes} from "@angular/router";

import {IonicModule} from "@ionic/angular";

import {TabsPage} from "./tabs.page";
import {LoginGuard} from "../../service/guard/login-guard.service";
import {SharedModule} from "../../shared.module";

const routes: Routes = [
  {
    path: '',
    redirectTo: "home",
    pathMatch: "full"
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
  }
];

@NgModule({
  declarations: [
    TabsPage
  ],
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    RouterModule.forChild(routes),
    SharedModule
  ],
  exports: [
    TabsPage
  ]
})
export class TabsPageModule {
}
