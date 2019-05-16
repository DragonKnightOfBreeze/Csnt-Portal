import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {StudyColumn} from "../../domain/entity/StudyColumn";
import {StudyColumnService} from "../../service/api/study-reform.service";

@Component({
  selector: 'app-study-column-detail',
  templateUrl: './study-column-detail.component.html',
  styleUrls: ['./study-column-detail.component.scss']
})
export class StudyColumnDetailComponent implements OnInit {
  /**当前数据对象。*/
  column: StudyColumn;

  /**是否通过后台表单参数验证。*/
  isValidForUpdate = true;


  constructor(private service: StudyColumnService,
              private route: ActivatedRoute,
              private location: Location) {
  }


  ngOnInit() {
    this.get();
  }

  /**
   * 得到当前数据。
   * 可能抛出：404 未找到
   */
  get() {
    //从路由地址中得到路由参数
    let id = +this.route.snapshot.paramMap.get("id");
    this.service.get(id).subscribe(column => this.column = column);
  }

  /**
   * 删除数据，传入数据id。如果操作成功，则弹出提示框。
   * 可能抛出：403 权限错误
   */
  delete() {
    window.alert("删除成功！");
    this.goBack();
    this.service.delete(this.column.id).subscribe();
  }

  /**
   * 更新数据，传入表单模型。
   * 只有管理员可以调用。
   * 可能抛出：400 参数错误，403 权限错误
   */
  update() {
    this.service.update(this.column).subscribe(updatedColumn => {
      this.column = updatedColumn;
      this.isValidForUpdate = true;
    },()=>this.isValidForUpdate = false);
  }

  /**
   * 返回到上一页。
   */
  goBack() {
    this.location.back();
  }
}
