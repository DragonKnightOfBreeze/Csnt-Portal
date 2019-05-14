import {Component, OnInit} from '@angular/core';
import {Dynamic} from "../../domain/entity/Dynamic";
import {DynamicService} from "../../service/api/dynamic.service";
import {Page} from "../../domain/interface/Page";
import {DynamicCategory} from "../../enums/DynamicCategory";
import {DynamicSearchVo} from "../../domain/vo/DynamicSearchVo";

@Component({
  selector: 'app-dynamic',
  templateUrl: './dynamic.component.html',
  styleUrls: ['./dynamic.component.scss']
})
export class DynamicComponent implements OnInit {
  /** 当前数据的页面对象，注意数据数组存储在content属性中。 */
  dynamicPage: Page<Dynamic>;

  /**增加数据表单的模型对象。*/
  newDynamic = new Dynamic();

  /**是否通过后台表单参数验证。*/
  isValidForCreate = true;

  /**查询表单的模型对象。*/
  searchVo = new DynamicSearchVo();

  /**是否通过后台表单参数验证。*/
  isValidForSearch = true;


  constructor(private service: DynamicService) {
  }


  ngOnInit() {
    this.list();
  }

  /**
   * 增加数据，传入表单模型数据。
   * 可能抛出：400 参数错误，401 未登录
   */
  create() {
    this.service.create(this.newDynamic).subscribe(dynamic => {
      this.dynamicPage.content.push(dynamic);
      this.dynamicPage.content.slice(0, 10);
      this.isValidForCreate = true;
    }, () => this.isValidForCreate = false);
  }

  /**
   * 删除数据，传入数据id。如果操作成功，则弹出提示框。
   * 只有管理员、发起用户可调用。
   * 可能抛出：403 权限错误
   */
  delete(id: number) {
    window.alert("删除成功！");
    this.dynamicPage.content.filter(e => e.id !== id);
    this.service.delete(id).subscribe();
  }

  /**
   * 列出所有数据，在组件初始化时调用。
   */
  list(page = 1, size = 10) {
    this.service.list(page, size).subscribe(dynamicPage => {
      this.dynamicPage = dynamicPage;
    });
  }

  /**
   * 根据参数查询数据，调用后会刷新当前显示的数据。
   */
  searchBySubject(subject: string, page = 1, size = 10) {
    this.service.searchBySubject(subject, page, size).subscribe(dynamicPage => {
      this.dynamicPage = dynamicPage;
    });
  }

  /**
   * 根据参数查询数据，调用后会刷新当前显示的数据。
   */
  searchBySponsorUsername(sponsorUsername: string, page = 1, size = 10) {
    this.service.searchBySponsorUsername(sponsorUsername, page, size).subscribe(dynamicPage => {
      this.dynamicPage = dynamicPage;
    });
  }

  /**
   * 根据参数查询数据，调用后会刷新当前显示的数据。
   */
  searchByCategory(category: DynamicCategory[], page = 1, size = 10) {
    this.service.searchByCategory(category, page, size).subscribe(dynamicPage => {
      this.dynamicPage = dynamicPage;
    });
  }

  /**
   * 高级查询，传入表单模型数据，调用后会刷新当前显示的数据。
   * 可能抛出：400 参数错误
   */
  advanceSearch(page = 1, size = 10) {
    this.service.advanceSearch(this.searchVo, page, size).subscribe(dynamicPage => {
      this.dynamicPage = dynamicPage;
      this.isValidForSearch = true;
    }, () => this.isValidForSearch = false);
  }
}
