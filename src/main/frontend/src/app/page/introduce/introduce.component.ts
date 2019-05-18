import {Component, OnInit} from '@angular/core';
import {Introduce} from "../../domain/entity/Introduce";
import {IntroduceService} from "../../service/api/introduce.service";
import {JwtUserResponse} from "../../domain/entity/JwtUserResponse";
import {UserService} from "../../service/api/user.service";

@Component({
  selector: 'app-introduce',
  templateUrl: './introduce.component.html',
  styleUrls: ['./introduce.component.scss']
})
export class IntroduceComponent implements OnInit {
  currentUser: JwtUserResponse;

  /** 当前数据的列表对象。*/
  introduceList: Introduce[];

  /**增加数据表单的模型对象。*/
  newIntroduce = new Introduce();

  /**是否通过后台表单参数验证。*/
  isValidForCreate = true;


  constructor(private userService: UserService,
              private service: IntroduceService) {
  }


  ngOnInit() {
    this.currentUser = this.userService.currentUserSubject.value;
    this.list();
  }

  /**
   * 增加数据，传入表单模型数据。
   * 可能抛出：400 参数错误
   */
  create() {
    this.service.create(this.newIntroduce).subscribe(introduce => {
      this.introduceList.push(introduce);
      this.isValidForCreate = true;
    }, () => this.isValidForCreate = false);
  }

  /**
   * 删除数据，传入数据id。如果操作成功，则弹出提示框。
   * 可能抛出：403 权限错误
   */
  delete(id: number) {
    window.alert("删除成功！");
    this.introduceList.filter(e => e.id !== id);
    this.service.delete(id).subscribe();
  }

  /**
   * 列出所有数据，在组件初始化时调用。
   */
  list() {
    this.service.list().subscribe(introduceList => {
      this.introduceList = introduceList;
    });
  }
}
