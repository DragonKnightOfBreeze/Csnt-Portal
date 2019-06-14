/**
 * 限制文本长度，并在末尾添加省略号的管道。
 */
import {Pipe, PipeTransform} from "@angular/core";

@Pipe({
  name: "limitText"
})
export class LimitTextPipe implements PipeTransform {
  /**
   * 限制文本长度，并在末尾添加省略号。
   * @param value 指定的文本。
   * @param length 限制长度。默认为120.
   * @param omitText 省略文本。默认为省略号。
   */
  transform(value: string, length = 120, omitText = "..."): string {
    return value.slice(0, length) + omitText;
  }
}
