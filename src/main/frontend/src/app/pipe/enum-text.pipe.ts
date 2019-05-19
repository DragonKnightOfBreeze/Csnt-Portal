import {Pipe, PipeTransform} from '@angular/core';
import {enumTexts} from "../../environments/environment.prod";

//非字符串枚举可以正向映射，也可以反向映射
//得到所有字符串枚举成员的键：Object.keys(EnumRef)
//得到所有字符串枚举成员的值：Object.values(EnumRef)

/**
 * 得到枚举值对应的格式化文本的管道。
 */
@Pipe({
  name: 'enumText'
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
