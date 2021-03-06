import {Pipe, PipeTransform} from "@angular/core";
import {DatePipe} from "@angular/common";

/**
 * 得到本地化的日期的管道。
 */
@Pipe({
  name: "localeDate"
})
export class LocaleDatePipe implements PipeTransform {
  constructor() {
  }

  /**
   * 得到本地化的日期。
   * @param value 代表日期的对象或字符串。
   */
  transform(value: any): String {
    return new DatePipe("en-US").transform(value, "yyyy-MM-dd hh:mm:ss");
  }
}
