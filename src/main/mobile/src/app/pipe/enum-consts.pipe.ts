import {Pipe, PipeTransform} from "@angular/core";

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
