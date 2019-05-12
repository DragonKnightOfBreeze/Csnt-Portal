import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {AppRoutingModule} from "./app-route.module";
import {FormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {PaginationComponent} from "./component/pagination/pagination.component";
import {NavigationComponent} from "./component/navigation/navigation.component";
import {LoginComponent} from "./component/login/login.component";
import {RegisterComponent} from "./component/register/register.component";
import {CookieService} from "ngx-cookie-service";
import {JwtInterceptor} from "./service/interceptor/jwt-interceptor.service";
import {ErrorInterceptor} from "./service/interceptor/error-interceptor.service";
import {AccountDetailComponent} from "./page/account/account-detail/account-detail.component";
import {DevelopmentColumnEditComponent} from "./page/development-column/development-column-edit/development-column-edit.component";
import {DevelopmentColumnDetailComponent} from "./page/development-column/development-column-detail/development-column-detail.component";
import {DynamicDetailComponent} from "./page/dynamic/dynamic-detail/dynamic-detail.component";
import {DynamicListComponent} from "./page/dynamic/dynamic-list/dynamic-list.component";
import {DevelopmentColumnListComponent} from "./page/development-column/development-column-list/development-column-list.component";
import {IntroduceEditComponent} from "./page/introduce/introduce-edit/introduce-edit.component";
import {IntroduceDetailComponent} from "./page/introduce/introduce-detail/introduce-detail.component";
import {IntroduceListComponent} from "./page/introduce/introduce-list/introduce-list.component";
import {ReformColumnEditComponent} from "./page/reform-column/reform-column-edit/reform-column-edit.component";
import {ReformColumnDetailComponent} from "./page/reform-column/reform-column-detail/reform-column-detail.component";
import {ReformColumnListComponent} from "./page/reform-column/reform-column-list/reform-column-list.component";
import {StudyColumnEditComponent} from "./page/study-column/study-column-edit/study-column-edit.component";
import {StudyColumnDetailComponent} from "./page/study-column/study-column-detail/study-column-detail.component";
import {StudyColumnListComponent} from "./page/study-column/study-column-list/study-column-list.component";
import {TeacherTeamEditComponent} from "./page/teacher-team/teacher-team-edit/teacher-team-edit.component";
import {TeacherTeamDetailComponent} from "./page/teacher-team/teacher-team-detail/teacher-team-detail.component";
import {TeacherTeamListComponent} from "./page/teacher-team/teacher-team-list/teacher-team-list.component";
import {LogoutComponent} from "./component/logout/logout.component";
import {TeacherInfoDetailComponent} from "./page/teacher-info/teacher-info-detail/teacher-info-detail.component";
import {TeacherInfoEditComponent} from "./page/teacher-info/teacher-info-edit/teacher-info-edit.component";

/**
 * 项目的主模块。
 */
@NgModule({
  declarations: [
    AppComponent,
    PaginationComponent,
    NavigationComponent,
    RegisterComponent,
    LoginComponent,
    LogoutComponent,

    AccountDetailComponent,

    DevelopmentColumnDetailComponent,
    DevelopmentColumnEditComponent,
    DevelopmentColumnListComponent,

    DynamicDetailComponent,
    DynamicListComponent,

    IntroduceDetailComponent,
    IntroduceEditComponent,
    IntroduceListComponent,

    ReformColumnEditComponent,
    ReformColumnDetailComponent,
    ReformColumnListComponent,

    StudyColumnDetailComponent,
    StudyColumnEditComponent,
    StudyColumnListComponent,

    TeacherTeamDetailComponent,
    TeacherTeamEditComponent,
    TeacherTeamListComponent,

    TeacherInfoDetailComponent,
    TeacherInfoEditComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
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
