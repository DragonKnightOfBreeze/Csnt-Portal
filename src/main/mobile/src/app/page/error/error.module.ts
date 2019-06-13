import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {RouterModule, Routes} from "@angular/router";

import {IonicModule} from "@ionic/angular";

import {ErrorPage} from "./error.page";
import {Error403Page} from "./error403/error403.page";
import {Error404Page} from "./error404/error404.page";

const routes: Routes = [{
  path: "",
  component: ErrorPage
}, {
  path: "403",
  loadChildren: "./error403/error403.module#Error403Module"
}, {
  path: "404",
  loadChildren: "./error404/error404.module#Error404Module"
}];

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    RouterModule.forChild(routes)
  ],
  declarations: [
    ErrorPage,
    Error403Page,
    Error404Page
  ]
})
export class ErrorPageModule {
}
