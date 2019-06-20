/**
 * java异常对象。
 * 另见：{@code java.lang.Exception}
 */
export interface Exception {
  message: string;
  localizedMessage: string;
  cause: any;
  stackTrace: any;
  suppressed: any;
  class: any;

  [index: string]: any;
}
