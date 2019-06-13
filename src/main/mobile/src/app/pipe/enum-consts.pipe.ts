import {Pipe, PipeTransform} from "@angular/core";

//非字符串枚举可以正向映射，也可以反向映射
//得到所有字符串枚举成员的键：Object.keys(EnumRef)
//得到所有字符串枚举成员的值：Object.values(EnumRef)

/**
 * 得到枚举的枚举成员列表的管道。
 */
@Pipe({
  name: "enumConsts"
})
export class EnumConstsPipe implements PipeTransform {
  /**
   * 得到枚举的枚举成员列表。
   * @param value 枚举。
   * @return 枚举成员列表。
   */
  transform(value: any): any[] {
    return Object.values(value);
  }
}
