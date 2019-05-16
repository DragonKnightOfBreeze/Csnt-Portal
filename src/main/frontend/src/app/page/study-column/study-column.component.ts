import {Component, OnInit} from '@angular/core';
import {Page} from "../../domain/interface/Page";
import {StudyColumn} from "../../domain/entity/StudyColumn";
import {StudyColumnService} from "../../service/api/study-reform.service";

@Component({
  selector: 'app-study-column',
  templateUrl: './study-column.component.html',
  styleUrls: ['./study-column.component.scss']
})
export class StudyColumnComponent implements OnInit {
  /** 当前数据的页面对象，注意数据数组存储在content属性中。 */
  columnPage: Page<StudyColumn>;

  /**增加数据表单的模型对象。*/
  newColumn = new StudyColumn();

  /**是否通过后台表单参数验证。*/
  isValidForCreate = true;


  constructor(private service: StudyColumnService) {
  }


  ngOnInit() {
    this.list();
  }

  /**
   * 增加数据，传入表单模型数据。
   * 可能抛出：400 参数错误
   */
  create() {
    this.service.create(this.newColumn).subscribe(column => {
      this.columnPage.content.push(column);
      this.columnPage.content.slice(0, 10);
      this.isValidForCreate = true;
    },()=>this.isValidForCreate = false);
  }

  /**
   * 删除数据，传入数据id。如果操作成功，则弹出提示框。
   * 可能抛出：403 权限错误
   */
  delete(id: number) {
    window.alert("删除成功！");
    this.columnPage.content.filter(e => e.id !== id);
    this.service.delete(id).subscribe();
  }

  /**
   * 列出所有数据，在组件初始化时调用。
   */
  list(page = 1, size = 10) {
    this.service.list(page, size).subscribe(columnPage => {
      this.columnPage = columnPage;
    });
  }

  /**
   * 根据参数查询数据，调用后会刷新当前显示的数据。
   */
  searchByTitle(title: string, page = 1, size = 10) {
    this.service.searchByTitle(title, page, size).subscribe(columnPage => {
      this.columnPage = columnPage;
    });
  }
}
