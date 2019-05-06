import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {AppRoutingModule} from "./app-route.module";
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";

/**
 * 项目的主模块。
 */
@NgModule({
  declarations: [
    AppComponent
    //TODO
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    //TODO
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
