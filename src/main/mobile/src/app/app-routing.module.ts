import {NgModule} from "@angular/core";
import {PreloadAllModules, RouterModule, Routes} from "@angular/router";

/**
 * 项目的路由模块。
 * 存在多级路由定义在不同模块对应的路由模块里。
 * 有些路由存在激活或读取限制(canLoad,canActive)，有些路由带有数据(data)。
 */
const routes: Routes = [{
  path: "",
  redirectTo: "/tabs",
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
},];
{
  'dynamic-create-modal', loadChildren;
:
  './page/tabs/info/dynamic-create-modal/dynamic-create-modal.module#DynamicCreateModalPageModule'
}
,
{
  'dynamic-search-modal', loadChildren;
:
  './page/tabs/info/dynamic-search-modal/dynamic-search-modal.module#DynamicSearchModalPageModule'
}
,
{
  'dynamic-category-popover', loadChildren;
:
  './page/tabs/info/dynamic-category-popover/dynamic-category-popover.module#DynamicCategoryPopoverPageModule'
}
,
{
  'account-menu', loadChildren;
:
  './page/tabs/account/account-menu/account-menu.module#AccountMenuPageModule'
}
,
{
  'info-menu', loadChildren;
:
  './page/tabs/info/info-menu/info-menu.module#InfoMenuPageModule'
}

@NgModule({
  imports: [
    RouterModule.forRoot(routes, {preloadingStrategy: PreloadAllModules})
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
