import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {RouterModule, Routes} from "@angular/router";

import {IonicModule} from "@ionic/angular";

import {ErrorPage} from "./error.page";
import {Error403Page} from "./error403/error403.page";
import {SharedModule} from "../../shared.module";
import {Error404Page} from "./error404/error404.page";
import {HttpClientModule} from "@angular/common/http";
import {IonicStorageModule} from "@ionic/storage";

const routes: Routes = [
  {
    path: "",
    component: ErrorPage
  }, {
    path: "403",
    component: Error403Page
  }, {
    path: "404",
    component: Error404Page
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
  declarations: [
    ErrorPage,
    Error403Page,
    Error404Page
  ],
  bootstrap: [ErrorPage]
})
export class ErrorModule {
}
