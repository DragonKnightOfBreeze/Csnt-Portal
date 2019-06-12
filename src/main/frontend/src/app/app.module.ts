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
import {RegisterComponent} from "./page/register/register.component";
import {DynamicComponent} from "./page/dynamic/dynamic.component";
import {IntroduceComponent} from "./page/introduce/introduce.component";
import {AccountComponent} from "./page/account/account.component";
import {DevelopmentColumnComponent} from "./page/development-column/development-column.component";
import {StudyColumnComponent} from "./page/study-column/study-column.component";
import {ReformColumnComponent} from "./page/reform-column/reform-column.component";
import {UserComponent} from "./page/user/user.component";
import {TeacherTeamComponent} from "./page/teacher-team/teacher-team.component";
import {HomeComponent} from './page/home/home.component';
import {Error403Component} from './page/error/error403/error403.component';
import {Error404Component} from './page/error/error404/error404.component';
import {Error500Component} from './page/error/error500/error500.component';
import {Error501Component} from './page/error/error501/error501.component';
import {ErrorComponent} from "./page/error/error.component";
import {EnumTextPipe} from './pipe/enum-text.pipe';
import {EnumConstsPipe} from "./pipe/enum-consts.pipe";
import {BodyComponent} from './component/body/body.component';
import {HeaderComponent} from './component/header/header.component';
import {DynamicsComponent} from './page/account/dynamics/dynamics.component';
import {IntroduceDetailComponent} from "./page/introduce/introduce-detail/introduce-detail.component";
import {DevelopmentColumnDetailComponent} from "./page/development-column/development-column-detail/development-column-detail.component";
import {DynamicDetailComponent} from "./page/dynamic/dynamic-detail/dynamic-detail.component";
import {PaginationComponent} from "./component/body/pagination/pagination.component";
import {ReformColumnDetailComponent} from "./page/reform-column/reform-column-detail/reform-column-detail.component";
import {StudyColumnDetailComponent} from "./page/study-column/study-column-detail/study-column-detail.component";
import {UserDetailComponent} from "./page/user/user-detail/user-detail.component";
import {TeacherInfoDetailComponent} from "./page/teacher-info/teacher-info-detail/teacher-info-detail.component";
import {FooterComponent} from "./component/footer/footer.component";
import {TeacherTeamDetailComponent} from "./page/teacher-team/teacher-team-detail/teacher-team-detail.component";
import {NavigationComponent} from "./component/header/navigation/navigation.component";
import {UniqueUserValidator} from "./directive/unique-user.validator";

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

    HeaderComponent,
    NavigationComponent,
    BodyComponent,
    PaginationComponent,
    FooterComponent,

    HomeComponent,
    RegisterComponent,
    LoginComponent,
    LogoutComponent,
    AccountComponent,
    DynamicsComponent,
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
    ErrorComponent,
    Error403Component,
    Error404Component,
    Error500Component,
    Error501Component,

    UniqueUserValidator,

    EnumConstsPipe,
    EnumTextPipe
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
