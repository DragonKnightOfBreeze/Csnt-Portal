import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LogoutComponent} from "./part/logout/logout.component";
import {LoginGuard} from "./service/guard/login-guard.service";
import {RegisterComponent} from "./part/register/register.component";
import {LoginComponent} from "./part/login/login.component";
import {AccountComponent} from "./page/account/account.component";
import {DynamicComponent} from "./page/dynamic/dynamic.component";
import {IntroduceDetailComponent} from "./page/introduce-detail/introduce-detail.component";
import {DevelopmentColumnDetailComponent} from "./page/development-column-detail/development-column-detail.component";
import {DynamicDetailComponent} from "./page/dynamic-detail/dynamic-detail.component";
import {IntroduceComponent} from "./page/introduce/introduce.component";
import {DevelopmentColumnComponent} from "./page/development-column/development-column.component";
import {ReformColumnComponent} from "./page/reform-column/reform-column.component";
import {ReformColumnDetailComponent} from "./page/reform-column-detail/reform-column-detail.component";
import {StudyColumnComponent} from "./page/study-column/study-column.component";
import {StudyColumnDetailComponent} from "./page/study-column-detail/study-column-detail.component";
import {TeacherInfoDetailComponent} from "./page/teacher-info-detail/teacher-info-detail.component";
import {TeacherTeamDetailComponent} from "./page/teacher-team-detail/teacher-team-detail.component";
import {TeacherTeamComponent} from "./page/teacher-team/teacher-team.component";
import {UserComponent} from "./page/user/user.component";
import {UserDetailComponent} from "./page/user-detail/user-detail.component";

//项目的路由数组
//NOTE
//因为后台已经使用了Spring Security，前端是否仍有必要严格限定canActive？
//增加、删除、查找操作默认合并在XxxComponent里面
//修改操作未确定是否要在XxxDetailComponent里面
const routes: Routes = [
  {path: "index", redirectTo: ""},
  {path: "home", redirectTo: ""},
  {path: "register", component: RegisterComponent},
  {path: "login", component: LoginComponent},
  {path: "logout", component: LogoutComponent},

  {path: "account/:username", component: AccountComponent, canActivate: [LoginGuard]},

  {path: "development-column", component: DevelopmentColumnComponent},
  {path: "development-column/:id", component: DevelopmentColumnDetailComponent},

  {path: "dynamic", component: DynamicComponent},
  {path: "dynamic/:id", component: DynamicDetailComponent},

  {path: "introduce", component: IntroduceComponent},
  {path: "introduce/:id", component: IntroduceDetailComponent},

  {path: "reform-column", component: ReformColumnComponent},
  {path: "reform-column/:id", component: ReformColumnDetailComponent},

  {path: "study-column", component: StudyColumnComponent, canActivate: [LoginGuard]},
  {path: "study-column/:id", component: StudyColumnDetailComponent, canActivate: [LoginGuard]},

  {path: "teacher-team", component: TeacherTeamComponent},
  {path: "teacher-team/:id", component: TeacherTeamDetailComponent},

  {path: "teacher-info/:id", component: TeacherInfoDetailComponent},

  {path: "user", component: UserComponent},
  {path: "user/:id", component: UserDetailComponent}
];

/**
 * 项目的路由模块。
 */
@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
