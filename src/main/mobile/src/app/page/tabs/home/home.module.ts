import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {RouterModule, Routes} from "@angular/router";

import {IonicModule} from "@ionic/angular";

import {HomePage} from "./home.page";
import {SharedModule} from "../../../shared.module";
import {HttpClientModule} from "@angular/common/http";
import {IonicStorageModule} from "@ionic/storage";

const routes: Routes = [
  {
  path: "",
  component: HomePage
  }, {
    path: "**",
    redirectTo: ""
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
  declarations: [HomePage]
})
export class HomePageModule {
}
