import {Component, OnInit} from '@angular/core';
import {Page} from "../../domain/interface/Page";
import {ReformColumn} from "../../domain/entity/ReformColumn";
import {ReformColumnService} from "../../service/api/reform-column.service";
import {UserService} from "../../service/api/user.service";
import {JwtUserResponse} from "../../domain/entity/JwtUserResponse";
import {ActivatedRoute} from "@angular/router";
import {SearchParams} from "../../domain/vo/SearchParams";

@Component({
  selector: 'app-reform-column',
  templateUrl: './reform-column.component.html',
  styleUrls: ['./reform-column.component.scss']
})
export class ReformColumnComponent implements OnInit {
  currentUser: JwtUserResponse;

  /** 当前数据的页面对象，注意数据数组存储在content属性中。 */
  currentPage: Page<ReformColumn>;

  /**增加数据表单的模型对象。*/
  newColumn = new ReformColumn();

  /**是否通过后台表单参数验证。*/
  isValidForCreate = true;

  /**查询参数的封装对象。*/
  searchParams = new SearchParams<string>();


  constructor(private userService: UserService,
              private service: ReformColumnService,
              private route: ActivatedRoute) {
  }


  ngOnInit() {
    this.currentUser = this.userService.currentUserSubject.value;
    this.show();
  }

  /**
   * 增加数据，传入表单模型数据。
   * 可能抛出：400 参数错误
   */
  create() {
    this.service.create(this.newColumn).subscribe(column => {
      this.currentPage.content.push(column);
      this.currentPage.content.slice(0, 10);
      this.isValidForCreate = true;
    }, () => this.isValidForCreate = false);
  }

  /**
   * 删除数据，传入数据id。如果操作成功，则弹出提示框。
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
    this.searchParams.field = this.route.snapshot.queryParamMap.get("field") || "";
    this.searchParams.page = +this.route.snapshot.queryParamMap.get("page") || 1;
    this.searchParams.size = +this.route.snapshot.queryParamMap.get("size") || 10;

    if (this.searchParams.type == "ByTitle") {
      this.searchByTitle();
    } else {
      this.list();
    }
  }

  /**
   * 列出所有数据，在组件初始化时调用。
   */
  list() {
    this.searchParams.type = "All";
    this.service.list(this.searchParams.page, this.searchParams.size).subscribe(columnPage => {
      this.currentPage = columnPage;
    });
  }

  /**
   * 根据参数查询数据，调用后会刷新当前显示的数据。
   */
  searchByTitle() {
    this.searchParams.type = "ByTitle";
    const title = this.searchParams.field;
    this.service.searchByTitle(title, this.searchParams.page, this.searchParams.size).subscribe(columnPage => {
      this.currentPage = columnPage;
    });
  }
}
