import {Component, OnInit} from '@angular/core';
import {DynamicService} from "./service/api/dynamic.service";

/**
 * 项目的主组件。
 */
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'Csnt Portal';
  testText: string;

  constructor(private dynamicService: DynamicService) {
  }

  ngOnInit(): void {
    this.dynamicService.get(1).subscribe(dynamic => this.testText = dynamic.subject);
  }
}
