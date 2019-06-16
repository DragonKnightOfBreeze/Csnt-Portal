import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {RouterModule, Routes} from '@angular/router';

import {IonicModule} from '@ionic/angular';

import {LoginPage} from './login.page';
import {SharedModule} from "../../shared.module";
import {HttpClientModule} from "@angular/common/http";
import {IonicStorageModule} from "@ionic/storage";

const routes: Routes = [
  {
    path: '',
    component: LoginPage
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
  declarations: [LoginPage]
})
export class LoginModule {
}
