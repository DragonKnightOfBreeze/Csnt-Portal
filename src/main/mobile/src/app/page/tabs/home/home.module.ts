import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {RouterModule, Routes} from "@angular/router";

import {IonicModule} from "@ionic/angular";

import {HomePage} from "./home.page";
import {SharedModule} from "../../../shared.module";

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
    FormsModule,
    IonicModule,
    RouterModule.forChild(routes),
    SharedModule
  ],
  declarations: [HomePage]
})
export class HomePageModule {
}
