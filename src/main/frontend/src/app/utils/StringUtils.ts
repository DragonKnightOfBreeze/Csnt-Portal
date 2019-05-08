/**
 * 字符串的工具类。允许包含返回结果为字符串的工具方法。
 */
export class StringUtils {
  /**
   * 将指定的名字-值对数组格式化为Http Url参数字符串。
   */
  static toUrlParams(...urlParamPairs: UrlParamPair[]): string {
    let result = urlParamPairs.map(pair => {
      if (pair[1] instanceof Array) {
        return pair[1].map(s => `${pair[0]}=${s.toString()}`).reduce((a, b) => `${a}&${b}`);
      } else {
        return `${pair[0]}=${pair[1].toString()}`
      }
    }).reduce((a, b) => `${a}&${b}`);
    return result;
  }
}

/**
 * Http Url参数的名字-值对。
 */
interface UrlParamPair extends Array<any> {
  0: string;
  1: any;
}
