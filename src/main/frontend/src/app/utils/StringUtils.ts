/**
 * 字符串的工具类。
 */
export class StringUtils {
  /**
   * 根据指定的子路径数组，得到格式化、标准化后的完整路径。
   */
  static joinPath(...paths: string[]): string {
    let result = paths.map(s => s.replace("/", "\\")).reduce((a, b) => `${a}\\${b}`);
    return result;
  }

  /**
   * 根据指定的名字-值对象，得到格式化后的Http Get参数字符串。
   */
  static joinUrlParam(params: object): string {
    let resultList = [];
    for (let name of Object.keys(params)) {
      if (params[name] instanceof Array) {
        const str = params[name].map(s => `${name}=${s.toString()}`).reduce((a, b) => `${a}&${b}`);
        resultList.push(str);
      } else {
        const str = `${name}=${params[name].toString()}`;
        resultList.push(str);
      }
    }
    return resultList.reduce((a, b) => `${a}&${b}`);
  }

  /**
   * 根据指定的名字和值数组，得到格式化后的Http Get参数字符串。
   * @param name
   * @param value
   */
  static joinMultiUrlParam(name: string, value: any[]) {
    let result = value.map(s => `${name}=${s.toString()}`).reduce((a, b) => `${a}&${b}`);
    return result;
  }
}
