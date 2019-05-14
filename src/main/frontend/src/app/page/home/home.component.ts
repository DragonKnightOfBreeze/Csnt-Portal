import {Component, OnInit} from '@angular/core';

/**
 * 项目的首页组件。
 * NOTE 首页内容暂时还只是静态内容，以后可以考虑从后台传入数据
 */
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  constructor() {
  }


  ngOnInit() {
  }
}
