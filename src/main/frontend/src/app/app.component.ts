import {Component, OnInit} from '@angular/core';

/**
 * 项目的主组件。
 */
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = '计算机科学与技术门户网站';


  constructor() {
  }


  ngOnInit(): void {
  }
}
