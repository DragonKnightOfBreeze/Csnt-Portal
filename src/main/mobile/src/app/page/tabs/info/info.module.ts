import {InfoPage} from "./info.page";
import {DevelopmentColumnListPage} from "./development-column/development-column-list/development-column-list.page";
import {DevelopmentColumnDetailPage} from "./development-column/development-column-detail/development-column-detail.page";
import {DynamicListPage} from "./dynamic/dynamic-list/dynamic-list.page";
import {DynamicDetailPage} from "./dynamic/dynamic-detail/dynamic-detail.page";
import {IntroduceListPage} from "./introduce/introduce-list/introduce-list.page";
import {IntroduceDetailPage} from "./introduce/introduce-detail/introduce-detail.page";
import {ReformColumnListPage} from "./reform-column/reform-column-list/reform-column-list.page";
import {ReformColumnDetailPage} from "./reform-column/reform-column-detail/reform-column-detail.page";
import {StudyColumnListPage} from "./study-column/study-column-list/study-column-list.page";
import {StudyColumnDetailPage} from "./study-column/study-column-detail/study-column-detail.page";
import {TeacherInfoDetailPage} from "./teacher-info/teacher-info-detail/teacher-info-detail.page";
import {TeacherTeamListPage} from "./teacher-team/teacher-team-list/teacher-team-list.page";
import {TeacherTeamDetailPage} from "./teacher-team/teacher-team-detail/teacher-team-detail.page";
import {LoginGuard} from "../../../service/guard/login-guard.service";
import {DynamicCategoryPopoverPage} from "./dynamic/dynamic-category-popover/dynamic-category-popover.page";
import {DynamicSearchModalPage} from "./dynamic/dynamic-search-modal/dynamic-search-modal.page";
import {DynamicCreateModalPage} from "./dynamic/dynamic-create-modal/dynamic-create-modal.page";
import {TeacherTeamSearchModalPage} from "./teacher-team/teacher-team-search-modal/teacher-team-search-modal.page";
import {TeacherTeamLevelPopoverPage} from "./teacher-team/teacher-team-level-popover/teacher-team-level-popover.page";
import {SharedModule} from "../../../shared.module";
import {IonicStorageModule} from "@ionic/storage";
import {RouterModule, Routes} from "@angular/router";
import {IonicModule} from "@ionic/angular";
import {CommonModule} from "@angular/common";
import {NgModule} from "@angular/core";
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";

const routes: Routes = [
  {
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
    //当都不匹配时重定向到404页面，不能直接引用组件
    path: "**",
    redirectTo: "/error/404"
  }
];

@NgModule({
  imports: [
    CommonModule,
    HttpClientModule,
    FormsModule,
    IonicModule.forRoot(),
    IonicStorageModule.forRoot(),
    RouterModule.forChild(routes),
    SharedModule
  ],
  declarations: [
    InfoPage,
    DevelopmentColumnListPage,
    DevelopmentColumnDetailPage,
    DynamicListPage,
    DynamicDetailPage,
    DynamicCategoryPopoverPage,
    DynamicSearchModalPage,
    DynamicCreateModalPage,
    IntroduceListPage,
    IntroduceDetailPage,
    ReformColumnListPage,
    ReformColumnDetailPage,
    StudyColumnListPage,
    StudyColumnDetailPage,
    TeacherInfoDetailPage,
    TeacherTeamListPage,
    TeacherTeamDetailPage,
    TeacherTeamLevelPopoverPage,
    TeacherTeamSearchModalPage
  ],
  bootstrap: [InfoPage],
  entryComponents: [
    DynamicCategoryPopoverPage,
    DynamicCreateModalPage,
    DynamicSearchModalPage,
    TeacherTeamLevelPopoverPage,
    TeacherTeamSearchModalPage]
})
export class InfoPageModule {
}
