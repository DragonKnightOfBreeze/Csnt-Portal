import {Component, OnInit} from '@angular/core';
import {DevelopmentColumn} from "../../domain/entity/DevelopmentColumn";
import {DevelopmentColumnService} from "../../service/api/development-column.service";
import {Page} from "../../domain/interface/Page";

@Component({
  selector: 'app-development-column',
  templateUrl: './development-column.component.html',
  styleUrls: ['./development-column.component.scss']
})
export class DevelopmentColumnComponent implements OnInit {
  /** 当前数据的页面对象，注意数据数组存储在content属性中。 */
  page: Page<DevelopmentColumn>;


  constructor(private service: DevelopmentColumnService) {
  }


  ngOnInit() {
    this.list();
  }

  /**
   * 增加数据，传入表单模型。如果操作成功，则弹出提示框。
   * 可能抛出：400 参数错误
   */
  create(column: DevelopmentColumn) {
    this.service.create(column).subscribe(() => window.alert("添加成功！"));
  }

  /**
   * 删除数据，传入数据id。如果操作成功，则弹出提示框。
   */
  delete(id: number) {
    this.service.delete(id).subscribe(() => window.alert("删除成功！"));
  }

  /**
   * 列出所有数据，在组件初始化时调用。
   * 可能抛出：204 没有内容
   */
  list() {
    this.service.list().subscribe(page => this.page = page);
  }

  /**
   * 根据参数查询数据，调用后会刷新当前显示的数据。
   * 可能抛出：204 没有内容
   */
  searchByTitle(title: string) {
    this.service.searchByTitle(title).subscribe(page => this.page = page);
  }
}
