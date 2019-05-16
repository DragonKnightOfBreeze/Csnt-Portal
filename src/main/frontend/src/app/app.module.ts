import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {AppRoutingModule} from "./app-route.module";
import {FormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {CookieService} from "ngx-cookie-service";
import {JwtInterceptor} from "./service/interceptor/jwt-interceptor.service";
import {ErrorInterceptor} from "./service/interceptor/error-interceptor.service";
import {LoginComponent} from "./page/login/login.component";
import {LogoutComponent} from "./page/logout/logout.component";
import {NavigationComponent} from "./part/navigation/navigation.component";
import {PaginationComponent} from "./part/pagination/pagination.component";
import {DevelopmentColumnDetailComponent} from "./page/development-column-detail/development-column-detail.component";
import {RegisterComponent} from "./page/register/register.component";
import {DynamicComponent} from "./page/dynamic/dynamic.component";
import {IntroduceComponent} from "./page/introduce/introduce.component";
import {AccountComponent} from "./page/account/account.component";
import {DevelopmentColumnComponent} from "./page/development-column/development-column.component";
import {UserDetailComponent} from "./page/user-detail/user-detail.component";
import {StudyColumnComponent} from "./page/study-column/study-column.component";
import {ReformColumnComponent} from "./page/reform-column/reform-column.component";
import {UserComponent} from "./page/user/user.component";
import {TeacherTeamComponent} from "./page/teacher-team/teacher-team.component";
import {DynamicDetailComponent} from "./page/dynamic-detail/dynamic-detail.component";
import {IntroduceDetailComponent} from "./page/introduce-detail/introduce-detail.component";
import {ReformColumnDetailComponent} from "./page/reform-column-detail/reform-column-detail.component";
import {StudyColumnDetailComponent} from "./page/study-column-detail/study-column-detail.component";
import {TeacherTeamDetailComponent} from "./page/teacher-team-detail/teacher-team-detail.component";
import {TeacherInfoDetailComponent} from "./page/teacher-info-detail/teacher-info-detail.component";
import {HomeComponent} from './page/home/home.component';
import {FooterComponent} from './part/footer/footer.component';

/**
 * 项目的主模块。
 */
@NgModule({
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  declarations: [
    AppComponent,

    LoginComponent,
    LogoutComponent,
    NavigationComponent,
    PaginationComponent,
    RegisterComponent,

    AccountComponent,
    DevelopmentColumnComponent,
    DevelopmentColumnDetailComponent,
    DynamicComponent,
    DynamicDetailComponent,
    IntroduceComponent,
    IntroduceDetailComponent,
    ReformColumnComponent,
    ReformColumnDetailComponent,
    StudyColumnComponent,
    StudyColumnDetailComponent,
    TeacherTeamComponent,
    TeacherTeamDetailComponent,
    TeacherInfoDetailComponent,
    UserComponent,
    UserDetailComponent,
    HomeComponent,
    FooterComponent
  ],
  providers: [
    //带有@Injectable({provideIn:"root"})的服务，不需要在这里声明
    CookieService,
    //类似拦截器的特殊服务仍然需要在这里声明
    {provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true},
    {provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
