import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LogoutComponent} from "./page/logout/logout.component";
import {LoginGuard} from "./service/guard/login-guard.service";
import {RegisterComponent} from "./page/register/register.component";
import {LoginComponent} from "./page/login/login.component";
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
import {AdminGuard} from "./service/guard/admin-guard.service";
import {HomeComponent} from "./page/home/home.component";
import {Error403Component} from "./error-page/error403/error403.component";
import {Error500Component} from "./error-page/error500/error500.component";
import {Error404Component} from "./error-page/error404/error404.component";
import {Error501Component} from "./error-page/error501/error501.component";

//项目的路由数组
//NOTE 当路由地址并未发生改变时，angular并不会刷新页面
const routes: Routes = [
  {path: "", redirectTo: "home", pathMatch: "full"},
  {path: "home", component: HomeComponent},

  {path: "register", component: RegisterComponent},
  {path: "login", component: LoginComponent},
  {path: "logout", component: LogoutComponent},

  {path: ":item/list", redirectTo: ":item"},
  {path: ":item/page", redirectTo: ":item"},

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
  {path: "user", component: UserComponent, canActivate: [AdminGuard]},
  {path: "user/:id", component: UserDetailComponent, canActivate: [AdminGuard]},

  {path: "error", component: Error403Component},
  {path: "error/403", component: Error403Component},
  {path: "error/404", component: Error404Component},
  {path: "error/500", component: Error500Component},
  {path: "error/501", component: Error501Component},
  //当前面的路由都不匹配时，跳转到404
  {path: "**", component: Error404Component}
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
