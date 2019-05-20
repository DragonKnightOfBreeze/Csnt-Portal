import {Component, OnInit} from '@angular/core';
import {Dynamic} from "../../domain/entity/Dynamic";
import {DynamicService} from "../../service/api/dynamic.service";
import {Page} from "../../domain/interface/Page";
import {UserService} from "../../service/api/user.service";
import {JwtUserResponse} from "../../domain/entity/JwtUserResponse";
import {ActivatedRoute} from "@angular/router";
import {SearchParams} from "../../domain/vo/SearchParams";
import {DynamicSearchVo} from "../../domain/vo/DynamicSearchVo";
import {DynamicCategory} from "../../enums/DynamicCategory";

@Component({
  selector: 'app-dynamic',
  templateUrl: './dynamic.component.html',
  styleUrls: ['./dynamic.component.scss']
})
export class DynamicComponent implements OnInit {
  currentUser: JwtUserResponse;

  /** 当前数据的页面对象，注意数据数组存储在content属性中。 */
  currentPage: Page<Dynamic>;

  /**增加数据表单的模型对象。*/
  newDynamic = new Dynamic();

  /**是否通过后台表单参数验证。*/
  isValidForCreate = true;

  /**查询参数的封装对象。*/
  searchParams = new SearchParams<DynamicSearchVo>();

  /**查询表单是否通过后台表单参数验证。*/
  isValidForSearch = true;

  DynamicCategory = DynamicCategory;


  constructor(private userService: UserService,
              private service: DynamicService,
              private route: ActivatedRoute) {
  }


  ngOnInit() {
    this.currentUser = this.userService.currentUserSubject.value;
    this.show();
  }

  /**
   * 增加数据，传入表单模型数据。
   * 可能抛出：400 参数错误，401 未登录
   */
  create() {
    this.service.create(this.newDynamic).subscribe(dynamic => {
      this.currentPage.content.push(dynamic);
      this.currentPage.content.slice(0, 10);
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
    this.currentPage.content.filter(e => e.id !== id);
    this.service.delete(id).subscribe();
  }

  /**
   * 根据不同的查询类型和可能的分页参数，列出数据。
   */
  private show() {
    this.searchParams.type = this.route.snapshot.queryParamMap.get("type") || "All";
    this.searchParams.field = JSON.parse(this.route.snapshot.queryParamMap.get("field")) || new DynamicSearchVo();
    this.searchParams.page = +this.route.snapshot.queryParamMap.get("page") || 1;
    this.searchParams.size = +this.route.snapshot.queryParamMap.get("size") || 10;

    if (this.searchParams.type === "BySubject") {
      this.searchBySubject();
    } else if (this.searchParams.type === "ByCategory") {
      this.searchByCategory(this.searchParams.field.categorySet[0]);
    } else if (this.searchParams.type === "BySponsorUsername") {
      this.searchBySponsorUsername();
    } else if (this.searchParams.type === "Advance") {
      this.advanceSearch();
    } else {
      this.list();
    }
  }

  /**
   * 列出所有数据，在组件初始化时调用。
   */
  list() {
    this.searchParams.type = "All";
    this.service.list(this.searchParams.page, this.searchParams.size).subscribe(dynamicPage => {
      this.currentPage = dynamicPage;
    });
  }

  /**
   * 根据参数查询数据，调用后会刷新当前显示的数据。
   */
  searchBySubject() {
    this.searchParams.type = "BySubject";
    const subject = this.searchParams.field.subject;
    this.service.searchBySubject(subject, this.searchParams.page, this.searchParams.size).subscribe(dynamicPage => {
      this.currentPage = dynamicPage;
    });
  }

  /**
   * 根据参数查询数据，调用后会刷新当前显示的数据。
   */
  searchBySponsorUsername() {
    this.searchParams.type = "BySponsorUsername";
    const sponsorUsername = this.searchParams.field.sponsorUsername;
    this.service.searchBySponsorUsername(sponsorUsername, this.searchParams.page, this.searchParams.size).subscribe(dynamicPage => {
      this.currentPage = dynamicPage;
    });
  }

  /**
   * 根据参数查询数据，调用后会刷新当前显示的数据。
   */
  searchByCategory(category: DynamicCategory) {
    this.searchParams.type = "ByCategory";
    this.searchParams.field.categorySet = [category];
    this.service.searchByCategory([category], this.searchParams.page, this.searchParams.size).subscribe(dynamicPage => {
      this.currentPage = dynamicPage;
    });
  }

  /**
   * 高级查询，传入表单模型数据，调用后会刷新当前显示的数据。
   * 可能抛出：400 参数错误
   */
  advanceSearch() {
    this.searchParams.type = "Advance";
    const searchVo = this.searchParams.field;
    this.service.advanceSearch(searchVo, this.searchParams.page, this.searchParams.size).subscribe(dynamicPage => {
      this.currentPage = dynamicPage;
      this.isValidForSearch = true;
    }, () => this.isValidForSearch = false);
  }
}
