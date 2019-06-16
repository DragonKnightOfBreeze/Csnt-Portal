import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {RouteReuseStrategy, RouterModule, Routes} from "@angular/router";

import {IonicModule, IonicRouteStrategy} from "@ionic/angular";
import {SplashScreen} from "@ionic-native/splash-screen/ngx";
import {StatusBar} from "@ionic-native/status-bar/ngx";

import {AppComponent} from "./app.component";
import {HttpClientModule} from "@angular/common/http";
import {IonicStorageModule} from "@ionic/storage";
import {TabsModule} from "./page/tabs/tabs.module";
import {AccountMenuPage} from "./menu/account-menu/account-menu.page";
import {InfoMenuPage} from "./menu/info-menu/info-menu.page";
import {FormsModule} from "@angular/forms";

//NOTE 懒加载的loadChildren必须配合SomeModule.forChild()使用。
//NOTE 懒加载不能与{preloadingStrategy: PreloadAllModules}一同使用。
//存在多级路由定义在不同模块对应的路由模块里。
//有些路由存在激活或读取限制(canLoad,canActive)，有些路由带有数据(data)。
//如果path=""，需要指定pathMatch="full"

const routes: Routes = [
  {
    path: "",
    redirectTo: "/tabs/home",
    pathMatch: "full"
  }, {
    path: "tabs",
    loadChildren: "./page/tabs/tabs.module#TabsPageModule"
  }, {
    path: 'login',
    loadChildren: './page/login/login.module#LoginPageModule'
  }, {
    path: 'register',
    loadChildren: './page/register/register.module#RegisterPageModule'
  }, {
    path: "error",
    loadChildren: "./page/error/error.module#ErrorPageModule"
  }, {
    path: "**",
    redirectTo: "/error/404"
  }
];

@NgModule({
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    IonicModule.forRoot(),
    IonicStorageModule.forRoot(),
    RouterModule.forRoot(routes),
    TabsModule
  ],
  declarations: [
    AppComponent,
    AccountMenuPage,
    InfoMenuPage
  ],
  providers: [
    StatusBar,
    SplashScreen,
    {provide: RouteReuseStrategy, useClass: IonicRouteStrategy},
    // //提供Jwt安全验证拦截器；
    // {provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true},
    // //提供错误拦截器
    // {provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
