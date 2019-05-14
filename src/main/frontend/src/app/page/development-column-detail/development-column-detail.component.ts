import {Component, OnInit} from '@angular/core';
import {DevelopmentColumn} from "../../domain/entity/DevelopmentColumn";
import {DevelopmentColumnService} from "../../service/api/development-column.service";

@Component({
  selector: 'app-development-column-detail',
  templateUrl: './development-column-detail.component.html',
  styleUrls: ['./development-column-detail.component.scss']
})
export class DevelopmentColumnDetailComponent implements OnInit {
  column: DevelopmentColumn;


  constructor(private service: DevelopmentColumnService) {
  }


  ngOnInit() {
    this.get();
  }

  /**
   * 得到当前数据。
   * 可能抛出：404 未找到
   */
  get() {
    //TODO
    let id = 1;
    this.service.get(id).subscribe(column => this.column = column);
  }

  /**
   * 删除数据，传入数据id。如果操作成功，则弹出提示框。
   */
  delete() {
    let id = this.column.id;
    this.service.delete(id).subscribe(() => window.alert("删除成功！"));
  }

  /**
   * 更新数据，传入表单模型。如果操作成功，则弹出提示框。
   * 只有管理员可以调用。
   * 可能抛出：400 参数错误，403 权限错误
   */
  update(updatedColumn: DevelopmentColumn) {
    this.service.update(updatedColumn).subscribe(() => window.alert("更新成功！"));
  }
}
