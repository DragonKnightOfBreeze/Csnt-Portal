import {Component, Input, OnInit} from "@angular/core";
import {Page} from "../../../domain/interface/Page";

/**
 * 项目的分页组件。
 */
@Component({
  selector: "app-pagination",
  templateUrl: "./pagination.component.html",
  styleUrls: ["./pagination.component.scss"]
})
export class PaginationComponent implements OnInit {
  /**查询类型。*/
  @Input() type: string;

  /**查询域。*/
  @Input() field: any;

  /** 当前页。可从中获得当前页数据、当前页数、总页数等。需要传入。 */
  @Input() currentPage: Page<any>;

  JSON = JSON;

  constructor() {
  }


  ngOnInit(): void {
  }

  /**
   * 得到指定范围内的连续整数数组（左闭右开）。
   */
  range(min = 0, max: number) {
    let result = [];
    for (let i = min; i < max; i++) {
      result.push(i);
    }
    return result;
  }
}
