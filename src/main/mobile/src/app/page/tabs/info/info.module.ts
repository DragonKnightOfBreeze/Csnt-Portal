import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {RouterModule, Routes} from "@angular/router";

import {IonicModule} from "@ionic/angular";

import {InfoPage} from "./info.page";
import {DevelopmentColumnListPage} from "./development-column-list/development-column-list.page";
import {DevelopmentColumnDetailPage} from "./development-column-detail/development-column-detail.page";
import {DynamicListPage} from "./dynamic-list/dynamic-list.page";
import {DynamicDetailPage} from "./dynamic-detail/dynamic-detail.page";
import {IntroduceListPage} from "./introduce-list/introduce-list.page";
import {IntroduceDetailPage} from "./introduce-detail/introduce-detail.page";
import {ReformColumnListPage} from "./reform-column-list/reform-column-list.page";
import {ReformColumnDetailPage} from "./reform-column-detail/reform-column-detail.page";
import {StudyColumnListPage} from "./study-column-list/study-column-list.page";
import {StudyColumnDetailPage} from "./study-column-detail/study-column-detail.page";
import {TeacherInfoDetailPage} from "./teacher-info-detail/teacher-info-detail.page";
import {TeacherTeamListPage} from "./teacher-team-list/teacher-team-list.page";
import {TeacherTeamDetailPage} from "./teacher-team-detail/teacher-team-detail.page";
import {Error404Page} from "../../error/error404/error404.page";
import {LoginGuard} from "../../../service/guard/login-guard.service";
import {InfoMenuPage} from "./info-menu/info-menu.page";

const routes: Routes = [{
  path: "",
  component: InfoPage
}, {
  path: "development-column",
  children: [{
    path: "",
    component: DevelopmentColumnListPage
  }, {
    path: ":id",
    component: DevelopmentColumnDetailPage
  }]
}, {
  path: "dynamic",
  children: [{
    path: "",
    component: DynamicListPage
  }, {
    path: ":id",
    component: DynamicDetailPage
  }]
}, {
  path: "introduce",
  children: [{
    path: "",
    component: IntroduceListPage
  }, {
    path: ":id",
    component: IntroduceDetailPage
  }]
}, {
  path: "reform-column",
  children: [{
    path: "",
    component: ReformColumnListPage
  }, {
    path: ":id",
    component: ReformColumnDetailPage
  }]
}, {
  path: "study-column",
  children: [{
    path: "",
    component: StudyColumnListPage
  }, {
    path: ":id",
    component: StudyColumnDetailPage
  }],
  canLoad: [LoginGuard]
}, {
  path: "teacher-info",
  children: [{
    path: ":id",
    component: TeacherInfoDetailPage
  }]
}, {
  path: "teacher-team",
  children: [{
    path: "",
    component: TeacherTeamListPage
  }, {
    path: "",
    component: TeacherTeamDetailPage
  }]
}, {
  //当都不匹配时重定向到404页面
  path: "**",
  component: Error404Page
}];

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    RouterModule.forChild(routes)
  ],
  declarations: [
    InfoPage,

    InfoMenuPage,

    DevelopmentColumnListPage,
    DevelopmentColumnDetailPage,
    DynamicListPage,
    DynamicDetailPage,
    IntroduceListPage,
    IntroduceDetailPage,
    ReformColumnListPage,
    ReformColumnDetailPage,
    StudyColumnListPage,
    StudyColumnDetailPage,
    TeacherInfoDetailPage,
    TeacherTeamListPage,
    TeacherTeamDetailPage
  ]
})
export class InfoPageModule {
}
