import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {RouteReuseStrategy} from "@angular/router";

import {IonicModule, IonicRouteStrategy} from "@ionic/angular";
import {SplashScreen} from "@ionic-native/splash-screen/ngx";
import {StatusBar} from "@ionic-native/status-bar/ngx";

import {AppRoutingModule} from "./app-routing.module";
import {AppComponent} from "./app.component";
import {EnumConstsPipe} from "./pipe/enum-consts.pipe";
import {EnumTextPipe} from "./pipe/enum-text.pipe";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {JwtInterceptor} from "../../../frontend/src/app/service/interceptor/jwt-interceptor.service";
import {ErrorInterceptor} from "../../../frontend/src/app/service/interceptor/error-interceptor.service";

@NgModule({
  declarations: [
    AppComponent,
    EnumConstsPipe,
    EnumTextPipe,
  ],
  entryComponents: [],
  imports: [
    BrowserModule,
    HttpClientModule,
    IonicModule.forRoot(),
    AppRoutingModule
  ],
  providers: [
    StatusBar,
    SplashScreen,
    {provide: RouteReuseStrategy, useClass: IonicRouteStrategy},
    //提供Jwt安全验证拦截器；
    {provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true},
    //提供错误拦截器
    {provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
