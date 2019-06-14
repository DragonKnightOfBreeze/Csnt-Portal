import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {RouterModule, Routes} from "@angular/router";

import {IonicModule} from "@ionic/angular";

import {TabsPage} from "./tabs.page";
import {LoginGuard} from "../../service/guard/login-guard.service";

const routes: Routes = [{
  path: '',
  component: TabsPage
}, {
  path: "home",
  loadChildren: "./home/home.module#HomeModule"
}, {
  path: "info",
  loadChildren: "./info/info.module#InfoModule"
}, {
  path: "account",
  loadChildren: "./account.module#AccountModule",
  canLoad: [LoginGuard]
}];

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    RouterModule.forChild(routes)
  ],
  declarations: [TabsPage]
})
export class TabsPageModule {
}