import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./component/login/login.component";
import {LogoutComponent} from "./component/logout/logout.component";
import {RegisterComponent} from "./component/register/register.component";
import {DevelopmentColumnListComponent} from "./page/development-column/development-column-list/development-column-list.component";
import {DynamicListComponent} from "./page/dynamic/dynamic-list/dynamic-list.component";
import {IntroduceListComponent} from "./page/introduce/introduce-list/introduce-list.component";
import {ReformColumnListComponent} from "./page/reform-column/reform-column-list/reform-column-list.component";
import {StudyColumnListComponent} from "./page/study-column/study-column-list/study-column-list.component";
import {TeacherTeamListComponent} from "./page/teacher-team/teacher-team-list/teacher-team-list.component";
import {DevelopmentColumnDetailComponent} from "./page/development-column/development-column-detail/development-column-detail.component";
import {DevelopmentColumnEditComponent} from "./page/development-column/development-column-edit/development-column-edit.component";
import {LoginGuard} from "./service/guard/login-guard.service";
import {AccountDetailComponent} from "./page/account/account-detail/account-detail.component";
import {AdminGuard} from "./service/guard/admin-guard.service";
import {DynamicDetailComponent} from "./page/dynamic/dynamic-detail/dynamic-detail.component";
import {IntroduceDetailComponent} from "./page/introduce/introduce-detail/introduce-detail.component";
import {IntroduceEditComponent} from "./page/introduce/introduce-edit/introduce-edit.component";
import {ReformColumnEditComponent} from "./page/reform-column/reform-column-edit/reform-column-edit.component";
import {StudyColumnEditComponent} from "./page/study-column/study-column-edit/study-column-edit.component";
import {TeacherTeamEditComponent} from "./page/teacher-team/teacher-team-edit/teacher-team-edit.component";
import {TeacherInfoDetailComponent} from "./page/teacher-info/teacher-info-detail/teacher-info-detail.component";
import {TeacherInfoEditComponent} from "./page/teacher-info/teacher-info-edit/teacher-info-edit.component";

//项目的路由数组
//NOTE 因为后台已经使用了Spring Security，前端是否仍有必要严格限定canActive？
//NOTE 增加、删除、查找操作默认合并在XxxListComponent里面
const routes: Routes = [
  {path: "index", redirectTo: ""},
  {path: "home", redirectTo: ""},
  {path: "register", component: RegisterComponent},
  {path: "login", component: LoginComponent},
  {path: "logout", component: LogoutComponent},

  {path: "account/:username", component: AccountDetailComponent, canActivate: [LoginGuard]},

  {path: "development-column", redirectTo: "development-column/list"},
  {path: "development-column/list", component: DevelopmentColumnListComponent},
  {path: "development-column/:id", component: DevelopmentColumnDetailComponent},
  {path: "development-column/:id/edit", component: DevelopmentColumnEditComponent, canActivate: [AdminGuard]},

  {path: "dynamic", redirectTo: "dynamic/list"},
  {path: "dynamic/list", component: DynamicListComponent},
  {path: "dynamic/:id", component: DynamicDetailComponent},

  {path: "introduce", redirectTo: "introduce/list"},
  {path: "introduce/list", component: IntroduceListComponent},
  {path: "introduce/:id", component: IntroduceDetailComponent},
  {path: "introduce/:id/edit", component: IntroduceEditComponent, canActivate: [AdminGuard]},

  {path: "reform-column", redirectTo: "reform-column/list"},
  {path: "reform-column/list", component: ReformColumnListComponent},
  {path: "reform-column/:id", component: ReformColumnListComponent},
  {path: "reform-column/:id/edit", component: ReformColumnEditComponent, canActivate: [AdminGuard]},

  {path: "study-column", redirectTo: "study-column/list"},
  {path: "study-column/list", component: StudyColumnListComponent, canActivate: [LoginGuard]},
  {path: "study-column/:id", component: StudyColumnListComponent, canActivate: [LoginGuard]},
  {path: "study-column/:id/edit", component: StudyColumnEditComponent, canActivate: [AdminGuard]},

  {path: "teacher-team", redirectTo: "teacher-team/list"},
  {path: "teacher-team/list", component: TeacherTeamListComponent},
  {path: "teacher-team/:id", component: TeacherTeamListComponent},
  {path: "teacher-team/:id/edit", component: TeacherTeamEditComponent, canActivate: [AdminGuard]},

  {path: "teacher-info/:id", component: TeacherInfoDetailComponent},
  {path: "teacher-info/:id/edit", component: TeacherInfoEditComponent, canActivate: [AdminGuard]}
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
