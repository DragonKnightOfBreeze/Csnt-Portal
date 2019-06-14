import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {RouterModule, Routes} from '@angular/router';

import {IonicModule} from '@ionic/angular';

import {RegisterPage} from './register.page';
import {AppModule} from "../../app.module";

const routes: Routes = [{
  path: '',
  component: RegisterPage
}];

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    RouterModule.forChild(routes),
    AppModule
  ],
  declarations: [RegisterPage]
})
export class RegisterPageModule {
}
