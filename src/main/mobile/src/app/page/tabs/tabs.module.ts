import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {RouterModule, Routes} from "@angular/router";

import {IonicModule} from "@ionic/angular";

import {TabsPage} from "./tabs.page";
import {LoginGuard} from "../../service/guard/login.guard.service";
import {SharedModule} from "../../shared.module";
import {HttpClientModule} from "@angular/common/http";
import {IonicStorageModule} from "@ionic/storage";

//不能使用canLoad——只在首次加载时验证

const routes: Routes = [
  {
    path: "",
    redirectTo: "home",
    pathMatch: "full"
  }, {
    path: "home",
    loadChildren: "./home/home.module#HomeModule"
  }, {
    path: "info",
    loadChildren: "./info/info.module#InfoModule"
  }, {
    path: "account",
    loadChildren: "./account/account.module#AccountModule",
    canActivateChild: [LoginGuard]
  }
];

@NgModule({
  imports: [
    CommonModule,
    HttpClientModule,
    FormsModule,
    IonicModule.forRoot(),
    IonicStorageModule.forRoot(),
    RouterModule.forChild(routes),
    SharedModule
  ],
  declarations: [TabsPage],
  exports: [TabsPage]
})
export class TabsModule {
}
