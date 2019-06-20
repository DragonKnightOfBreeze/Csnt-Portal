/**
 * 对象错误的接口。
 * 另见：{@code org.springframework.validation.ObjectError}
 */
export interface ObjectError {
  objectName: string;
  code: string | null;
  codes: string[] | null;
  arguments: any[] | null;
  defaultMessage: string | null;
  class: any;
}
