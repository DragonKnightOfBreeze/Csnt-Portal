import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {RouteReuseStrategy, RouterModule, Routes} from "@angular/router";

import {IonicModule, IonicRouteStrategy} from "@ionic/angular";
import {SplashScreen} from "@ionic-native/splash-screen/ngx";
import {StatusBar} from "@ionic-native/status-bar/ngx";

import {AppComponent} from "./app.component";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {IonicStorageModule} from "@ionic/storage";
import {TabsModule} from "./page/tabs/tabs.module";
import {AccountMenuPage} from "./menu/account-menu/account-menu.page";
import {InfoMenuPage} from "./menu/info-menu/info-menu.page";
import {FormsModule} from "@angular/forms";
import {JwtInterceptor} from "./service/interceptor/jwt.interceptor";
import {ErrorInterceptor} from "./service/interceptor/error.interceptor";

//NOTE 懒加载的loadChildren必须配合SomeModule.forChild()使用。
//NOTE 懒加载不能与{preloadingStrategy: PreloadAllModules}一同使用。
//存在多级路由定义在不同模块对应的路由模块里。
//有些路由存在激活或读取限制(canLoad,canActive)，有些路由带有数据(data)。
//如果path=""，需要指定pathMatch="full"

//NOTE 如何强制刷新当前路由地址
//* 导入 `RouterModule.forRoot(routes, {onSameUrlNavigation: "reload"})`
//* 在需要强制刷新的路由中配置runGuardsAndResolvers属性
//* 在对应组件的初始化方法中监听NavigationEnd事件，不能和this.route.snapshot一同使用

const routes: Routes = [
  {
    path: "",
    redirectTo: "/tabs/home",
    pathMatch: "full"
  }, {
    path: "tabs",
    loadChildren: "./page/tabs/tabs.module#TabsModule"
  }, {
    path: 'login',
    loadChildren: './page/login/login.module#LoginModule'
  }, {
    path: 'register',
    loadChildren: './page/register/register.module#RegisterModule'
  }, {
    path: "error",
    loadChildren: "./page/error/error.module#ErrorModule"
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
    //如果导航后的地址相同，将会重载，需要另外配置provider RouteReuseStrategy
    RouterModule.forRoot(routes, {onSameUrlNavigation: "reload"}),
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
    //提供Jwt安全验证拦截器；
    {provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true},
    //提供错误拦截器
    {provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true}
  ],
  bootstrap: [AppComponent],
  entryComponents: [
    AccountMenuPage,
    InfoMenuPage
  ]
})
export class AppModule {
}
