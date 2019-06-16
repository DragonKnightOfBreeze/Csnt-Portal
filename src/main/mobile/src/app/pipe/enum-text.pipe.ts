import {Pipe, PipeTransform} from "@angular/core";
import {enumTexts} from "../../environments/environment.prod";

/**
 * 得到枚举值对应的格式化文本的管道。
 */
@Pipe({
  name: "enumText"
})
export class EnumTextPipe implements PipeTransform {
  /**
   * 得到枚举值对应的格式化文本。
   * 从全局常量enumTexts中读取。
   * @param value 枚举值/枚举值对应的字符串。
   * @param enumName 枚举名。
   * @return 对应的格式化文本。
   */
  transform(value: any, enumName: string): string {
    return enumTexts[enumName][value.toString()];
  }
}
